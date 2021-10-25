package cana.codelessautomation.api.resources.testplan.mappers;

import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.testplan.models.CreateTestplanModel;
import cana.codelessautomation.api.resources.testplan.models.TestPlanModel;
import cana.codelessautomation.api.resources.testplan.models.UpdateTestplanModel;
import cana.codelessautomation.api.resources.testplan.models.UpdateTestplanStatusModel;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestPlanStatus;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TestplanResourceMapperImpl implements TestplanResourceMapper {
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
        resultModel.setId(createTestplan.getId());
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
        updateTestplanStatus.setUserId(updateTestplanStatus.getUserId());
        updateTestplanStatus.setTestplanId(testplanId);
        updateTestplanStatus.setStatus(EnumUtils.getEnum(TestPlanStatus.class, updateTestplanStatusModel.getStatus()));
        return updateTestplanStatus;
    }
}
