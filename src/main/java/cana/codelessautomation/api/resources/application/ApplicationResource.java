package cana.codelessautomation.api.resources.application;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.application.mappers.ApplicationResourceMapper;
import cana.codelessautomation.api.resources.application.models.ApplicationModel;
import cana.codelessautomation.api.resources.application.models.CreateAppModel;
import cana.codelessautomation.api.resources.application.models.UpdateApplicationModel;
import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.application.ApplicationService;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Path("/api")
public class ApplicationResource {

    @Inject
    ApplicationResourceMapper appResourceMapper;

    @Inject
    ApplicationService applicationService;

    @POST
    @Path("/applications")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel createApplication(@Valid CreateAppModel createAppModel) throws ValidationException {
        var createApplicationDto = appResourceMapper.mapCreateAppDto(createAppModel);
        var errorMessages = applicationService.createApplication(createApplicationDto);
        return appResourceMapper.mapResultModel(createApplicationDto, errorMessages);
    }

    @GET
    @Path("/applications")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ApplicationModel> getApplications(@Valid @QueryParam String userId) throws ValidationException {
        var applicationDaos = applicationService.getApplications(userId);
        if (CollectionUtils.isEmpty(applicationDaos)) {
            return Collections.emptyList();
        }
        return appResourceMapper.mapApplicationModels(applicationDaos);
    }

    @GET
    @Path("/applications/{applicationId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public ApplicationModel getApplicationById(@Valid @PathParam Long applicationId) throws ValidationException {
        var applicationDao = applicationService.getApplicationById(applicationId);
        if (Objects.isNull(applicationDao)) {
            return null;
        }
        return appResourceMapper.mapApplicationModel(applicationDao);
    }

    @DELETE
    @Path("/applications/{applicationId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> deleteApplication(@Valid @PathParam Long applicationId) throws ValidationException {
        var deleteApplicationDto = appResourceMapper.mapDeleteApplicationDto(applicationId);
        var errorMessages = applicationService.deleteApplication(deleteApplicationDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }

    @PUT
    @Path("/applications/{applicationId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public List<ErrorMessageModel> updateApplication(@Valid @PathParam Long applicationId,
                                                     @Valid UpdateApplicationModel updateApplicationModel) throws ValidationException {
        var updateApplicationDto = appResourceMapper.mapUpdateApplicationDto(applicationId, updateApplicationModel);
        var errorMessages = applicationService.updateApplication(updateApplicationDto);
        if (CollectionUtils.isEmpty(errorMessages)) {
            return Collections.emptyList();
        }
        return CanaUtility.getErrorMessageModels(errorMessages);
    }
}
