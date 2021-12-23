package cana.codelessautomation.api.resources.result.testcaseresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.testcaseresult.service.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;

public interface TestCaseResultProcessorMapper {
    TestCaseResultDao mapTestCaseResultDao(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto);
}
