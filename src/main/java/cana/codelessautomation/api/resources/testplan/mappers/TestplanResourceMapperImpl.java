package cana.codelessautomation.api.resources.testplan.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduleTestPlanModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduledTestCaseModel;
import cana.codelessautomation.api.resources.testcase.mappers.TestCaseServiceMapper;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestplanTestcaseGroupingDaoEntity;
import cana.codelessautomation.api.resources.testplan.models.CreateTestplanModel;
import cana.codelessautomation.api.resources.testplan.models.TestPlanModel;
import cana.codelessautomation.api.resources.testplan.models.UpdateTestplanModel;
import cana.codelessautomation.api.resources.testplan.models.UpdateTestplanStatusModel;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.entities.TestPlanSummaryDaoEntity;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestplanResourceMapperImpl implements TestplanResourceMapper {

    @Inject
    TestCaseServiceMapper testCaseServiceMapper;

    @Override
    public CreateTestplanDto mapCreateTestplanDto(CreateTestplanModel createTestplanModel) {
        CreateTestplanDto createTestplanDto = new CreateTestplanDto();
        createTestplanDto.setName(createTestplanModel.getName());
        createTestplanDto.setUserId(createTestplanModel.getUserId());
        createTestplanDto.setComments(createTestplanModel.getComments());
        return createTestplanDto;
    }

    @Override
    public ResultModel mapResultModel(CreateTestplanDto createTestplan, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createTestplan.getId().toString());
        return resultModel;
    }

    @Override
    public List<TestPlanModel> mapTestPlanModels(List<TestplanDao> testPlanDaos) {
        List<TestPlanModel> testPlanModels = new ArrayList<>();
        for (TestplanDao testplan : testPlanDaos) {
            TestPlanModel testPlanModel = new TestPlanModel();
            testPlanModel.setName(testplan.getName());
            testPlanModel.setId(testplan.getId());
            testPlanModel.setStatus(testplan.getStatus().name());
            testPlanModel.setComments(testplan.getComments());
            testPlanModel.setCreatedBy(testplan.getCreatedBy());
            testPlanModel.setCreatedOn(testplan.getCreatedOn());
            testPlanModel.setModifiedOn(testplan.getModifiedOn());
            testPlanModel.setModifiedBy(testplan.getModifiedBy());
            testPlanModels.add(testPlanModel);
        }
        return testPlanModels;
    }

    @Override
    public TestPlanModel mapTestPlanModel(TestplanDao testplan) {
        TestPlanModel testPlanModel = new TestPlanModel();
        testPlanModel.setName(testplan.getName());
        testPlanModel.setId(testplan.getId());
        testPlanModel.setStatus(testplan.getStatus().name());
        testPlanModel.setComments(testplan.getComments());
        testPlanModel.setCreatedBy(testplan.getCreatedBy());
        testPlanModel.setCreatedOn(testplan.getCreatedOn());
        testPlanModel.setModifiedOn(testplan.getModifiedOn());
        testPlanModel.setModifiedBy(testplan.getModifiedBy());
        return testPlanModel;
    }

    @Override
    public DeleteTestplanDto mapDeleteTestplanDto(Long testplanId) {
        DeleteTestplanDto deleteTestplan = new DeleteTestplanDto();
        deleteTestplan.setTestplanId(testplanId);
        return deleteTestplan;
    }

    @Override
    public UpdateTestplanDto mapUpdateTestplanDto(UpdateTestplanModel updateTestplanModel, Long testplanId) {
        UpdateTestplanDto updateTestplan = new UpdateTestplanDto();
        updateTestplan.setComments(updateTestplanModel.getComments());
        updateTestplan.setName(updateTestplanModel.getName());
        updateTestplan.setUserId(updateTestplanModel.getUserId());
        updateTestplan.setTestplanId(testplanId);
        return updateTestplan;
    }

    @Override
    public UpdateTestplanStatusDto mapUpdateTestplanStatusDto(UpdateTestplanStatusModel updateTestplanStatusModel, Long testplanId) {
        UpdateTestplanStatusDto updateTestplanStatus = new UpdateTestplanStatusDto();
        updateTestplanStatus.setUserId(updateTestplanStatusModel.getUserId());
        updateTestplanStatus.setTestplanId(testplanId);
        updateTestplanStatus.setStatus(EnumUtils.getEnum(TestPlanStatusDao.class, updateTestplanStatusModel.getStatus()));
        return updateTestplanStatus;
    }

    @Override
    public ScheduleTestPlanModel mapTestPlanModel(TestPlanSummaryDaoEntity testPlanSummaryDaoEntity) {
        ScheduleTestPlanModel scheduleTestPlanModel = new ScheduleTestPlanModel();
        scheduleTestPlanModel.setName(testPlanSummaryDaoEntity.getName());
        scheduleTestPlanModel.setId(testPlanSummaryDaoEntity.getId());
        scheduleTestPlanModel.setStatus(testPlanSummaryDaoEntity.getStatus().name());
        scheduleTestPlanModel.setComments(testPlanSummaryDaoEntity.getComments());
        scheduleTestPlanModel.setCreatedBy(testPlanSummaryDaoEntity.getCreatedBy());
        scheduleTestPlanModel.setCreatedOn(testPlanSummaryDaoEntity.getCreatedOn());
        scheduleTestPlanModel.setModifiedOn(testPlanSummaryDaoEntity.getModifiedOn());
        scheduleTestPlanModel.setModifiedBy(testPlanSummaryDaoEntity.getModifiedBy());

        if (CollectionUtils.isEmpty(testPlanSummaryDaoEntity.getTestplanTestcaseGroupings())) {
            return scheduleTestPlanModel;
        }

        List<ScheduledTestCaseModel> scheduledTestCaseModels = new ArrayList<>();

        for (TestplanTestcaseGroupingDaoEntity testplanTestcaseGroupingDaoEntity : testPlanSummaryDaoEntity.getTestplanTestcaseGroupings()) {
            var scheduledTestCaseModel = testCaseServiceMapper.mapScheduledTestCaseModel(testplanTestcaseGroupingDaoEntity.getTestCase());
            scheduledTestCaseModel.setOrder(testplanTestcaseGroupingDaoEntity.getExecutionOrder());
            scheduledTestCaseModels.add(scheduledTestCaseModel);
        }

        scheduleTestPlanModel.setScheduledTestCaseModel(scheduledTestCaseModels);

        return scheduleTestPlanModel;
    }
}
