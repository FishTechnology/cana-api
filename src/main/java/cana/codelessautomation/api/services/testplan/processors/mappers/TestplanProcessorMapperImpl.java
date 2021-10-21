package cana.codelessautomation.api.services.testplan.processors.mappers;


import cana.codelessautomation.api.services.testplan.dtos.CreateTestplanDto;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestplanProcessorMapperImpl implements TestplanProcessorMapper {
    @Override
    public TestplanDao mapTestplanDao(CreateTestplanDto createTestplan) {
        TestplanDao testplan = new TestplanDao();
        testplan.setName(createTestplan.getName());
        testplan.setComments(createTestplan.getComments());
        testplan.setStatus(createTestplan.getStatus());
        testplan.setCreatedOn(createTestplan.getCreatedOn());
        testplan.setModifiedOn(createTestplan.getModifiedOn());
        testplan.setCreatedBy(createTestplan.getCreatedBy());
        testplan.setModifiedBy(createTestplan.getModifiedBy());
        testplan.setUserId(createTestplan.getUserId());
        return testplan;
    }
}
