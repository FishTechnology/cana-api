package cana.codelessautomation.api.services.globalvariable.processors.mappers;

import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import com.googlecode.jmapper.JMapper;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GlobalVariableProcessorMapperImpl implements GlobalVariableProcessorMapper {

    JMapper<GlobalVariableDao, CreateGlobalVariableDto> mapperGlobalVariableDao;

    public GlobalVariableProcessorMapperImpl() {
        mapperGlobalVariableDao = new JMapper<>(GlobalVariableDao.class, CreateGlobalVariableDto.class);
    }

    @Override
    public GlobalVariableDao mapGlobalVariableDao(CreateGlobalVariableDto createGlobalVariable) {
        return mapperGlobalVariableDao.getDestination(createGlobalVariable);
    }
}
