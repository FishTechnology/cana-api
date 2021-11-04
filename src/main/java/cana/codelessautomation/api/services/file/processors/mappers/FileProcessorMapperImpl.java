package cana.codelessautomation.api.services.file.processors.mappers;

import cana.codelessautomation.api.services.file.dtos.UpdateFileDto;
import cana.codelessautomation.api.services.file.repositories.daos.FileDao;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FileProcessorMapperImpl implements FileProcessorMapper {
    @Override
    public FileDao mapFileDao(UpdateFileDto updateFileDto) {
        FileDao fileDao = new FileDao();
        fileDao.setFileSize(updateFileDto.getFileSize());
        fileDao.setFileName(updateFileDto.getFileName());
        fileDao.setContent(updateFileDto.getContent());
        fileDao.setCreatedBy(updateFileDto.getCreatedBy());
        fileDao.setCreatedOn(updateFileDto.getCreatedOn());
        fileDao.setIsActive(updateFileDto.getIsActive());
        fileDao.setModifiedBy(updateFileDto.getModifiedBy());
        fileDao.setModifiedOn(updateFileDto.getModifiedOn());
        return fileDao;
    }
}
