package cana.codelessautomation.api.resources.config.services.configservice.repositories;

import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigTypeDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConfigRepository implements PanacheRepository<ConfigDao> {
    public List<ConfigDao> findByUserId(Long userId, ConfigTypeDao configType) {
        return list("userId = ?1 and type = ?2  and isActive=true", userId, configType);
    }

    public List<ConfigDao> findByUserIdAndTypeAndName(Long userId, ConfigTypeDao configType, String name) {
        return list("userId = ?1 and type = ?2 and lower(name) = lower(?3) and isActive=true", userId, configType, name);
    }

    public List<ConfigDao> findByUserIdAndTypeAndNameAndIdentifier(Long userId, ConfigTypeDao configType, String name, Long identifier) {
        return list("userId = ?1 and type = ?2 and lower(name) = lower(?3) and isActive=true and identifier = ?4", userId, configType, name, identifier);
    }

    public ConfigDao findByIdAndActive(Long configId) {
        return find("id = ?1 and isActive=true", configId).firstResult();
    }

    public ConfigDao findByIdAndTypeAndActive(ConfigTypeDao configType, Long configId) {
        return find("id = ?1 and type = ?2 and isActive=true", configId, configType).firstResult();
    }
}
