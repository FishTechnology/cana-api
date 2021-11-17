package cana.codelessautomation.api.services.results.testcase.repositories.daos;

import cana.codelessautomation.api.services.results.action.repositories.daos.ActionResultDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "testcase_result")
public class TestCaseResultDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long testCaseId;
    @JMap
    @Column(name = "testplan_result_id")
    private Long testplanResultId;
    @JMap
    private OffsetDateTime startedOn;
    @JMap
    private OffsetDateTime completedOn;
    @JMap
    @Column(name = "execution_order")
    private Long executionOrder;
    @JMap
    @Column(name = "error_message")
    private String errorMessage;
    @JMap
    @Enumerated(EnumType.STRING)
    private TestCaseResultStatusDao status;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    @Column(name = "total_duration")
    private String totalDuration;
    @JMap
    private OffsetDateTime modifiedOn;
    @OneToMany(mappedBy = "testcaseResultId", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("executionOrder ASC")
    private List<ActionResultDao> actionResultDaos;
    @OneToOne
    @JoinColumn(name = "testCaseId", referencedColumnName = "id", updatable = false, insertable = false)
    private TestCaseDao testCase;
}
