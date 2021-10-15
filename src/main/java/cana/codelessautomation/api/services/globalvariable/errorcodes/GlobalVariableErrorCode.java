package cana.codelessautomation.api.services.globalvariable.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GlobalVariableErrorCode extends BaseErrorCode {
    public String getDuplicateKey() {
        return "CanaApi.{0}." + getHttpMethod() + ".GlobalVariable.Key.Duplicate";
    }

    public String getGlobalVariableIdNotFound() {
        return "CanaApi.{0}." + getHttpMethod() + ".GlobalVariable.Id.NotFound";
    }
}
