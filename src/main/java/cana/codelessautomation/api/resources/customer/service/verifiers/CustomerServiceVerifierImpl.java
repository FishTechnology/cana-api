package cana.codelessautomation.api.resources.customer.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.customer.service.errorcodes.CustomerErrorCode;
import cana.codelessautomation.api.resources.customer.service.repository.CustomerRepository;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.commons.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CustomerServiceVerifierImpl implements CustomerServiceVerifier {
    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerErrorCode customerErrorCodes;

    @Override
    public KeyValue<List<ErrorMessageDto>, CustomDetailDao> isUserIdValid(Long userId) {
        KeyValue<List<ErrorMessageDto>, CustomDetailDao> response = new KeyValue<>();
        var customDetailDao = customerRepository.findByUserId(userId);
        if (customDetailDao == null) {
            response.setKey(CanaUtility.getErrorMessages(customerErrorCodes.userIdNotFound()));
            return response;
        }
        response.setValue(customDetailDao);
        return response;
    }
}
