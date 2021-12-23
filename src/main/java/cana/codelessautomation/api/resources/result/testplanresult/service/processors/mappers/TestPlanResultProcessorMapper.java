package cana.codelessautomation.api.resources.result.testplanresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;

public interface TestPlanResultProcessorMapper {
    TestPlanResultDao mapTestPlanResultDao(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);
}
