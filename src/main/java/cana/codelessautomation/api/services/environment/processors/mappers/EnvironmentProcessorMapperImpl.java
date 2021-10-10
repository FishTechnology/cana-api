package cana.codelessautomation.api.services.environment.processors.mappers;

import cana.codelessautomation.api.services.environment.dtos.CreateEnvironmentDto;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import com.googlecode.jmapper.JMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentProcessorMapperImpl implements EnvironmentProcessorMapper {

    JMapper<EnvironmentDao, CreateEnvironmentDto> mapperEnvironmentDao;

    public EnvironmentProcessorMapperImpl() {
        mapperEnvironmentDao = new JMapper<>(EnvironmentDao.class, CreateEnvironmentDto.class);
    }

    @Override
    public EnvironmentDao mapEnvironmentDao(CreateEnvironmentDto createEnvironment) {
        return mapperEnvironmentDao.getDestination(createEnvironment);
    }
}
