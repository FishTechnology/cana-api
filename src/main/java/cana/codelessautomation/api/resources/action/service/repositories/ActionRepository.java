package cana.codelessautomation.api.resources.action.service.repositories;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionRepository implements PanacheRepository<ActionDao> {
    public List<ActionDao> findByTestCaseId(Long testCaseId) {
        return list("testCaseId = ?1 and isActive=true", Sort.ascending("orderNumber"), testCaseId);
    }

    public ActionDao getLatestActionByTestCaseId(Long testCaseId) {
        return find("testCaseId = ?1 and isactive=true", Sort.descending("orderNumber"), testCaseId).firstResult();
    }

    public ActionDao findByIdAndIsActive(Long actionId) {
        return find("id = ?1 and isactive=true", actionId).firstResult();
    }

    public List<ActionDao> findByGreaterThan(Long orderNumber) {
        return list("isactive=true and orderNumber >= ?1", orderNumber);
    }
}
