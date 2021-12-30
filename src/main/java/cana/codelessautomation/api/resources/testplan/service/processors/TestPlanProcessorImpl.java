package cana.codelessautomation.api.resources.testplan.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.DeleteTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.resources.testplan.service.processors.mappers.TestplanProcessorMapper;
import cana.codelessautomation.api.resources.testplan.service.repositories.TestPlanRepository;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

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
    public List<TestplanDao> processorGetTestplans(Long userId) {
        return getTestplans(userId);
    }

    @Override
    public List<TestplanDao> getTestplans(Long userId) {
        return testPlanRepository.findByUserId(userId);
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
}