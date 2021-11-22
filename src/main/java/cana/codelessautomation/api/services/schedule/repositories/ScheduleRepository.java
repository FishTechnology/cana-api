package cana.codelessautomation.api.services.schedule.repositories;

import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleDao;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<ScheduleDao> {

    public PanacheQuery<ScheduleDao> findByUserIdPage(Long userId) {
        return find("userid = ?1", Sort.descending("modifiedOn"), userId);
    }
}
