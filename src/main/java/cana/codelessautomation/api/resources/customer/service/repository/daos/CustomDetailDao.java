package cana.codelessautomation.api.resources.customer.service.repository.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "customer")
public class CustomDetailDao {
    @Id
    private Long id;
    @JMap
    private String username;
    @JMap
    private String password;
    @JMap
    @Column(name = "last_login")
    private OffsetDateTime lastLogin;
    @JMap
    private String status;
    @JMap
    @Column(name = "created_on")
    private OffsetDateTime createdOn;
    @JMap
    @Column(name = "modified_on")
    private OffsetDateTime modifiedOn;
    @JMap
    @Column(name = "created_by")
    private String createdBy;
    @JMap
    @Column(name = "modified_by")
    private String modifiedBy;
}
