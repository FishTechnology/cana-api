package cana.codelessautomation.api.services.environment.repositories;

import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class EnvironmentRepository implements PanacheRepository<EnvironmentDao> {
    public EnvironmentDao findByNameAndUserId(String name, Long userId) {
        return find("name = ?1 and userid = ?2 and isactive=true", name, userId).firstResult();
    }

    public List<EnvironmentDao> findByUserId(Long userId) {
        return list("userid = ?1 and isactive=true", userId);
    }

    public EnvironmentDao findByIdAndActive(Long environmentId) {
        return find("id = ?1 and isactive=true", environmentId).firstResult();
    }

    public void deleteEnvironment(Long environmentId) {
        update("isactive = false , modifiedon = ?1 WHERE id = ?2 ", OffsetDateTime.now(), environmentId);
    }
}
