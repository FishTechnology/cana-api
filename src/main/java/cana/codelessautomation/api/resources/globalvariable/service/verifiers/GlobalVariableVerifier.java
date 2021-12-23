package cana.codelessautomation.api.resources.globalvariable.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;

import java.util.List;

public interface GlobalVariableVerifier {
    List<ErrorMessageDto> verifyGlobalVariables(GetGlobalVariableDto getGlobalVariableDto);

    List<ErrorMessageDto> isUserIdValid(UpdateGlobalVariableDto updateGlobalVariableDto);

    List<ErrorMessageDto> isKeyValid(UpdateGlobalVariableDto updateGlobalVariableDto);

    List<ErrorMessageDto> isGlobalVariableIdValid(UpdateGlobalVariableDto updateGlobalVariableDto);

    List<ErrorMessageDto> isGlobalVariableIdValid(DeleteGlobalVariableDto deleteGlobalVariableDto);

    List<ErrorMessageDto> isGlobalVariableIdValid(GetGlobalVariableByIdDto getGlobalVariableByIdDto);

    List<ErrorMessageDto> isKeyValid(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> isUserIdValid(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> isUserIdValid(GetGlobalVariableDto getGlobalVariableDto);

    List<ErrorMessageDto> verifyCreateGlobalVariable(CreateGlobalVariableDto createGlobalVariable);

    List<ErrorMessageDto> verifyGetGlobalVariableById(GetGlobalVariableByIdDto getGlobalVariableByIdDto);

    List<ErrorMessageDto> verifyDeleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto);

    List<ErrorMessageDto>  verifyUpdateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto);
}
