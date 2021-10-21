package cana.codelessautomation.api.services.testplan.processors.mappers;

import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;

public interface TestplanProcessorMapper {
    TestplanDao mapTestplanDao(CreateTestplanDto createTestplan);
}
