package cana.codelessautomation.api.resources.globalvariable.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.DeleteGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.GetGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.UpdateGlobalVariableDto;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;

import java.util.List;

public interface GlobalVariableProcessor {
    List<GlobalVariableDao> processGetGlobalVariables(GetGlobalVariableDto getGlobalVariableDto);

    List<GlobalVariableDao> getGlobalVariables(GetGlobalVariableDto getGlobalVariableDto);

    List<ErrorMessageDto> processCreateGlobalVariable(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> createUIControlOptions(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> processDeleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto);

    List<ErrorMessageDto> deleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto);

    List<ErrorMessageDto> processUpdateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto);

    List<ErrorMessageDto> updateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto);
}
