package cana.codelessautomation.api.resources.config.services.configservice.repositories;

import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConfigRepository implements PanacheRepository<ConfigDao> {
    public List<ConfigDao> findByUserId(Long applicationId, ConfigTypeDao configType) {
        return list("applicationId = ?1 and type = ?2  and isActive=true", applicationId, configType.name());
    }

    public List<ConfigDao> findByUserIdAndTypeAndName(Long userId, String configType, String name) {
        return list("userId = ?1 and type = ?2 and lower(name) = lower(?3) and isActive=true", userId, configType, name);
    }

    public List<ConfigDao> findByUserIdAndTypeAndNameAndIdentifier(Long userId, String configType, String name, Long identifier) {
        return list("userId = ?1 and type = ?2 and lower(name) = lower(?3) and isActive=true and identifier = ?4", userId, configType, name, identifier);
    }

    public ConfigDao findByIdAndActive(Long configId) {
        return find("id = ?1 and isActive=true", configId).firstResult();
    }

    public ConfigDao findByIdAndTypeAndActive(ConfigTypeDao configType, Long configId) {
        return find("id = ?1 and type = ?2 and isActive=true", configId, configType.name()).firstResult();
    }

    public List<ConfigDao> findByAppId(Long applicationId) {
        return list("applicationId = ?1 and isActive=true", applicationId);
    }
}
