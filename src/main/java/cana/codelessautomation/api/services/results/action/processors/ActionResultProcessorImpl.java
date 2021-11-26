package cana.codelessautomation.api.services.results.action.processors;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.processors.mappers.ActionResultProcessorMapper;
import cana.codelessautomation.api.services.results.action.repositories.ActionResultRepository;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionResultProcessorImpl implements ActionResultProcessor {

    @Inject
    ActionResultRepository actionResultRepository;

    @Inject
    ActionResultProcessorMapper actionResultProcessorMapper;

    @Override

    public List<ErrorMessageDto> processUpdateActionResult(UpdateActionResultDto updateActionResultDto) {
        return updateActionResult(updateActionResultDto);
    }

    @Override
    public List<ErrorMessageDto> updateActionResult(UpdateActionResultDto updateActionResultDto) {
        var actionResultDao = actionResultProcessorMapper.mapActionResultDao(updateActionResultDto);
        actionResultRepository.persist(actionResultDao);
        return Collections.emptyList();
    }

    @Override
    public List<ActionResultDao> processGetActionResultsByTestCaseResultId(Long testCaseResultId) {
        return actionResultRepository.findByTestCaseId(testCaseResultId);
    }
}
