package cana.codelessautomation.api.resources.customer.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.commons.dtos.KeyValue;
import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;

import java.util.List;

public interface CustomerServiceVerifier {
    KeyValue<List<ErrorMessageDto>, CustomDetailDao> isUserIdValid(Long userId);
}
