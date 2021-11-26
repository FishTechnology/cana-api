package cana.codelessautomation.api.services.results.action.verifiers;

import cana.codelessautomation.api.services.action.verifiers.ActionServiceVerifier;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.results.action.dtos.UpdateActionResultDto;
import cana.codelessautomation.api.services.results.action.errorcodes.ActionResultErrorCode;
import cana.codelessautomation.api.services.results.action.repositories.ActionResultRepository;
import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.services.results.testcase.verifiers.TestCaseResultVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ActionResultVerifierImpl implements ActionResultVerifier {

    @Inject
    ActionServiceVerifier actionServiceVerifier;

    @Inject
    TestCaseResultVerifier testCaseResultVerifier;

    @Inject
    ActionResultRepository actorResultRepository;

    @Inject
    ActionResultErrorCode actionResultErrorCode;

    @Override
    public List<ErrorMessageDto> verifyUpdateActionResult(UpdateActionResultDto updateActionResultDto) {
        var errorMessages = isTestCaseResultIdValid(updateActionResultDto);
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            return errorMessages;
        }
        return isActionResultIdValid(updateActionResultDto);
    }

    @Override
    public List<ErrorMessageDto> isActionIdValid(UpdateActionResultDto updateActionResultDto) {
        var response = actionServiceVerifier.isActionIdIsValid(updateActionResultDto.getActionId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateActionResultDto.setActionDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isActionResultIdValid(UpdateActionResultDto updateActionResultDto) {
        var response = isActionResultIdValid(updateActionResultDto.getActionResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateActionResultDto.setActionResult(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ActionResultDao> isActionResultIdValid(Long actionResultId) {
        KeyValue<List<ErrorMessageDto>, ActionResultDao> response = new KeyValue<>();
        var scheduleIterationDao = actorResultRepository.findById(actionResultId);
        if (scheduleIterationDao == null) {
            response.setKey(CanaUtility.getErrorMessages(actionResultErrorCode.getActionResultIdNotFound()));
            return response;
        }
        response.setValue(scheduleIterationDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isTestCaseResultIdValid(UpdateActionResultDto updateActionResultDto) {
        var response = testCaseResultVerifier.isTestCaseResultIdValid(updateActionResultDto.getTestCaseResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateActionResultDto.setTestCaseResult(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetActionResultsByTestCaseResultId(Long testCaseResultId) {
        return isTestCaseResultIdValid(testCaseResultId);
    }

    @Override
    public List<ErrorMessageDto> isTestCaseResultIdValid(Long testCaseResultId) {
        var response = testCaseResultVerifier.isTestCaseResultIdValid(testCaseResultId);
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }
}
