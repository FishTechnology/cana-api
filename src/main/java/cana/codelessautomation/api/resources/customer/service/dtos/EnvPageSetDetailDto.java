package cana.codelessautomation.api.resources.customer.service.dtos;

import cana.codelessautomation.api.resources.envvariable.service.repositories.daos.EnvironmentVariableDao;
import lombok.Data;

import java.util.List;

@Data
public class EnvPageSetDetailDto {
    private int pageNumber;
    private int pageSize;
    private Long totalPageCount;
    private List<EnvironmentVariableDao> environmentVariables;
}
