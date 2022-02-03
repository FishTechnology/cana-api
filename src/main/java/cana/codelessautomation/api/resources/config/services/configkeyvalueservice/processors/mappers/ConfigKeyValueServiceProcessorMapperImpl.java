package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.processors.mappers;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.dtos.CreateConfigKeyValueDto;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueType;

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
        configKeyValueDao.setIsApplicationVariable(createConfigKeyValueDto.getIsApplicationVariable());
        return configKeyValueDao;
    }

    @Override
    public ConfigKeyValueDao mapConfigKeyValueDao(Long configId, String key, String value, ConfigKeyValueType type, Long userId) {
        ConfigKeyValueDao configKeyValueDao = new ConfigKeyValueDao();
        configKeyValueDao.setKey(key);
        configKeyValueDao.setValue(value);
        configKeyValueDao.setUserId(userId);
        configKeyValueDao.setConfigId(configId);
        configKeyValueDao.setType(type);
        configKeyValueDao.setFileId(configKeyValueDao.getFileId());
        configKeyValueDao.setIsActive(true);
        configKeyValueDao.setCreatedBy(userId.toString());
        configKeyValueDao.setModifiedBy(userId.toString());
        configKeyValueDao.setCreatedOn(OffsetDateTime.now());
        configKeyValueDao.setModifiedOn(OffsetDateTime.now());
        return configKeyValueDao;
    }
}
