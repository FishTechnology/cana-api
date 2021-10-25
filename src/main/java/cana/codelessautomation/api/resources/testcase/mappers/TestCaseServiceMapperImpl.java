package cana.codelessautomation.api.resources.testcase.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.testcase.models.CreateTestCaseByTestPlanIdModel;
import cana.codelessautomation.api.resources.testcase.models.CreateTestCaseModel;
import cana.codelessautomation.api.resources.testcase.models.TestCaseModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.dtos.GetTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestCaseServiceMapperImpl implements TestCaseServiceMapper {
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
        resultModel.setId(createTestCase.getId());
        return resultModel;
    }

    @Override
    public ResultModel mapResultModel(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createTestCaseByTestPlanId.getId());
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
}
