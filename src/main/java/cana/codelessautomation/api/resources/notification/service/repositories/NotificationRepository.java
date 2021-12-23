package cana.codelessautomation.api.resources.notification.service.repositories;

import cana.codelessautomation.api.resources.notification.service.repositories.daos.NotificationDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationRepository implements PanacheRepository<NotificationDao> {
}
