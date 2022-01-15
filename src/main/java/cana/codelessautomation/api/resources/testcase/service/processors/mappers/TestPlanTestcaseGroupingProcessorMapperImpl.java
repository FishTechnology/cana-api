package cana.codelessautomation.api.resources.testcase.service.processors.mappers;

import cana.codelessautomation.api.resources.testcase.service.dtos.TestCaseOrderDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.UpdateTestCaseOrderDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.Objects;

@ApplicationScoped
public class TestPlanTestcaseGroupingProcessorMapperImpl implements TestPlanTestcaseGroupingProcessorMapper {
    @Override
    public TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(UpdateTestCaseOrderDto updateTestCaseOrderDto, TestCaseOrderDto testCaseOrderDto) {
        var testPlanTestcaseGroupingDao = testCaseOrderDto.getTestplanTestcaseGrouping();
        if (Objects.isNull(testPlanTestcaseGroupingDao)) {
            return mapNewTestplanTestcaseGroupingDao(updateTestCaseOrderDto, testCaseOrderDto);
        }
        testPlanTestcaseGroupingDao.setModifiedOn(OffsetDateTime.now());
        testPlanTestcaseGroupingDao.setModifiedBy(updateTestCaseOrderDto.getUserId().toString());
        testPlanTestcaseGroupingDao.setExecutionOrder(testCaseOrderDto.getCurrentExecutionOrder());
        return testPlanTestcaseGroupingDao;
    }

    @Override
    public TestplanTestcaseGroupingDao mapNewTestplanTestcaseGroupingDao(UpdateTestCaseOrderDto updateTestCaseOrderDto, TestCaseOrderDto testCaseOrderDto) {
        TestplanTestcaseGroupingDao curTestplanTestcaseGroupingDao = new TestplanTestcaseGroupingDao();
        curTestplanTestcaseGroupingDao.setUserId(updateTestCaseOrderDto.getUserId());
        curTestplanTestcaseGroupingDao.setIsActive(true);
        curTestplanTestcaseGroupingDao.setCreatedOn(OffsetDateTime.now());
        curTestplanTestcaseGroupingDao.setModifiedOn(OffsetDateTime.now());
        curTestplanTestcaseGroupingDao.setCreatedBy(updateTestCaseOrderDto.getUserId().toString());
        curTestplanTestcaseGroupingDao.setModifiedBy(updateTestCaseOrderDto.getUserId().toString());
        curTestplanTestcaseGroupingDao.setTestCaseId(testCaseOrderDto.getTestCaseId());
        curTestplanTestcaseGroupingDao.setTestPlanId(updateTestCaseOrderDto.getTestPlanId());
        curTestplanTestcaseGroupingDao.setExecutionOrder(testCaseOrderDto.getCurrentExecutionOrder());
        return curTestplanTestcaseGroupingDao;
    }
}
