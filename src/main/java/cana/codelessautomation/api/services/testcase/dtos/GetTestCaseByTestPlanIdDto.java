package cana.codelessautomation.api.services.testcase.dtos;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import lombok.Data;

import java.util.List;

@Data
public class GetTestCaseByTestPlanIdDto {
    private Long testPlanId;
    private TestplanDao testplanDao;
    private List<TestplanTestcaseGroupingDao> testplanTestcaseGroupingDaos;
    private List<TestCaseDao> testCaseDaos;
}
