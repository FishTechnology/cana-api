package cana.codelessautomation.api.resources.result.actionoptionresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.processors.mappers.ActionOptionResultProcessorMapper;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.ActionOptionResultRepository;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionOptionResultProcessorImpl implements ActionOptionResultProcessor {
    @Inject
    ActionOptionResultRepository actionOptionResultRepository;

    @Inject
    ActionOptionResultProcessorMapper actionOptionResultProcessorMapper;

    @Override
    public List<ActionOptionResultDao> processGetActionResultsByTestCaseResultId(Long actionResultId) {
        return actionOptionResultRepository.findByActionResultId(actionResultId);
    }

    @Override
    public List<ErrorMessageDto> processUpdateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto) {
        return updateActionOptionResult(updateActionOptionResultDto);
    }

    @Override
    public List<ErrorMessageDto> updateActionOptionResult(UpdateActionOptionResultDto updateActionOptionResultDto) {
        var actionOptionResultDao = actionOptionResultProcessorMapper.mapActionOptionResultDao(updateActionOptionResultDto);
        actionOptionResultRepository.persist(actionOptionResultDao);
        return Collections.emptyList();
    }
}
