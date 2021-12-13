package cana.codelessautomation.api.services.applicationconfig.processors.mappers;

import cana.codelessautomation.api.services.applicationconfig.dto.CreateAppConfigDto;
import cana.codelessautomation.api.services.applicationconfig.dto.UpdateApplicationConfigDto;
import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ApplicationConfigProcessorMapperImpl implements ApplicationConfigProcessorMapper {
    @Override
    public ApplicationConfigDao mapApplicationConfigDao(CreateAppConfigDto createAppConfigDto) {
        ApplicationConfigDao applicationConfigDao = new ApplicationConfigDao();
        applicationConfigDao.setKey(createAppConfigDto.getKey());
        applicationConfigDao.setValue(createAppConfigDto.getValue());
        applicationConfigDao.setComments(createAppConfigDto.getComments());
        applicationConfigDao.setCreatedBy(createAppConfigDto.getUserId());
        applicationConfigDao.setModifiedBy(createAppConfigDto.getModifiedBy());
        applicationConfigDao.setCreatedOn(OffsetDateTime.now());
        applicationConfigDao.setModifiedOn(OffsetDateTime.now());
        applicationConfigDao.setIsActive(createAppConfigDto.getIsActive());
        applicationConfigDao.setUserId(Long.valueOf(createAppConfigDto.getUserId()));
        return applicationConfigDao;
    }

    @Override
    public ApplicationConfigDao mapApplicationConfigDao(UpdateApplicationConfigDto updateApplicationConfigDto) {
        var applicationConfigDao = updateApplicationConfigDto.getApplicationConfigDao();
        
        if (StringUtils.equalsAnyIgnoreCase(updateApplicationConfigDto.getKey(), applicationConfigDao.getKey())) {
            applicationConfigDao.setKey(updateApplicationConfigDto.getKey());
        }

        if (StringUtils.equalsAnyIgnoreCase(updateApplicationConfigDto.getValue(), applicationConfigDao.getValue())) {
            applicationConfigDao.setValue(updateApplicationConfigDto.getValue());
        }

        if (StringUtils.isNotEmpty(updateApplicationConfigDto.getComments())) {
            applicationConfigDao.setComments(updateApplicationConfigDto.getComments());
        }

        applicationConfigDao.setModifiedOn(OffsetDateTime.now());
        applicationConfigDao.setModifiedBy(updateApplicationConfigDto.getUserId());
        return applicationConfigDao;
    }
}
