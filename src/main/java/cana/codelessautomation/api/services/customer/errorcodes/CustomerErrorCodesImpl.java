package cana.codelessautomation.api.services.customer.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerErrorCodesImpl extends BaseErrorCode implements CustomerErrorCodes {
    public String userIdNotFound() {
        return "CanaApi.GetCustomer." + getHttpMethod() + ".UserId.NotFound";
    }
}
