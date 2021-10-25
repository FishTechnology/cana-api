package cana.codelessautomation.api.services.testcase.repositories;

import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class TestCaseRepository implements PanacheRepository<TestCaseDao> {

    public List<TestCaseDao> findByUserId(Long userId) {
        return list("userId = ?1 and isActive=true", userId);
    }

    public List<TestCaseDao> findByIds(List<Long> testCaseIds) {
        return list("isActive=true , id IN ( ?2 )", testCaseIds);
    }
}
