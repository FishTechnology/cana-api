package cana.codelessautomation.api.resources.config.services.configservice.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import lombok.Data;

import java.util.List;

@Data
public class GetConfigsByAppIdDto {
    private Long applicationId;
    private ApplicationDao application;
    private List<ConfigDao> configDaos;
}
