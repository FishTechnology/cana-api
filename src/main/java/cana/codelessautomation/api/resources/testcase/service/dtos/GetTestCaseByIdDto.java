package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import lombok.Data;

@Data
public class GetTestCaseByIdDto {
    private Long testCaseId;
    private TestCaseDao testCase;
}
