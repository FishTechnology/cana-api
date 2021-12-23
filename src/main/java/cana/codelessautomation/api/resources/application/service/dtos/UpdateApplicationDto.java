package cana.codelessautomation.api.resources.application.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
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
