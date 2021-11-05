package cana.codelessautomation.api.services.schedule;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.ScheduleSummaryDto;
import cana.codelessautomation.api.services.schedule.processors.ScheduleServiceProcessor;
import cana.codelessautomation.api.services.schedule.repositories.ScheduleIterationRepository;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
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
}