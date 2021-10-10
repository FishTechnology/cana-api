package cana.codelessautomation.api.services.customer.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.errorcodes.CustomerErrorCodes;
import cana.codelessautomation.api.services.customer.repository.CustomerRepository;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.utilities.CanaUtility;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CustomerServiceVerifierImpl implements CustomerServiceVerifier {
    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerErrorCodes customerErrorCodes;

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
