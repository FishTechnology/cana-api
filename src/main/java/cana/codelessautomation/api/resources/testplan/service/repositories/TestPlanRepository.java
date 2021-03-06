package cana.codelessautomation.api.resources.testplan.service.repositories;

import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanDto;
import cana.codelessautomation.api.resources.testplan.service.dtos.UpdateTestplanStatusDto;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.time.OffsetDateTime;
import java.util.List;

@ApplicationScoped
public class TestPlanRepository implements PanacheRepository<TestplanDao> {
    public TestplanDao findByNameAndUserId(Long userId, String name) {
        return find("name = ?1 and userid = ?2", name, userId).firstResult();
    }

    public List<TestplanDao> findByAppId(Long applicationId) {
        return list("applicationId = ?1 and status NOT IN ( ?2 )", Sort.descending("modifiedOn"), applicationId, TestPlanStatusDao.DELETED);
    }

    public TestplanDao findByIdAndStatus(Long applicationId, Long testplanId) {
        return find("id = ?1 and applicationId = ?2 and status NOT IN ( ?3 )", testplanId, applicationId, TestPlanStatusDao.DELETED).firstResult();
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
