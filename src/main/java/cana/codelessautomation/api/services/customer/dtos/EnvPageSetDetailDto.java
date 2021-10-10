package cana.codelessautomation.api.services.customer.dtos;

import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import lombok.Data;

import java.util.List;

@Data
public class EnvPageSetDetailDto {
    private int pageNumber;
    private int pageSize;
    private Long totalPageCount;
    private List<EnvironmentVariableDao>  environmentVariables;
}
