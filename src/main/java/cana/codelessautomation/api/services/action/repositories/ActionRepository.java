package cana.codelessautomation.api.services.action.repositories;

import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
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
}
