package cana.codelessautomation.api.services.testcase.repositories.daos.entities;

import com.googlecode.jmapper.annotations.JMap;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "testplan_testcase_grouping")
public class TestplanTestcaseGroupingDaoEntity extends PanacheEntityBase {
    @Id
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
    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(insertable = false, updatable = false, name = "testCaseId", referencedColumnName = "id")
    private TestCaseDaoEntity testCase;
}
