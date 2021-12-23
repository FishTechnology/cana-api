package cana.codelessautomation.api.resources.testcase.service.processors.mappers;

import cana.codelessautomation.api.resources.testcase.service.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class TestCaseProcessorMapperImpl implements TestCaseProcessorMapper {
    @Override
    public TestCaseDao mapTestCaseDao(CreateTestCaseDto createTestCase) {
        TestCaseDao testCaseDao = new TestCaseDao();
        testCaseDao.setName(createTestCase.getName());
        testCaseDao.setUserId(createTestCase.getUserId());
        testCaseDao.setComments(createTestCase.getComments());
        testCaseDao.setIsActive(createTestCase.getIsActive());
        testCaseDao.setCreatedOn(OffsetDateTime.now());
        testCaseDao.setModifiedOn(OffsetDateTime.now());
        testCaseDao.setCreatedBy(createTestCase.getCreatedBy());
        testCaseDao.setModifiedBy(createTestCase.getModifiedBy());
        return testCaseDao;
    }

    @Override
    public TestCaseDao mapTestCaseDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        TestCaseDao testCaseDao = new TestCaseDao();
        testCaseDao.setName(createTestCaseByTestPlanId.getName());
        testCaseDao.setUserId(createTestCaseByTestPlanId.getUserId());
        testCaseDao.setComments(createTestCaseByTestPlanId.getComments());
        testCaseDao.setIsActive(createTestCaseByTestPlanId.getIsActive());
        testCaseDao.setCreatedOn(OffsetDateTime.now());
        testCaseDao.setModifiedOn(OffsetDateTime.now());
        testCaseDao.setCreatedBy(createTestCaseByTestPlanId.getCreatedBy());
        testCaseDao.setModifiedBy(createTestCaseByTestPlanId.getModifiedBy());
        return testCaseDao;
    }

    @Override
    public TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        TestplanTestcaseGroupingDao testplanTestcaseGroupingDao = new TestplanTestcaseGroupingDao();
        testplanTestcaseGroupingDao.setUserId(createTestCaseByTestPlanId.getUserId());
        testplanTestcaseGroupingDao.setIsActive(createTestCaseByTestPlanId.getIsActive());
        testplanTestcaseGroupingDao.setCreatedOn(OffsetDateTime.now());
        testplanTestcaseGroupingDao.setModifiedOn(OffsetDateTime.now());
        testplanTestcaseGroupingDao.setCreatedBy(createTestCaseByTestPlanId.getCreatedBy());
        testplanTestcaseGroupingDao.setModifiedBy(createTestCaseByTestPlanId.getModifiedBy());
        testplanTestcaseGroupingDao.setTestCaseId(createTestCaseByTestPlanId.getId());
        testplanTestcaseGroupingDao.setTestPlanId(createTestCaseByTestPlanId.getTestPlanId());
        return testplanTestcaseGroupingDao;
    }
}
