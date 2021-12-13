package cana.codelessautomation.api.services.application.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
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
