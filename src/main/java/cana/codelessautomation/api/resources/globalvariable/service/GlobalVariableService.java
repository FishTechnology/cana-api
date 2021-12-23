package cana.codelessautomation.api.resources.globalvariable.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;

import java.util.List;

public interface GlobalVariableService {
    List<GlobalVariableDao> getGlobalVariables(GetGlobalVariableDto getGlobalVariableDto) throws ValidationException;

    List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariableDto) throws ValidationException;

    List<ErrorMessageDto> getGlobalVariableById(GetGlobalVariableByIdDto getGlobalVariableByIdDto) throws ValidationException;

    List<ErrorMessageDto> deleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto) throws ValidationException;

    List<ErrorMessageDto> updateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto);
}
