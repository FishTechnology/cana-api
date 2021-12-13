package cana.codelessautomation.api.services.applicationconfig.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

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
