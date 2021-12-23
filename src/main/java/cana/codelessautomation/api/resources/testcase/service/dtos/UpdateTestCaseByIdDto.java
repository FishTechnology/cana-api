package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
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
