package cana.codelessautomation.api.services.testcase.repositories;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestCaseRepository implements PanacheRepository<TestCaseDao> {

    public List<TestCaseDao> findByUserId(Long userId) {
        return list("userId = ?1 and isActive=true", userId);
    }

    public List<TestCaseDao> findByIds(List<Long> testCaseIds) {
        return list("isActive=true and id IN ( ?1 )", testCaseIds);
    }

    public TestCaseDao findByIdAndStatus(Long testCaseId) {
        return find("id= ?1 and isActive=true", testCaseId).firstResult();
    }

    public void updateTestCase(String name, String comments, OffsetDateTime modifiedOn, String modifiedBy, Long testCaseId) {
        update(" name = ?1 , comments = ?2 , modifiedOn = ?3 , modifiedBy = ?4 WHERE id = ?5",
                name,
                comments,
                modifiedOn,
                modifiedBy,
                testCaseId);
    }
}
