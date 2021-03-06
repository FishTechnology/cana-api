package cana.codelessautomation.api.resources.result.testplanresult.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.result.testplanresult.service.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultDao;

import java.util.List;

public interface TestPlanResultVerifier {
    List<ErrorMessageDto> verifyUpdateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    List<ErrorMessageDto> isTestPlanResultIdValid(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    List<ErrorMessageDto> isScheduleIterationIdValid(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    KeyValue<List<ErrorMessageDto>, TestPlanResultDao> isTestPlanResultIdValid(Long testPlanResultId);

    List<ErrorMessageDto> verifyGetTestPlanResultBySchIterId(Long scheduleIterationId);

    List<ErrorMessageDto> isScheduleIterationIdValid(Long scheduleIterationId);
}
