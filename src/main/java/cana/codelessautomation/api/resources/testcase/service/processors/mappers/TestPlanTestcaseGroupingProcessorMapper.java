package cana.codelessautomation.api.resources.testcase.service.processors.mappers;

import cana.codelessautomation.api.resources.testcase.service.dtos.TestCaseOrderDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.UpdateTestCaseOrderDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;

public interface TestPlanTestcaseGroupingProcessorMapper {

    TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(UpdateTestCaseOrderDto updateTestCaseOrderDto, TestCaseOrderDto testCaseOrderDto);

    TestplanTestcaseGroupingDao mapNewTestplanTestcaseGroupingDao(UpdateTestCaseOrderDto updateTestCaseOrderDto, TestCaseOrderDto testCaseOrderDto);
}
