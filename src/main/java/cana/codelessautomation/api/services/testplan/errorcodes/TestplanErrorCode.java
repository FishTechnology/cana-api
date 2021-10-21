package cana.codelessautomation.api.services.testplan.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestplanErrorCode extends BaseErrorCode {
    public String getTestPlanNameIsDuplicate() {
        return "CanaApi." + getHttpMethod() + ".Testplan.Name.Duplicate";
    }

    public String getTestPlanIdNotFound() {
        return "CanaApi." + getHttpMethod() + ".Testplan.Id.NotFound";
    }

    public String getTestPlanIsInActive() {
        return "CanaApi." + getHttpMethod() + ".Testplan.InActive.Status";
    }

    public String getTestPlanIsInSameStatus() {
        return "CanaApi." + getHttpMethod() + ".Testplan.Same.Status";
    }
}
