package cana.codelessautomation.api.services.schedule.processors.mappers;

import cana.codelessautomation.api.commons.CanaConstants;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.dtos.UpdateScheduleStatusReadyDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
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
        scheduleDao.setCreatedOn(createScheduleDto.getCreatedOn());
        scheduleDao.setModifiedOn(createScheduleDto.getModifiedOn());
        scheduleDao.setCreatedBy(createScheduleDto.getCreatedBy());
        scheduleDao.setModifiedBy(createScheduleDto.getModifiedBy());
        scheduleDao.setStatus(createScheduleDto.getStatus());
        return scheduleDao;
    }

    @Override
    public ScheduleIterationDao mapScheduleIterationDao(CreateScheduleDto createScheduleDto) {
        ScheduleIterationDao scheduleIterationDao = new ScheduleIterationDao();
        scheduleIterationDao.setStatus(createScheduleDto.getStatus());
        scheduleIterationDao.setComments(createScheduleDto.getComments());
        scheduleIterationDao.setScheduleId(createScheduleDto.getId());
        scheduleIterationDao.setCreatedOn(createScheduleDto.getCreatedOn());
        scheduleIterationDao.setModifiedOn(createScheduleDto.getModifiedOn());
        scheduleIterationDao.setCreatedBy(createScheduleDto.getCreatedBy());
        scheduleIterationDao.setModifiedBy(createScheduleDto.getModifiedBy());
        scheduleIterationDao.setIsDisableScreenshot(createScheduleDto.getIsDisableScreenshot());
        scheduleIterationDao.setIsRecordVideoEnabled(createScheduleDto.getIsRecordVideoEnabled());
        scheduleIterationDao.setIsCaptureNetworkTraffic(createScheduleDto.getIsCaptureNetworkTraffic());
        scheduleIterationDao.setBrowserType(createScheduleDto.getBrowserType());
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
}
