package cana.codelessautomation.api.services.customer.verifiers;

import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.common.dtos.KeyValue;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;

import java.util.List;

public interface CustomerServiceVerifier {
    KeyValue<List<ErrorMessageDto>, CustomDetailDao> isUserIdValid(Long userId);
}
