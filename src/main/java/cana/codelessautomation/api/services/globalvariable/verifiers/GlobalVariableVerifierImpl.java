package cana.codelessautomation.api.services.globalvariable.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.DeleteGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableByIdDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.errorcodes.GlobalVariableErrorCode;
import cana.codelessautomation.api.services.globalvariable.repositories.GlobalVariableRepository;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GlobalVariableVerifierImpl implements GlobalVariableVerifier {

    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    GlobalVariableRepository globalVariableRepository;

    @Inject
    GlobalVariableErrorCode globalVariableErrorCode;

    @Override
    public List<ErrorMessageDto> verifyGlobalVariables(GetGlobalVariableDto getGlobalVariableDto) {
        return isUserIdValid(getGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> verifyCreateGlobalVariable(CreateGlobalVariableDto createGlobalVariable) {
        var errors = isUserIdValid(createGlobalVariable);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isKeyValid(createGlobalVariable);
    }

    @Override
    public List<ErrorMessageDto> verifyGetGlobalVariableById(GetGlobalVariableByIdDto getGlobalVariableByIdDto) {
        return isGlobalVariableIdValid(getGlobalVariableByIdDto);
    }

    @Override
    public List<ErrorMessageDto> verifyDeleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto) {
        return isGlobalVariableIdValid(deleteGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> isGlobalVariableIdValid(DeleteGlobalVariableDto deleteGlobalVariableDto) {
        var globalVariables = globalVariableRepository.findByIdAndIsActive(deleteGlobalVariableDto.getGlobalVariableId());
        if (globalVariables == null) {
            return CanaUtility.getErrorMessages(globalVariableErrorCode.getGlobalVariableIdNotFound());
        }
        deleteGlobalVariableDto.setGlobalVariable(globalVariables);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isGlobalVariableIdValid(GetGlobalVariableByIdDto getGlobalVariableByIdDto) {
        var globalVariables = globalVariableRepository.findByIdAndIsActive(getGlobalVariableByIdDto.getGlobalVariableId());
        if (globalVariables == null) {
            return CanaUtility.getErrorMessages(globalVariableErrorCode.getGlobalVariableIdNotFound());
        }
        getGlobalVariableByIdDto.setGlobalVariable(globalVariables);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isKeyValid(CreateGlobalVariableDto createGlobalVariable) {
        var globalVariables = globalVariableRepository.findByKeyAndUserId(createGlobalVariable.getKey(), createGlobalVariable.getUserId());
        if (CollectionUtils.isNotEmpty(globalVariables)) {
            createGlobalVariable.setGlobalVariables(globalVariables);
            return CanaUtility.getErrorMessages(globalVariableErrorCode.getDuplicateKey());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(CreateGlobalVariableDto createGlobalVariable) {
        var response = customerServiceVerifier.isUserIdValid(createGlobalVariable.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        createGlobalVariable.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(GetGlobalVariableDto getGlobalVariableDto) {
        var response = customerServiceVerifier.isUserIdValid(getGlobalVariableDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        getGlobalVariableDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }
}
