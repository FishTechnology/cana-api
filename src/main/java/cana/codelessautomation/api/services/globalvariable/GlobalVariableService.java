package cana.codelessautomation.api.services.globalvariable;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.*;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;

import java.util.List;

public interface GlobalVariableService {
    List<GlobalVariableDao> getGlobalVariables(GetGlobalVariableDto getGlobalVariableDto) throws ValidationException;

    List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariableDto) throws ValidationException;

    List<ErrorMessageDto> getGlobalVariableById(GetGlobalVariableByIdDto getGlobalVariableByIdDto) throws ValidationException;

    List<ErrorMessageDto> deleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto) throws ValidationException;

    List<ErrorMessageDto> updateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto);
}
