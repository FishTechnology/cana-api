package cana.codelessautomation.api.services.schedule.dtos;

import cana.codelessautomation.api.resources.commonmodels.BrowserType;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.environment.repositories.daos.EnvironmentDao;
import cana.codelessautomation.api.services.schedule.repositories.daos.ScheduleStatusDao;
import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class CreateScheduleDto {
    private Long id;
    private Long testPlanId;
    private Long environmentId;
    private Long userId;
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
    private TestplanDao testplan;
    private EnvironmentDao environment;
    private CustomDetailDao customDetail;
}
