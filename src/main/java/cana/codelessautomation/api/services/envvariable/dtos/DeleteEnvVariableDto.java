package cana.codelessautomation.api.services.envvariable.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import lombok.Data;

@Data
public class DeleteEnvVariableDto {
    private Long userid;
    private Long envVariableId;
    private EnvironmentVariableDao environmentVariableDao;
    private CustomDetailDao customDetailDao;
}
