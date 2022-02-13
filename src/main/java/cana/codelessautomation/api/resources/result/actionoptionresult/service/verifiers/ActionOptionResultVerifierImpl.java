package cana.codelessautomation.api.resources.result.actionoptionresult.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.dto.UpdateActionOptionResultDto;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.errorcodes.ActionOptionResultErrorCodes;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.ActionOptionResultRepository;
import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import cana.codelessautomation.api.resources.result.actionresult.service.verifiers.ActionResultVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionOptionResultVerifierImpl implements ActionOptionResultVerifier {
    @Inject
    ActionResultVerifier actionResultVerifier;

    @Inject
    ActionOptionResultRepository actionOptionResultRepository;

    @Inject
    ActionOptionResultErrorCodes actionOptionResultErrorCodes;

    @Override
    public List<ErrorMessageDto> verifyGetActionResultsByTestCaseResultId(Long actionResultId) {
        return isActionResultIdValid(actionResultId);
    }

    @Override
    public List<ErrorMessageDto> isActionResultIdValid(Long actionResultId) {
        var response = actionResultVerifier.isActionResultIdValid(actionResultId);
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateActionOptionResult(
            UpdateActionOptionResultDto updateActionOptionResultDto) {
        var errorMessages = isActionOptionResultIdValid(updateActionOptionResultDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return errorMessages;
        }
        return isActionResultId(updateActionOptionResultDto);
    }

    @Override
    public List<ErrorMessageDto> isActionResultId(UpdateActionOptionResultDto updateActionOptionResultDto) {
        var response = actionResultVerifier.isActionResultIdValid(updateActionOptionResultDto.getActionResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        updateActionOptionResultDto.setActionResultDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isActionOptionResultIdValid(UpdateActionOptionResultDto updateActionOptionResultDto) {
        var response = isActionOptionResultIdValid(updateActionOptionResultDto.getActionOptionResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        updateActionOptionResultDto.setActionOptionResultDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ActionOptionResultDao> isActionOptionResultIdValid(
            Long actionOptionResultId) {
        KeyValue<List<ErrorMessageDto>, ActionOptionResultDao> response = new KeyValue<>();
        var scheduleIterationDao = actionOptionResultRepository.findById(actionOptionResultId);
        if (scheduleIterationDao == null) {
            response.setKey(
                    CanaUtility.getErrorMessages(actionOptionResultErrorCodes.getActionOptionResultIdNotFound()));
            return response;
        }
        response.setValue(scheduleIterationDao);
        return response;
    }
}
