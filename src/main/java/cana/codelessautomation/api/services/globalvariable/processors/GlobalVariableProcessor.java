package cana.codelessautomation.api.services.globalvariable.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.DeleteGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;

import java.util.List;

public interface GlobalVariableProcessor {
    List<GlobalVariableDao> processGetGlobalVariables(GetGlobalVariableDto getGlobalVariableDto);

    List<GlobalVariableDao> getGlobalVariables(GetGlobalVariableDto getGlobalVariableDto);

    List<ErrorMessageDto> processCreateGlobalVariable(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> processDeleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto);

    List<ErrorMessageDto> deleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto);
}
