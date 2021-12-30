package cana.codelessautomation.api.resources.action.service.processors;

import cana.codelessautomation.api.resources.action.service.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.repositories.ActionOptionRepository;
import cana.codelessautomation.api.resources.action.service.repositories.ActionRepository;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.processors.mappers.ActionServiceProcessorMapper;
import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionServiceProcessorImpl implements ActionServiceProcessor {

    @Inject
    ActionServiceProcessorMapper actionServiceProcessorMapper;

    @Inject
    ActionRepository actionRepository;

    @Inject
    ActionOptionRepository actionOptionRepository;

    @Override
    public List<ErrorMessageDto> processCreateAction(CreateActionDto createActionDto) {
        var errors = getActionOrder(createActionDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = createAction(createActionDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createActionOption(createActionDto);
    }

    @Override
    public List<ErrorMessageDto> getActionOrder(CreateActionDto createActionDto) {
        var actionDao = actionRepository.getLatestActionByTestCaseId(createActionDto.getTestCaseId());
        if (actionDao == null) {
            createActionDto.setOrder(1L);
            return Collections.emptyList();
        }
        createActionDto.setOrder(actionDao.getOrderNumber() + 1);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createActionOption(CreateActionDto createActionDto) {
        if (CollectionUtils.isEmpty(createActionDto.getOptionDtos())) {
            return Collections.emptyList();
        }
        for (CreateActionOptionDto createActionOptionDto : createActionDto.getOptionDtos()) {
            var actionOptionDao = actionServiceProcessorMapper.mapActionOptionDao(createActionDto, createActionOptionDto);
            actionOptionRepository.persist(actionOptionDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> createAction(CreateActionDto createActionDto) {
        var actionDao = actionServiceProcessorMapper.mapActionDao(createActionDto);
        actionRepository.persist(actionDao);
        createActionDto.setId(actionDao.getId());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        return getActionsByTestCaseId(getActionsByTestCaseIdDto);
    }

    @Override
    public List<ErrorMessageDto> getActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        var actions = actionRepository.findByTestCaseId(getActionsByTestCaseIdDto.getTestCaseId());
        getActionsByTestCaseIdDto.setActionDaos(actions);
        return Collections.emptyList();
    }
}