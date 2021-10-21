package cana.codelessautomation.api.services.testplan.dtos;

import cana.codelessautomation.api.services.testplan.repositories.daos.TestplanDao;
import lombok.Data;

@Data
public class DeleteTestplanDto {
    private Long testplanId;
    private TestplanDao testplan;
}
