package cana.codelessautomation.api.resources.testplan.service.processors.mappers;

import cana.codelessautomation.api.resources.testplan.service.dtos.CopyTestPlanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

public interface TestplanProcessorMapper {
    TestplanDao mapTestplanDao(CreateTestplanDto createTestplan);

    TestplanDao mapTestplanDao(CopyTestPlanDto copyTestPlanDto);

    TestplanDao getBaseTestplanDao();
}
