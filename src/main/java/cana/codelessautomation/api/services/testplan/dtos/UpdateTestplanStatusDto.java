package cana.codelessautomation.api.services.testplan.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.environment.repositories.daos.TestPlanStatus;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateTestplanStatusDto {
    private Long userId;
    private TestPlanStatus status;
    private Long testplanId;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private TestplanDao testplan;
    private CustomDetailDao customDetail;
}
