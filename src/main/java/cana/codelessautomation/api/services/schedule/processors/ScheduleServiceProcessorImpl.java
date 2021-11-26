package cana.codelessautomation.api.services.schedule.processors;

import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.notification.processors.mappers.NotificationMapper;
import cana.codelessautomation.api.services.notification.repositories.NotificationRepository;
import cana.codelessautomation.api.services.results.action.repositories.ActionResultRepository;
import cana.codelessautomation.api.services.results.testcase.repositories.TestCaseResultRepository;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.results.testplan.repositories.TestPlanResultRepository;
import cana.codelessautomation.api.services.schedule.dtos.*;
import cana.codelessautomation.api.services.schedule.errorcodes.ScheduleServiceErrorCode;
import cana.codelessautomation.api.services.schedule.processors.mappers.ResultMapper;
import cana.codelessautomation.api.services.schedule.processors.mappers.ScheduleServiceProcessorMapper;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleRepository;
import cana.codelessautomation.api.services.schedule.repositories.daos.entities.ScheduleDetailEntity;
import cana.codelessautomation.api.services.testplan.repositories.daos.entities.TestPlanSummaryDaoEntity;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ScheduleServiceProcessorImpl implements ScheduleServiceProcessor {

    @Inject
    ScheduleServiceProcessorMapper scheduleServiceProcessorMapper;

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    ScheduleIterationRepository scheduleIterationRepository;

    @Inject
    ScheduleServiceErrorCode scheduleServiceErrorCode;

    @Inject
    TestPlanResultRepository testPlanResultRepository;

    @Inject
    TestCaseResultRepository testCaseResultRepository;

    @Inject
    ActionResultRepository actionResultRepository;

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    ResultMapper resultMapper;

    @Inject
    NotificationMapper notificationMapper;

    @Override
    public List<ErrorMessageDto> processCreateSchedule(CreateScheduleDto createScheduleDto) {
        var errors = createSchedule(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = createScheduleIteration(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createNotification(createScheduleDto);
    }

    @Override
    public List<ErrorMessageDto> createNotification(CreateScheduleDto createScheduleDto) {
        if (Objects.isNull(createScheduleDto.getNotification())) {
            return Collections.emptyList();
        }
        var notificationDao = notificationMapper.mapNotificationDao(createScheduleDto);
        notificationRepository.persist(notificationDao);
        return Collections.emptyList();
    }


    @Override
    public List<ErrorMessageDto> processGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        return getScheduleSummary(scheduleSummaryDto);
    }

    @Override
    public List<ErrorMessageDto> processCopyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        var errors = getScheduleIteration(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = getTestPlanDetails(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = createDraftTestPlanResult(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processGetScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto) {
        return getTestPlanResultDetails(scheduleIterationResultDto);
    }

    @Override
    public ScheduleDetailEntity processGetScheduleDetail(Long scheduleId) {
        return ScheduleDetailEntity.findByIdAndStatus(scheduleId);
    }

    @Override
    public List<ErrorMessageDto> processUpdateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var errors = updateScheduleStatus(updateScheduleStatusReadyDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return updateScheduleIteration(updateScheduleStatusReadyDto);
    }

    @Override
    public List<ErrorMessageDto> processReSchedule(ReScheduleStatusDto reScheduleStatusDto) {
        var errors = updateScheduleStatus(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = updateScheduleIteration(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createScheduleIteration(reScheduleStatusDto);
    }

    @Override
    public List<ErrorMessageDto> updateScheduleIteration(ReScheduleStatusDto reScheduleStatusDto) {
        var scheduleIterationDao = scheduleIterationRepository.findLatestIteration(reScheduleStatusDto.getScheduleId());
        reScheduleStatusDto.setScheduleIteration(scheduleIterationDao);
        scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationDao(reScheduleStatusDto, scheduleIterationDao);
        scheduleIterationRepository.persist(scheduleIterationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> updateScheduleStatus(ReScheduleStatusDto reScheduleStatusDto) {
        var scheduleDao = scheduleServiceProcessorMapper.mapScheduleDao(reScheduleStatusDto);
        scheduleRepository.persist(scheduleDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createScheduleIteration(ReScheduleStatusDto reScheduleStatusDto) {
        var scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationDao(reScheduleStatusDto);
        scheduleIterationRepository.persist(scheduleIterationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> updateScheduleIteration(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationDao(updateScheduleStatusReadyDto);
        scheduleIterationRepository.persist(scheduleIterationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> updateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var scheduleDao = scheduleServiceProcessorMapper.mapScheduleDao(updateScheduleStatusReadyDto);
        scheduleRepository.persist(scheduleDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getTestPlanResultDetails(ScheduleIterationResultDto scheduleIterationResultDto) {
        var testPlanResultDao = testPlanResultRepository.findByScheduleIterationId(scheduleIterationResultDto.getScheduleIterationId());
        scheduleIterationResultDto.setTestPlanResultDao(testPlanResultDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createDraftTestPlanResult(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        var errors = createTestPlanResult(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = createTestCaseResult(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return Collections.emptyList();
    }

    public List<ErrorMessageDto> createActionOptions(CopyTestPlanDetailDto copyTestPlanDetailDto, ActionDaoEntity actionDaoEntity) {
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createActionResult(CopyTestPlanDetailDto copyTestPlanDetailDto,
                                                    TestCaseResultDao testCaseResultDao,
                                                    List<ActionDaoEntity> actionDaoEntities) {
        for (ActionDaoEntity actionDaoEntity : actionDaoEntities) {
            var actionResultDao = resultMapper.mapActionResultDao(copyTestPlanDetailDto, testCaseResultDao, actionDaoEntity);
            actionResultRepository.persist(actionResultDao);
            var id = actionResultDao.getId();
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createTestCaseResult(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        for (var testplanTestcaseGroupings : copyTestPlanDetailDto.getTestPlanSummary().getTestplanTestcaseGroupings()) {
            var testCaseResultDao = resultMapper.mapTestCaseResultDao(copyTestPlanDetailDto, testplanTestcaseGroupings);
            testCaseResultRepository.persist(testCaseResultDao);
            var errors = createActionResult(
                    copyTestPlanDetailDto,
                    testCaseResultDao,
                    testplanTestcaseGroupings.getTestCase().getActionDaoEntities());
            if (CollectionUtils.isNotEmpty(errors)) {
                return errors;
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createTestPlanResult(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        var testPlanResultDao = resultMapper.mapTestPlanResultDao(copyTestPlanDetailDto);
        testPlanResultRepository.persist(testPlanResultDao);
        copyTestPlanDetailDto.setTestPlanResultId(testPlanResultDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getTestPlanDetails(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        TestPlanSummaryDaoEntity testPlanSummaryDaoEntity = TestPlanSummaryDaoEntity.findById(copyTestPlanDetailDto.getSchedule().getTestPlanId());
        copyTestPlanDetailDto.setTestPlanSummary(testPlanSummaryDaoEntity);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getScheduleIteration(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        var scheduleIteration = scheduleIterationRepository.findLatestIteration(copyTestPlanDetailDto.getScheduleId());
        if (scheduleIteration == null) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleIterationNotFound());
        }
        copyTestPlanDetailDto.setScheduleIteration(scheduleIteration);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        var panacheQuery = scheduleRepository.findByUserIdPage(scheduleSummaryDto.getUserId());
        var scheduleDaos = panacheQuery.page(scheduleSummaryDto.getPageNumber(), scheduleSummaryDto.getPageSize()).list();
        scheduleSummaryDto.setTotalPageCount(panacheQuery.pageCount());
        scheduleSummaryDto.setScheduleDaos(scheduleDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createScheduleIteration(CreateScheduleDto createScheduleDto) {
        var scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationDao(createScheduleDto);
        scheduleIterationRepository.persist(scheduleIterationDao);
        createScheduleDto.setIterationId(scheduleIterationDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto) {
        var scheduleDao = scheduleServiceProcessorMapper.mapScheduleDao(createScheduleDto);
        scheduleRepository.persist(scheduleDao);
        createScheduleDto.setId(scheduleDao.getId());
        return Collections.emptyList();
    }
}
