package cana.codelessautomation.api.services.testplan.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestplanErrorCode extends BaseErrorCode {
    public static final String getTestPlanStatusIsInValid = "CanaApi..Testplan.Status.InValid";

    public String getTestPlanIsNotInReadyStatus() {
        return "CanaApi." + getHttpMethod() + ".Testplan.Status.Not.Ready.Status";
    }

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
