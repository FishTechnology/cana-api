package cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos;

import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "testplan_result")
public class TestPlanResultDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long testPlanId;
    @JMap
    @Column(name = "schedule_iteration_id")
    private Long scheduleIterationId;
    @JMap
    private OffsetDateTime startedOn;
    @JMap
    private OffsetDateTime completedOn;
    @JMap
    @Column(name = "error_message")
    private String errorMessage;
    @JMap
    @Enumerated(EnumType.STRING)
    private TestPlanResultStatusDao status;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    @Column(name = "total_duration")
    private String totalDuration;
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "testplanResultId")
    @OrderBy("executionOrder ASC")
    private List<TestCaseResultDao> testCaseResults;
    @OneToOne
    @JoinColumn(name = "testPlanId", referencedColumnName = "id", updatable = false, insertable = false)
    private TestplanDao testplan;
}
