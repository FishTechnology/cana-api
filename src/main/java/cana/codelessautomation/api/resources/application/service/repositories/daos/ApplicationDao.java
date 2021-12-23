package cana.codelessautomation.api.resources.application.service.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.OffsetDateTime;

@Data
@Entity(name = "application")
public class ApplicationDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    @NotEmpty
    @NotNull
    @JMap
    private String name;

    @Column(nullable = false)
    @Positive
    @NotNull
    @JMap
    private Long userId;

    @JMap
    private OffsetDateTime createdOn;

    @JMap
    private OffsetDateTime modifiedOn;

    @JMap
    private String createdBy;

    @JMap
    private String modifiedBy;

    @Column(nullable = false)
    @NotNull
    @JMap
    private Boolean isActive = false;

    @JMap
    private String comments;
}
