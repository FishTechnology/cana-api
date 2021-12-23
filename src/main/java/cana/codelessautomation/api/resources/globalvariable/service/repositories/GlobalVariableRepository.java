package cana.codelessautomation.api.resources.globalvariable.service.repositories;

import cana.codelessautomation.api.resources.globalvariable.service.repositories.daos.GlobalVariableDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class GlobalVariableRepository implements PanacheRepository<GlobalVariableDao> {
    public List<GlobalVariableDao> findByUserId(Long userId) {
        return list("userid = ?1 and isactive=true", userId);
    }

    public List<GlobalVariableDao> findByKeyAndUserId(String key, Long userId) {
        return list("key = ?1 and userid = ?2 and isactive=true", key, userId);
    }

    public GlobalVariableDao findByIdAndIsActive(Long globalVariableId) {
        return find("id = ?1 and isActive=true", globalVariableId).firstResult();
    }

    public void deleteByIdAndIsActive(Long globalVariableId) {
        update(" modifiedOn = ?1 , isActive=false  WHERE id = ?2", OffsetDateTime.now(), globalVariableId);
    }
}
