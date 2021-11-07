package cana.codelessautomation.api.services.schedule.repositories.daos;

import cana.codelessautomation.api.resources.commonmodels.BrowserType;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "scheduleIteration")
public class ScheduleIterationDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JMap
    private Long scheduleId;
    @JMap
    @Enumerated(EnumType.STRING)
    private ScheduleStatusDao status;
    @JMap
    private String comments;
    @JMap
    @Column(name = "is_record_video_enabled")
    private Boolean isRecordVideoEnabled;
    @JMap
    @Column(name = "is_disable_screenshot")
    private Boolean isDisableScreenshot;
    @JMap
    @Column(name = "is_capture_network_traffic")
    private Boolean isCaptureNetworkTraffic;
    @JMap
    @Enumerated(EnumType.STRING)
    private BrowserType browserType;
    @JMap
    private OffsetDateTime startedOn;
    @JMap
    private OffsetDateTime completedOn;
    @JMap
    private OffsetDateTime createdOn;
    @JMap
    private OffsetDateTime modifiedOn;
    @JMap
    private String createdBy;
    @JMap
    private String modifiedBy;
}
