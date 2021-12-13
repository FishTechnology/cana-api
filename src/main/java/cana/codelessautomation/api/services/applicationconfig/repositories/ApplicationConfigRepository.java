package cana.codelessautomation.api.services.applicationconfig.repositories;

import cana.codelessautomation.api.services.applicationconfig.repositories.daos.ApplicationConfigDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ApplicationConfigRepository implements PanacheRepository<ApplicationConfigDao> {
    public List<ApplicationConfigDao> findByUserIdAndKey(String userId, String key) {
        return list("userId = ?1 and key = ?2 and isActive=true", userId, key);
    }

    public List<ApplicationConfigDao> findByUserId(String userId) {
        return list("userId = ?1 and isActive=true", userId);
    }

    public ApplicationConfigDao findByAppConfigId(UUID applicationConfigId) {
        return find("id = ?1 and isActive=true", applicationConfigId).firstResult();
    }

    public void deleteAppConfig(UUID applicationConfigId) {
        update(" modifiedOn = ?1 , isActive=false  WHERE id = ?2", OffsetDateTime.now(), applicationConfigId);
    }
}
