package cana.codelessautomation.api.services.results.testplan.processors.mappers;

import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;

public interface TestPlanResultProcessorMapper {
    TestPlanResultDao mapTestPlanResultDao(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);
}
