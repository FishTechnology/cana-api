package cana.codelessautomation.api.resources.config.service.repositories;

import cana.codelessautomation.api.resources.config.service.repositories.daos.ConfigDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConfigRepository implements PanacheRepository<ConfigDao> {
    public List<ConfigDao> findByUserId(Long userId) {
        return list("userId = ?1 and isActive=true", userId);
    }
}
