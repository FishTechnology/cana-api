package cana.codelessautomation.api.resources.globalvariable.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.UIControlOptionDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.UpdateGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;

public interface GlobalVariableProcessorMapper {
    GlobalVariableDao mapGlobalVariableDao(CreateGlobalVariableDto createGlobalVariable);

    GlobalVariableDao mapGlobalVariableDao(UpdateGlobalVariableDto updateGlobalVariableDto);

    ActionOptionDao mapActionOptionDao(CreateGlobalVariableDto createGlobalVariable, UIControlOptionDto uiControlOptionDto);
}
