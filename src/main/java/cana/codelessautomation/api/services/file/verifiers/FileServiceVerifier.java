package cana.codelessautomation.api.services.file.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;

import java.util.List;

public interface FileServiceVerifier {
    List<ErrorMessageDto> verifyUpdateFile(UpdateFileDto updateFileDto);

    List<ErrorMessageDto> isUserIdValid(UpdateFileDto updateFileDto);
}
