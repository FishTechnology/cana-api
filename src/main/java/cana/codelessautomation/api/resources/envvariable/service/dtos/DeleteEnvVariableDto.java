package cana.codelessautomation.api.resources.envvariable.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import lombok.Data;

@Data
public class DeleteEnvVariableDto {
    private Long userid;
    private Long envVariableId;
    private Long environmentId;
    private EnvironmentVariableDao environmentVariableDao;
    private CustomDetailDao customDetailDao;
    private EnvironmentDao environment;
}
