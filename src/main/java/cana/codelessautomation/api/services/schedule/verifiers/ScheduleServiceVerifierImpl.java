package cana.codelessautomation.api.services.schedule.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.services.environment.verifiers.EnvironmentVerifier;
import cana.codelessautomation.api.services.schedule.dtos.*;
import cana.codelessautomation.api.services.schedule.errorcodes.ScheduleServiceErrorCode;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleRepository;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.services.testplan.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.services.testplan.verifiers.TestplanVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class ScheduleServiceVerifierImpl implements ScheduleServiceVerifier {

    @Inject
    TestplanVerifier testplanVerifier;

    @Inject
    EnvironmentVerifier environmentVerifier;

    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    ScheduleServiceErrorCode scheduleServiceErrorCode;

    @Inject
    ScheduleIterationRepository scheduleIterationRepository;

    @Inject
    TestplanErrorCode testplanErrorCode;


    @Override
    public List<ErrorMessageDto> verifyCreateSchedule(CreateScheduleDto createScheduleDto) {
        var errors = isTestPlanIdValid(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isUserId(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isEnvironmentIdValid(createScheduleDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        return isUserId(scheduleSummaryDto);
    }

    @Override
    public List<ErrorMessageDto> verifyCopyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        return isScheduleIdValid(copyTestPlanDetailDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto) {
        var errors = isScheduleIdValid(scheduleIterationResultDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isScheduleIterationIdValid(scheduleIterationResultDto);
    }

    @Override
    public List<ErrorMessageDto> verifyUpdateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var errors = isScheduleIdValid(updateScheduleStatusReadyDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isScheduleStatusValid(updateScheduleStatusReadyDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isScheduleIterationStatusValid(updateScheduleStatusReadyDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isTestPlanStatusValid(updateScheduleStatusReadyDto);
    }

    @Override
    public List<ErrorMessageDto> isTestPlanStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var testPlanDto = updateScheduleStatusReadyDto
                .getSchedule()
                .getTestplanDaos();
        if (testPlanDto.getStatus() != TestPlanStatusDao.READY) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIsNotInReadyStatus());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIterationStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var scheduleIterationDao = updateScheduleStatusReadyDto
                .getSchedule()
                .getScheduleIterations()
                .get(0);

        if (updateScheduleStatusReadyDto.getScheduleStatus() == ScheduleStatusDao.INPROGRESS) {
            if (updateScheduleStatusReadyDto.getSchedule().getScheduleIterations().get(0).getStatus() != ScheduleStatusDao.READY) {
                return CanaUtility.getErrorMessages(ScheduleServiceErrorCode.getScheduleNotInReadyStatus);
            }
        }

        if (updateScheduleStatusReadyDto.getScheduleStatus() == updateScheduleStatusReadyDto.getSchedule().getScheduleIterations().get(0).getStatus()) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleStatusAreSame(), "existing status and upcoming status are same");
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        if (updateScheduleStatusReadyDto.getScheduleStatus() == ScheduleStatusDao.INPROGRESS) {
            if (updateScheduleStatusReadyDto.getSchedule().getStatus() != ScheduleStatusDao.READY) {
                return CanaUtility.getErrorMessages(ScheduleServiceErrorCode.getScheduleNotInReadyStatus);
            }
        }

        if (updateScheduleStatusReadyDto.getScheduleStatus() == updateScheduleStatusReadyDto.getSchedule().getStatus()) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleStatusAreSame(), "existing status and upcoming status are same");
        }

        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIdValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var response = isScheduleIdValid(updateScheduleStatusReadyDto.getScheduleId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateScheduleStatusReadyDto.setSchedule(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIterationIdValid(ScheduleIterationResultDto scheduleIterationResultDto) {
        var response = isScheduleIterationIdValid(scheduleIterationResultDto.getScheduleIterationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        scheduleIterationResultDto.setScheduleIteration(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIdValid(ScheduleIterationResultDto scheduleIterationResultDto) {
        var response = isScheduleIdValid(scheduleIterationResultDto.getScheduleId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        scheduleIterationResultDto.setSchedule(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ScheduleIterationDao> isScheduleIterationIdValid(Long scheduleIterationId) {
        KeyValue<List<ErrorMessageDto>, ScheduleIterationDao> response = new KeyValue<>();
        var scheduleIterationDao = scheduleIterationRepository.findById(scheduleIterationId);
        if (scheduleIterationDao == null) {
            response.setKey(CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleIdNotFound()));
            return response;
        }
        response.setValue(scheduleIterationDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isScheduleIdValid(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        var response = isScheduleIdValid(copyTestPlanDetailDto.getScheduleId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        copyTestPlanDetailDto.setSchedule(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public KeyValue<List<ErrorMessageDto>, ScheduleDao> isScheduleIdValid(Long scheduleId) {
        KeyValue<List<ErrorMessageDto>, ScheduleDao> response = new KeyValue<>();
        var scheduleDao = scheduleRepository.findById(scheduleId);
        if (scheduleDao == null) {
            response.setKey(CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleIdNotFound()));
            return response;
        }
        response.setValue(scheduleDao);
        return response;
    }

    @Override
    public List<ErrorMessageDto> isUserId(ScheduleSummaryDto scheduleSummaryDto) {
        var response = customerServiceVerifier.isUserIdValid(scheduleSummaryDto.getUserId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        scheduleSummaryDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }


    @Override
    public List<ErrorMessageDto> isUserId(CreateScheduleDto createScheduleDto) {
        var response = customerServiceVerifier.isUserIdValid(createScheduleDto.getUserId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createScheduleDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanIdValid(CreateScheduleDto createScheduleDto) {
        var response = testplanVerifier.isTestplanIdValid(createScheduleDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createScheduleDto.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentIdValid(CreateScheduleDto createScheduleDto) {
        var response = environmentVerifier.isEnvironmentIdValid(createScheduleDto.getEnvironmentId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        createScheduleDto.setEnvironment(response.getValue());
        return Collections.emptyList();
    }
}
