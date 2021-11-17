package cana.codelessautomation.api.services.results.testplan.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestPlanResultErrorCode extends BaseErrorCode {
    public static final String getTestPlanResultStatusInValid = "";

    public String getTestPlanResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".TestPlanResult.Id.NotFound";
    }
}
