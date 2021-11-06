package cana.codelessautomation.api.services.results.testplan.repositories;

import cana.codelessautomation.api.services.results.testplan.repositories.daos.TestPlanResultDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestPlanResultRepository implements PanacheRepository<TestPlanResultDao> {
    public TestPlanResultDao findByScheduleIterationId(Long scheduleIterationId) {
        return find("scheduleIterationId = ?1", scheduleIterationId).firstResult();
    }
}
