package cana.codelessautomation.api.resources.application.service.repositories;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ApplicationRepository implements PanacheRepository<ApplicationDao> {
    public ApplicationDao findByNameAndUserId(String name, Long userId) {
        return find("name = ?1 and userId = ?2 and isActive=true", name, userId).firstResult();
    }

    public List<ApplicationDao> findByUserId(Long userId) {
        return list("userId = ?1 and isActive=true", userId);
    }

    public ApplicationDao findById(Long applicationId) {
        return find("id = ?1 and isActive=true", applicationId).firstResult();
    }

    public ApplicationDao findByAppId(Long applicationId) {
        return find("id = ?1 and isActive=true", applicationId).firstResult();
    }
}
