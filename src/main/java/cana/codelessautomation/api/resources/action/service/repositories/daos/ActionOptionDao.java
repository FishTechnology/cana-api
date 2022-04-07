package cana.codelessautomation.api.resources.action.service.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "actionOption")
public class ActionOptionDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long actionId;
    @JMap
    @Enumerated(EnumType.STRING)
    private ActionOptionTypeDao optionType;
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
    private Long duration;
    @JMap
    private String value;
    @JMap
    @Enumerated(EnumType.STRING)
    @Column(name = "control_type")
    private UIControlConditionTypeDao controlConditionType;

    @JMap
    @Enumerated(EnumType.STRING)
    @Column(name = "content_type")
    private UIOptionContentTypeDao contentType;
}
