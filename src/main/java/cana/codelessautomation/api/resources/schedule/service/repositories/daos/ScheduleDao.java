package cana.codelessautomation.api.resources.schedule.service.repositories.daos;

import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "schedule")
public class ScheduleDao {
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
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private Long applicationId;
    @JMap
    @Enumerated(EnumType.STRING)
    private ScheduleStatusDao status;
    @JMap
    private String modifiedBy;
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "scheduleId")
    @OrderBy("modifiedOn DESC")
    private List<ScheduleIterationDao> scheduleIterations;

    @OneToOne
    @JoinColumn(name = "environmentId", referencedColumnName = "id", updatable = false, insertable = false)
    private ConfigDao environmentDaos;

    @OneToOne
    @JoinColumn(name = "testPlanId", updatable = false, insertable = false, referencedColumnName = "id")
    private TestplanDao testplanDaos;
}
