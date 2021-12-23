package cana.codelessautomation.api.resources.file.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;

import java.util.List;

public interface FileService {
    List<ErrorMessageDto> updateFile(UpdateFileDto updateFileDto);
}
