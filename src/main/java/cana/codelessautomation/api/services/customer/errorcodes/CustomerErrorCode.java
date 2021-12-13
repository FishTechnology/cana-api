package cana.codelessautomation.api.services.customer.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerErrorCode extends BaseErrorCode {
    public static final String getUserIdIsZero = "CanaApi.GetCustomer..UserId.Zero";
    public static final String getUserIdIsNullErrorCode = "CanaApi.ApplicationResource.createApplication.Post.Application.Name.IsNull";
    public static final String getUserIdIsEmptyErrorCode = "";

    public String userIdNotFound() {
        return "CanaApi.GetCustomer..UserId.NotFound";
    }
}
