package cana.codelessautomation.api.services.schedule;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.*;
import cana.codelessautomation.api.services.schedule.processors.ScheduleServiceProcessor;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.entities.ScheduleDetailEntity;
import cana.codelessautomation.api.services.schedule.verifiers.ScheduleServiceVerifier;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class ScheduleServiceImpl implements ScheduleService {

    @Inject
    ScheduleServiceVerifier scheduleServiceVerifier;

    @Inject
    ScheduleServiceProcessor scheduleServiceProcessor;

    @Inject
    ScheduleIterationRepository scheduleIterationRepository;

    @Override
    public List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto) {
        createScheduleDto.setCreatedOn(OffsetDateTime.now());
        createScheduleDto.setModifiedOn(OffsetDateTime.now());
        createScheduleDto.setCreatedBy(createScheduleDto.getUserId().toString());
        createScheduleDto.setModifiedBy(createScheduleDto.getUserId().toString());
        createScheduleDto.setStatus(ScheduleStatusDao.READY);

        var errors = scheduleServiceVerifier.verifyCreateSchedule(createScheduleDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processCreateSchedule(createScheduleDto);
    }

    @Override
    public List<ErrorMessageDto> getScheduleSummary(ScheduleSummaryDto scheduleSummaryDto) {
        var errors = scheduleServiceVerifier.verifyGetScheduleSummary(scheduleSummaryDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processGetScheduleSummary(scheduleSummaryDto);
    }

    @Override
    public List<ScheduleIterationDao> getScheduleIterations(Long scheduleId) {
        return scheduleIterationRepository.findByScheduleId(scheduleId);
    }

    @Override
    public List<ErrorMessageDto> copyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto) {
        copyTestPlanDetailDto.setCreatedOn(OffsetDateTime.now());
        copyTestPlanDetailDto.setModifiedOn(OffsetDateTime.now());
        copyTestPlanDetailDto.setCreatedBy("SCHEDULED_JOB");
        copyTestPlanDetailDto.setModifiedBy("SCHEDULED_JOB");

        var errors = scheduleServiceVerifier.verifyCopyTestPlanDetail(copyTestPlanDetailDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processCopyTestPlanDetail(copyTestPlanDetailDto);
    }

    @Override
    public List<ErrorMessageDto> getScheduleIterationResult(ScheduleIterationResultDto scheduleIterationResultDto) {
        var errors = scheduleServiceVerifier.verifyGetScheduleIterationResult(scheduleIterationResultDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processGetScheduleIterationResult(scheduleIterationResultDto);
    }

    @Override
    public ScheduleDetailEntity getScheduler(Long scheduleId) {
        return scheduleServiceProcessor.processGetScheduleDetail(scheduleId);
    }

    @Override
    public List<ErrorMessageDto> setAsInProgress(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var errors = scheduleServiceVerifier.verifyUpdateScheduleStatus(updateScheduleStatusReadyDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processUpdateScheduleStatus(updateScheduleStatusReadyDto);
    }

    @Override
    public List<ErrorMessageDto> updateScheduleStatus(UpdateScheduleStatusReadyDto updateScheduleStatusDto) {
        var errors = scheduleServiceVerifier.verifyUpdateScheduleStatus(updateScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processUpdateScheduleStatus(updateScheduleStatusDto);
    }

    @Override
    public List<ErrorMessageDto> reSchedule(ReScheduleStatusDto reScheduleStatusDto) {
        reScheduleStatusDto.setCreatedOn(OffsetDateTime.now());
        reScheduleStatusDto.setModifiedOn(OffsetDateTime.now());
        reScheduleStatusDto.setCreatedBy("SCHEDULED_JOB");
        reScheduleStatusDto.setModifiedBy("SCHEDULED_JOB");

        var errors = scheduleServiceVerifier.verifyReSchedule(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processReSchedule(reScheduleStatusDto);
    }
}
