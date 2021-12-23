package cana.codelessautomation.api.resources.notification.service.mappers;

import cana.codelessautomation.api.resources.notification.service.repositories.daos.NotificationDao;
import cana.codelessautomation.api.resources.schedule.service.dtos.CreateScheduleDto;

public interface NotificationMapper {
    NotificationDao mapNotificationDao(CreateScheduleDto createScheduleDto);
}
