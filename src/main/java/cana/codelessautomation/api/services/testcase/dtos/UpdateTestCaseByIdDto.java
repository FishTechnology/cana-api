package cana.codelessautomation.api.services.testcase.dtos;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateTestCaseByIdDto {
    private Long testCaseId;
    private Long userId;
    private String name;
    private String comments;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private TestCaseDao testCase;
}
