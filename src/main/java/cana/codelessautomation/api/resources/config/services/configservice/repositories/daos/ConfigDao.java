package cana.codelessautomation.api.resources.config.services.configservice.repositories.daos;

import cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos.ConfigKeyValueDao;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "config")
@Data
public class ConfigDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long applicationId;

    private Long identifier;

    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private String comments;

    @Column(nullable = false)
    private Boolean isActive = false;

    @OneToMany(mappedBy = "configId", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ConfigKeyValueDao> configKeyValues;
}
