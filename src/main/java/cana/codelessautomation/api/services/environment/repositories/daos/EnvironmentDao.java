package cana.codelessautomation.api.services.environment.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "environment")
public class EnvironmentDao {
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
}
