package cana.codelessautomation.api.resources.testcase.service.processors.mappers;

import cana.codelessautomation.api.resources.testcase.service.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.resources.testcase.service.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;

public interface TestCaseProcessorMapper {
    TestCaseDao mapTestCaseDao(CreateTestCaseDto createTestCase);

    TestCaseDao mapTestCaseDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);
}
