package cana.codelessautomation.api.resources.globalvariable.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;
import cana.codelessautomation.api.resources.globalvariable.service.processors.GlobalVariableProcessor;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;
import cana.codelessautomation.api.resources.globalvariable.service.verifiers.GlobalVariableVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GlobalVariableServiceImpl implements GlobalVariableService {

    @Inject
    GlobalVariableVerifier globalVariableVerifier;

    @Inject
    GlobalVariableProcessor globalVariableProcessor;

    @Override
    public List<GlobalVariableDao> getGlobalVariables(GetGlobalVariableDto getGlobalVariableDto) throws ValidationException {
        var errorMessages = globalVariableVerifier.verifyGlobalVariables(getGlobalVariableDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return globalVariableProcessor.processGetGlobalVariables(getGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariable) throws ValidationException {
        createGlobalVariable.setCreatedOn(OffsetDateTime.now());
        createGlobalVariable.setModifiedOn(OffsetDateTime.now());
        createGlobalVariable.setCreatedBy(createGlobalVariable.getUserId().toString());
        createGlobalVariable.setModifiedBy(createGlobalVariable.getUserId().toString());
        createGlobalVariable.setIsActive(true);

        var errorMessages = globalVariableVerifier.verifyCreateGlobalVariable(createGlobalVariable);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return globalVariableProcessor.processCreateGlobalVariable(createGlobalVariable);
    }

    @Override
    public List<ErrorMessageDto> getGlobalVariableById(GetGlobalVariableByIdDto getGlobalVariableByIdDto) throws ValidationException {
        var errorMessages = globalVariableVerifier.verifyGetGlobalVariableById(getGlobalVariableByIdDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> deleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto) throws ValidationException {
        var errorMessages = globalVariableVerifier.verifyDeleteGlobalVariable(deleteGlobalVariableDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return globalVariableProcessor.processDeleteGlobalVariable(deleteGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> updateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto) {
        updateGlobalVariableDto.setCreatedOn(OffsetDateTime.now());
        updateGlobalVariableDto.setModifiedOn(OffsetDateTime.now());
        updateGlobalVariableDto.setCreatedBy(updateGlobalVariableDto.getUserId().toString());
        updateGlobalVariableDto.setModifiedBy(updateGlobalVariableDto.getUserId().toString());

        var errorMessages = globalVariableVerifier.verifyUpdateGlobalVariable(updateGlobalVariableDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return globalVariableProcessor.processUpdateGlobalVariable(updateGlobalVariableDto);
    }
}
