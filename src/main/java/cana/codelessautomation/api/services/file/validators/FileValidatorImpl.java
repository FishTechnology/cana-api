package cana.codelessautomation.api.services.file.validators;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;
import cana.codelessautomation.api.services.file.errorcode.FileErrorCode;
import cana.codelessautomation.api.services.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class FileValidatorImpl implements FileValidator {

    @Inject
    FileErrorCode fileErrorCode;

    @Override
    public List<ErrorMessageDto> validUpdateFile(UpdateFileDto updateFileDto) {
        return isFileStreamValid(updateFileDto);
    }

    @Override
    public List<ErrorMessageDto> isFileStreamValid(UpdateFileDto updateFileDto) {
        String fileContent = null;
        try {
            fileContent = new String(updateFileDto.getFile().readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return CanaUtility.getErrorMessages(fileErrorCode.getFileStreamContentInValid());
        }
        updateFileDto.setContent(fileContent);
        return Collections.emptyList();
    }
}
