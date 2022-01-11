package cana.codelessautomation.api.resources.testcase.service.processors.mappers;

import cana.codelessautomation.api.resources.testcase.service.dtos.TestCaseOrderDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.UpdateTestCaseOrderDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class TestPlanTestcaseGroupingProcessorMapperImpl implements TestPlanTestcaseGroupingProcessorMapper {
    @Override
    public TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(UpdateTestCaseOrderDto updateTestCaseOrderDto, TestCaseOrderDto testCaseOrderDto) {
        var testPlanTestcaseGroupingDao = testCaseOrderDto.getTestplanTestcaseGrouping();
        testPlanTestcaseGroupingDao.setModifiedOn(OffsetDateTime.now());
        testPlanTestcaseGroupingDao.setModifiedBy(updateTestCaseOrderDto.getUserId().toString());
        testPlanTestcaseGroupingDao.setExecutionOrder(testCaseOrderDto.getCurrentExecutionOrder());
        return testPlanTestcaseGroupingDao;
    }
}
