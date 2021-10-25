package cana.codelessautomation.api.services.testcase.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "testplan_testcase_grouping")
public class TestplanTestcaseGroupingDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long userId;
    @JMap
    private Long testPlanId;
    @JMap
    private Long testCaseId;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    private Boolean isActive;
    @JMap
    @Column(name = "execution_order")
    private Long executionOrder;
}
