package cana.codelessautomation.api.resources.config.service.dtos;

import cana.codelessautomation.api.resources.config.service.repositories.daos.ConfigTypeDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class CreateConfigDto {
    private Long id;
    private String name;
    private ConfigTypeDao type;
    private Long userId;
    private CustomDetailDao customDetailDao;
}
