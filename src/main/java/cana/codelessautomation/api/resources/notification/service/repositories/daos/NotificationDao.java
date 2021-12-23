package cana.codelessautomation.api.resources.notification.service.repositories.daos;

import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "notification")
public class NotificationDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    @Column(name = "schedule_iteration_id")
    private Long scheduleIterationId;
    @JMap
    private String emailAddress;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String modifiedBy;
    @JMap
    private String createdBy;
    @JMap
    private Boolean isActive;
}
