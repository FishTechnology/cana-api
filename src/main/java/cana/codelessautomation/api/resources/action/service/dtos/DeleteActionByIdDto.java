package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import lombok.Data;

@Data
public class DeleteActionByIdDto {
    private Long testCaseId;
    private Long actionId;
    private TestCaseDao testCaseDao;
    private ActionDao actionDao;
}
