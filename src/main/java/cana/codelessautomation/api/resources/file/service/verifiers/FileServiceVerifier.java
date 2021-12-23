package cana.codelessautomation.api.resources.file.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;

import java.util.List;

public interface FileServiceVerifier {
    List<ErrorMessageDto> verifyUpdateFile(UpdateFileDto updateFileDto);

    List<ErrorMessageDto> isUserIdValid(UpdateFileDto updateFileDto);
}
