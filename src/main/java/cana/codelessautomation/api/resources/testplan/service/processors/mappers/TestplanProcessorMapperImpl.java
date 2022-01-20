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
        TestplanDao testplan = getBaseTestplanDao();
        testplan.setName(createTestplan.getName());
        testplan.setComments(createTestplan.getComments());
        testplan.setStatus(createTestplan.getStatus());
        testplan.setCreatedBy(createTestplan.getCreatedBy());
        testplan.setModifiedBy(createTestplan.getModifiedBy());
        testplan.setApplicationId(createTestplan.getApplicationId());
        testplan.setUserId(createTestplan.getUserId());
        return testplan;
    }

    @Override
    public TestplanDao mapTestplanDao(CopyTestPlanDto copyTestPlanDto) {
        TestplanDao testplan = getBaseTestplanDao();
        testplan.setName(copyTestPlanDto.getTestPlanName());
        testplan.setComments(copyTestPlanDto.getComments());
        testplan.setStatus(copyTestPlanDto.getStatus());
        testplan.setCreatedBy(copyTestPlanDto.getUserId().toString());
        testplan.setModifiedBy(copyTestPlanDto.getUserId().toString());
        testplan.setUserId(copyTestPlanDto.getUserId());
        testplan.setApplicationId(copyTestPlanDto.getApplicationId());
        return testplan;
    }

    @Override
    public TestplanDao getBaseTestplanDao() {
        TestplanDao testplan = new TestplanDao();
        testplan.setCreatedOn(OffsetDateTime.now());
        testplan.setModifiedOn(OffsetDateTime.now());
        return testplan;
    }
}
