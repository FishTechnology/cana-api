package cana.codelessautomation.api.resources.globalvariable.service.repositories.daos;

import cana.codelessautomation.api.resources.file.service.repositories.daos.FileDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "globalvariable")
public class GlobalVariableDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    @Enumerated(EnumType.STRING)
    private GlobalVariableType valueType;
    @JMap
    private Long fileId;
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
    @OneToOne(orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "fileId", insertable = false, updatable = false)
    private FileDao fileDaos;
}
