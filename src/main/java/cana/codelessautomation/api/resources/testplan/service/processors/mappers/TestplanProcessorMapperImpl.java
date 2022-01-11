package cana.codelessautomation.api.resources.testplan.service.processors.mappers;


import cana.codelessautomation.api.resources.testplan.service.dtos.CopyTestPlanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.CreateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

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

    @Override
    public TestplanDao mapTestplanDao(CopyTestPlanDto copyTestPlanDto) {
        TestplanDao testplan = new TestplanDao();
        testplan.setName(copyTestPlanDto.getTestPlanName());
        testplan.setComments(copyTestPlanDto.getComments());
        testplan.setStatus(copyTestPlanDto.getStatus());
        testplan.setCreatedOn(OffsetDateTime.now());
        testplan.setModifiedOn(OffsetDateTime.now());
        testplan.setCreatedBy(copyTestPlanDto.getUserId().toString());
        testplan.setModifiedBy(copyTestPlanDto.getUserId().toString());
        testplan.setUserId(copyTestPlanDto.getUserId());
        return testplan;
    }
}
