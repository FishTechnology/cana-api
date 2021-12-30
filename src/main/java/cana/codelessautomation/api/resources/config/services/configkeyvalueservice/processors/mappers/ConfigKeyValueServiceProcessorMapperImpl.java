package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors.mappers;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ConfigKeyValueServiceProcessorMapperImpl implements ConfigKeyValueServiceProcessorMapper {
    @Override
    public ConfigKeyValueDao mapConfigKeyValueDao(CreateConfigKeyValueDto createConfigKeyValueDto) {
        ConfigKeyValueDao configKeyValueDao = new ConfigKeyValueDao();
        configKeyValueDao.setKey(createConfigKeyValueDto.getKey());
        configKeyValueDao.setValue(createConfigKeyValueDto.getValue());
        configKeyValueDao.setUserId(createConfigKeyValueDto.getUserId());
        configKeyValueDao.setConfigId(createConfigKeyValueDto.getConfigId());
        configKeyValueDao.setType(createConfigKeyValueDto.getType());
        configKeyValueDao.setFileId(configKeyValueDao.getFileId());
        configKeyValueDao.setIsActive(true);
        configKeyValueDao.setComments(createConfigKeyValueDto.getComments());
        configKeyValueDao.setCreatedBy(createConfigKeyValueDto.getUserId().toString());
        configKeyValueDao.setModifiedBy(createConfigKeyValueDto.getUserId().toString());
        configKeyValueDao.setCreatedOn(OffsetDateTime.now());
        configKeyValueDao.setModifiedOn(OffsetDateTime.now());
        return configKeyValueDao;
    }
}
