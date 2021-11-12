package cana.codelessautomation.api.services.action.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "action")
public class ActionDao {
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
    @JMap
    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type")
    private ConditionType conditionType;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "actionId")
    private List<ActionOptionDao> actionOptionDaos;
}
