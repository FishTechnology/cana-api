package cana.codelessautomation.api.services.globalvariable.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.globalvariable.dtos.CreateGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.DeleteGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.dtos.GetGlobalVariableDto;
import cana.codelessautomation.api.services.globalvariable.processors.mappers.GlobalVariableProcessorMapper;
import cana.codelessautomation.api.services.globalvariable.repositories.GlobalVariableRepository;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class GlobalVariableProcessorImpl implements GlobalVariableProcessor {

    @Inject
    GlobalVariableRepository globalVariableRepository;

    @Inject
    GlobalVariableProcessorMapper globalVariableProcessorMapper;

    @Override
    public List<GlobalVariableDao> processGetGlobalVariables(GetGlobalVariableDto getGlobalVariableDto) {
        return getGlobalVariables(getGlobalVariableDto);
    }

    @Override
    public List<GlobalVariableDao> getGlobalVariables(GetGlobalVariableDto getGlobalVariableDto) {
        return globalVariableRepository.findByUserId(getGlobalVariableDto.getUserId());
    }

    @Override
    public List<ErrorMessageDto> processCreateGlobalVariable(CreateGlobalVariableDto createGlobalVariable) {
        return createGlobalVariable(createGlobalVariable);
    }

    @Override
    public List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariable) {
        var globalVariable = globalVariableProcessorMapper.mapGlobalVariableDao(createGlobalVariable);
        globalVariableRepository.persist(globalVariable);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processDeleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto) {
        return deleteGlobalVariable(deleteGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> deleteGlobalVariable(DeleteGlobalVariableDto deleteGlobalVariableDto) {
        globalVariableRepository.deleteByIdAndIsActive(deleteGlobalVariableDto.getGlobalVariableId());
        return Collections.emptyList();
    }
}
