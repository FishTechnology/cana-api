package cana.codelessautomation.api.services.schedule.errorcodes;

import cana.codelessautomation.api.services.common.dtos.BaseErrorCode;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ScheduleServiceErrorCode extends BaseErrorCode {
    public String getScheduleIdNotFound() {
        return "CanaApi.{0}." + getHttpMethod() + ".Schedule.Id.NotFound";
    }

    public String getScheduleIterationNotFound() {
        return "CanaApi.{0}." + getHttpMethod() + ".ScheduleIteration.NotFound";
    }
}
