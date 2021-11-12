package cana.codelessautomation.api.services.notification.repositories;

import cana.codelessautomation.api.services.notification.repositories.daos.NotificationDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationRepository implements PanacheRepository<NotificationDao> {
}
