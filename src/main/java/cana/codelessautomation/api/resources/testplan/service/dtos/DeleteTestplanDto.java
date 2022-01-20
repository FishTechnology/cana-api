package cana.codelessautomation.api.resources.testplan.service.dtos;

import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import lombok.Data;

@Data
public class DeleteTestplanDto {
    private Long testplanId;
    private Long applicationId;
    private TestplanDao testplan;
}
