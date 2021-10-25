package cana.codelessautomation.api.services.testcase.processors.mappers;

import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestplanTestcaseGroupingDao;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestCaseProcessorMapperImpl implements TestCaseProcessorMapper {
    @Override
    public TestCaseDao mapTestCaseDao(CreateTestCaseDto createTestCase) {
        TestCaseDao testCaseDao = new TestCaseDao();
        testCaseDao.setName(createTestCase.getName());
        testCaseDao.setUserId(createTestCase.getUserId());
        testCaseDao.setComments(createTestCase.getComments());
        testCaseDao.setIsActive(createTestCase.getIsActive());
        testCaseDao.setCreatedOn(createTestCase.getCreatedOn());
        testCaseDao.setModifiedOn(createTestCase.getModifiedOn());
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
        testCaseDao.setCreatedOn(createTestCaseByTestPlanId.getCreatedOn());
        testCaseDao.setModifiedOn(createTestCaseByTestPlanId.getModifiedOn());
        testCaseDao.setCreatedBy(createTestCaseByTestPlanId.getCreatedBy());
        testCaseDao.setModifiedBy(createTestCaseByTestPlanId.getModifiedBy());
        return testCaseDao;
    }

    @Override
    public TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId) {
        TestplanTestcaseGroupingDao testplanTestcaseGroupingDao = new TestplanTestcaseGroupingDao();
        testplanTestcaseGroupingDao.setUserId(createTestCaseByTestPlanId.getUserId());
        testplanTestcaseGroupingDao.setIsActive(createTestCaseByTestPlanId.getIsActive());
        testplanTestcaseGroupingDao.setCreatedOn(createTestCaseByTestPlanId.getCreatedOn());
        testplanTestcaseGroupingDao.setModifiedOn(createTestCaseByTestPlanId.getModifiedOn());
        testplanTestcaseGroupingDao.setCreatedBy(createTestCaseByTestPlanId.getCreatedBy());
        testplanTestcaseGroupingDao.setModifiedBy(createTestCaseByTestPlanId.getModifiedBy());
        testplanTestcaseGroupingDao.setTestCaseId(createTestCaseByTestPlanId.getId());
        testplanTestcaseGroupingDao.setTestPlanId(createTestCaseByTestPlanId.getTestPlanId());
        return testplanTestcaseGroupingDao;
    }
}