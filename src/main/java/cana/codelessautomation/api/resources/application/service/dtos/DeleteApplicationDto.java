package cana.codelessautomation.api.resources.application.service.dtos;

import cana.codelessautomation.api.resources.application.service.repositories.daos.ApplicationDao;
import lombok.Data;

@Data
public class DeleteApplicationDto {
    private Long applicationId;
    private ApplicationDao applicationDao;
}
