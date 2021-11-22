package cana.codelessautomation.api.services.schedule.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduleServiceErrorCode extends BaseErrorCode {
    public static final String getBrowserTypeInValidError = "CanaApi.Schedule.BrowserType.InValid";
    public static final String getBrowserActionTypeInValidError = "CanaApi.Schedule.Browser.ActionType.InValid";
    public static final String getBrowserConditionTypeInValidError = "CanaApi.Schedule.Browser.ConditionType.InValid";
    public static final String getScheduleStatusInValid = "CanaApi.ScheduleResource.UpdateScheduleStatus.PUT.Schedule.Status.InValid";
    public static final String getScheduleNotInReadyStatus = "CanaApi.Schedule.Status.Not.InReady.Status";
    public static final String getScheduleIterationNotInReadyStatus = "CanaApi.ScheduleIteration.Status.Not.InReady.Status";

    public String getScheduleStatusAreSame() {
        return "CanaApi." + getResourceName() + "." + getResourceActionName() + "." + getHttpMethod() + ".Schedule.Status.Are.Same";
    }

    public String getScheduleIdNotFound() {
        return "CanaApi." + getResourceName() + "." + getHttpMethod() + ".Schedule.Id.NotFound";
    }

    public String getScheduleIterationNotFound() {
        return "CanaApi." + getResourceName() + "." + getHttpMethod() + ".ScheduleIteration.NotFound";
    }

    public String getScheduleStatusIsInValid() {
        return "CanaApi." + getResourceName() + "." + getHttpMethod() + ".Schedule.Status.InValid";
    }
}
