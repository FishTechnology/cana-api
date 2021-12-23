package cana.codelessautomation.api.resources.result.testcaseresult.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestCaseResultErrorCode extends BaseErrorCode {
    public static final String getTestCaseResultStatusInValid = "";

    public String getTestCaseResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".TestCaseResult.Id.NotFound";
    }
}
