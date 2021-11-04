package cana.codelessautomation.api.services.file.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;

import java.util.List;

public interface FileProcessor {
    List<ErrorMessageDto> processUpdateFile(UpdateFileDto updateFileDto);

    List<ErrorMessageDto> updateFile(UpdateFileDto updateFileDto);
}
