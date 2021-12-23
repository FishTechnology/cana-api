package cana.codelessautomation.api.resources.result.actionresult.service.repositories;

import cana.codelessautomation.api.resources.result.actionresult.service.repositories.daos.ActionResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ActionResultRepository implements PanacheRepository<ActionResultDao> {
    public List<ActionResultDao> findByTestCaseId(Long testCaseResultId) {
        return list("testcase_result_id = ?1", testCaseResultId);
    }
}
