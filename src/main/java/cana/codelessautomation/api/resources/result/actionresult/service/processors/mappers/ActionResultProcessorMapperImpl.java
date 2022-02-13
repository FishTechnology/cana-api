package cana.codelessautomation.api.resources.result.actionresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.enums.ActionResultStatusDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ActionResultProcessorMapperImpl implements ActionResultProcessorMapper {
    @Override
    public ActionResultDao mapActionResultDao(UpdateActionResultDto updateActionResultDto) {
        var actionResultDao = updateActionResultDto.getActionResult();
        actionResultDao.setStatus(updateActionResultDto.getStatus());

        if (actionResultDao.getStatus() == ActionResultStatusDao.STARTED) {
            actionResultDao.setStartedOn(OffsetDateTime.now());
        } else if (actionResultDao.getStatus() == ActionResultStatusDao.COMPLETED
                || actionResultDao.getStatus() == ActionResultStatusDao.ERROR) {
            actionResultDao.setCompletedOn(OffsetDateTime.now());
            actionResultDao.setDuration(updateActionResultDto.getTotalDuration());
        }

        if (StringUtils.isNotEmpty(updateActionResultDto.getErrorMessage())) {
            actionResultDao.setErrorMessage(updateActionResultDto.getErrorMessage());
        }

        actionResultDao.setModifiedOn(OffsetDateTime.now());

        return actionResultDao;
    }
}
