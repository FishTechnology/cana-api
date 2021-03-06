package cana.codelessautomation.api.resources.environment.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateEnvironmentDto {
    private Long id;
    @JMap
    private String name;
    @JMap
    private Long userId;
    @JMap
    private String comments;
    private CustomDetailDao customDetail;
    private Boolean isActive;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private EnvironmentDao environment;
}
