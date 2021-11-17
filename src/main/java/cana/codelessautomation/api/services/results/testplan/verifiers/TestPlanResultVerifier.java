package cana.codelessautomation.api.services.results.testplan.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.results.testplan.dtos.UpdateTestPlanResultStatusDto;
import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;

import java.util.List;

public interface TestPlanResultVerifier {
    List<ErrorMessageDto> verifyUpdateTestPlanResultStatus(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    List<ErrorMessageDto> isTestPlanResultIdValid(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    List<ErrorMessageDto> isScheduleIterationIdValid(UpdateTestPlanResultStatusDto updateTestPlanResultStatusDto);

    KeyValue<List<ErrorMessageDto>, TestPlanResultDao> isTestPlanResultIdValid(Long testPlanResultId);

    List<ErrorMessageDto> verifyGetTestPlanResultBySchIterId(Long scheduleIterationId);

    List<ErrorMessageDto> isScheduleIterationIdValid(Long scheduleIterationId);
}
