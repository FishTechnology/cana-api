package cana.codelessautomation.api.services.envvariable.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateEnvVariableDto {
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private Long userId;
    @JMap
    private String type;
    @JMap
    private Long environmentId;
    @JMap
    private String comments;
    private Boolean isActive;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private CustomDetailDao customDetailDao;
    private EnvironmentDao environment;
    private EnvironmentVariableDao environmentVariable;
}
