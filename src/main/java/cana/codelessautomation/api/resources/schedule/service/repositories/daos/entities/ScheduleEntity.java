package cana.codelessautomation.api.resources.schedule.service.repositories.daos.entities;

import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleIterationDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import com.googlecode.jmapper.annotations.JMap;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "schedule")
public class ScheduleEntity extends PanacheEntityBase {
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

    public static List<ScheduleEntity> findFirstByStatus(ScheduleStatusDao scheduleStatusDao) {
        return list("status = ?1", scheduleStatusDao);
    }
}
