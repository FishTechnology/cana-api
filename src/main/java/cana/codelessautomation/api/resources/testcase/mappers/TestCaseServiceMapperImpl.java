package cana.codelessautomation.api.resources.testcase.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.action.mappers.ActionResourceMapper;
import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduledActionDetailModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduledTestCaseModel;
import cana.codelessautomation.api.resources.testcase.models.*;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestCaseDaoEntity;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseServiceMapperImpl implements TestCaseServiceMapper {

    @Inject
    ActionResourceMapper actionResourceMapper;

    @Override
    public CreateTestCaseDto mapCreateTestCaseDto(CreateTestCaseModel createTestCaseModel) {
        CreateTestCaseDto createTestCase = new CreateTestCaseDto();
        createTestCase.setName(createTestCaseModel.getName());
        createTestCase.setComments(createTestCaseModel.getComments());
        createTestCase.setUserId(createTestCaseModel.getUserId());
        return createTestCase;
    }

    @Override
    public ResultModel mapResultModel(CreateTestCaseDto createTestCase, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createTestCase.getId().toString());
        return resultModel;
    }

    @Override
    public ResultModel mapResultModel(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createTestCaseByTestPlanId.getId().toString());
        return resultModel;
    }

    @Override
    public CreateTestCaseByTestPlanIdDto mapCreateTestCaseByTestPlanIdDto(CreateTestCaseByTestPlanIdModel createTestCaseByTestPlanIdModel, Long testPlanId) {
        CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId = new CreateTestCaseByTestPlanIdDto();
        createTestCaseByTestPlanId.setName(createTestCaseByTestPlanIdModel.getName());
        createTestCaseByTestPlanId.setComments(createTestCaseByTestPlanIdModel.getComments());
        createTestCaseByTestPlanId.setUserId(createTestCaseByTestPlanIdModel.getUserId());
        createTestCaseByTestPlanId.setTestPlanId(testPlanId);
        return createTestCaseByTestPlanId;
    }

    @Override
    public List<TestCaseModel> mapTestCaseModels(List<TestCaseDao> testCaseDaos) {
        List<TestCaseModel> testCaseModels = new ArrayList<>();
        for (TestCaseDao testCaseDao : testCaseDaos) {
            TestCaseModel testCaseModel = new TestCaseModel();
            testCaseModel.setId(testCaseDao.getId());
            testCaseModel.setComments(testCaseDao.getComments());
            testCaseModel.setName(testCaseDao.getName());
            testCaseModel.setIsActive(testCaseDao.getIsActive());
            testCaseModel.setUserId(testCaseDao.getUserId());
            testCaseModel.setCreatedBy(testCaseDao.getCreatedBy());
            testCaseModel.setCreatedOn(testCaseDao.getCreatedOn().toString());
            testCaseModel.setModifiedBy(testCaseDao.getModifiedBy());
            testCaseModel.setModifiedOn(testCaseDao.getModifiedOn().toString());
            testCaseModels.add(testCaseModel);
        }
        return testCaseModels;
    }

    @Override
    public GetTestCaseByTestPlanIdDto mapGetTestCaseByTestPlanIdDto(Long testPlanId) {
        GetTestCaseByTestPlanIdDto getTestCaseByTestPlanId = new GetTestCaseByTestPlanIdDto();
        getTestCaseByTestPlanId.setTestPlanId(testPlanId);
        return getTestCaseByTestPlanId;
    }

    @Override
    public List<TestCaseModel> mapTestCaseModels(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        return mapTestCaseModels(getTestCaseByTestPlanIdDto.getTestCaseDaos());
    }

    @Override
    public CheckTestCaseIsDeletableDto mapCheckTestCaseIsDeletableDto(Long testCaseId) {
        CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto = new CheckTestCaseIsDeletableDto();
        checkTestCaseIsDeletableDto.setTestCaseId(testCaseId);
        return checkTestCaseIsDeletableDto;
    }

    @Override
    public CheckTestCaseIsDeletableModel mapCheckTestCaseIsDeletableModel(CheckTestCaseIsDeletableDto checkTestCaseIsDeletableDto, List<ErrorMessageDto> errors) {
        var checkTestCaseIsDeletableModel = new CheckTestCaseIsDeletableModel();
        checkTestCaseIsDeletableModel.setErrorMessageModels(CanaUtility.getErrorMessageModels(errors));
        for (var testplanTestcaseGroupingDao : checkTestCaseIsDeletableDto.getTestplanTestcaseGroupings()) {
            var testPlanMappedModel = new TestPlanMappedModel();
            testPlanMappedModel.setId(testplanTestcaseGroupingDao.getId());
            checkTestCaseIsDeletableModel.getTestPlanMappedModels().add(testPlanMappedModel);
        }
        return checkTestCaseIsDeletableModel;
    }

    @Override
    public GetTestCaseByIdDto mapGetTestCaseByIdDto(Long testCaseId) {
        GetTestCaseByIdDto getTestCaseByIdDto = new GetTestCaseByIdDto();
        getTestCaseByIdDto.setTestCaseId(testCaseId);
        return getTestCaseByIdDto;
    }

    @Override
    public TestCaseModel mapTestCaseModel(GetTestCaseByIdDto getTestCaseByIdDto) {
        var testCaseDao = getTestCaseByIdDto.getTestCase();
        TestCaseModel testCaseModel = new TestCaseModel();
        testCaseModel.setId(testCaseDao.getId());
        testCaseModel.setComments(testCaseDao.getComments());
        testCaseModel.setName(testCaseDao.getName());
        testCaseModel.setIsActive(testCaseDao.getIsActive());
        testCaseModel.setUserId(testCaseDao.getUserId());
        testCaseModel.setCreatedBy(testCaseDao.getCreatedBy());
        testCaseModel.setCreatedOn(testCaseDao.getCreatedOn().toString());
        testCaseModel.setModifiedBy(testCaseDao.getModifiedBy());
        testCaseModel.setModifiedOn(testCaseDao.getModifiedOn().toString());
        return testCaseModel;
    }

    @Override
    public UpdateTestCaseByTestPlanIdDto mapUpdateTestCaseByTestPlanIdDto(Long testPlanId, Long testCaseId, UpdateTestCaseModel updateTestCaseModel) {
        UpdateTestCaseByTestPlanIdDto updateTestCaseByTestPlanId = new UpdateTestCaseByTestPlanIdDto();
        updateTestCaseByTestPlanId.setTestCaseId(testCaseId);
        updateTestCaseByTestPlanId.setTestPlanId(testPlanId);
        updateTestCaseByTestPlanId.setComments(updateTestCaseModel.getComments());
        updateTestCaseByTestPlanId.setName(updateTestCaseModel.getName());
        updateTestCaseByTestPlanId.setUserId(updateTestCaseModel.getUserId());
        return updateTestCaseByTestPlanId;
    }

    @Override
    public UpdateTestCaseByIdDto mapUpdateTestCaseByIdDto(Long testCaseId, UpdateTestCaseModel updateTestCaseModel) {
        UpdateTestCaseByIdDto updateTestCaseById = new UpdateTestCaseByIdDto();
        updateTestCaseById.setTestCaseId(testCaseId);
        updateTestCaseById.setComments(updateTestCaseModel.getComments());
        updateTestCaseById.setName(updateTestCaseModel.getName());
        updateTestCaseById.setUserId(updateTestCaseModel.getUserId());
        return updateTestCaseById;
    }

    @Override
    public ScheduledTestCaseModel mapScheduledTestCaseModel(TestCaseDaoEntity testCaseDaoEntity) {
        ScheduledTestCaseModel scheduledTestCaseModel = new ScheduledTestCaseModel();
        scheduledTestCaseModel.setId(testCaseDaoEntity.getId());
        scheduledTestCaseModel.setComments(testCaseDaoEntity.getComments());
        scheduledTestCaseModel.setName(testCaseDaoEntity.getName());
        scheduledTestCaseModel.setIsActive(testCaseDaoEntity.getIsActive());
        scheduledTestCaseModel.setUserId(testCaseDaoEntity.getUserId());
        scheduledTestCaseModel.setCreatedBy(testCaseDaoEntity.getCreatedBy());
        scheduledTestCaseModel.setCreatedOn(testCaseDaoEntity.getCreatedOn().toString());
        scheduledTestCaseModel.setModifiedBy(testCaseDaoEntity.getModifiedBy());
        scheduledTestCaseModel.setModifiedOn(testCaseDaoEntity.getModifiedOn().toString());
        if (CollectionUtils.isEmpty(testCaseDaoEntity.getActionDaoEntities())) {
            return scheduledTestCaseModel;
        }
        List<ScheduledActionDetailModel> scheduledActionDetails = new ArrayList<>();
        for (ActionDaoEntity actionDaoEntity : testCaseDaoEntity.getActionDaoEntities()) {
            var scheduledActionDetailModel = actionResourceMapper.mapScheduledActionDetailModel(actionDaoEntity);
            scheduledActionDetails.add(scheduledActionDetailModel);
        }
        scheduledTestCaseModel.setScheduledActionDetails(scheduledActionDetails);
        return scheduledTestCaseModel;
    }
}

