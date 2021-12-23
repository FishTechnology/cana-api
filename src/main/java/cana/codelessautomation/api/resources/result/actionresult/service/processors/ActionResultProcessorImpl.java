package cana.codelessautomation.api.resources.result.actionresult.service.processors;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.result.actionresult.service.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.resources.result.actionresult.service.processors.mappers.ActionResultProcessorMapper;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.ActionResultRepository;
import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;

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
