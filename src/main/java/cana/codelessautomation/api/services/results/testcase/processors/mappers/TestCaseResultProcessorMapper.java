package cana.codelessautomation.api.services.results.testcase.processors.mappers;

import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;

public interface TestCaseResultProcessorMapper {
    TestCaseResultDao mapTestCaseResultDao(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);
}
