package cana.codelessautomation.api.services.results.testcase.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.results.testcase.dtos.UpdateTestCaseResultStatusDto;
import cana.codelessautomation.api.services.results.testcase.errorcodes.TestCaseResultErrorCode;
import cana.codelessautomation.api.services.results.testcase.repositories.TestCaseResultRepository;
import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.results.testplan.verifiers.TestPlanResultVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestCaseResultVerifierImpl implements TestCaseResultVerifier {

    @Inject
    TestPlanResultVerifier testPlanResultVerifier;

    @Inject
    TestCaseResultRepository testCaseResultRepository;

    @Inject
    TestCaseResultErrorCode testCaseResultErrorCode;

    @Override
    public List<ErrorMessageDto> verifyUpdateTestCaseResultStatus(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var errors = isTestPlanResultIdValid(updateTestCaseResultStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestCaseResultIdValid(updateTestCaseResultStatusDto);
    }

    @Override
    public List<ErrorMessageDto> isTestCaseResultIdValid(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var response = isTestCaseResultIdValid(updateTestCaseResultStatusDto.getTestCaseResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestCaseResultStatusDto.setTestCaseResultDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, TestCaseResultDao> isTestCaseResultIdValid(Long testCaseResultId) {
        KeyValue<List<ErrorMessageDto>, TestCaseResultDao> response = new KeyValue<>();
        var scheduleIterationDao = testCaseResultRepository.findById(testCaseResultId);
        if (scheduleIterationDao == null) {
            response.setKey(CanaUtility.getErrorMessages(testCaseResultErrorCode.getTestCaseResultIdNotFound()));
            return response;
        }
        response.setValue(scheduleIterationDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isTestPlanResultIdValid(UpdateTestCaseResultStatusDto updateTestCaseResultStatusDto) {
        var response = testPlanResultVerifier.isTestPlanResultIdValid(updateTestCaseResultStatusDto.getTestPlanResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestCaseResultStatusDto.setTestPlanResult(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetTestCaseResultByPlanResultId(Long testPlanResultId) {
        return isTestPlanResultIdValid(testPlanResultId);
    }

    @Override
    public List<ErrorMessageDto> isTestPlanResultIdValid(Long testPlanResultId) {
        var response = testPlanResultVerifier.isTestPlanResultIdValid(testPlanResultId);
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }
}
