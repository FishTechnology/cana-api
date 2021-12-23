package cana.codelessautomation.api.resources.envvariable.service.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "environmentVariable")
public class EnvironmentVariableDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    @Enumerated(EnumType.STRING)
    private EnvironmentVariableType type;
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
    private String comments;
    @JMap
    private Long userId;
    @JMap
    private String content;
    @JMap
    private Long environmentId;
}
