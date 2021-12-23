package cana.codelessautomation.api.resources.result.actionresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.Objects;

@ApplicationScoped
public class ActionResultProcessorMapperImpl implements ActionResultProcessorMapper {
    @Override
    public ActionResultDao mapActionResultDao(UpdateActionResultDto updateActionResultDto) {
        var actionResultDao = updateActionResultDto.getActionResult();
        actionResultDao.setStatus(updateActionResultDto.getStatus());

        if (!Objects.isNull(updateActionResultDto.getCompletedOn())) {
            actionResultDao.setCompletedOn(updateActionResultDto.getCompletedOn());
        }

        if (!Objects.isNull(updateActionResultDto.getStartedOn())) {
            actionResultDao.setStartedOn(updateActionResultDto.getStartedOn());
        }

        if (StringUtils.isNotEmpty(updateActionResultDto.getTotalDuration())) {
            actionResultDao.setDuration(updateActionResultDto.getTotalDuration());
        }

        if (StringUtils.isNotEmpty(updateActionResultDto.getErrorMessage())) {
            actionResultDao.setErrorMessage(updateActionResultDto.getErrorMessage());
        }

        actionResultDao.setModifiedOn(OffsetDateTime.now());

        return actionResultDao;
    }
}
