package cana.codelessautomation.api.resources.config.service.repositories.daos;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    private ConfigTypeDao type;

    @Column(nullable = false)
    private Long userId;

    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;

    @Column(nullable = false)
    private Boolean isActive = false;
}
