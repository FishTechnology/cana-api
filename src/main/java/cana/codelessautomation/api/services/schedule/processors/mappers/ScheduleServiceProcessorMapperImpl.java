package cana.codelessautomation.api.services.schedule.processors.mappers;

import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;

import javax.enterprise.context.ApplicationScoped;

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
        return scheduleIterationDao;
    }
}
