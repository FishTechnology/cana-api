package cana.codelessautomation.api.services.file;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;

import java.util.List;

public interface FileService {
    List<ErrorMessageDto> updateFile(UpdateFileDto updateFileDto);
}
