package cana.codelessautomation.api.resources.config.services.configservice.dtos;

import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

import java.util.List;

@Data
public class GetConfigNameKeyValueDto {
    private Long userId;
    private CustomDetailDao customDetailDao;
    private List<ConfigDao> configDaos;
}
