package cana.codelessautomation.api.resources.config.service.dtos;

import cana.codelessautomation.api.resources.config.service.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

import java.util.List;

@Data
public class GetConfigsByUserIdDto {
    private Long userId;
    private CustomDetailDao customDetailDao;
    private List<ConfigDao> configDaos;
}
