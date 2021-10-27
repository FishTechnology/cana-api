package cana.codelessautomation.api.services.testcase.dtos;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import lombok.Data;

@Data
public class GetTestCaseByIdDto {
    private Long testCaseId;
    private TestCaseDao testCase;
}
