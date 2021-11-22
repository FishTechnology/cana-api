package cana.codelessautomation.api.services.environment.processors.mappers;

import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import com.googlecode.jmapper.JMapper;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class EnvironmentProcessorMapperImpl implements EnvironmentProcessorMapper {

    JMapper<EnvironmentDao, CreateEnvironmentDto> mapperEnvironmentDao;

    public EnvironmentProcessorMapperImpl() {
        //mapperEnvironmentDao = new JMapper<>(EnvironmentDao.class, CreateEnvironmentDto.class);
    }

    @Override
    public EnvironmentDao mapEnvironmentDao(CreateEnvironmentDto createEnvironment) {
        EnvironmentDao environmentDao = new EnvironmentDao();
        environmentDao.setName(createEnvironment.getName());
        environmentDao.setUserId(createEnvironment.getUserId());
        environmentDao.setComments(createEnvironment.getComments());
        environmentDao.setIsActive(createEnvironment.getIsActive());
        environmentDao.setCreatedOn(OffsetDateTime.now());
        environmentDao.setModifiedOn(OffsetDateTime.now());
        environmentDao.setCreatedBy(createEnvironment.getCreatedBy());
        environmentDao.setModifiedBy(createEnvironment.getModifiedBy());
        //return mapperEnvironmentDao.getDestination(createEnvironment);
        return environmentDao;
    }
}
