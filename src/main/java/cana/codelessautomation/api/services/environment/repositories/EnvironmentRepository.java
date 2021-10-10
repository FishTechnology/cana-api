package cana.codelessautomation.api.services.environment.repositories;

import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentRepository implements PanacheRepository<EnvironmentDao> {
    public EnvironmentDao findByNameAndUserId(String name, Long userId) {
        return find("name = ?1 and userid = ?2 and isactive=true", name, userId).firstResult();
    }
}
