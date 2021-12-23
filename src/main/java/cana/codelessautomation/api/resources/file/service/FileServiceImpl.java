package cana.codelessautomation.api.resources.file.service;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;
import cana.codelessautomation.api.resources.file.service.processors.FileProcessor;
import cana.codelessautomation.api.resources.file.service.validators.FileValidator;
import cana.codelessautomation.api.resources.file.service.verifiers.FileServiceVerifier;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class FileServiceImpl implements FileService {
    @Inject
    FileServiceVerifier fileServiceVerifier;

    @Inject
    FileProcessor fileProcessor;

    @Inject
    FileValidator fileValidator;

    @Override
    public List<ErrorMessageDto> updateFile(UpdateFileDto updateFileDto) {
        updateFileDto.setCreatedOn(OffsetDateTime.now());
        updateFileDto.setModifiedOn(OffsetDateTime.now());
        updateFileDto.setCreatedBy(updateFileDto.getUserId().toString());
        updateFileDto.setModifiedBy(updateFileDto.getUserId().toString());
        updateFileDto.setIsActive(true);

        var errorMessages = fileValidator.validUpdateFile(updateFileDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }

        errorMessages = fileServiceVerifier.verifyUpdateFile(updateFileDto);
        if (!errorMessages.isEmpty()) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errorMessages));
        }
        return fileProcessor.processUpdateFile(updateFileDto);
    }
}
