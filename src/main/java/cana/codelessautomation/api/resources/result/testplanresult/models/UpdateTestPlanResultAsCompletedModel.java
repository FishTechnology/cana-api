package cana.codelessautomation.api.resources.result.testplanresult.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.result.testplanresult.service.errorcodes.TestPlanResultErrorCode;
import cana.codelessautomation.api.resources.result.testplanresult.service.repositories.daos.TestPlanResultStatusDao;
import lombok.Data;

@Data
public class UpdateTestPlanResultAsCompletedModel {
    @ValidEnumString(enumRef = TestPlanResultStatusDao.class, message = TestPlanResultErrorCode.getTestPlanResultStatusInValid)
    private String status;
    private String errorMessage;
    private String totalDuration;
    private String modifiedBy;
    private String startedOn;
    private String completedOn;
}
