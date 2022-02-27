package cana.codelessautomation.api.resources.schedule.service.dtos;

import cana.codelessautomation.api.resources.commonmodels.BrowserType;
import cana.codelessautomation.api.resources.config.services.configservice.repositories.daos.ConfigDao;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleDao;
import cana.codelessautomation.api.resources.schedule.service.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CreateScheduleDto {
    private Long id;
    private Long testPlanId;
    private Long environmentId;
    private Long userId;
    private Long iterationId;
    private ScheduleStatusDao status;
    private String comments;
    private BrowserType browserType;
    private Boolean isRecordVideoEnabled;
    private Boolean isDisableScreenshot;
    private Boolean isCaptureNetworkTraffic;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private Long applicationId;
    private TestplanDao testplan;
    private ConfigDao environment;
    private Integer retryCount;
    private CustomDetailDao customDetail;
    private CreateNotificationDto notification;
    private List<ScheduleDao> schedules;
}
