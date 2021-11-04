package cana.codelessautomation.api.services.globalvariable.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GlobalVariableErrorCode extends BaseErrorCode {
    public static final String getOptionTypeIsInValid = "CanaApi.{0}..GlobalVariable.OptionType.InValid";
    public static final String getOptionTypeIsNull = "CanaApi.{0}..GlobalVariable.OptionType.IsNull";
    public static final String getOptionTypeIsEmpty = "CanaApi.{0}..GlobalVariable.OptionType.IsEmpty";

    public String getDuplicateKey() {
        return "CanaApi.{0}." + getHttpMethod() + ".GlobalVariable.Key.Duplicate";
    }

    public String getGlobalVariableIdNotFound() {
        return "CanaApi.{0}." + getHttpMethod() + ".GlobalVariable.Id.NotFound";
    }
}
