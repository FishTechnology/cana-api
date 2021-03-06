package cana.codelessautomation.api.resources.schedule.service.processors.mappers;

import cana.codelessautomation.api.commons.CanaConstants;
import cana.codelessautomation.api.resources.schedule.service.dtos.CreateScheduleDto;
import cana.codelessautomation.api.resources.schedule.service.dtos.ReScheduleStatusDto;
import cana.codelessautomation.api.resources.schedule.service.dtos.UpdateScheduleSessionDto;
import cana.codelessautomation.api.resources.schedule.service.dtos.UpdateScheduleStatusReadyDto;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import org.apache.commons.lang3.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class ScheduleServiceProcessorMapperImpl implements ScheduleServiceProcessorMapper {
    @Override
    public ScheduleDao mapScheduleDao(CreateScheduleDto createScheduleDto) {
        ScheduleDao scheduleDao = new ScheduleDao();
        scheduleDao.setUserId(createScheduleDto.getUserId());
        scheduleDao.setEnvironmentId(createScheduleDto.getEnvironmentId());
        scheduleDao.setTestPlanId(createScheduleDto.getTestPlanId());
        scheduleDao.setCreatedOn(OffsetDateTime.now());
        scheduleDao.setModifiedOn(OffsetDateTime.now());
        scheduleDao.setCreatedBy(createScheduleDto.getCreatedBy());
        scheduleDao.setModifiedBy(createScheduleDto.getModifiedBy());
        scheduleDao.setApplicationId(createScheduleDto.getApplicationId());
        scheduleDao.setStatus(createScheduleDto.getStatus());
        return scheduleDao;
    }

    @Override
    public ScheduleIterationDao mapScheduleIterationDao(CreateScheduleDto createScheduleDto) {
        ScheduleIterationDao scheduleIterationDao = new ScheduleIterationDao();
        scheduleIterationDao.setStatus(createScheduleDto.getStatus());
        scheduleIterationDao.setComments(createScheduleDto.getComments());
        scheduleIterationDao.setScheduleId(createScheduleDto.getId());
        scheduleIterationDao.setCreatedOn(OffsetDateTime.now());
        scheduleIterationDao.setModifiedOn(OffsetDateTime.now());
        scheduleIterationDao.setCreatedBy(createScheduleDto.getCreatedBy());
        scheduleIterationDao.setModifiedBy(createScheduleDto.getModifiedBy());
        scheduleIterationDao.setIsDisableScreenshot(createScheduleDto.getIsDisableScreenshot());
        scheduleIterationDao.setIsRecordVideoEnabled(createScheduleDto.getIsRecordVideoEnabled());
        scheduleIterationDao.setIsCaptureNetworkTraffic(createScheduleDto.getIsCaptureNetworkTraffic());
        scheduleIterationDao.setBrowserType(createScheduleDto.getBrowserType());
        scheduleIterationDao.setRetryCount(createScheduleDto.getRetryCount());
        return scheduleIterationDao;
    }

    @Override
    public ScheduleDao mapScheduleDao(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        ScheduleDao scheduleDao = updateScheduleStatusReadyDto.getSchedule();
        scheduleDao.setStatus(updateScheduleStatusReadyDto.getScheduleStatus());
        scheduleDao.setModifiedBy(CanaConstants.scheduleUser);
        scheduleDao.setModifiedOn(OffsetDateTime.now());
        return scheduleDao;
    }

    @Override
    public ScheduleIterationDao mapScheduleIterationDao(UpdateScheduleStatusReadyDto updateScheduleStatusReadyDto) {
        var scheduleIterationDao = updateScheduleStatusReadyDto.getSchedule().getScheduleIterations().get(0);
        scheduleIterationDao.setStatus(updateScheduleStatusReadyDto.getScheduleStatus());
        scheduleIterationDao.setModifiedOn(OffsetDateTime.now());
        scheduleIterationDao.setModifiedBy(CanaConstants.scheduleUser);
        if (updateScheduleStatusReadyDto.getScheduleStatus() == ScheduleStatusDao.INPROGRESS) {
            scheduleIterationDao.setStartedOn(OffsetDateTime.now());
        } else {
            scheduleIterationDao.setCompletedOn(OffsetDateTime.now());
            scheduleIterationDao.setErrorMessage(updateScheduleStatusReadyDto.getErrorMessage());
            if (!StringUtils.isEmpty(updateScheduleStatusReadyDto.getTotalDuration())) {
                scheduleIterationDao.setTotalDuration(updateScheduleStatusReadyDto.getTotalDuration());
            }
        }
        return scheduleIterationDao;
    }

    @Override
    public ScheduleDao mapScheduleDao(ReScheduleStatusDto reScheduleStatusDto) {
        ScheduleDao scheduleDao = reScheduleStatusDto.getScheduleDao();
        scheduleDao.setStatus(ScheduleStatusDao.QUEUE);
        scheduleDao.setModifiedBy(reScheduleStatusDto.getModifiedBy());
        scheduleDao.setModifiedOn(OffsetDateTime.now());
        return scheduleDao;
    }

    @Override
    public ScheduleIterationDao mapScheduleIterationDao(ReScheduleStatusDto reScheduleStatusDto) {
        ScheduleIterationDao existingScheduleIteration = reScheduleStatusDto.getScheduleIteration();
        ScheduleIterationDao scheduleIterationDao = new ScheduleIterationDao();
        scheduleIterationDao.setRetryCount(existingScheduleIteration.getRetryCount());
        scheduleIterationDao.setStatus(ScheduleStatusDao.QUEUE);
        scheduleIterationDao.setComments(reScheduleStatusDto.getComments());
        scheduleIterationDao.setScheduleId(reScheduleStatusDto.getScheduleId());
        scheduleIterationDao.setCreatedOn(OffsetDateTime.now());
        scheduleIterationDao.setModifiedOn(OffsetDateTime.now());
        scheduleIterationDao.setCreatedBy(reScheduleStatusDto.getCreatedBy());
        scheduleIterationDao.setModifiedBy(reScheduleStatusDto.getModifiedBy());
        scheduleIterationDao.setIsDisableScreenshot(existingScheduleIteration.getIsDisableScreenshot());
        scheduleIterationDao.setIsRecordVideoEnabled(existingScheduleIteration.getIsRecordVideoEnabled());
        scheduleIterationDao.setIsCaptureNetworkTraffic(existingScheduleIteration.getIsCaptureNetworkTraffic());
        scheduleIterationDao.setBrowserType(existingScheduleIteration.getBrowserType());
        return scheduleIterationDao;
    }

    @Override
    public ScheduleIterationDao mapScheduleIterationDao(ReScheduleStatusDto reScheduleStatusDto, ScheduleIterationDao scheduleIterationDao) {
        scheduleIterationDao.setStatus(ScheduleStatusDao.RE_SCHEDULE);
        scheduleIterationDao.setModifiedOn(OffsetDateTime.now());
        scheduleIterationDao.setModifiedBy(reScheduleStatusDto.getModifiedBy());
        return scheduleIterationDao;
    }

    @Override
    public ScheduleIterationDao mapScheduleIterationSession(UpdateScheduleSessionDto updateScheduleSessionDto, ScheduleIterationDao scheduleIterationDao) {
        scheduleIterationDao.setSessionId(updateScheduleSessionDto.getSessionId());
        scheduleIterationDao.setModifiedOn(OffsetDateTime.now());
        return scheduleIterationDao;
    }
}
