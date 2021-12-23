package cana.codelessautomation.api.resources.environment.service.dtos;

import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import lombok.Data;

@Data
public class DeleteEnvironmentDto {
    private Long environmentId;
    private EnvironmentDao environment;
}
