package cana.codelessautomation.api.resources.result.actionoptionresult.mapper;

import cana.codelessautomation.api.resources.result.actionoptionresult.models.ActionOptionResultModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.models.UpdateActionOptionResultModel;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultStatus;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ActionOptionResultResourceMapperImpl implements ActionOptionResultResourceMapper {

    @Override
    public List<ActionOptionResultModel> mapActionOptionResultModels(List<ActionOptionResultDao> actionOptionResultDtos) {
        List<ActionOptionResultModel> actionOptionResultModels = new ArrayList<>();
        for (ActionOptionResultDao actionOptionResult : actionOptionResultDtos) {
            ActionOptionResultModel actionResultModel = new ActionOptionResultModel();
            actionResultModel.setCompletedOn(actionOptionResult.getCompletedOn());
            actionResultModel.setCreatedOn(actionOptionResult.getCreatedOn());
            actionResultModel.setDuration(actionOptionResult.getDuration());
            actionResultModel.setErrorMessage(actionOptionResult.getErrorMessage());
            actionResultModel.setModifiedOn(actionOptionResult.getModifiedOn());
            actionResultModel.setStatus(actionOptionResult.getStatus().name());
            actionResultModel.setActionResultId(actionOptionResult.getActionResultId());
            actionResultModel.setActionOptionId(actionOptionResult.getActionOptionId());
            actionResultModel.setExecutionOrder(actionOptionResult.getExecutionOrder());
            actionResultModel.setId(actionOptionResult.getId());
            actionResultModel.setStartedOn(actionOptionResult.getStartedOn());
            actionOptionResultModels.add(actionResultModel);
        }
        return actionOptionResultModels;
    }

    @Override
    public UpdateActionOptionResultDto mapUpdateActionOptionResultDto(Long actionResultId, Long actionOptionResultId, UpdateActionOptionResultModel updateActionOptionResultModel) {
        UpdateActionOptionResultDto updateActionOptionResultDto = new UpdateActionOptionResultDto();
        updateActionOptionResultDto.setActionOptionResultId(actionOptionResultId);
        updateActionOptionResultDto.setActionResultId(actionResultId);
        updateActionOptionResultDto.setStatus(EnumUtils.getEnum(ActionOptionResultStatus.class, updateActionOptionResultModel.getStatus()));
        updateActionOptionResultDto.setTotalDuration(updateActionOptionResultModel.getTotalDuration());
        updateActionOptionResultDto.setErrorMessage(updateActionOptionResultModel.getErrorMessage());
        return updateActionOptionResultDto;
    }
}
