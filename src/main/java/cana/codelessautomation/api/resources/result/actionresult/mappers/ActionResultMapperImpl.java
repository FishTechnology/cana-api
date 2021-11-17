package cana.codelessautomation.api.resources.result.actionresult.mappers;

import cana.codelessautomation.api.resources.result.actionresult.models.UpdateActionResultModel;
import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionResultStatusDao;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionResultMapperImpl implements ActionResultMapper {
    @Override
    public UpdateActionResultDto mapUpdateActionResultDto(Long testCaseResultId, Long actionResultId, UpdateActionResultModel updateActionResultModel) {
        UpdateActionResultDto updateActionResultDto = new UpdateActionResultDto();
        updateActionResultDto.setTestCaseResultId(testCaseResultId);
        updateActionResultDto.setActionResultId(actionResultId);
        updateActionResultDto.setActionId(updateActionResultModel.getActionId());
        updateActionResultDto.setCompletedOn(updateActionResultModel.getCompletedOn());
        updateActionResultDto.setErrorMessage(updateActionResultModel.getErrorMessage());
        updateActionResultDto.setStatus(EnumUtils.getEnumIgnoreCase(ActionResultStatusDao.class, updateActionResultModel.getStatus()));
        updateActionResultDto.setStartedOn(updateActionResultModel.getStartedOn());
        return updateActionResultDto;
    }
}
