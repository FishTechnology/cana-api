package cana.codelessautomation.api.services.globalvariable.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import lombok.Data;

@Data
public class GetGlobalVariableDto {
    private Long userId;
    private CustomDetailDao customDetail;
}
