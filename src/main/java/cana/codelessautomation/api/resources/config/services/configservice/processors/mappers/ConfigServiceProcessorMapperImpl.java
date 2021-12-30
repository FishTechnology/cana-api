package cana.codelessautomation.api.resources.config.services.configservice.processors.mappers;


import cana.codelessautomation.api.resources.config.services.configservice.dtos.CreateConfigDto;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

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
        configDao.setUserId(Long.valueOf(createConfigDto.getUserId()));
        configDao.setIsActive(true);
        return configDao;
    }
}
