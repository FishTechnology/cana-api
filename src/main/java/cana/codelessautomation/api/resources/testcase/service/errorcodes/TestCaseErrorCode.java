package cana.codelessautomation.api.resources.testcase.service.errorcodes;

import cana.codelessautomation.api.commons.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestCaseErrorCode extends BaseErrorCode {
    public String getTestCaseNameDuplicate() {
        return "CanaApi.{0}.{1}." + getHttpMethod() + ".TestCase.Name.Duplicated";
    }

    public String getTestCaseIdNotFound() {
        return "CanaApi.{0}.{1}." + getHttpMethod() + ".TestCase.Id.NotFound";
    }

    public String getTestPlanIdAndTestCaseIdNotMappedFound() {
        return "CanaApi.{0}.{1}." + getHttpMethod() + ".TestPlanId.TestCaseId.Not.Mapped";
    }

    public String getTestCaseIdMappedToTestPlanIdFound() {
        return "CanaApi.{0}.{1}." + getHttpMethod() + ".TestCaseId.MappedTo.TestPlanId";
    }
}
