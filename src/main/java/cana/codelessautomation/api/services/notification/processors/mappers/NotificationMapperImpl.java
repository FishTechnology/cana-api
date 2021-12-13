package cana.codelessautomation.api.services.notification.processors.mappers;

import cana.codelessautomation.api.services.notification.repositories.daos.NotificationDao;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;

@ApplicationScoped
public class NotificationMapperImpl implements NotificationMapper {
    @Override
    public NotificationDao mapNotificationDao(CreateScheduleDto createScheduleDto) {
        NotificationDao notificationDao = new NotificationDao();
        notificationDao.setScheduleIterationId(createScheduleDto.getIterationId());
        notificationDao.setCreatedOn(OffsetDateTime.now());
        notificationDao.setModifiedOn(OffsetDateTime.now());
        notificationDao.setCreatedBy(createScheduleDto.getCreatedBy());
        notificationDao.setModifiedBy(createScheduleDto.getModifiedBy());
        notificationDao.setEmailAddress(createScheduleDto.getNotification().getEmailAddress());
        notificationDao.setIsActive(true);
        return notificationDao;
    }
}