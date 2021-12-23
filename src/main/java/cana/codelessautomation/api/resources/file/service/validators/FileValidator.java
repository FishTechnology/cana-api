package cana.codelessautomation.api.resources.file.service.validators;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;

import java.util.List;

public interface FileValidator {
    List<ErrorMessageDto> validUpdateFile(UpdateFileDto updateFileDto);

    List<ErrorMessageDto> isFileStreamValid(UpdateFileDto updateFileDto);
}
