package cana.codelessautomation.api.services.file.processors.mappers;

import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;
import cana.codelessautomation.api.services.file.repositories.daos.FileDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class FileProcessorMapperImpl implements FileProcessorMapper {
    @Override
    public FileDao mapFileDao(UpdateFileDto updateFileDto) {
        FileDao fileDao = new FileDao();
        fileDao.setFileSize(updateFileDto.getFileSize());
        fileDao.setFileName(updateFileDto.getFileName());
        fileDao.setContent(updateFileDto.getContent());
        fileDao.setCreatedBy(updateFileDto.getCreatedBy());
        fileDao.setCreatedOn(OffsetDateTime.now());
        fileDao.setIsActive(updateFileDto.getIsActive());
        fileDao.setModifiedBy(updateFileDto.getModifiedBy());
        fileDao.setModifiedOn(OffsetDateTime.now());
        return fileDao;
    }
}
