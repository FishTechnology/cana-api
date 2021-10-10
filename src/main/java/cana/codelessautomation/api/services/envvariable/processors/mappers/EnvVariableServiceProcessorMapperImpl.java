package cana.codelessautomation.api.services.envvariable.processors.mappers;

import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import com.googlecode.jmapper.JMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvVariableServiceProcessorMapperImpl implements EnvVariableServiceProcessorMapper {

    JMapper<EnvironmentVariableDao, CreateEnvVariableDto> mapperEnvironmentVariable;

    public EnvVariableServiceProcessorMapperImpl() {
        mapperEnvironmentVariable = new JMapper<>(EnvironmentVariableDao.class, CreateEnvVariableDto.class);
    }

    @Override
    public EnvironmentVariableDao mapEnvironmentVariableDao(CreateEnvVariableDto createEnvVariableDto) {
       return mapperEnvironmentVariable.getDestination(createEnvVariableDto);
    }
}
