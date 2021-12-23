package cana.codelessautomation.api.resources.globalvariable.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;
import cana.codelessautomation.api.resources.globalvariable.service.errorcodes.GlobalVariableErrorCode;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.GlobalVariableRepository;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
    public List<ErrorMessageDto> verifyUpdateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto) {
        var errors = isGlobalVariableIdValid(updateGlobalVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isKeyValid(updateGlobalVariableDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isUserIdValid(updateGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(UpdateGlobalVariableDto updateGlobalVariableDto) {
        var response = customerServiceVerifier.isUserIdValid(updateGlobalVariableDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        updateGlobalVariableDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isKeyValid(UpdateGlobalVariableDto updateGlobalVariableDto) {
        if (StringUtils.equalsAnyIgnoreCase(updateGlobalVariableDto.getGlobalVariable().getKey(), updateGlobalVariableDto.getKey())) {
            return Collections.emptyList();
        }
        var globalVariables = globalVariableRepository.findByKeyAndUserId(updateGlobalVariableDto.getKey(), updateGlobalVariableDto.getUserId());
        if (CollectionUtils.isNotEmpty(globalVariables)) {
            updateGlobalVariableDto.setGlobalVariables(globalVariables);
            return CanaUtility.getErrorMessages(globalVariableErrorCode.getDuplicateKey());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isGlobalVariableIdValid(UpdateGlobalVariableDto updateGlobalVariableDto) {
        var globalVariables = globalVariableRepository.findByIdAndIsActive(updateGlobalVariableDto.getGlobalVariableId());
        if (globalVariables == null) {
            return CanaUtility.getErrorMessages(globalVariableErrorCode.getGlobalVariableIdNotFound());
        }
        updateGlobalVariableDto.setGlobalVariable(globalVariables);
        return Collections.emptyList();
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
