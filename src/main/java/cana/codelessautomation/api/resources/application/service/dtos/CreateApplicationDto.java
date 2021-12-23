package cana.codelessautomation.api.resources.application.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class CreateApplicationDto {
    private Long id;
    private String name;
    private Long userId;
    private Boolean isActive;
    private String createdBy;
    private String modifiedBy;
    private String comments;
    private CustomDetailDao customDetailDao;
}
