package cana.codelessautomation.api.services.action.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    private String key;
    @JMap
    private String value;
    @JMap
    private String comments;
    @JMap
    private Long userId;
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
}
