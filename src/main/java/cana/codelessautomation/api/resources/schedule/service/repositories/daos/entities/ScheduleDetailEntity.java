package cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities;

import cana.codelessautomation.api.resources.environment.service.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.entities.TestPlanSummaryDaoEntity;
import com.googlecode.jmapper.annotations.JMap;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "schedule")
public class ScheduleDetailEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long testPlanId;
    @JMap
    private Long environmentId;
    @JMap
    private Long userId;
    @JMap
    @Enumerated(EnumType.STRING)
    private ScheduleStatusDao status;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;

    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "scheduleId")
    @OrderBy("modifiedOn DESC")
    private List<ScheduleIterationDao> scheduleIterations;

    @OneToOne
    @JoinColumn(name = "environmentId", referencedColumnName = "id", updatable = false, insertable = false)
    private EnvironmentDao environmentDaos;

    @OneToOne
    @JoinColumn(name = "testPlanId", updatable = false, insertable = false, referencedColumnName = "id")
    private TestPlanSummaryDaoEntity testplanDaos;

    public static ScheduleDetailEntity findByIdAndStatus(Long scheduleId) {
        return find("id = ?1 and status = ?2", scheduleId, ScheduleStatusDao.READY).firstResult();
    }
}
