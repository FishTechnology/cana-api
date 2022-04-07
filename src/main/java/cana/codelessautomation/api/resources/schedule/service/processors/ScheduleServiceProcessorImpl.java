package cana.codelessautomation.api.resources.schedule.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.notification.service.mappers.NotificationMapper;
import cana.codelessautomation.api.resources.notification.service.repositories.NotificationRepository;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.ActionOptionResultRepository;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.ActionResultRepository;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.TestCaseResultRepository;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.TestPlanResultRepository;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.errorcodes.ScheduleServiceErrorCode;
import cana.codelessautomation.api.resources.schedule.service.processors.mappers.ResultMapper;
import cana.codelessautomation.api.resources.schedule.service.processors.mappers.ScheduleServiceProcessorMapper;
import cana.codelessautomation.api.resources.schedule.service.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.resources.schedule.service.repositories.ScheduleRepository;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleSummaryEntity;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.entities.TestPlanSummaryDaoEntity;
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
    ActionOptionResultRepository actionOptionResultRepository;

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
    public ScheduleSummaryEntity processGetScheduleDetail(Long scheduleId) {
        return ScheduleSummaryEntity.findByIdAndStatus(scheduleId);
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
    public List<ErrorMessageDto> processGetScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto) {
        return getScheduleIterations(getScheduleIterationsDto);
    }

    @Override
    public List<ErrorMessageDto> processUpdateScheduleSession(UpdateScheduleSessionDto updateScheduleSessionDto) {
        return updateScheduleSession(updateScheduleSessionDto);
    }

    @Override
    public List<ErrorMessageDto> updateScheduleSession(UpdateScheduleSessionDto updateScheduleSessionDto) {
        var iteration = updateScheduleSessionDto
                .getSchedule()
                .getScheduleIterations()
                .stream()
                .filter(iter -> Objects.equals(iter.getId(), updateScheduleSessionDto.getIterationId()))
                .findFirst();
        var scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationSession(updateScheduleSessionDto, iteration.get());
        scheduleIterationRepository.persist(scheduleIterationDao);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto) {
        var scheduleIterationDaos = scheduleIterationRepository.findByScheduleId(getScheduleIterationsDto.getScheduleId());
        if (CollectionUtils.isNotEmpty(scheduleIterationDaos)) {
            getScheduleIterationsDto.setScheduleIterations(scheduleIterationDaos);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> updateScheduleIteration(ReScheduleStatusDto reScheduleStatusDto) {
        var scheduleIterationDao = scheduleIterationRepository.findLatestIteration(reScheduleStatusDto.getScheduleId());
        reScheduleStatusDto.setScheduleIteration(scheduleIterationDao);
        if (scheduleIterationDao.getStatus() == ScheduleStatusDao.QUEUE ||
                scheduleIterationDao.getStatus() == ScheduleStatusDao.READY ||
                scheduleIterationDao.getStatus() == ScheduleStatusDao.INPROGRESS ||
                scheduleIterationDao.getStatus() == ScheduleStatusDao.PAUSE) {
            scheduleIterationDao = scheduleServiceProcessorMapper.mapScheduleIterationDao(reScheduleStatusDto, scheduleIterationDao);
            scheduleIterationRepository.persist(scheduleIterationDao);
        }

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

    @Override
    public List<ErrorMessageDto> createActionOptionsResult(ActionDaoEntity actionDaoEntity, ActionResultDao actionResultDao) {
        if (CollectionUtils.isEmpty(actionDaoEntity.getActionOptionDaos())) {
            return Collections.emptyList();
        }
        for (ActionOptionDao actionOptionDao : actionDaoEntity.getActionOptionDaos()) {
            var actionOptionResultDao = resultMapper.mapActionOptionResultDao(actionOptionDao, actionResultDao);
            actionOptionResultRepository.persist(actionOptionResultDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createActionResult(CopyTestPlanDetailDto copyTestPlanDetailDto,
                                                    TestCaseResultDao testCaseResultDao,
                                                    List<ActionDaoEntity> actionDaoEntities) {
        for (ActionDaoEntity actionDaoEntity : actionDaoEntities) {
            var actionResultDao = resultMapper.mapActionResultDao(copyTestPlanDetailDto, testCaseResultDao, actionDaoEntity);
            actionResultRepository.persist(actionResultDao);
            var errors = createActionOptionsResult(actionDaoEntity, actionResultDao);
            if (CollectionUtils.isNotEmpty(errors)) {
                return errors;
            }
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
        var panacheQuery = scheduleRepository.findByAppId(scheduleSummaryDto.getApplicationId());
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
