package cana.codelessautomation.api.services.envvariable.dtos;

import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import lombok.Data;

@Data
public class GetEnvVariableByIdDto {
    private Long environmentId;
    private Long envVariableId;
    private EnvironmentVariableDao environmentVariable;
    private EnvironmentDao environment;
}
