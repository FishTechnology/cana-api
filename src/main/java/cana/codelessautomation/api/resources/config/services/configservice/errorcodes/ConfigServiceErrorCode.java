package cana.codelessautomation.api.resources.config.services.configservice.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfigServiceErrorCode extends BaseErrorCode {

    public String getConfigTypeDuplicateErrorCode() {
        return "CanaApi.{0}." + getHttpMethod() + ".Config.Type.Duplicated";
    }

    public String getConfigIdNotFound() {
        return "CanaApi.{0}." + getHttpMethod() + ".Config.Id.Not.Found";
    }
}
