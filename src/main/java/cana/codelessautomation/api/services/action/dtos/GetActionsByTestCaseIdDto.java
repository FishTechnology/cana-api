package cana.codelessautomation.api.services.action.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.ActionDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import lombok.Data;

import java.util.List;

@Data
public class GetActionsByTestCaseIdDto {
    private Long testCaseId;
    private TestCaseDao testCaseDao;
    private List<ActionDao> actionDaos;
}
