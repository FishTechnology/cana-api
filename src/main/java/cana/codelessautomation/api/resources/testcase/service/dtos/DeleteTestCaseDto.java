package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import lombok.Data;

@Data
public class DeleteTestCaseDto {
    private Long applicationId;
    private Long testCaseId;
    private Long testPlanId;
    private ApplicationDao application;
    private TestplanDao testplan;
    private TestCaseDao testCase;
    private TestplanTestcaseGroupingDao testplanTestcaseGroupingDao;
}
