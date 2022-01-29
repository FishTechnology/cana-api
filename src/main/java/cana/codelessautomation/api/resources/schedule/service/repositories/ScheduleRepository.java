package cana.codelessautomation.api.resources.schedule.service.repositories;

import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<ScheduleDao> {

    public PanacheQuery<ScheduleDao> findByUserIdPage(Long userId) {
        return find("userid = ?1", Sort.descending("modifiedOn"), userId);
    }

    public PanacheQuery<ScheduleDao> findByAppId(Long applicationId) {
        return find("applicationId = ?1", Sort.descending("modifiedOn"), applicationId);
    }

    public List<ScheduleDao> findByTestPlanIdAndEnvId(Long testPlanId, Long environmentId, ScheduleStatusDao scheduleStatus) {
        return list("testPlanId = ?1 AND environmentId = ?2 AND status = ?3", testPlanId, environmentId, scheduleStatus);
    }
}
