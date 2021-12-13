package cana.codelessautomation.api.services.application.dtos;

import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class UpdateApplicationDto {
    private Long applicationId;
    private String modifiedBy;
    private String name;
    private String comments;
    private Long userId;
    private CustomDetailDao customDetailDao;
    private ApplicationDao applicationDao;
}
