package cana.codelessautomation.api.resources.system.service.repositories;

import cana.codelessautomation.api.resources.system.service.repositories.daos.SystemConfigDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class SystemConfigRepository implements PanacheRepository<SystemConfigDao> {
    public List<SystemConfigDao> findByAppId() {
        return list("isActive=true");
    }
}
