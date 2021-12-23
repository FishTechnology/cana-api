package cana.codelessautomation.api.resources.globalvariable.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class GetGlobalVariableDto {
    private Long userId;
    private CustomDetailDao customDetail;
}
