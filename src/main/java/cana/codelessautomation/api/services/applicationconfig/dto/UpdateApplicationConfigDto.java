package cana.codelessautomation.api.services.applicationconfig.dto;

import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateApplicationConfigDto {
    private UUID id;
    private String key;
    private String value;
    private String comments;
    private String userId;
    private CustomDetailDao customDetailDao;
    private ApplicationConfigDao applicationConfigDao;
}
