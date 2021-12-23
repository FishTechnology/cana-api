package cana.codelessautomation.api.resources.schedule.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.enums.ActionResultStatusDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultStatusDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultStatusDao;
import cana.codelessautomation.api.resources.schedule.service.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestplanTestcaseGroupingDaoEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ResultMapperImpl implements ResultMapper {
    @Override
    public TestPlanResultDao mapTestPlanResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        TestPlanResultDao testPlanResultDao = new TestPlanResultDao();
        testPlanResultDao.setTestPlanId(copyTestPlanDetailDto.getTestPlanSummary().getId());
        testPlanResultDao.setCreatedOn(copyTestPlanDetailDto.getCreatedOn());
        testPlanResultDao.setModifiedOn(copyTestPlanDetailDto.getModifiedOn());
        testPlanResultDao.setStatus(TestPlanResultStatusDao.NOT_STARTED);
        testPlanResultDao.setScheduleIterationId(copyTestPlanDetailDto.getScheduleIteration().getId());
        return testPlanResultDao;
    }

    @Override
    public TestCaseResultDao mapTestCaseResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto, TestplanTestcaseGroupingDaoEntity testplanTestcaseGroupings) {
        TestCaseResultDao testCaseResultDao = new TestCaseResultDao();
        testCaseResultDao.setTestCaseId(testplanTestcaseGroupings.getTestCaseId());
        testCaseResultDao.setCreatedOn(copyTestPlanDetailDto.getCreatedOn());
        testCaseResultDao.setModifiedOn(copyTestPlanDetailDto.getModifiedOn());
        testCaseResultDao.setStatus(TestCaseResultStatusDao.NOT_STARTED);
        testCaseResultDao.setTestplanResultId(copyTestPlanDetailDto.getTestPlanResultId());
        testCaseResultDao.setExecutionOrder(testplanTestcaseGroupings.getExecutionOrder());
        return testCaseResultDao;
    }

    @Override
    public ActionResultDao mapActionResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto,
                                              TestCaseResultDao testCaseResultDao,
                                              ActionDaoEntity actionDaoEntity) {
        ActionResultDao actionResultDao = new ActionResultDao();
        actionResultDao.setCreatedOn(copyTestPlanDetailDto.getCreatedOn());
        actionResultDao.setModifiedOn(copyTestPlanDetailDto.getModifiedOn());
        actionResultDao.setTestcaseResultId(testCaseResultDao.getId());
        actionResultDao.setActionId(actionDaoEntity.getId());
        actionResultDao.setStatus(ActionResultStatusDao.NOT_STARTED);
        actionResultDao.setExecutionOrder(actionDaoEntity.getOrderNumber());
        return actionResultDao;
    }
}
