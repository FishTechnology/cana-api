package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.repositories.daos;


import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "config_key_value")
@Data
public class ConfigKeyValueDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long configId;

    private String key;
    private String value;
    @Enumerated(EnumType.STRING)
    private ConfigKeyValueType type;

    private Long fileId;

    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private String comments;
    @Column(name = "is_application_variable")
    private Boolean isApplicationVariable;

    @Column(nullable = false)
    private Boolean isActive = false;
}
