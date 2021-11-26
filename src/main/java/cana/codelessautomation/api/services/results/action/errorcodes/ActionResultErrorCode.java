package cana.codelessautomation.api.services.results.action.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionResultErrorCode extends BaseErrorCode {
    public static final String getActionResultStatusInValid = "CanaApi..Testplan.Status.InValid";

    public String getActionResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".ActionResult.Id.NotFound";
    }
}
