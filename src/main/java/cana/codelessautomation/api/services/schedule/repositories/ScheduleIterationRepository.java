package cana.codelessautomation.api.services.schedule.repositories;

import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleIterationDao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ScheduleIterationRepository implements PanacheRepository<ScheduleIterationDao> {
    public List<ScheduleIterationDao> findByScheduleId(Long scheduleId) {
        return list("scheduleId = ?1", scheduleId);
    }
}
