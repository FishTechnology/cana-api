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
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestCaseDaoEntity;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseResourceMapperImpl implements TestCaseResourceMapper {

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
    public CreateTestCaseByTestPlanIdDto mapCreateTestCaseByTestPlanIdDto(CreateTestCaseByTestPlanIdModel createTestCaseByTestPlanIdModel, Long applicationId, Long testPlanId) {
        CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId = new CreateTestCaseByTestPlanIdDto();
        createTestCaseByTestPlanId.setName(createTestCaseByTestPlanIdModel.getName());
        createTestCaseByTestPlanId.setComments(createTestCaseByTestPlanIdModel.getComments());
        createTestCaseByTestPlanId.setUserId(createTestCaseByTestPlanIdModel.getUserId());
        createTestCaseByTestPlanId.setTestPlanId(testPlanId);
        createTestCaseByTestPlanId.setApplicationId(applicationId);
        return createTestCaseByTestPlanId;
    }

    @Override
    public List<TestCaseModel> mapTestCaseModels(List<TestCaseDao> testCaseDaos) {
        List<TestCaseModel> testCaseModels = new ArrayList<>();
        for (TestCaseDao testCaseDao : testCaseDaos) {
            testCaseModels.add(mapTestCaseModel(testCaseDao));
        }
        return testCaseModels;
    }

    @Override
    public GetTestCaseByTestPlanIdDto mapGetTestCaseByTestPlanIdDto(Long applicationId, Long testPlanId) {
        GetTestCaseByTestPlanIdDto getTestCaseByTestPlanId = new GetTestCaseByTestPlanIdDto();
        getTestCaseByTestPlanId.setApplicationId(applicationId);
        getTestCaseByTestPlanId.setTestPlanId(testPlanId);
        return getTestCaseByTestPlanId;
    }

    @Override
    public List<TestCaseModel> mapTestCaseModels(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        List<TestCaseModel> testCaseModels = new ArrayList<>();
        for (TestplanTestcaseGroupingDao testplanTestcaseGroupingDao : getTestCaseByTestPlanIdDto.getTestplanTestcaseGroupingDaos()) {
            var testCaseDao = getTestCaseByTestPlanIdDto
                    .getTestCaseDaos()
                    .stream()
                    .filter(p -> p.getId().equals(testplanTestcaseGroupingDao.getTestCaseId()))
                    .findFirst();
            if (!testCaseDao.isPresent()) {
                continue;
            }
            var testCaseModel = mapTestCaseModel(testCaseDao.get());
            testCaseModel.setExecutionOrder(testplanTestcaseGroupingDao.getExecutionOrder());
            testCaseModels.add(testCaseModel);
        }
        return testCaseModels;
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
    public TestCaseModel mapTestCaseModel(TestCaseDao testCaseDao) {
        TestCaseModel testCaseModel = new TestCaseModel();
        testCaseModel.setId(testCaseDao.getId().toString());
        testCaseModel.setComments(testCaseDao.getComments());
        testCaseModel.setName(testCaseDao.getName());
        testCaseModel.setIsActive(testCaseDao.getIsActive());
        testCaseModel.setUserId(testCaseDao.getUserId().toString());
        testCaseModel.setCreatedBy(testCaseDao.getCreatedBy());
        testCaseModel.setCreatedOn(testCaseDao.getCreatedOn().toString());
        testCaseModel.setModifiedBy(testCaseDao.getModifiedBy());
        testCaseModel.setModifiedOn(testCaseDao.getModifiedOn().toString());
        return testCaseModel;
    }

    @Override
    public TestCaseModel mapTestCaseModel(GetTestCaseByIdDto getTestCaseByIdDto) {
        return mapTestCaseModel(getTestCaseByIdDto.getTestCase());
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
        scheduledTestCaseModel.setId(testCaseDaoEntity.getId().toString());
        scheduledTestCaseModel.setComments(testCaseDaoEntity.getComments());
        scheduledTestCaseModel.setName(testCaseDaoEntity.getName());
        scheduledTestCaseModel.setIsActive(testCaseDaoEntity.getIsActive());
        scheduledTestCaseModel.setUserId(testCaseDaoEntity.getUserId().toString());
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

    @Override
    public UpdateTestCaseOrderDto mapUpdateTestCaseOrderDto(Long testPlanId, UpdateTestCaseOrderModel updateTestCaseOrderModel) {
        UpdateTestCaseOrderDto updateTestCaseOrderDto = new UpdateTestCaseOrderDto();
        updateTestCaseOrderDto.setTestPlanId(testPlanId);
        updateTestCaseOrderDto.setUserId(updateTestCaseOrderModel.getUserId());
        List<TestCaseOrderDto> testCaseOrderDtos = new ArrayList<>();
        for (TestCaseOrderModel testCaseOrderModel : updateTestCaseOrderModel.getTestcaseOrderModels()) {
            TestCaseOrderDto testCaseOrderDto = new TestCaseOrderDto();
            testCaseOrderDto.setTestCaseId(testCaseOrderModel.getTestCaseId());
            testCaseOrderDto.setOldExecutionOrder(testCaseOrderModel.getOldExecutionOrder());
            testCaseOrderDto.setCurrentExecutionOrder(testCaseOrderModel.getCurrentExecutionOrder());
            testCaseOrderDtos.add(testCaseOrderDto);
        }

        updateTestCaseOrderDto.setTestCaseOrderDtos(testCaseOrderDtos);
        return updateTestCaseOrderDto;
    }

    @Override
    public DeleteTestCaseDto mapDeleteTestCaseDto(Long applicationId, Long testPlanId, Long testCaseId) {
        DeleteTestCaseDto deleteTestCaseDto = new DeleteTestCaseDto();
        deleteTestCaseDto.setApplicationId(applicationId);
        deleteTestCaseDto.setTestCaseId(testCaseId);
        deleteTestCaseDto.setTestPlanId(testPlanId);
        return deleteTestCaseDto;
    }

    @Override
    public DeleteTestCaseDto mapDeleteTestCaseDto(Long applicationId, Long testCaseId) {
        DeleteTestCaseDto deleteTestCaseDto = new DeleteTestCaseDto();
        deleteTestCaseDto.setApplicationId(applicationId);
        deleteTestCaseDto.setTestCaseId(testCaseId);
        return deleteTestCaseDto;
    }
}

