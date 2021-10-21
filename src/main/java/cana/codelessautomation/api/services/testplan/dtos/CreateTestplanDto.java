package cana.codelessautomation.api.services.testplan.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.environment.repositories.daos.TestPlanStatus;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateTestplanDto {
    private Long id;
    @JMap
    private Long userId;
    @JMap
    private String name;
    @JMap
    private String comments;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private TestPlanStatus status;
    private CustomDetailDao customDetail;
}
