package cana.codelessautomation.api.resources.result.testcaseresult.mappers;

import cana.codelessautomation.api.resources.result.testcaseresult.models.TestCaseResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.models.UpdateTestCaseResultModel;
import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultStatusDao;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class TestCaseResultMapperImpl implements TestCaseResultMapper {
    @Override
    public UpdateTestCaseResultStatusDto mapUpdateTestCaseResultStatusDto(Long testPlanResultId,
                                                                          Long testCaseResultId,
                                                                          UpdateTestCaseResultModel updateTestCaseResultAsCompletedModel) {
        UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto = new UpdateTestCaseResultStatusDto();
        updateTestCaseResultStatusDto.setTestCaseResultId(testCaseResultId);
        updateTestCaseResultStatusDto.setTestPlanResultId(testPlanResultId);
        updateTestCaseResultStatusDto.setStatus(EnumUtils.getEnumIgnoreCase(TestCaseResultStatusDao.class, updateTestCaseResultAsCompletedModel.getStatus()));
        if (!Objects.isNull(updateTestCaseResultAsCompletedModel.getCompletedOn())) {
            updateTestCaseResultStatusDto.setCompletedOn(OffsetDateTime.parse(updateTestCaseResultAsCompletedModel.getCompletedOn()));
        }

        if (!Objects.isNull(updateTestCaseResultAsCompletedModel.getStartedOn())) {
            updateTestCaseResultStatusDto.setStartedOn(OffsetDateTime.parse(updateTestCaseResultAsCompletedModel.getStartedOn()));
        }

        updateTestCaseResultStatusDto.setErrorMessage(updateTestCaseResultAsCompletedModel.getErrorMessage());
        updateTestCaseResultStatusDto.setTotalDuration(updateTestCaseResultAsCompletedModel.getTotalDuration());
        return updateTestCaseResultStatusDto;
    }

    @Override
    public List<TestCaseResultModel> mapTestCaseResultModels(List<TestCaseResultDao> testPlanResultDaos) {
        List<TestCaseResultModel> testCaseResultModels = new ArrayList<>();
        for (TestCaseResultDao testCaseResultDao : testPlanResultDaos) {
            testCaseResultModels.add(mapTestCaseResultModel(testCaseResultDao));
        }
        return testCaseResultModels;
    }

    @Override
    public TestCaseResultModel mapTestCaseResultModel(TestCaseResultDao testPlanResultDao) {
        TestCaseResultModel testCaseResultModel = new TestCaseResultModel();
        testCaseResultModel.setId(testPlanResultDao.getId());
        testCaseResultModel.setTestPlanResultId(testPlanResultDao.getTestplanResultId());
        testCaseResultModel.setTestCaseId(testPlanResultDao.getTestCaseId());
        if (!Objects.isNull(testPlanResultDao.getStartedOn())) {
            testCaseResultModel.setStartedOn(testPlanResultDao.getStartedOn().toString());
        }

        if (!Objects.isNull(testPlanResultDao.getCompletedOn())) {
            testCaseResultModel.setCompletedOn(testPlanResultDao.getCompletedOn().toString());
        }

        testCaseResultModel.setStatus(testPlanResultDao.getStatus().name());
        testCaseResultModel.setErrorMessage(testPlanResultDao.getErrorMessage());

        if (!Objects.isNull(testPlanResultDao.getCreatedOn())) {
            testCaseResultModel.setCreatedOn(testPlanResultDao.getCreatedOn().toString());
        }

        if (!Objects.isNull(testPlanResultDao.getModifiedOn())) {
            testCaseResultModel.setModifiedOn(testPlanResultDao.getModifiedOn().toString());
        }

        testCaseResultModel.setExecutionOrder(testPlanResultDao.getExecutionOrder());
        testCaseResultModel.setTotalDuration(testPlanResultDao.getTotalDuration());

        return testCaseResultModel;
    }

}
