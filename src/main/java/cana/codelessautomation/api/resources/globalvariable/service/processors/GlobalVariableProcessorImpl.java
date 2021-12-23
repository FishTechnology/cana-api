package cana.codelessautomation.api.resources.globalvariable.service.processors;

import cana.codelessautomation.api.resources.action.service.repositories.ActionOptionRepository;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.globalvariable.service.dtos.*;
import cana.codelessautomation.api.resources.globalvariable.service.processors.mappers.GlobalVariableProcessorMapper;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.GlobalVariableRepository;
import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;
import org.apache.commons.collections.CollectionUtils;

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

    @Inject
    ActionOptionRepository actionOptionRepository;

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
        var errors= createGlobalVariable(createGlobalVariable);
        if(CollectionUtils.isNotEmpty(errors)){
            return errors;
        }
        return createUIControlOptions(createGlobalVariable);
    }

    @Override
    public List<ErrorMessageDto> createUIControlOptions(CreateGlobalVariableDto createGlobalVariable) {
        for (UIControlOptionDto uiControlOptionDto: createGlobalVariable.getUiControlOptions()){
            var actionOptionDao = globalVariableProcessorMapper.mapActionOptionDao(createGlobalVariable, uiControlOptionDto);
            actionOptionRepository.persist(actionOptionDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createGlobalVariable(CreateGlobalVariableDto createGlobalVariable) {
        var globalVariable = globalVariableProcessorMapper.mapGlobalVariableDao(createGlobalVariable);
        globalVariableRepository.persist(globalVariable);
        createGlobalVariable.setId(globalVariable.getId());
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

    @Override
    public List<ErrorMessageDto> processUpdateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto) {
        return updateGlobalVariable(updateGlobalVariableDto);
    }

    @Override
    public List<ErrorMessageDto> updateGlobalVariable(UpdateGlobalVariableDto updateGlobalVariableDto) {
        var globalVariable = globalVariableProcessorMapper.mapGlobalVariableDao(updateGlobalVariableDto);
        globalVariableRepository.persist(globalVariable);
        return Collections.emptyList();
    }
}
