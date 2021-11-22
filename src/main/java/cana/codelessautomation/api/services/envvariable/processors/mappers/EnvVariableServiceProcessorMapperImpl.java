package cana.codelessautomation.api.services.envvariable.processors.mappers;

import cana.codelessautomation.api.services.envvariable.dtos.CreateEnvVariableDto;
import cana.codelessautomation.api.services.envvariable.repositories.daos.EnvironmentVariableDao;
import com.googlecode.jmapper.JMapper;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class EnvVariableServiceProcessorMapperImpl implements EnvVariableServiceProcessorMapper {

    JMapper<EnvironmentVariableDao, CreateEnvVariableDto> mapperEnvironmentVariable;

    public EnvVariableServiceProcessorMapperImpl() {
        // mapperEnvironmentVariable = new JMapper<>(EnvironmentVariableDao.class, CreateEnvVariableDto.class);
    }

    @Override
    public EnvironmentVariableDao mapEnvironmentVariableDao(CreateEnvVariableDto createEnvVariableDto) {
        EnvironmentVariableDao environmentVariable = new EnvironmentVariableDao();
        environmentVariable.setKey(createEnvVariableDto.getKey());
        environmentVariable.setValue(createEnvVariableDto.getValue());
        environmentVariable.setType(createEnvVariableDto.getType());
        environmentVariable.setComments(createEnvVariableDto.getComments());
        environmentVariable.setEnvironmentId(createEnvVariableDto.getEnvironmentId());
        environmentVariable.setUserId(createEnvVariableDto.getUserId());
        environmentVariable.setCreatedBy(createEnvVariableDto.getCreatedBy());
        environmentVariable.setCreatedOn(OffsetDateTime.now());
        environmentVariable.setModifiedBy(createEnvVariableDto.getModifiedBy());
        environmentVariable.setModifiedOn(OffsetDateTime.now());
        environmentVariable.setIsActive(createEnvVariableDto.getIsActive());
        return environmentVariable;
    }
}
