package cana.codelessautomation.api.services.testcase.dtos;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestplanTestcaseGroupingDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import lombok.Data;

import java.util.List;

@Data
public class CheckTestCaseIsDeletableDto {
    private Long testCaseId;
    private TestplanDao testplan;
    private TestCaseDao testCase;
    private List<TestplanTestcaseGroupingDao> testplanTestcaseGroupings;
}
