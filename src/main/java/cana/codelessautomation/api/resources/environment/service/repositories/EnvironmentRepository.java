package cana.codelessautomation.api.resources.environment.service.repositories;

import cana.codelessautomation.api.resources.environment.service.dtos.UpdateEnvironmentDto;
import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
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

    public void updateEnv(UpdateEnvironmentDto updateEnvironment) {
        update("name = ?1 , modifiedOn = ?2, comments = ?3  , modifiedBy = ?4 WHERE id = ?5",
                updateEnvironment.getName(),
                OffsetDateTime.now(),
                updateEnvironment.getComments(),
                updateEnvironment.getModifiedBy(),
                updateEnvironment.getId());
    }
}
