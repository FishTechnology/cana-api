package cana.codelessautomation.api.resources.applicationconfig.service.repositories;

import cana.codelessautomation.api.resources.applicationconfig.service.repositories.daos.ApplicationConfigDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class ApplicationConfigRepository implements PanacheRepository<ApplicationConfigDao> {
    public List<ApplicationConfigDao> findByUserIdAndKey(String userId, String key) {
        return list("userId = ?1 and key = ?2 and isActive=true", userId, key);
    }

    public List<ApplicationConfigDao> findByUserId(String userId) {
        return list("userId = ?1 and isActive=true", userId);
    }

    public ApplicationConfigDao findByAppConfigId(Long applicationId, Long applicationConfigId) {
        return find("id = ?1 and applicationid = ?2 and isActive=true", applicationConfigId, applicationId).firstResult();
    }

    public void deleteAppConfig(Long applicationId, Long applicationConfigId) {
        update(" modifiedOn = ?1 , isActive=false  WHERE id = ?2 and applicationid = ?3", OffsetDateTime.now(), applicationConfigId, applicationId);
    }

    public List<ApplicationConfigDao> findByAppId(Long applicationId) {
        return list("applicationId = ?1", applicationId);
    }
}
