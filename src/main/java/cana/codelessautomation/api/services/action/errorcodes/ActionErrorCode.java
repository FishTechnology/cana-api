package cana.codelessautomation.api.services.action.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ActionErrorCode extends BaseErrorCode {
    public static final String getActionTypeIsInValid = "CanaApi.{0}.{1}.Action.Type.InValid";
    public static final String getActionTypeIsEmpty = "CanaApi.{0}.{1}.Action.Type.IsEmpty";
    public static final String getActionTypeIsNull = "CanaApi.{0}.{1}.Action.Type.IsNull";
    public static final String getActionKeyIsNull = "CanaApi.{0}.{1}.Action.Key.IsNull";
    public static final String getActionKeyIsEmpty = "CanaApi.{0}.{1}.Action.Key.IsEmpty";
    public static final String getActionOptionTypeIsInValid = "CanaApi.{0}.{1}.Action.OptionType.InValid";
    public static final String getActionOptionTypeIsEmpty = "CanaApi.{0}.{1}.Action.OptionType.Empty";
    public static final String getActionOptionTypeIsNull = "CanaApi.{0}.{1}.Action.OptionType.Null";
    public static final String getActionBrowserActionTypeIsNotValid = "CanaApi.{0}.{1}.Action.Browser.ActionType.IsNotValid";
    public static final String getActionBrowserActionTypeIsEmpty = "CanaApi.{0}.{1}.Action.Browser.ActionType.IsEmpty";
    public static final String getActionBrowserActionTypeIsNull = "CanaApi.{0}.{1}.Action.Browser.ActionType.IsNull";
}
