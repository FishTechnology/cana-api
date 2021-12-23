package cana.codelessautomation.api.resources.file.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;
import cana.codelessautomation.api.resources.file.service.processors.mappers.FileProcessorMapper;
import cana.codelessautomation.api.resources.file.service.repositories.FileRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class FileProcessorImpl implements FileProcessor {

    @Inject
    FileRepository fileRepository;

    @Inject
    FileProcessorMapper fileProcessorMapper;

    @Override
    public List<ErrorMessageDto> processUpdateFile(UpdateFileDto updateFileDto) {
        return updateFile(updateFileDto);
    }

    @Override
    public List<ErrorMessageDto> updateFile(UpdateFileDto updateFileDto) {
        var fileDao = fileProcessorMapper.mapFileDao(updateFileDto);
        fileRepository.persist(fileDao);
        updateFileDto.setId(fileDao.getId());
        return Collections.emptyList();
    }
}
