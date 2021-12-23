package cana.codelessautomation.api.resources.action.service.repositories.daos.entities;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionOptionDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.BrowserActionTypeDao;
import cana.codelessautomation.api.resources.action.service.repositories.daos.UIActionTypeDao;
import com.googlecode.jmapper.annotations.JMap;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "action")
public class ActionDaoEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    @Enumerated(EnumType.STRING)
    private ActionTypeDao type;
    @JMap
    @Enumerated(EnumType.STRING)
    private UIActionTypeDao uiActionType;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private String comments;
    @JMap
    @Enumerated(EnumType.STRING)
    @Column(name = "browser_actionType")
    private BrowserActionTypeDao browserActionType;
    @JMap
    @Column(name = "isassert_verification")
    private Boolean isAssertVerification;
    @JMap
    private String browserValue;
    @JMap
    private Long userId;
    @JMap
    private Long testCaseId;
    @JMap
    private Long orderNumber;
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

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "actionId")
    private List<ActionOptionDao> actionOptionDaos;
}
