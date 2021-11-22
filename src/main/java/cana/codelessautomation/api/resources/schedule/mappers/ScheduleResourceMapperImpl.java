package cana.codelessautomation.api.resources.schedule.mappers;

import cana.codelessautomation.api.resources.commonmodels.BrowserType;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultSummaryModel;
import cana.codelessautomation.api.resources.result.testplanresult.models.TestPlanResultSummaryModel;
import cana.codelessautomation.api.resources.schedule.models.*;
import cana.codelessautomation.api.resources.testplan.mappers.TestplanResourceMapper;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.services.schedule.dtos.*;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.entities.ScheduleDetailEntity;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ScheduleResourceMapperImpl implements ScheduleResourceMapper {

    @Inject
    TestplanResourceMapper testplanResourceMapper;

    @Override
    public ResultModel mapResultModel(CreateScheduleDto createScheduleDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createScheduleDto.getId());
        return resultModel;
    }

    @Override
    public CreateScheduleDto mapCreateScheduleDto(CreateScheduleModel createScheduleModel, Long testPlanId) {
        CreateScheduleDto createScheduleDto = new CreateScheduleDto();
        createScheduleDto.setEnvironmentId(createScheduleModel.getEnvironmentId());
        createScheduleDto.setUserId(createScheduleModel.getUserId());
        createScheduleDto.setTestPlanId(testPlanId);
        createScheduleDto.setIsCaptureNetworkTraffic(createScheduleModel.getIsCaptureNetworkTraffic());
        createScheduleDto.setIsDisableScreenshot(createScheduleModel.getIsDisableScreenshot());
        createScheduleDto.setIsRecordVideoEnabled(createScheduleModel.getIsRecordVideoEnabled());
        createScheduleDto.setBrowserType(EnumUtils.getEnumIgnoreCase(BrowserType.class, createScheduleModel.getBrowserType()));
        if (createScheduleModel.getNotification() != null) {
            createScheduleDto.setNotification(new CreateNotificationDto());
            createScheduleDto.getNotification().setEmailAddress(createScheduleModel.getNotification().getEmailAddress());
        }
        return createScheduleDto;
    }

    @Override
    public ScheduleSummaryDto mapScheduleSummaryDto(Long userId, int pageSize, int pageNumber) {
        ScheduleSummaryDto scheduleSummaryDto = new ScheduleSummaryDto();
        scheduleSummaryDto.setUserId(userId);
        scheduleSummaryDto.setPageSize(pageSize);
        scheduleSummaryDto.setPageNumber(pageNumber);
        return scheduleSummaryDto;
    }

    @Override
    public SchedulePageModel mapSchedulePageModel(ScheduleSummaryDto scheduleSummaryDto, List<ErrorMessageDto> errorMessages) {
        SchedulePageModel schedulePageModel = new SchedulePageModel();
        schedulePageModel.setPageSize(scheduleSummaryDto.getPageSize());
        schedulePageModel.setPageNumber(scheduleSummaryDto.getPageNumber());
        schedulePageModel.setTotalPage(scheduleSummaryDto.getTotalPageCount());
        List<ScheduleItemModel> scheduleItemModels = new ArrayList<>();
        for (ScheduleDao scheduleDao : scheduleSummaryDto.getScheduleDaos()) {
            ScheduleItemModel scheduleItemModel = new ScheduleItemModel();
            scheduleItemModel.setScheduleId(scheduleDao.getId());
            var scheduleIteration = scheduleDao.getScheduleIterations().get(0);
            scheduleItemModel.setStatus(scheduleIteration.getStatus().name());
            scheduleItemModel.setLastExecute(scheduleIteration.getModifiedOn().toString());
            scheduleItemModel.setEnvironmentName(scheduleDao.getEnvironmentDaos().getName());
            scheduleItemModel.setTestplanName(scheduleDao.getTestplanDaos().getName());
            scheduleItemModels.add(scheduleItemModel);
        }
        schedulePageModel.setScheduleItem(scheduleItemModels);
        return schedulePageModel;
    }

    @Override
    public List<ScheduleIterationModel> mapScheduleIterationModels(List<ScheduleIterationDao> scheduleIterationDaos) {
        List<ScheduleIterationModel> scheduleIterationModels = new ArrayList<>();
        for (ScheduleIterationDao scheduleIterationDao : scheduleIterationDaos) {
            scheduleIterationModels.add(mapScheduleIterationModel(scheduleIterationDao));
        }
        return scheduleIterationModels;
    }

    @Override
    public ScheduleIterationModel mapScheduleIterationModel(ScheduleIterationDao scheduleIterationDao) {
        ScheduleIterationModel scheduleIterationModel = new ScheduleIterationModel();
        scheduleIterationModel.setId(scheduleIterationDao.getId());
        scheduleIterationModel.setScheduleId(scheduleIterationDao.getScheduleId());
        scheduleIterationModel.setComments(scheduleIterationDao.getComments());
        scheduleIterationModel.setCreatedBy(scheduleIterationDao.getCreatedBy());
        scheduleIterationModel.setCreatedOn(scheduleIterationDao.getCreatedOn().toString());
        scheduleIterationModel.setModifiedBy(scheduleIterationDao.getModifiedBy());
        scheduleIterationModel.setModifiedOn(scheduleIterationDao.getModifiedOn().toString());
        scheduleIterationModel.setStatus(scheduleIterationDao.getStatus().name());
        scheduleIterationModel.setStartedOn(Objects.toString(scheduleIterationDao.getStartedOn()));
        scheduleIterationModel.setCompletedOn(Objects.toString(scheduleIterationDao.getCompletedOn()));
        scheduleIterationModel.setIsDisableScreenshot(scheduleIterationDao.getIsDisableScreenshot());
        scheduleIterationModel.setIsRecordVideoEnabled(scheduleIterationDao.getIsRecordVideoEnabled());
        scheduleIterationModel.setIsCaptureNetworkTraffic(scheduleIterationDao.getIsCaptureNetworkTraffic());
        scheduleIterationModel.setBrowserType(scheduleIterationDao.getBrowserType().name());
        return scheduleIterationModel;
    }

    @Override
    public CopyTestPlanDetailDto mapCopyTestPlanDetailDto(Long scheduleId) {
        CopyTestPlanDetailDto copyTestPlanDetailDto = new CopyTestPlanDetailDto();
        copyTestPlanDetailDto.setScheduleId(scheduleId);
        return copyTestPlanDetailDto;
    }

    @Override
    public ScheduleIterationResultDto mapScheduleIterationResultDto(Long scheduleId, Long scheduleIterationId) {
        ScheduleIterationResultDto scheduleIterationResultDto = new ScheduleIterationResultDto();
        scheduleIterationResultDto.setScheduleIterationId(scheduleIterationId);
        scheduleIterationResultDto.setScheduleId(scheduleId);
        return scheduleIterationResultDto;
    }

    @Override
    public ScheduleIterationResultModel mapScheduleIterationResultModel(List<ErrorMessageDto> errors,
                                                                        ScheduleIterationResultDto scheduleIterationResultDto) {
        ScheduleIterationResultModel scheduleIterationResultModel = new ScheduleIterationResultModel();
        scheduleIterationResultModel.setScheduleIteration(mapScheduleIterationModel(scheduleIterationResultDto.getScheduleIteration()));
        scheduleIterationResultModel.setSchedule(mapScheduleModel(scheduleIterationResultDto.getSchedule()));
        scheduleIterationResultModel.setTestPlanResultSummary(mapTestPlanResultSummaryModel(scheduleIterationResultDto.getTestPlanResultDao()));
        return scheduleIterationResultModel;
    }

    @Override
    public TestPlanResultSummaryModel mapTestPlanResultSummaryModel(TestPlanResultDao testPlanResultDao) {
        TestPlanResultSummaryModel testPlanResultSummaryModel = new TestPlanResultSummaryModel();
        testPlanResultSummaryModel.setTestPlanName(testPlanResultDao.getTestplan().getName());
        testPlanResultSummaryModel.setStatus(testPlanResultDao.getStatus().name());
        testPlanResultSummaryModel.setId(testPlanResultDao.getId());
        //testPlanResultSummaryModel.setDuration();

        List<TestCaseResultSummaryModel> testCaseResultSummaryModels = new ArrayList<>();
        for (TestCaseResultDao testCaseResultDao : testPlanResultDao.getTestCaseResults()) {
            testCaseResultSummaryModels.add(mapTestCaseResultModel(testCaseResultDao));
        }

        testPlanResultSummaryModel.setTestCaseResults(testCaseResultSummaryModels);
        return testPlanResultSummaryModel;
    }

    @Override
    public TestCaseResultSummaryModel mapTestCaseResultModel(TestCaseResultDao testCaseResultDao) {
        TestCaseResultSummaryModel testCaseResultSummaryModel = new TestCaseResultSummaryModel();
        testCaseResultSummaryModel.setTestCaseName(testCaseResultDao.getTestCase().getName());
        testCaseResultSummaryModel.setStatus(testCaseResultDao.getStatus().name());
        testCaseResultSummaryModel.setExecutionOrder(testCaseResultDao.getExecutionOrder());
        testCaseResultSummaryModel.setId(testCaseResultDao.getId());
        //testCaseResultSummaryModel.setDuration();
        testCaseResultSummaryModel.setErrorMessage(testCaseResultDao.getErrorMessage());

        List<ActionResultModel> actionResultModels = new ArrayList<>();
        for (ActionResultDao actionResultDao : testCaseResultDao.getActionResultDaos()) {
            actionResultModels.add(mapActionResultModel(actionResultDao));
        }

        testCaseResultSummaryModel.setActionResults(actionResultModels);
        return testCaseResultSummaryModel;
    }

    @Override
    public ActionResultModel mapActionResultModel(ActionResultDao actionResultDao) {
        ActionResultModel actionResultModel = new ActionResultModel();
        //actionResultModel.setDuration();
        actionResultModel.setId(actionResultDao.getId());
        actionResultModel.setStatus(actionResultDao.getStatus().name());
        actionResultModel.setErrorMessage(actionResultDao.getErrorMessage());
        actionResultModel.setKey(actionResultDao.getAction().getKey());
        actionResultModel.setValue(actionResultDao.getAction().getValue());
        actionResultModel.setExecutionOrder(actionResultDao.getExecutionOrder());
        return actionResultModel;
    }

    @Override
    public ScheduleModel mapScheduleModel(ScheduleDao scheduleDao) {
        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setId(scheduleDao.getId());
        scheduleModel.setEnvironmentId(scheduleDao.getEnvironmentId());
        scheduleModel.setTestPlanId(scheduleDao.getTestPlanId());
        scheduleModel.setUserId(scheduleDao.getUserId());
        scheduleModel.setCreatedBy(scheduleDao.getCreatedBy());
        scheduleModel.setCreatedOn(scheduleDao.getCreatedOn().toString());
        scheduleModel.setModifiedBy(scheduleDao.getModifiedBy());
        scheduleModel.setModifiedOn(scheduleDao.getModifiedOn().toString());
        return scheduleModel;
    }

    @Override
    public ScheduleModel mapScheduleModel(ScheduleDetailEntity scheduleDetailEntity) {
        ScheduleModel scheduleModel = new ScheduleModel();
        scheduleModel.setId(scheduleDetailEntity.getId());
        scheduleModel.setEnvironmentId(scheduleDetailEntity.getEnvironmentId());
        scheduleModel.setTestPlanId(scheduleDetailEntity.getTestPlanId());
        scheduleModel.setUserId(scheduleDetailEntity.getUserId());
        scheduleModel.setCreatedBy(scheduleDetailEntity.getCreatedBy());
        scheduleModel.setCreatedOn(scheduleDetailEntity.getCreatedOn().toString());
        scheduleModel.setModifiedBy(scheduleDetailEntity.getModifiedBy());
        scheduleModel.setModifiedOn(scheduleDetailEntity.getModifiedOn().toString());
        scheduleModel.setStatus(scheduleDetailEntity.getStatus().name());
        return scheduleModel;
    }

    @Override
    public ScheduleDetailModel mapScheduleDetailModel(ScheduleDetailEntity scheduleDetailEntity) {
        ScheduleDetailModel scheduleDetailModel = new ScheduleDetailModel();
        scheduleDetailModel.setScheduleModel(mapScheduleModel(scheduleDetailEntity));
        var filterScheduleIteration = scheduleDetailEntity
                .getScheduleIterations()
                .stream()
                .filter(x -> x.getStatus() == ScheduleStatusDao.READY)
                .findFirst()
                .get();
        scheduleDetailModel.setScheduleIterationModel(mapScheduleIterationModel(filterScheduleIteration));
        scheduleDetailModel.setScheduleTestPlanModel(testplanResourceMapper.mapTestPlanModel(scheduleDetailEntity.getTestplanDaos()));
        return scheduleDetailModel;
    }

    @Override
    public UpdateScheduleStatusReadyDto mapUpdateScheduleStatusDto(Long scheduleId, UpdateScheduleStatusModel updateScheduleStatusModel) {
        UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto = new UpdateScheduleStatusReadyDto();
        updateScheduleStatusReadyDto.setErrorMessage(updateScheduleStatusModel.getErrorMessage());
        updateScheduleStatusReadyDto.setScheduleId(scheduleId);
        updateScheduleStatusReadyDto.setTotalDuration(updateScheduleStatusModel.getTotalDuration());
        updateScheduleStatusReadyDto.setScheduleStatus(EnumUtils.getEnumIgnoreCase(ScheduleStatusDao.class, updateScheduleStatusModel.getStatus()));
        return updateScheduleStatusReadyDto;
    }

    @Override
    public ReScheduleStatusDto mapReScheduleStatusDto(Long scheduleId, ReScheduleModel reScheduleModel) {
        ReScheduleStatusDto reScheduleStatusDto = new ReScheduleStatusDto();
        reScheduleStatusDto.setUserId(reScheduleModel.getUserId());
        reScheduleStatusDto.setScheduleId(scheduleId);
        return reScheduleStatusDto;
    }
}
