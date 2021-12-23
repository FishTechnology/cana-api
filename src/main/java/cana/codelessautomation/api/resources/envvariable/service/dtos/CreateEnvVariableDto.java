package cana.codelessautomation.api.resources.envvariable.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableType;
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
    private EnvironmentVariableType type;
    @JMap
    private Long environmentId;
    @JMap
    private String comments;
    @JMap
    private String content;
    private Boolean isActive;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private CustomDetailDao customDetailDao;
    private EnvironmentDao environment;
    private EnvironmentVariableDao environmentVariable;
}
