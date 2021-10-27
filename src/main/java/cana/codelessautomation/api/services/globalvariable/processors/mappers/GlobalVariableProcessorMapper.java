package cana.codelessautomation.api.services.globalvariable.processors.mappers;

import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.UpdateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;

public interface GlobalVariableProcessorMapper {
    GlobalVariableDao mapGlobalVariableDao(CreateGlobalVariableDto createGlobalVariable);

    GlobalVariableDao mapGlobalVariableDao(UpdateGlobalVariableDto updateGlobalVariableDto);
}
