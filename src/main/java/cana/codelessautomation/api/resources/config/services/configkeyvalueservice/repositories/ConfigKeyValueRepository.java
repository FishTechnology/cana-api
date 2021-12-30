package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConfigKeyValueRepository implements PanacheRepository<ConfigKeyValueDao> {
    public ConfigKeyValueDao findByConfigIdAndKey(Long configId, String key) {
        return find("configId = ?1 and key = ?2 and isActive=true", configId, key).firstResult();
    }

    public List<ConfigKeyValueDao> findByConfigId(Long configId) {
        return list("configId = ?1 and isActive=true", configId);
    }
}
