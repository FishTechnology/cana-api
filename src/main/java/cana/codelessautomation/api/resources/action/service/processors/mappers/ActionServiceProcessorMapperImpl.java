package cana.codelessautomation.api.resources.action.service.processors.mappers;

import cana.codelessautomation.api.resources.action.service.dtos.*;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionKeyDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ActionServiceProcessorMapperImpl implements ActionServiceProcessorMapper {
    @Override
    public ActionDao mapActionDao(CreateActionDto createActionDto) {
        ActionDao actionDao = new ActionDao();
        actionDao.setTestCaseId(createActionDto.getTestCaseId());
        actionDao.setType(createActionDto.getType());
        actionDao.setKey(createActionDto.getKey());
        actionDao.setValue(createActionDto.getValue());
        actionDao.setUserId(createActionDto.getUserId());
        actionDao.setComments(createActionDto.getComments());
        actionDao.setIsActive(createActionDto.getIsActive());
        actionDao.setCreatedOn(OffsetDateTime.now());
        actionDao.setModifiedOn(OffsetDateTime.now());
        actionDao.setCreatedBy(createActionDto.getCreatedBy());
        actionDao.setModifiedBy(createActionDto.getModifiedBy());
        actionDao.setOrderNumber(createActionDto.getOrder());
        actionDao.setUiActionType(createActionDto.getUiActionType());
        actionDao.setIsAssertVerification(createActionDto.getIsAssertVerification());
        actionDao.setIsOptional(createActionDto.getIsOptional());
        actionDao.setConditionType(createActionDto.getConditionType());
        if (createActionDto.getBrowserDetailDto() != null
                && createActionDto.getBrowserDetailDto().getActionType() != null) {
            actionDao.setBrowserActionType(createActionDto.getBrowserDetailDto().getActionType());
            actionDao.setComments(createActionDto.getBrowserDetailDto().getComments());
            actionDao.setBrowserValue(createActionDto.getBrowserDetailDto().getValue());
            actionDao.setConditionType(createActionDto.getBrowserDetailDto().getConditionType());
        }
        return actionDao;
    }

    @Override
    public ActionOptionDao mapActionOptionDao(CreateActionDto createActionDto, CreateActionOptionDto createActionOptionDto) {
        ActionOptionDao actionOptionDao = new ActionOptionDao();
        actionOptionDao.setActionId(createActionDto.getId());
        actionOptionDao.setOrderNumber(createActionOptionDto.getOrder());
        actionOptionDao.setDuration(createActionOptionDto.getWaitDuration());
        actionOptionDao.setOptionType(createActionOptionDto.getOptionType());
        actionOptionDao.setIsActive(createActionDto.getIsActive());
        actionOptionDao.setCreatedOn(OffsetDateTime.now());
        actionOptionDao.setModifiedOn(OffsetDateTime.now());
        actionOptionDao.setCreatedBy(createActionDto.getCreatedBy());
        actionOptionDao.setModifiedBy(createActionDto.getModifiedBy());
        actionOptionDao.setDuration(createActionOptionDto.getDuration());
        actionOptionDao.setControlConditionType(createActionOptionDto.getControlConditionType());
        actionOptionDao.setValue(createActionOptionDto.getValue());
        actionOptionDao.setContentType(createActionOptionDto.getOptionContentType());
        return actionOptionDao;
    }

    @Override
    public ActionDao mapDeleteActionDao(ActionDao actionDao) {
        actionDao.setModifiedOn(OffsetDateTime.now());
        actionDao.setIsActive(false);
        return actionDao;
    }

    @Override
    public ActionDao mapActionDao(UpdateActionOrderDto updateActionOrderDto, ActionOrderDto actionOrderDto) {
        ActionDao actionDao = actionOrderDto.getActionDao();
        actionDao.setOrderNumber(actionOrderDto.getCurrentExecutionOrder());
        actionDao.setModifiedOn(OffsetDateTime.now());
        return actionDao;
    }

    @Override
    public ActionKeyDao mapActionKeyDao(CreateActionDto createActionDto, UIKeyDetailDto uiKeyDetailDto) {
        ActionKeyDao actionKeyDao = new ActionKeyDao();
        actionKeyDao.setActionId(createActionDto.getId());
        actionKeyDao.setKey(uiKeyDetailDto.getKey());
        actionKeyDao.setOrderNumber(uiKeyDetailDto.getOrderNumber());
        actionKeyDao.setIsActive(uiKeyDetailDto.getIsActive());
        actionKeyDao.setCreatedOn(OffsetDateTime.now());
        actionKeyDao.setModifiedOn(OffsetDateTime.now());
        actionKeyDao.setCreatedBy(createActionDto.getCreatedBy());
        actionKeyDao.setModifiedBy(createActionDto.getModifiedBy());
        return actionKeyDao;
    }
}
