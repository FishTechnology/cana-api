package cana.codelessautomation.api.services.testcase.repositories.daos.entities;

import cana.codelessautomation.api.services.action.repositories.daos.entities.ActionDaoEntity;
import com.googlecode.jmapper.annotations.JMap;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "testcase")
public class TestCaseDaoEntity extends PanacheEntityBase {
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
    private Boolean isActive;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "testCaseId", orphanRemoval = true)
    private List<ActionDaoEntity> actionDaoEntities;
}
