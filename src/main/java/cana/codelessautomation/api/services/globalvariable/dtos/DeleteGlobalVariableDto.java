package cana.codelessautomation.api.services.globalvariable.dtos;

import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import lombok.Data;

@Data
public class DeleteGlobalVariableDto {
    private Long globalVariableId;
    private GlobalVariableDao globalVariable;
}
