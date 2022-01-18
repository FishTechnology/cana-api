package cana.codelessautomation.api.resources.config.services.configservice.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

import java.util.List;

@Data
public class CreateConfigDto {
    private Long id;
    private String name;
    private ConfigTypeDao type;
    private Long userId;
    private String comments;
    private Long identifier;
    private Long applicationId;
    private ApplicationDao application;
    private CustomDetailDao customDetailDao;
    private List<ConfigDao> configDaos;
}
