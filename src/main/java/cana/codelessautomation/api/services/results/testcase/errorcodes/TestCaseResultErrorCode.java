package cana.codelessautomation.api.services.results.testcase.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestCaseResultErrorCode extends BaseErrorCode {
    public static final String getTestCaseResultStatusInValid = "";

    public String getTestCaseResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".TestCaseResult.Id.NotFound";
    }
}
