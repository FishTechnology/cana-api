package cana.codelessautomation.api.services.results.testplan.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.errorcodes.TestPlanResultErrorCode;
import cana.codelessautomation.api.services.results.testplan.repositories.TestPlanResultRepository;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import cana.codelessautomation.api.services.schedule.verifiers.ScheduleServiceVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class TestPlanResultVerifierImpl implements TestPlanResultVerifier {

    @Inject
    ScheduleServiceVerifier scheduleServiceVerifier;

    @Inject
    TestPlanResultRepository testPlanResultRepository;

    @Inject
    TestPlanResultErrorCode testPlanResultErrorCode;

    @Override
    public List<ErrorMessageDto> verifyUpdateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        isScheduleIterationIdValid(updateTestPlanResultStatusDto);
        return isTestPlanResultIdValid(updateTestPlanResultStatusDto);
    }

    @Override
    public List<ErrorMessageDto> isTestPlanResultIdValid(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        var response = isTestPlanResultIdValid(updateTestPlanResultStatusDto.getTestPlanResultId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestPlanResultStatusDto.setTestPlanResult(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIterationIdValid(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto) {
        var response = scheduleServiceVerifier.isScheduleIterationIdValid(updateTestPlanResultStatusDto.getScheduleIterationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateTestPlanResultStatusDto.setScheduleIteration(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, TestPlanResultDao> isTestPlanResultIdValid(Long testPlanResultId) {
        KeyValue<List<ErrorMessageDto>, TestPlanResultDao> response = new KeyValue<>();
        var scheduleIterationDao = testPlanResultRepository.findById(testPlanResultId);
        if (scheduleIterationDao == null) {
            response.setKey(CanaUtility.getErrorMessages(testPlanResultErrorCode.getTestPlanResultIdNotFound()));
            return response;
        }
        response.setValue(scheduleIterationDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> verifyGetTestPlanResultBySchIterId(Long scheduleIterationId) {
        return isScheduleIterationIdValid(scheduleIterationId);
    }

    @Override
    public List<ErrorMessageDto> isScheduleIterationIdValid(Long scheduleIterationId) {
        var response = scheduleServiceVerifier.isScheduleIterationIdValid(scheduleIterationId);
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        return Collections.emptyList();
    }
}
