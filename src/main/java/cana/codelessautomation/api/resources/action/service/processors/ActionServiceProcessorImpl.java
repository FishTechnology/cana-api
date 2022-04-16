package cana.codelessautomation.api.resources.action.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.action.service.dtos.*;
import cana.codelessautomation.api.resources.action.service.processors.mappers.ActionServiceProcessorMapper;
import cana.codelessautomation.api.resources.action.service.repositories.ActionKeyRepository;
import cana.codelessautomation.api.resources.action.service.repositories.ActionOptionRepository;
import cana.codelessautomation.api.resources.action.service.repositories.ActionRepository;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
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

    @Inject
    ActionKeyRepository actionKeyRepository;

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
        errors = createActionKey(createActionDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return createActionOption(createActionDto);
    }

    @Override
    public List<ErrorMessageDto> createActionKey(CreateActionDto createActionDto) {
        if (CollectionUtils.isEmpty(createActionDto.getUiActionKeys())) {
            return Collections.emptyList();
        }

        for (UIKeyDetailDto uiKeyDetailDto : createActionDto.getUiActionKeys()) {
            var actionKeyDao = actionServiceProcessorMapper.mapActionKeyDao(createActionDto, uiKeyDetailDto);
            actionKeyRepository.persist(actionKeyDao);
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> processDeleteActionById(DeleteActionByIdDto deleteActionByIdDto) {
        var errors = deleteActionById(deleteActionByIdDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return updateActionOrder(deleteActionByIdDto);
    }

    @Override
    public List<ErrorMessageDto> processUpdateActionOrder(UpdateActionOrderDto updateActionOrderDto) {
        return updateActionOrder(updateActionOrderDto);
    }

    @Override
    public List<ErrorMessageDto> processGetActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        return getActionsByTestCaseId(getActionsByTestCaseIdDto);
    }

    @Override
    public List<ErrorMessageDto> updateActionOrder(UpdateActionOrderDto updateActionOrderDto) {
        for (ActionOrderDto actionOrderDto : updateActionOrderDto.getActionOrderDtos()) {
            if (actionOrderDto.getCurrentExecutionOrder().equals(actionOrderDto.getOldExecutionOrder())) {
                continue;
            }
            var actionDao = actionServiceProcessorMapper.mapActionDao(updateActionOrderDto, actionOrderDto);
            actionRepository.persist(actionDao);
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> deleteActionById(DeleteActionByIdDto deleteActionByIdDto) {
        var actionDao = actionServiceProcessorMapper.mapDeleteActionDao(deleteActionByIdDto.getActionDao());
        actionRepository.persist(actionDao);
        return Collections.emptyList();
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
    public List<ErrorMessageDto> getActionsByTestCaseId(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        var actions = actionRepository.findByTestCaseId(getActionsByTestCaseIdDto.getTestCaseId());
        getActionsByTestCaseIdDto.setActionDaos(actions);
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> updateActionOrder(DeleteActionByIdDto deleteActionByIdDto) {
        var actionDaos = actionRepository.findByGreaterThan(deleteActionByIdDto.getActionDao().getOrderNumber());
        Long currentOrder = deleteActionByIdDto.getActionDao().getOrderNumber();
        for (ActionDao actionDao : actionDaos) {
            actionDao.setModifiedOn(OffsetDateTime.now());
            currentOrder += 1;
            actionDao.setOrderNumber(currentOrder);
            actionRepository.persist(actionDao);
        }
        return Collections.emptyList();
    }
}
