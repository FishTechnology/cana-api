package cana.codelessautomation.api.resources.config.services.configservice.processors.mappers;


import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.Objects;

@ApplicationScoped
public class ConfigServiceProcessorMapperImpl implements ConfigServiceProcessorMapper {
    @Override
    public ConfigDao mapConfigDao(CreateConfigDto createConfigDto) {
        ConfigDao configDao = new ConfigDao();
        configDao.setCreatedBy(createConfigDto.getUserId().toString());
        configDao.setModifiedBy(createConfigDto.getUserId().toString());
        configDao.setCreatedOn(OffsetDateTime.now());
        configDao.setModifiedOn(OffsetDateTime.now());
        configDao.setType(createConfigDto.getType());
        configDao.setName(createConfigDto.getName());
        configDao.setComments(createConfigDto.getComments());
        configDao.setApplicationId(createConfigDto.getApplicationId());
        if (!Objects.isNull(createConfigDto.getIdentifier()) && createConfigDto.getIdentifier() > 0) {
            configDao.setIdentifier(createConfigDto.getIdentifier());
        }
        configDao.setUserId(Long.valueOf(createConfigDto.getUserId()));
        configDao.setIsActive(true);
        return configDao;
    }

    @Override
    public ConfigDao mapConfigDao(String name, String type, String value, Long applicationId, Long userId) {
        ConfigDao configDao = new ConfigDao();
        configDao.setCreatedBy(userId.toString());
        configDao.setModifiedBy(userId.toString());
        configDao.setCreatedOn(OffsetDateTime.now());
        configDao.setModifiedOn(OffsetDateTime.now());
        configDao.setType(type);
        configDao.setName(name);
        configDao.setApplicationId(applicationId);
        configDao.setUserId(userId);
        configDao.setIsActive(true);
        return configDao;
    }
}
