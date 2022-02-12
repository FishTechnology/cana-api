package cana.codelessautomation.api.resources.result.actionoptionresult.service.processors.mappers;

import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultStatus;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ActionOptionResultProcessorMapperImpl implements ActionOptionResultProcessorMapper {
    @Override
    public ActionOptionResultDao mapActionOptionResultDao(UpdateActionOptionResultDto updateActionOptionResultDto) {
        var actionOptionResultDao = updateActionOptionResultDto.getActionOptionResultDao();

        actionOptionResultDao.setStatus(updateActionOptionResultDto.getStatus());

        if (updateActionOptionResultDto.getStatus() == ActionOptionResultStatus.STARTED) {
            actionOptionResultDao.setStartedOn(OffsetDateTime.now());
        } else if (updateActionOptionResultDto.getStatus() == ActionOptionResultStatus.COMPLETED) {
            actionOptionResultDao.setCompletedOn(OffsetDateTime.now());
            actionOptionResultDao.setDuration(updateActionOptionResultDto.getTotalDuration());
        }

        if (StringUtils.isNotEmpty(updateActionOptionResultDto.getErrorMessage())) {
            actionOptionResultDao.setErrorMessage(updateActionOptionResultDto.getErrorMessage());
        }

        actionOptionResultDao.setModifiedOn(OffsetDateTime.now());

        return actionOptionResultDao;
    }
}
