package cana.codelessautomation.api.services.file;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;
import cana.codelessautomation.api.services.file.processors.FileProcessor;
import cana.codelessautomation.api.services.file.validators.FileValidator;
import cana.codelessautomation.api.services.file.verifiers.FileServiceVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;

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
