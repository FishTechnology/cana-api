package cana.codelessautomation.api.resources.testplan.service.repositories.daos.entities;

import cana.codelessautomation.api.resources.testcase.service.repositories.daos.entities.TestplanTestcaseGroupingDaoEntity;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestPlanStatusDao;
import com.googlecode.jmapper.annotations.JMap;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "testplan")
public class TestPlanSummaryDaoEntity extends PanacheEntityBase {
    @Id
    private Long id;
    @JMap
    private Long userId;
    @JMap
    private String name;
    @JMap
    private String comments;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
    @JMap
    @Enumerated(EnumType.STRING)
    private TestPlanStatusDao status;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testPlanId", orphanRemoval = true)
    private List<TestplanTestcaseGroupingDaoEntity> testplanTestcaseGroupings;
}
