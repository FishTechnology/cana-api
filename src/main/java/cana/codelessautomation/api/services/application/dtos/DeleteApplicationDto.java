package cana.codelessautomation.api.services.application.dtos;

import cana.codelessautomation.api.services.application.repositories.daos.ApplicationDao;
import lombok.Data;

@Data
public class DeleteApplicationDto {
    private Long applicationId;
    private ApplicationDao applicationDao;
}
