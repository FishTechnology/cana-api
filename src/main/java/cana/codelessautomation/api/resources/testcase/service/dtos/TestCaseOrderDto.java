package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import lombok.Data;

@Data
public class TestCaseOrderDto {
    private Long testCaseId;
    private Long oldExecutionOrder;
    private Long currentExecutionOrder;
    private TestCaseDao testCase;
    private TestplanTestcaseGroupingDao testplanTestcaseGrouping;
}
