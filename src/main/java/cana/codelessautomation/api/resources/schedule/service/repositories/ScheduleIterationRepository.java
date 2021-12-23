package cana.codelessautomation.api.resources.schedule.service.repositories;

import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ScheduleIterationRepository implements PanacheRepository<ScheduleIterationDao> {
    public List<ScheduleIterationDao> findByScheduleId(Long scheduleId) {
        return list("scheduleId = ?1", scheduleId);
    }

    public ScheduleIterationDao findLatestIteration(Long scheduleId) {
        return find("scheduleId = ?1", Sort.descending("id"),scheduleId).firstResult();
    }
}
