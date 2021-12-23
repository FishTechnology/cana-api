package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import lombok.Data;

import java.util.List;

@Data
public class GetTestCaseByTestPlanIdDto {
    private Long testPlanId;
    private TestplanDao testplanDao;
    private List<TestplanTestcaseGroupingDao> testplanTestcaseGroupingDaos;
    private List<TestCaseDao> testCaseDaos;
}
