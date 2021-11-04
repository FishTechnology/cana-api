package cana.codelessautomation.api.services.file.processors.mappers;

import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;
import cana.codelessautomation.api.services.file.repositories.daos.FileDao;

public interface FileProcessorMapper {
    FileDao mapFileDao(UpdateFileDto updateFileDto);
}
