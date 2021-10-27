package cana.codelessautomation.api.resources.testcase.models;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import lombok.Data;

import java.util.List;

@Data
public class CheckTestCaseIsDeletableModel {
    private List<ErrorMessageModel> errorMessageModels;
    private List<TestPlanMappedModel> testPlanMappedModels;
}
