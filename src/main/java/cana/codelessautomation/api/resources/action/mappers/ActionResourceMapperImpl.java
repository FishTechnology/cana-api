package cana.codelessautomation.api.resources.action.mappers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.action.models.ActionDetailModel;
import cana.codelessautomation.api.resources.action.models.ActionOptionModel;
import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.action.models.CreateActionOptionModel;
import cana.codelessautomation.api.resources.action.service.dtos.BrowserDetailDto;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionDto;
import cana.codelessautomation.api.resources.action.service.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.resources.action.service.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.resources.action.service.repositories.daos.*;
import cana.codelessautomation.api.resources.action.service.repositories.daos.entities.ActionDaoEntity;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.schedule.models.ScheduledActionDetailModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ActionResourceMapperImpl implements ActionResourceMapper {
    @Override
    public CreateActionDto mapCreateActionDto(CreateActionModel createActionModel, Long testCaseId) {
        CreateActionDto createActionDto = new CreateActionDto();
        createActionDto.setTestCaseId(testCaseId);
        createActionDto.setUserId(createActionModel.getUserId());
        createActionDto.setKey(createActionModel.getKey());
        createActionDto.setValue(createActionModel.getValue());
        createActionDto.setType(EnumUtils.getEnumIgnoreCase(ActionTypeDao.class, createActionModel.getType()));
        createActionDto.setComments(createActionModel.getComments());
        createActionDto.setIsAssertVerification(createActionModel.getIsAssertVerification());
        createActionDto.setUiActionType(EnumUtils.getEnumIgnoreCase(UIActionTypeDao.class, createActionModel.getUiActionType()));
        createActionDto.setIsOptional(createActionModel.getIsOptional());
        List<CreateActionOptionDto> createActionOptionDtos = new ArrayList<>();

        if (createActionModel.getBrowserOptions() != null) {
            BrowserDetailDto browserDetailDto = new BrowserDetailDto();
            browserDetailDto.setActionType(EnumUtils.getEnumIgnoreCase(BrowserActionTypeDao.class, createActionModel.getBrowserOptions().getActionType()));
            browserDetailDto.setComments(createActionModel.getBrowserOptions().getComments());
            browserDetailDto.setValue(createActionModel.getBrowserOptions().getValue());
            browserDetailDto.setConditionType(EnumUtils.getEnumIgnoreCase(ConditionType.class, createActionModel.getBrowserOptions().getConditionType()));

            createActionDto.setBrowserDetailDto(browserDetailDto);
        }

        if (CollectionUtils.isEmpty(createActionModel.getUiControlOptions())) {
            return createActionDto;
        }
        for (CreateActionOptionModel createActionOptionModel : createActionModel.getUiControlOptions()) {
            CreateActionOptionDto createActionOptionDto = new CreateActionOptionDto();
            createActionOptionDto.setOptionType(EnumUtils.getEnumIgnoreCase(ActionOptionTypeDao.class, createActionOptionModel.getOptionType()));
            createActionOptionDto.setWaitDuration(createActionOptionModel.getWaitDuration());
            createActionOptionDto.setOrder(createActionOptionModel.getOrder());
            createActionOptionDto.setConditionType(EnumUtils.getEnumIgnoreCase(UIOptionConditionTypeDao.class, createActionOptionModel.getConditionType()));
            createActionOptionDtos.add(createActionOptionDto);
        }
        createActionDto.setOptionDtos(createActionOptionDtos);
        return createActionDto;
    }

    @Override
    public ResultModel mapResultModel(CreateActionDto createActionDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createActionDto.getId().toString());
        return resultModel;
    }

    @Override
    public GetActionsByTestCaseIdDto mapGetActionsByTestCaseIdDto(Long testCaseId) {
        GetActionsByTestCaseIdDto getActionsByTestCaseIdDto = new GetActionsByTestCaseIdDto();
        getActionsByTestCaseIdDto.setTestCaseId(testCaseId);
        return getActionsByTestCaseIdDto;
    }

    @Override
    public List<ActionDetailModel> mapActionDetailModels(GetActionsByTestCaseIdDto getActionsByTestCaseIdDto) {
        List<ActionDetailModel> actionDetailModels = new ArrayList<>();
        for (ActionDao actionDao : getActionsByTestCaseIdDto.getActionDaos()) {
            actionDetailModels.add(mapActionDetailModel(actionDao));
        }
        return actionDetailModels;
    }

    @Override
    public ActionDetailModel mapActionDetailModel(ActionDao actionDao) {
        ActionDetailModel actionDetailModel = new ActionDetailModel();
        actionDetailModel.setId(actionDao.getId());
        actionDetailModel.setKey(actionDao.getKey());
        actionDetailModel.setValue(actionDao.getValue());
        actionDetailModel.setComments(actionDao.getComments());
        actionDetailModel.setOrder(actionDao.getOrderNumber());
        actionDetailModel.setTestCaseId(actionDao.getTestCaseId());
        actionDetailModel.setType(actionDao.getType());
        actionDetailModel.setUserId(actionDao.getUserId());
        actionDetailModel.setModifiedBy(actionDao.getModifiedBy());
        actionDetailModel.setModifiedOn(actionDao.getModifiedOn().toString());
        actionDetailModel.setCreatedBy(actionDao.getCreatedBy());
        actionDetailModel.setCreatedOn(actionDao.getCreatedOn().toString());
        actionDetailModel.setIsActive(actionDao.getIsActive());
        actionDetailModel.setIsOptional(actionDao.getIsOptional());
        actionDetailModel.setUiActionType(actionDao.getUiActionType().name());

        if (!Objects.isNull(actionDao.getConditionType())) {
            actionDetailModel.setConditionType(actionDao.getConditionType().name());
        }

        if (CollectionUtils.isNotEmpty(actionDao.getActionOptionDaos())) {
            List<ActionOptionModel> actionOptionModels = new ArrayList<>();
            for (ActionOptionDao actionOptionDao : actionDao.getActionOptionDaos()) {
                if (!actionOptionDao.getIsActive()) {
                    continue;
                }
                ActionOptionModel actionOptionModel = new ActionOptionModel();
                actionOptionModel.setOptionType(actionOptionDao.getOptionType().name());
                actionOptionModel.setOrder(actionOptionDao.getOrderNumber());
                actionOptionModel.setWaitDuration(actionOptionDao.getWaitDuration());
                actionOptionModel.setConditionType(actionOptionDao.getConditionType().name());

                actionOptionModels.add(actionOptionModel);
            }
            actionDetailModel.setActionOptionModels(actionOptionModels);
        }

        if (actionDao.getBrowserActionType() != null) {
            actionDetailModel.setBrowserActionType(actionDao.getBrowserActionType().name());
        }
        actionDetailModel.setBrowserValue(actionDao.getBrowserValue());
        actionDetailModel.setIsAssertVerification(actionDao.getIsAssertVerification());
        return actionDetailModel;
    }

    @Override
    public ScheduledActionDetailModel mapScheduledActionDetailModel(ActionDaoEntity actionDaoEntity) {
        ScheduledActionDetailModel scheduledActionDetail = new ScheduledActionDetailModel();
        scheduledActionDetail.setId(actionDaoEntity.getId());
        scheduledActionDetail.setKey(actionDaoEntity.getKey());
        scheduledActionDetail.setValue(actionDaoEntity.getValue());
        scheduledActionDetail.setComments(actionDaoEntity.getComments());
        scheduledActionDetail.setOrder(actionDaoEntity.getOrderNumber());
        scheduledActionDetail.setTestCaseId(actionDaoEntity.getTestCaseId());
        scheduledActionDetail.setType(actionDaoEntity.getType());
        scheduledActionDetail.setUserId(actionDaoEntity.getUserId());
        scheduledActionDetail.setModifiedBy(actionDaoEntity.getModifiedBy());
        scheduledActionDetail.setModifiedOn(actionDaoEntity.getModifiedOn().toString());
        scheduledActionDetail.setCreatedBy(actionDaoEntity.getCreatedBy());
        scheduledActionDetail.setCreatedOn(actionDaoEntity.getCreatedOn().toString());
        scheduledActionDetail.setIsActive(actionDaoEntity.getIsActive());
        scheduledActionDetail.setUiActionType(actionDaoEntity.getUiActionType().name());

        if (CollectionUtils.isNotEmpty(actionDaoEntity.getActionOptionDaos())) {
            List<ActionOptionModel> actionOptionModels = new ArrayList<>();
            for (ActionOptionDao actionOptionDao : actionDaoEntity.getActionOptionDaos()) {
                if (!actionOptionDao.getIsActive()) {
                    continue;
                }
                ActionOptionModel actionOptionModel = new ActionOptionModel();
                actionOptionModel.setOptionType(actionOptionDao.getOptionType().name());
                actionOptionModel.setOrder(actionOptionDao.getOrderNumber());
                actionOptionModel.setWaitDuration(actionOptionDao.getWaitDuration());
                actionOptionModels.add(actionOptionModel);
            }
            scheduledActionDetail.setActionOptionModels(actionOptionModels);
        }

        if (actionDaoEntity.getBrowserActionType() != null) {
            scheduledActionDetail.setBrowserActionType(actionDaoEntity.getBrowserActionType().name());
        }
        scheduledActionDetail.setBrowserValue(actionDaoEntity.getBrowserValue());
        scheduledActionDetail.setIsAssertVerification(actionDaoEntity.getIsAssertVerification());
        return scheduledActionDetail;
    }
}
