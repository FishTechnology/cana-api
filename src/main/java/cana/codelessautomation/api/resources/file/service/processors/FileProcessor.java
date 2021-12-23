package cana.codelessautomation.api.resources.file.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;

import java.util.List;

public interface FileProcessor {
    List<ErrorMessageDto> processUpdateFile(UpdateFileDto updateFileDto);

    List<ErrorMessageDto> updateFile(UpdateFileDto updateFileDto);
}
