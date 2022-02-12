package cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories;

import cana.codelessautomation.api.resources.result.actionoptionresult.service.repositories.daos.ActionOptionResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionOptionResultRepository implements PanacheRepository<ActionOptionResultDao> {
    public List<ActionOptionResultDao> findByActionResultId(Long actionResultId) {
        return list("action_result_id = ?1 ", actionResultId);
    }
}
