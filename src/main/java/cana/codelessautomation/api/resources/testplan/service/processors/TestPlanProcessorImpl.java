package cana.codelessautomation.api.resources.testplan.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testcase.service.processors.TestCaseProcessor;
import cana.codelessautomation.api.resources.testplan.service.dtos.*;
import cana.codelessautomation.api.resources.testplan.service.processors.mappers.TestplanProcessorMapper;
import cana.codelessautomation.api.resources.testplan.service.repositories.TestPlanRepository;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestPlanProcessorImpl implements TestPlanProcessor {

    @Inject
    TestPlanRepository testPlanRepository;

    @Inject
    TestplanProcessorMapper testplanProcessorMapper;

    @Inject
    TestCaseProcessor testCaseProcessor;

    @Override
    public List<ErrorMessageDto> processorCreateTestplan(CreateTestplanDto createTestplan) {
        return createTestplan(createTestplan);
    }

    @Override
    public List<ErrorMessageDto> createTestplan(CreateTestplanDto createTestplan) {
        var testplanDao = testplanProcessorMapper.mapTestplanDao(createTestplan);
        testPlanRepository.persist(testplanDao);
        createTestplan.setId(testplanDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<TestplanDao> processorGetTestplans(Long applicationId, Long userId) {
        return getTestplans(applicationId);
    }

    @Override
    public List<TestplanDao> getTestplans(Long applicationId) {
        return testPlanRepository.findByUserId(applicationId);
    }

    @Override
    public List<ErrorMessageDto> processorDeleteTestplan(DeleteTestplanDto deleteTestplan) {
        return deleteTestplan(deleteTestplan);
    }

    @Override
    public List<ErrorMessageDto> deleteTestplan(DeleteTestplanDto deleteTestplan) {
        testPlanRepository.deleteTestplan(deleteTestplan.getTestplanId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorUpdateTestplan(UpdateTestplanDto updateTestplanModel) {
        return updateTestplan(updateTestplanModel);
    }

    @Override
    public List<ErrorMessageDto> updateTestplan(UpdateTestplanDto updateTestplanModel) {
        testPlanRepository.updateTestplan(updateTestplanModel);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorUpdateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) {
        return updateTestplanStatus(updateTestplanStatus);
    }

    @Override
    public List<ErrorMessageDto> updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) {
        testPlanRepository.updateTestplanStatus(updateTestplanStatus);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processorCopyTestplan(CopyTestPlanDto copyTestPlanDto) {
        var errors = createTestplan(copyTestPlanDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return copyTestCases(copyTestPlanDto);
    }

    @Override
    public List<ErrorMessageDto> copyTestCases(CopyTestPlanDto copyTestPlanDto) {
        return testCaseProcessor.copyTestCase(copyTestPlanDto);
    }

    @Override
    public List<ErrorMessageDto> createTestplan(CopyTestPlanDto copyTestPlanDto) {
        var testplanDao = testplanProcessorMapper.mapTestplanDao(copyTestPlanDto);
        testPlanRepository.persist(testplanDao);
        copyTestPlanDto.setId(testplanDao.getId());
        return Collections.emptyList();
    }
}
