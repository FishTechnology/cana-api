package cana.codelessautomation.api.services.envvariable.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "environmentvariable")
public class EnvironmentVariableDao {
    @Id
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private String type;
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
    private Long environmentId;
}
