package cana.codelessautomation.api.resources.file.service.processors.mappers;

import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;
import cana.codelessautomation.api.resources.file.service.repositories.daos.FileDao;

public interface FileProcessorMapper {
    FileDao mapFileDao(UpdateFileDto updateFileDto);
}
