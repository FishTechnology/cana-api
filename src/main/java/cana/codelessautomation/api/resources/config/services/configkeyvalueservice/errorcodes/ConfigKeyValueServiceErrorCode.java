package cana.codelessautomation.api.resources.config.services.configkeyvalueservice.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfigKeyValueServiceErrorCode extends BaseErrorCode {
    public String getConfigKeyValueDuplicateErrorCode() {
        return "CanaApi.{0}." + getHttpMethod() + ".ConfigKeyValue.Key.Duplicated";
    }

    public String getInValidSystemVariable() {
        return "CanaApi.{0}." + getHttpMethod() + ".SystemVariable.Invalid";
    }
}
