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

    @Enumerated(EnumType.STRING)
    private ConfigTypeDao type;

    @Column(nullable = false)
    private Long userId;

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
