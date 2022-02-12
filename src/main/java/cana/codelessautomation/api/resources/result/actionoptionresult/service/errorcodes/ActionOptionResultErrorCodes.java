package cana.codelessautomation.api.resources.result.actionoptionresult.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionOptionResultErrorCodes extends BaseErrorCode {
    public static final String getActionOptionResultStatusInValid = "CanaApi..Testplan.Status.InValid";

    public String getActionOptionResultIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".ActionOptionResult.Id.NotFound";
    }
}
