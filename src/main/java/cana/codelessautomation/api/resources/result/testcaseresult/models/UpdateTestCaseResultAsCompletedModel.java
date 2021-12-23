package cana.codelessautomation.api.resources.result.testcaseresult.models;

import cana.codelessautomation.api.commons.validators.ValidEnumString;
import cana.codelessautomation.api.resources.result.testcaseresult.service.errorcodes.TestCaseResultErrorCode;
import cana.codelessautomation.api.resources.result.testcaseresult.service.repositories.daos.TestCaseResultStatusDao;
import lombok.Data;

@Data
public class UpdateTestCaseResultAsCompletedModel {
    @ValidEnumString(enumRef = TestCaseResultStatusDao.class, message = TestCaseResultErrorCode.getTestCaseResultStatusInValid)
    private String status;
    private String errorMessage;
    private String totalDuration;
    private String modifiedBy;
    private String startedOn;
    private String completedOn;
}
