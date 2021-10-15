package cana.codelessautomation.api.services.globalvariable.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.DeleteGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableByIdDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableDto;

import java.util.List;

public interface GlobalVariableVerifier {
    List<ErrorMessageDto> verifyGlobalVariables(GetGlobalVariableDto getGlobalVariableDto);

    List<ErrorMessageDto> isGlobalVariableIdValid(DeleteGlobalVariableDto deleteGlobalVariableDto);

    List<ErrorMessageDto> isGlobalVariableIdValid(GetGlobalVariableByIdDto getGlobalVariableByIdDto);

    List<ErrorMessageDto> isKeyValid(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> isUserIdValid(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> isUserIdValid(GetGlobalVariableDto getGlobalVariableDto);

    List<ErrorMessageDto> verifyCreateGlobalVariable(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> verifyGetGlobalVariableById(GetGlobalVariableByIdDto getGlobalVariableByIdDto);

    List<ErrorMessageDto> verifyDeleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto);
}
