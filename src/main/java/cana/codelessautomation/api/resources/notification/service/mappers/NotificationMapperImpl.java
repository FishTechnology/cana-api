package cana.codelessautomation.api.resources.notification.service.mappers;

import cana.codelessautomation.api.resources.notification.service.repositories.daos.NotificationDao;
import cana.codelessautomation.api.resources.schedule.service.dtos.CreateScheduleDto;

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
