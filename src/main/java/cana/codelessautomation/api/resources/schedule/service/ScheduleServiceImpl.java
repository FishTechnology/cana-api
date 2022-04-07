package cana.codelessautomation.api.resources.schedule.service;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.commons.utilities.CanaUtility;
import cana.codelessautomation.api.resources.schedule.service.dtos.*;
import cana.codelessautomation.api.resources.schedule.service.processors.ScheduleServiceProcessor;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleEntity;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities.ScheduleSummaryEntity;
import cana.codelessautomation.api.resources.schedule.service.verifiers.ScheduleServiceVerifier;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class ScheduleServiceImpl implements ScheduleService {

    @Inject
    ScheduleServiceVerifier scheduleServiceVerifier;

    @Inject
    ScheduleServiceProcessor scheduleServiceProcessor;

    @Override
    public List<ErrorMessageDto> createSchedule(CreateScheduleDto createScheduleDto) {
        createScheduleDto.setCreatedBy(createScheduleDto.getUserId().toString());
        createScheduleDto.setModifiedBy(createScheduleDto.getUserId().toString());
        createScheduleDto.setStatus(ScheduleStatusDao.QUEUE);

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
    public List<ErrorMessageDto> getScheduleIterations(GetScheduleIterationsDto getScheduleIterationsDto) {
        var errors = scheduleServiceVerifier.verifyGetScheduleIterations(getScheduleIterationsDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processGetScheduleIterations(getScheduleIterationsDto);
    }

    @Override
    public List<ErrorMessageDto> copyTestPlanDetail(CopyTestPlanDetailDto copyTestPlanDetailDto) {
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
    public ScheduleSummaryEntity getScheduler(Long scheduleId) {
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
        reScheduleStatusDto.setCreatedBy(reScheduleStatusDto.getUserId().toString());
        reScheduleStatusDto.setModifiedBy(reScheduleStatusDto.getUserId().toString());

        var errors = scheduleServiceVerifier.verifyReSchedule(reScheduleStatusDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processReSchedule(reScheduleStatusDto);
    }

    @Override
    public List<ScheduleEntity> getRunningScheduleByAppId(Long applicationId) {
        return ScheduleEntity.findByAppIdStatus(applicationId, ScheduleStatusDao.INPROGRESS);
    }

    @Override
    public ScheduleEntity getRunningSchedule() {
        return ScheduleEntity.findByStatus(ScheduleStatusDao.INPROGRESS);
    }

    @Override
    public ScheduleEntity getScheduleToExecute() {
        return ScheduleEntity.findByStatus(ScheduleStatusDao.QUEUE);
    }

    @Override
    public List<ErrorMessageDto> updateScheduleSession(UpdateScheduleSessionDto updateScheduleSessionDto) {
        var errors = scheduleServiceVerifier.verifyUpdateScheduleSession(updateScheduleSessionDto);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ValidationException(CanaUtility.getErrorMessageModels(errors));
        }
        return scheduleServiceProcessor.processUpdateScheduleSession(updateScheduleSessionDto);
    }
}
