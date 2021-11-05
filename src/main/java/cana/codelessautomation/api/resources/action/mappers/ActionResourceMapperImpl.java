package cana.codelessautomation.api.resources.action.mappers;

import cana.codelessautomation.api.resources.action.models.ActionDetailModel;
import cana.codelessautomation.api.resources.action.models.ActionOptionModel;
import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.action.models.CreateActionOptionModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.action.dtos.BrowserDetailDto;
import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.services.action.dtos.GetActionsByTestCaseIdDto;
import cana.codelessautomation.api.services.action.repositories.daos.*;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

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
        createActionDto.setUiActionType(EnumUtils.getEnumIgnoreCase(UIActionTypeDao.class, createActionModel.getUiActionType()));
        List<CreateActionOptionDto> createActionOptionDtos = new ArrayList<>();

        if (createActionModel.getBrowserOptions() != null) {
            BrowserDetailDto browserDetailDto = new BrowserDetailDto();
            browserDetailDto.setActionType(EnumUtils.getEnumIgnoreCase(BrowserActionTypeDao.class, createActionModel.getBrowserOptions().getActionType()));
            browserDetailDto.setComments(createActionModel.getBrowserOptions().getComments());
            browserDetailDto.setUrl(createActionModel.getBrowserOptions().getUrl());
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
        resultModel.setId(createActionDto.getId());
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
            actionDetailModel.setUiActionType(actionDao.getUiActionType().name());

            if (CollectionUtils.isNotEmpty(actionDao.getActionOptionDaos())) {
                List<ActionOptionModel> actionOptionModels = new ArrayList<>();
                for (ActionOptionDao actionOptionDao : actionDao.getActionOptionDaos()) {
                    if(!actionOptionDao.getIsActive()){
                        continue;
                    }
                    ActionOptionModel actionOptionModel = new ActionOptionModel();
                    actionOptionModel.setOptionType(actionOptionDao.getOptionType().name());
                    actionOptionModel.setOrder(actionOptionDao.getOrderNumber());
                    actionOptionModel.setWaitDuration(actionOptionDao.getWaitDuration());
                    actionOptionModels.add(actionOptionModel);
                }
                actionDetailModel.setActionOptionModels(actionOptionModels);
            }

            if (actionDao.getBrowserActionType() != null) {
                actionDetailModel.setBrowserActionType(actionDao.getBrowserActionType().name());
            }
            actionDetailModel.setUrl(actionDao.getUrl());
            actionDetailModels.add(actionDetailModel);

        }
        return actionDetailModels;
    }
}