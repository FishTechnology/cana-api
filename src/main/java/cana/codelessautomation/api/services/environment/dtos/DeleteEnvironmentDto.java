package cana.codelessautomation.api.services.environment.dtos;

import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import lombok.Data;

@Data
public class DeleteEnvironmentDto {
    private Long environmentId;
    private EnvironmentDao environment;
}
