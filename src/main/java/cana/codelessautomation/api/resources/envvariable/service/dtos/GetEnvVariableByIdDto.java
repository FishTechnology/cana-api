package cana.codelessautomation.api.resources.envvariable.service.dtos;

import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import lombok.Data;

@Data
public class GetEnvVariableByIdDto {
    private Long environmentId;
    private Long envVariableId;
    private EnvironmentVariableDao environmentVariable;
    private EnvironmentDao environment;
}
