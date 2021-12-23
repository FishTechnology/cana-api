package cana.codelessautomation.api.resources.action.service.dtos;

import cana.codelessautomation.api.resources.action.service.repositories.daos.ActionDao;
import cana.codelessautomation.api.resources.testcase.service.repositories.daos.TestCaseDao;
import lombok.Data;

import java.util.List;

@Data
public class GetActionsByTestCaseIdDto {
    private Long testCaseId;
    private TestCaseDao testCaseDao;
    private List<ActionDao> actionDaos;
}
