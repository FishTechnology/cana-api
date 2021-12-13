package cana.codelessautomation.api.services.application.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApplicationErrorCode extends BaseErrorCode {
    public static final String getAppNameIsNullErrorCode = "CanaApi.ApplicationResource.createApplication.Post.Application.Name.IsNull";
    public static final String getAppNameIsEmptyErrorCode = "CanaApi.ApplicationResource.createApplication.Post.Application.Name.IsEmpty";
    public static final String getAppNameIsMinErrorCode = "CanaApi.ApplicationResource.createApplication.Post.Application.Name.Min.Failed";

    public String getDuplicateNameErrorCode() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".Application.Name.Duplicated";
    }

    public String getAppIdNotFoundErrorCode() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".Application.Id.NotFound";
    }
}
