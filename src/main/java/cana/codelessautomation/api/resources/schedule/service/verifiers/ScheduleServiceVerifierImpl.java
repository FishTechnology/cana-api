package cana.codelessautomation.api.resources.schedule.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.application.service.verifiers.ApplicationVerifier;
import cana.codelessautomation.api.resources.config.services.configservice.verifiers.ConfigServiceVerifier;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.errorcodes.ScheduleServiceErrorCode;
import cana.codelessautomation.api.resources.schedule.service.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.resources.schedule.service.repositories.ScheduleRepository;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.resources.testplan.service.errorcodes.TestplanErrorCode;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.resources.testplan.service.verifiers.TestplanVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class ScheduleServiceVerifierImpl implements ScheduleServiceVerifier {

    @Inject
    TestplanVerifier testplanVerifier;

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

    @Inject
    ConfigServiceVerifier configServiceVerifier;

    @Inject
    ApplicationVerifier applicationVerifier;


    @Override
    public List<ErrorMessageDto> verifyCreateSchedule(CreateScheduleDto createScheduleDto) {
        var errors = isTestPlanIdValid(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isTestPlanStatusValid(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isUserId(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isEnvironmentIdValid(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isCheckTestPlanAlreadyScheduled(createScheduleDto);
    }

    @Override
    public List<ErrorMessageDto> isCheckTestPlanAlreadyScheduled(CreateScheduleDto createScheduleDto) {
        var schedules = scheduleRepository.findByTestPlanIdAndEnvId(createScheduleDto.getTestPlanId(), createScheduleDto.getEnvironmentId(), ScheduleStatusDao.QUEUE);
        if (CollectionUtils.isEmpty(schedules)) {
            return Collections.emptyList();
        }
        createScheduleDto.setSchedules(schedules);
        return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getTestPlanIsAlreadyScheduled());
    }

    @Override
    public List<ErrorMessageDto> isTestPlanStatusValid(CreateScheduleDto createScheduleDto) {
        if (Objects.isNull(createScheduleDto.getTestplan())) {
            return Collections.emptyList();
        }
        if (createScheduleDto.getTestplan().getStatus() != TestPlanStatusDao.READY) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIsNotInReadyStatus(), "Test plan status is ready status");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyGetScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        return isApplicationIdValid(scheduleSummaryDto);
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(ScheduleSummaryDto scheduleSummaryDto) {
        var response = applicationVerifier.isApplicationIdValid(scheduleSummaryDto.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        scheduleSummaryDto.setApplication(response.getValue());
        return Collections.emptyList();
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
        errors = isApplicationIdValid(updateScheduleStatusReadyDto);
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
    public List<ErrorMessageDto> isApplicationIdValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        if (Objects.isNull(updateScheduleStatusReadyDto.getApplicationId())) {
            return Collections.emptyList();
        }
        var response = applicationVerifier.isApplicationIdValid(updateScheduleStatusReadyDto.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        updateScheduleStatusReadyDto.setApplication(response.getValue());

        if (!Objects.equals(updateScheduleStatusReadyDto.getSchedule().getApplicationId(), updateScheduleStatusReadyDto.getApplication().getId())) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleIdAndAppIdNotMapped(), "schedule status is ready status");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> verifyReSchedule(ReScheduleStatusDto reScheduleStatusDto) {
        var errors = isScheduleIdValid(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        errors = isApplicationIdValid(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isScheduleStatusValid(reScheduleStatusDto);
    }

    @Override
    public List<ErrorMessageDto> verifyGetScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto) {
        var errors = isScheduleIdValid(getScheduleIterationsDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            return errors;
        }
        return isApplicationIdValid(getScheduleIterationsDto);
    }

    @Override
    public List<ErrorMessageDto> isScheduleIdValid(GetScheduleIterationsDto getScheduleIterationsDto) {
        var response = isScheduleIdValid(getScheduleIterationsDto.getScheduleId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        getScheduleIterationsDto.setSchedule(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(GetScheduleIterationsDto getScheduleIterationsDto) {
        if (Objects.isNull(getScheduleIterationsDto.getApplicationId())) {
            return Collections.emptyList();
        }
        var response = applicationVerifier.isApplicationIdValid(getScheduleIterationsDto.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        getScheduleIterationsDto.setApplication(response.getValue());

        if (!Objects.equals(getScheduleIterationsDto.getSchedule().getApplicationId(), getScheduleIterationsDto.getApplication().getId())) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleIdAndAppIdNotMapped(), "schedule status is ready status");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isApplicationIdValid(ReScheduleStatusDto reScheduleStatusDto) {
        if (Objects.isNull(reScheduleStatusDto.getApplicationId())) {
            return Collections.emptyList();
        }
        var response = applicationVerifier.isApplicationIdValid(reScheduleStatusDto.getApplicationId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        reScheduleStatusDto.setApplication(response.getValue());

        if (!Objects.equals(reScheduleStatusDto.getScheduleDao().getApplicationId(), reScheduleStatusDto.getApplication().getId())) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleIdAndAppIdNotMapped(), "schedule status is ready status");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleStatusValid(ReScheduleStatusDto reScheduleStatusDto) {
        if (reScheduleStatusDto.getScheduleDao().getStatus() == ScheduleStatusDao.READY) {
            return CanaUtility.getErrorMessages(scheduleServiceErrorCode.getScheduleStatusIsInValid(), "schedule status is ready status");
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIdValid(ReScheduleStatusDto reScheduleStatusDto) {
        var response = isScheduleIdValid(reScheduleStatusDto.getScheduleId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        reScheduleStatusDto.setScheduleDao(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isTestPlanStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var testPlanDto = updateScheduleStatusReadyDto.getSchedule().getTestplanDaos();
        if (testPlanDto.getStatus() != TestPlanStatusDao.READY) {
            return CanaUtility.getErrorMessages(testplanErrorCode.getTestPlanIsNotInReadyStatus());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isScheduleIterationStatusValid(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var scheduleIterationDao = updateScheduleStatusReadyDto.getSchedule().getScheduleIterations().get(0);

        if (updateScheduleStatusReadyDto.getScheduleStatus() == ScheduleStatusDao.INPROGRESS) {
            if (updateScheduleStatusReadyDto.getSchedule().getScheduleIterations().get(0).getStatus() != ScheduleStatusDao.QUEUE) {
                return CanaUtility.getErrorMessages(ScheduleServiceErrorCode.getScheduleNotInQueueStatus);
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
            if (updateScheduleStatusReadyDto.getSchedule().getStatus() != ScheduleStatusDao.QUEUE) {
                return CanaUtility.getErrorMessages(ScheduleServiceErrorCode.getScheduleNotInQueueStatus);
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
        var response = testplanVerifier.isTestplanIdValid(createScheduleDto.getApplicationId(), createScheduleDto.getTestPlanId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }
        createScheduleDto.setTestplan(response.getValue());
        return Collections.emptyList();
    }

    @Override
    public List<ErrorMessageDto> isEnvironmentIdValid(CreateScheduleDto createScheduleDto) {
        var response = configServiceVerifier.isConfigIdValid(createScheduleDto.getEnvironmentId());
        if (CollectionUtils.isNotEmpty(response.getKey())) {
            return response.getKey();
        }

        createScheduleDto.setEnvironment(response.getValue());
        return Collections.emptyList();
    }
}
