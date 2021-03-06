package cana.codelessautomation.api.resources.schedule.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultStatus;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.enums.ActionResultStatusDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultStatusDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultStatusDao;
import cana.codelessautomation.api.resources.schedule.service.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestplanTestcaseGroupingDaoEntity;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ResultMapperImpl implements ResultMapper {
    @Override
    public TestPlanResultDao mapTestPlanResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        TestPlanResultDao testPlanResultDao = new TestPlanResultDao();
        testPlanResultDao.setTestPlanId(copyTestPlanDetailDto.getTestPlanSummary().getId());
        testPlanResultDao.setCreatedOn(OffsetDateTime.now());
        testPlanResultDao.setModifiedOn(OffsetDateTime.now());
        testPlanResultDao.setStatus(TestPlanResultStatusDao.NOT_STARTED);
        testPlanResultDao.setScheduleIterationId(copyTestPlanDetailDto.getScheduleIteration().getId());
        return testPlanResultDao;
    }

    @Override
    public TestCaseResultDao mapTestCaseResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto, TestplanTestcaseGroupingDaoEntity testplanTestcaseGroupings) {
        TestCaseResultDao testCaseResultDao = new TestCaseResultDao();
        testCaseResultDao.setTestCaseId(testplanTestcaseGroupings.getTestCaseId());
        testCaseResultDao.setCreatedOn(OffsetDateTime.now());
        testCaseResultDao.setModifiedOn(OffsetDateTime.now());
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
        actionResultDao.setCreatedOn(OffsetDateTime.now());
        actionResultDao.setModifiedOn(OffsetDateTime.now());
        actionResultDao.setTestcaseResultId(testCaseResultDao.getId());
        actionResultDao.setActionId(actionDaoEntity.getId());
        actionResultDao.setStatus(ActionResultStatusDao.NOT_STARTED);
        actionResultDao.setExecutionOrder(actionDaoEntity.getOrderNumber());
        return actionResultDao;
    }

    @Override
    public ActionOptionResultDao mapActionOptionResultDao(ActionOptionDao actionOptionDao, ActionResultDao actionResultDao) {
        ActionOptionResultDao actionOptionResultDao = new ActionOptionResultDao();
        actionOptionResultDao.setStatus(ActionOptionResultStatus.NOT_STARTED);
        actionOptionResultDao.setCreatedOn(OffsetDateTime.now());
        actionOptionResultDao.setModifiedOn(OffsetDateTime.now());
        actionOptionResultDao.setExecutionOrder(actionOptionDao.getOrderNumber());
        actionOptionResultDao.setActionResultId(actionResultDao.getId());
        actionOptionResultDao.setActionOptionId(actionOptionDao.getId());
        return actionOptionResultDao;
    }
}
