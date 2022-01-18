package cana.codelessautomation.api.resources.system.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import lombok.Data;

import java.util.List;

@Data
public class GetSystemConfigsByAppIdDto {
    private Long applicationId;
    private ApplicationDao applicationDao;
    private List<cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao> SystemConfigDao;
}
