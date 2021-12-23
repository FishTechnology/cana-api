package cana.codelessautomation.api.resources.applicationconfig.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationConfigErrorCode extends BaseErrorCode {
    public String getKeyDuplicateErrorCode() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".ApplicationConfig.Key.Duplicated";
    }

    public String getAppConfigIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".ApplicationConfig.Id.NotFound";
    }
}
