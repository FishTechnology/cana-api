package cana.codelessautomation.api.services.testcase.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateTestCaseByTestPlanIdDto {
    private Long id;
    private Long testPlanId;
    private Long userId;
    private String name;
    private String comments;
    private Boolean isActive;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private CustomDetailDao customDetail;
    private TestplanDao testplan;
    private Long testPlanTestCaseGroupingId;
}
