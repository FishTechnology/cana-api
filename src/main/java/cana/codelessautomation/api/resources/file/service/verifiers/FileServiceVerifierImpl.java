package cana.codelessautomation.api.resources.file.service.verifiers;

import cana.codelessautomation.api.commons.dtos.ErrorMessageDto;
import cana.codelessautomation.api.resources.customer.service.verifiers.CustomerServiceVerifier;
import cana.codelessautomation.api.resources.file.service.dtos.UpdateFileDto;
import org.apache.commons.collections.CollectionUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@ApplicationScoped
public class FileServiceVerifierImpl implements FileServiceVerifier {
    @Inject
    CustomerServiceVerifier customerServiceVerifier;

    @Override
    public List<ErrorMessageDto> verifyUpdateFile(UpdateFileDto updateFileDto) {
        return isUserIdValid(updateFileDto);
    }

    @Override
    public List<ErrorMessageDto> isUserIdValid(UpdateFileDto updateFileDto) {
        var response = customerServiceVerifier.isUserIdValid(updateFileDto.getUserId());
        if (!CollectionUtils.isEmpty(response.getKey())) {
            return response.getKey();
        }
        updateFileDto.setCustomDetail(response.getValue());
        return Collections.emptyList();
    }
}
