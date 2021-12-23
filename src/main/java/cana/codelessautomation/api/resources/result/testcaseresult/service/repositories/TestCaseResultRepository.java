package cana.codelessautomation.api.resources.result.testcaseresult.service.repositories;

import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TestCaseResultRepository implements PanacheRepository<TestCaseResultDao> {
    public List<TestCaseResultDao> findByPlanResultId(Long testPlanResultId) {
        return list("testplan_result_id = ? 1", testPlanResultId);
    }
}
