package cana.codelessautomation.api.services.schedule.processors.mappers;

import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.services.schedule.dtos.CopyTestPlanDetailDto;
import cana.codelessautomation.api.services.testcase.repositories.daos.entities.TestplanTestcaseGroupingDaoEntity;

public interface ResultMapper {
    TestPlanResultDao mapTestPlanResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto);

    TestCaseResultDao mapTestCaseResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto, TestplanTestcaseGroupingDaoEntity testplanTestcaseGroupings);

    ActionResultDao mapActionResultDao(CopyTestPlanDetailDto copyTestPlanDetailDto, TestCaseResultDao testCaseResultDao, ActionDaoEntity actionDaoEntity);
}
