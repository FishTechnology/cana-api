package cana.codelessautomation.api.resources.application.service.processors.mappers;

import cana.codelessautomation.api.resources.application.service.dtos.CreateApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.DeleteApplicationDto;
import cana.codelessautomation.api.resources.application.service.dtos.UpdateApplicationDto;
import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ApplicationProcessorMapperImpl implements ApplicationProcessorMapper {
    @Override
    public ApplicationDao mapApplicationDao(CreateApplicationDto createApplicationDto) {
        ApplicationDao applicationDao = new ApplicationDao();
        applicationDao.setName(createApplicationDto.getName());
        applicationDao.setComments(createApplicationDto.getComments());
        applicationDao.setCreatedBy(createApplicationDto.getUserId().toString());
        applicationDao.setModifiedBy(createApplicationDto.getModifiedBy());
        applicationDao.setCreatedOn(OffsetDateTime.now());
        applicationDao.setModifiedOn(OffsetDateTime.now());
        applicationDao.setIsActive(createApplicationDto.getIsActive());
        applicationDao.setUserId(createApplicationDto.getUserId());
        return applicationDao;
    }

    @Override
    public ApplicationDao mapApplicationDao(DeleteApplicationDto deleteApplicationDto) {
        ApplicationDao applicationDao = deleteApplicationDto.getApplicationDao();
        applicationDao.setIsActive(false);
        applicationDao.setModifiedOn(OffsetDateTime.now());
        return applicationDao;
    }

    @Override
    public ApplicationDao mapApplicationDao(UpdateApplicationDto updateApplicationDto) {
        ApplicationDao applicationDao = updateApplicationDto.getApplicationDao();

        if (StringUtils.isNotEmpty(applicationDao.getComments())) {
            applicationDao.setComments(updateApplicationDto.getComments());
        }

        if (StringUtils.isNotEmpty(applicationDao.getName())) {
            applicationDao.setName(updateApplicationDto.getName());
        }

        applicationDao.setModifiedBy(updateApplicationDto.getUserId().toString());
        applicationDao.setModifiedOn(OffsetDateTime.now());
        return applicationDao;
    }
}
