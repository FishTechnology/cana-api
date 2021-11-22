package cana.codelessautomation.api.services.results.testcase.repositories;

import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TestCaseResultRepository implements PanacheRepository<TestCaseResultDao> {
    public List<TestCaseResultDao> findByPlanResultId(Long testPlanResultId) {
        return list("testplan_result_id = ? 1", testPlanResultId);
    }
}
