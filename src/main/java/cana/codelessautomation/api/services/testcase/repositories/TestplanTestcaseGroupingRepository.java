package cana.codelessautomation.api.services.testcase.repositories;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestplanTestcaseGroupingDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TestplanTestcaseGroupingRepository implements PanacheRepository<TestplanTestcaseGroupingDao> {
    public List<TestplanTestcaseGroupingDao> findByTestPlanId(Long testPlanId) {
        return list("testPlanId = ?1 and isActive=true", Sort.by("execution_order"), testPlanId);
    }

    public TestplanTestcaseGroupingDao findLastTestCaseByTestPlanId(Long testPlanId) {
        return find("testPlanId = ?1", Sort.descending("execution_order"), testPlanId).firstResult();
    }
}