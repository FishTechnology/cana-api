package cana.codelessautomation.api.resources.globalvariable.service.dtos;

import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;
import lombok.Data;

@Data
public class DeleteGlobalVariableDto {
    private Long globalVariableId;
    private GlobalVariableDao globalVariable;
}
