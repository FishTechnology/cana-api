package cana.codelessautomation.api.services.notification.processors.mappers;

import cana.codelessautomation.api.services.notification.repositories.daos.NotificationDao;
import cana.codelessautomation.api.services.schedule.dtos.CreateScheduleDto;

public interface NotificationMapper {
    NotificationDao mapNotificationDao(CreateScheduleDto createScheduleDto);
}
