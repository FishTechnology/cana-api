package cana.codelessautomation.api.resources.testcase.service.repositories;

import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestplanTestcaseGroupingDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestplanTestcaseGroupingRepository implements PanacheRepository<TestplanTestcaseGroupingDao> {
    public List<TestplanTestcaseGroupingDao> findByTestPlanId(Long testPlanId) {
        return list("testPlanId = ?1 and isActive=true", Sort.by("execution_order"), testPlanId);
    }

    public TestplanTestcaseGroupingDao findLastTestCaseByTestPlanId(Long testPlanId) {
        return find("testPlanId = ?1", Sort.descending("execution_order"), testPlanId).firstResult();
    }

    public TestplanTestcaseGroupingDao findByTestPlanIdAndTestCaseId(Long testPlanId, Long testCaseId) {
        return find("testPlanId = ?1 and testCaseId = ?2 and isActive=true", testPlanId, testCaseId).firstResult();
    }

    public List<TestplanTestcaseGroupingDao> findByTestCaseId(Long testCaseId) {
        return list("testCaseId = ?1 and isActive=true", testCaseId);
    }

    public void deleteByTestCaseIdAndTestPlanId(Long testPlanId, Long testCaseId) {
        update(" isActive=false ,modifiedOn = ?1  WHERE testPlanId = ?2 AND testCaseId = ?3",
                OffsetDateTime.now(),
                testPlanId,
                testCaseId);
    }
}
