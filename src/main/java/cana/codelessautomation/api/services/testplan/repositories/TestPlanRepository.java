package cana.codelessautomation.api.services.testplan.repositories;

import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.services.testplan.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestPlanRepository implements PanacheRepository<TestplanDao> {
    public TestplanDao findByNameAndUserId(Long userId, String name) {
        return find("name = ?1 and userid = ?2", name, userId).firstResult();
    }

    public List<TestplanDao> findByUserId(Long userId) {
        return list("userid = ?1 and status NOT IN ( ?2 )", userId, TestPlanStatusDao.DELETED);
    }

    public TestplanDao findByIdAndStatus(Long testplanId) {
        return find("id = ?1 and status NOT IN ( ?2 )", testplanId, TestPlanStatusDao.DELETED).firstResult();
    }

    public void deleteTestplan(Long testplanId) {
        update("status = ?1 , modifiedOn = ?2 WHERE id = ?3", TestPlanStatusDao.DELETED, OffsetDateTime.now(), testplanId);
    }

    public void updateTestplan(UpdateTestplanDto updateTestplan) {
        update("name = ?1 , comments = ?2 , modifiedOn = ?3 , modifiedBy = ?4 WHERE id = ?5",
                updateTestplan.getName(),
                updateTestplan.getComments(),
                updateTestplan.getModifiedOn(),
                updateTestplan.getModifiedBy(),
                updateTestplan.getTestplanId());
    }

    public void updateTestplanStatus(UpdateTestplanStatusDto updateTestplanStatus) {
        update("status = ?1 , modifiedOn = ?2 , modifiedBy = ?3 WHERE id = ?4",
                updateTestplanStatus.getStatus(),
                updateTestplanStatus.getModifiedOn(),
                updateTestplanStatus.getModifiedBy(),
                updateTestplanStatus.getTestplanId());
    }
}
