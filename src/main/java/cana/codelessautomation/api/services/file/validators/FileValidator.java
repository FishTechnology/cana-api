package cana.codelessautomation.api.services.file.validators;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;

import java.util.List;

public interface FileValidator {
    List<ErrorMessageDto> validUpdateFile(UpdateFileDto updateFileDto);

    List<ErrorMessageDto> isFileStreamValid(UpdateFileDto updateFileDto);
}
