package cana.codelessautomation.api.resources.schedule.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.resources.schedule.service.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestplanTestcaseGroupingDaoEntity;

public interface ResultMapper {
    TestPlanResultDao mapTestPlanResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto);

    TestCaseResultDao mapTestCaseResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto, TestplanTestcaseGroupingDaoEntity testplanTestcaseGroupings);

    ActionResultDao mapActionResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto, TestCaseResultDao testCaseResultDao, ActionDaoEntity actionDaoEntity);
}
