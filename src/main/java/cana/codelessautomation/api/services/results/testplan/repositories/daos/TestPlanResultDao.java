package cana.codelessautomation.api.services.results.testplan.repositories.daos;

import cana.codelessautomation.api.services.results.testcase.repositories.daos.TestCaseResultDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
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
    @OneToMany(orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "testplanResultId")
    @OrderBy("executionOrder ASC")
    private List<TestCaseResultDao> testCaseResults;
    @OneToOne
    @JoinColumn(name = "testPlanId", referencedColumnName = "id", updatable = false, insertable = false)
    private TestplanDao testplan;
}
