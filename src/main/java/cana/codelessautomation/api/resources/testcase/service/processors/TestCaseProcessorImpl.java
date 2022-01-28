package cana.codelessautomation.api.resources.testcase.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.*;
import cana.codelessautomation.api.resources.testcase.service.processors.mappers.TestCaseProcessorMapper;
import cana.codelessautomation.api.resources.testcase.service.processors.mappers.TestPlanTestcaseGroupingProcessorMapper;
import cana.codelessautomation.api.resources.testcase.service.repositories.TestCaseRepository;
import cana.codelessautomation.api.resources.testcase.service.repositories.TestplanTestcaseGroupingRepository;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.resources.testplan.service.dtos.CopyTestPlanDto;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestCaseProcessorImpl implements TestCaseProcessor {
    @Inject
    TestCaseRepository testCaseRepository;

    @Inject
    TestCaseProcessorMapper testCaseProcessorMapper;

    @Inject
    TestplanTestcaseGroupingRepository testplanTestcaseGroupingRepository;

    @Inject
    TestPlanTestcaseGroupingProcessorMapper testPlanTestcaseGroupingProcessorMapper;

    @Override
    public List<ErrorMessageDto> copyTestCase(CopyTestPlanDto copyTestPlanDto) {
        List<TestplanTestcaseGroupingDao> testplanTestcaseGroupingDaos = testplanTestcaseGroupingRepository.findByTestPlanId(copyTestPlanDto.getTestPlanId());
        if (CollectionUtils.isEmpty(testplanTestcaseGroupingDaos)) {
            return Collections.emptyList();
        }
        for (TestplanTestcaseGroupingDao testplanTestcaseGroupingDao : testplanTestcaseGroupingDaos) {
            var curTestplanTestcaseGroupingDao = testCaseProcessorMapper.mapTestplanTestcaseGroupingDao(copyTestPlanDto, testplanTestcaseGroupingDao);
            testplanTestcaseGroupingRepository.persist(curTestplanTestcaseGroupingDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processCreateTestCase(CreateTestCaseDto createTestCase) {
        return createTestCase(createTestCase);
    }

    @Override
    public List<TestCaseDao> processGetTestCaseByUserId(Long userId) {
        return getTestCaseByUserId(userId);
    }

    @Override
    public List<ErrorMessageDto> processGetTestCaseByTestPlanId(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        var errors = getTestPlanAndTestCaseGrouping(getTestCaseByTestPlanIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return getTestCaseByTestCaseIds(getTestCaseByTestPlanIdDto);
    }

    @Override
    public List<ErrorMessageDto> getTestPlanAndTestCaseGrouping(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        var response = testplanTestcaseGroupingRepository.findByTestPlanId(getTestCaseByTestPlanIdDto.getTestPlanId());
        getTestCaseByTestPlanIdDto.setTestplanTestcaseGroupingDaos(response);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processCreateTestCaseByPlanId(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var errors = createTestCase(createTestCaseByTestPlanId);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createTestPlanTestCaseMapping(createTestCaseByTestPlanId);
    }

    @Override
    public List<ErrorMessageDto> createTestCase(CreateTestCaseDto createTestCase) {
        var testCaseDao = testCaseProcessorMapper.mapTestCaseDao(createTestCase);
        testCaseRepository.persist(testCaseDao);
        createTestCase.setId(testCaseDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createTestPlanTestCaseMapping(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var orderNumber = getTestCaseOrderNumber(createTestCaseByTestPlanId.getTestPlanId());
        var testCaseDao = testCaseProcessorMapper.mapTestplanTestcaseGroupingDao(createTestCaseByTestPlanId);
        testCaseDao.setExecutionOrder(orderNumber);
        testplanTestcaseGroupingRepository.persist(testCaseDao);
        createTestCaseByTestPlanId.setTestPlanTestCaseGroupingId(testCaseDao.getId());
        return Collections.emptyList();
    }

    @Override
    public Long getTestCaseOrderNumber(Long testPlanId) {
        var testplanTestcaseGroupingDao = testplanTestcaseGroupingRepository.findLastTestCaseByTestPlanId(testPlanId);
        if (testplanTestcaseGroupingDao == null) {
            return Long.valueOf(0) + 1;
        }
        return testplanTestcaseGroupingDao.getExecutionOrder() + 1;
    }

    @Override
    public List<ErrorMessageDto> createTestCase(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        var testCaseDao = testCaseProcessorMapper.mapTestCaseDao(createTestCaseByTestPlanId);
        testCaseRepository.persist(testCaseDao);
        createTestCaseByTestPlanId.setId(testCaseDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<TestCaseDao> getTestCaseByUserId(Long userId) {
        return testCaseRepository.findByUserId(userId);
    }

    @Override
    public List<ErrorMessageDto> processUpdateTestCaseById(UpdateTestCaseByIdDto updateTestCaseByIdDto) {
        return updateTestCase(updateTestCaseByIdDto);
    }

    @Override
    public List<ErrorMessageDto> updateTestCase(UpdateTestCaseByIdDto updateTestCaseByIdDto) {
        testCaseRepository.updateTestCase(
                updateTestCaseByIdDto.getName(),
                updateTestCaseByIdDto.getComments(),
                updateTestCaseByIdDto.getModifiedOn(),
                updateTestCaseByIdDto.getModifiedBy(),
                updateTestCaseByIdDto.getTestCaseId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processUpdateTestCaseOrder(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        return updateTestCaseOrder(updateTestCaseOrderDto);
    }

    @Override
    public List<ErrorMessageDto> updateTestCaseOrder(UpdateTestCaseOrderDto updateTestCaseOrderDto) {
        for (TestCaseOrderDto testCaseOrderDto : updateTestCaseOrderDto.getTestCaseOrderDtos()) {
            if (testCaseOrderDto.getCurrentExecutionOrder().equals(testCaseOrderDto.getOldExecutionOrder())) {
                continue;
            }
            var testPlanTestcaseGroupingDao = testPlanTestcaseGroupingProcessorMapper.mapTestplanTestcaseGroupingDao(updateTestCaseOrderDto, testCaseOrderDto);
            testplanTestcaseGroupingRepository.persist(testPlanTestcaseGroupingDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> getTestCaseByTestCaseIds(GetTestCaseByTestPlanIdDto getTestCaseByTestPlanIdDto) {
        if (CollectionUtils.isEmpty(getTestCaseByTestPlanIdDto.getTestplanTestcaseGroupingDaos())) {
            return Collections.emptyList();
        }
        List<Long> testCaseIds = new ArrayList<>();

        for (TestplanTestcaseGroupingDao testplanTestcaseGroupingDao : getTestCaseByTestPlanIdDto.getTestplanTestcaseGroupingDaos()) {
            testCaseIds.add(testplanTestcaseGroupingDao.getTestCaseId());
        }

        var testCaseDaos = testCaseRepository.findByIds(testCaseIds);
        getTestCaseByTestPlanIdDto.setTestCaseDaos(testCaseDaos);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processDeleteTestCase(DeleteTestCaseDto deleteTestCaseDto) {
        deleteTestCaseDto(deleteTestCaseDto);
        return deleteTestplanTestcaseGrouping(deleteTestCaseDto);
    }

    @Override
    public List<ErrorMessageDto> deleteTestplanTestcaseGrouping(DeleteTestCaseDto deleteTestCaseDto) {
        testplanTestcaseGroupingRepository.deleteByTestCaseIdAndTestPlanId(deleteTestCaseDto.getTestPlanId(), deleteTestCaseDto.getTestCaseId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> deleteTestCaseDto(DeleteTestCaseDto deleteTestCaseDto) {
        testCaseRepository.deleteByTestCaseId(deleteTestCaseDto.getTestCaseId());
        return Collections.emptyList();
    }
}
