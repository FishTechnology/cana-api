package cana.codelessautomation.api.resources.result.testplanresult.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestPlanResultErrorCode extends BaseErrorCode {
    public static final String getTestPlanResultStatusInValid = "";

    public String getTestPlanResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".TestPlanResult.Id.NotFound";
    }
}
