package cana.codelessautomation.api.resources.result.actionresult.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionResultErrorCode extends BaseErrorCode {
    public static final String getActionResultStatusInValid = "CanaApi..Testplan.Status.InValid";

    public String getActionResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".ActionResult.Id.NotFound";
    }
}
