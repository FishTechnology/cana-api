package cana.codelessautomation.api.services.action.processors.mappers;

import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionDao;

import javax.enterprise.context.ApplicationScoped;

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
        actionDao.setCreatedOn(createActionDto.getCreatedOn());
        actionDao.setModifiedOn(createActionDto.getModifiedOn());
        actionDao.setCreatedBy(createActionDto.getCreatedBy());
        actionDao.setModifiedBy(createActionDto.getModifiedBy());
        actionDao.setOrderNumber(createActionDto.getOrder());
        actionDao.setUiActionType(createActionDto.getUiActionType());
        actionDao.setIsAssertVerification(createActionDto.getIsAssertVerification());
        if (createActionDto.getBrowserDetailDto() != null
                && createActionDto.getBrowserDetailDto().getActionType() != null) {
            actionDao.setBrowserActionType(createActionDto.getBrowserDetailDto().getActionType());
            actionDao.setComments(createActionDto.getBrowserDetailDto().getComments());
            actionDao.setBrowserValue(createActionDto.getBrowserDetailDto().getValue());
        }
        return actionDao;
    }

    @Override
    public ActionOptionDao mapActionOptionDao(CreateActionDto createActionDto, CreateActionOptionDto createActionOptionDto) {
        ActionOptionDao actionOptionDao = new ActionOptionDao();
        actionOptionDao.setActionId(createActionDto.getId());
        actionOptionDao.setOrderNumber(createActionOptionDto.getOrder());
        actionOptionDao.setWaitDuration(createActionOptionDto.getWaitDuration());
        actionOptionDao.setOptionType(createActionOptionDto.getOptionType());
        actionOptionDao.setIsActive(createActionDto.getIsActive());
        actionOptionDao.setCreatedOn(createActionDto.getCreatedOn());
        actionOptionDao.setModifiedOn(createActionDto.getModifiedOn());
        actionOptionDao.setCreatedBy(createActionDto.getCreatedBy());
        actionOptionDao.setModifiedBy(createActionDto.getModifiedBy());
        return actionOptionDao;
    }
}
