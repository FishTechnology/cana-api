package cana.codelessautomation.api.resources.result.actionresult.mappers;

import cana.codelessautomation.api.resources.result.actionresult.models.ActionResultModel;
import cana.codelessautomation.api.resources.result.actionresult.models.UpdateActionResultModel;
import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.enums.ActionResultStatusDao;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ActionResultResourceMapperImpl implements ActionResultResourceMapper {
    @Override
    public UpdateActionResultDto mapUpdateActionResultDto(Long testCaseResultId, Long actionResultId, UpdateActionResultModel updateActionResultModel) {
        UpdateActionResultDto updateActionResultDto = new UpdateActionResultDto();
        updateActionResultDto.setTestCaseResultId(testCaseResultId);
        updateActionResultDto.setActionResultId(actionResultId);
        updateActionResultDto.setTotalDuration(updateActionResultModel.getTotalDuration());

        updateActionResultDto.setErrorMessage(updateActionResultModel.getErrorMessage());
        updateActionResultDto.setStatus(EnumUtils.getEnumIgnoreCase(ActionResultStatusDao.class, updateActionResultModel.getStatus()));
        
        return updateActionResultDto;
    }

    @Override
    public List<ActionResultModel> mapActionResultModels(List<ActionResultDao> actionResultDtos) {
        List<ActionResultModel> actionResultModels = new ArrayList<>();
        for (ActionResultDao actionResultDao : actionResultDtos) {
            ActionResultModel actionResultModel = new ActionResultModel();
            actionResultModel.setExecutionOrder(actionResultDao.getExecutionOrder());
            actionResultModel.setStatus(actionResultDao.getStatus().name());
            actionResultModel.setId(actionResultDao.getId());
            actionResultModel.setDuration(actionResultDao.getDuration());

            if (!Objects.isNull(actionResultDao.getCompletedOn())) {
                actionResultModel.setCompletedOn(actionResultDao.getCompletedOn().toString());
            }

            if (!Objects.isNull(actionResultDao.getStartedOn())) {
                actionResultModel.setCompletedOn(actionResultDao.getStartedOn().toString());
            }

            actionResultModel.setTestcaseResultId(actionResultDao.getTestcaseResultId());
            actionResultModel.setActionId(actionResultDao.getActionId());
            actionResultModels.add(actionResultModel);
        }
        return actionResultModels;
    }
}
