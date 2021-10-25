package cana.codelessautomation.api.services.testcase.processors.mappers;

import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseByTestPlanIdDto;
import cana.codelessautomation.api.services.testcase.dtos.CreateTestCaseDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestplanTestcaseGroupingDao;

public interface TestCaseProcessorMapper {
    TestCaseDao mapTestCaseDao(CreateTestCaseDto createTestCase);

    TestCaseDao mapTestCaseDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);

    TestplanTestcaseGroupingDao mapTestplanTestcaseGroupingDao(CreateTestCaseByTestPlanIdDto createTestCaseByTestPlanId);
}
