package cana.codelessautomation.api.resources.testplan.service.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "testplan")
public class TestplanDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Long applicationId;
    @JMap
    @Enumerated(EnumType.STRING)
    private TestPlanStatusDao status;
}
