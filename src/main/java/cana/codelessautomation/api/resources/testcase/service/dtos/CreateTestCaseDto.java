package cana.codelessautomation.api.resources.testcase.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateTestCaseDto {
    private Long id;
    private Long userId;
    private String name;
    private String comments;
    private Boolean isActive;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private CustomDetailDao customDetail;
}
