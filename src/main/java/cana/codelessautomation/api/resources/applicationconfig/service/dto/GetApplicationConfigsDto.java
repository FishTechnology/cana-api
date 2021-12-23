package cana.codelessautomation.api.resources.applicationconfig.service.dto;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import lombok.Data;

@Data
public class GetApplicationConfigsDto {
    private Long applicationId;
    private Long userId;
    private ApplicationDao applicationDao;
}
