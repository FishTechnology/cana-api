package cana.codelessautomation.api.resources.file;

import cana.codelessautomation.api.commons.exceptions.ValidationException;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.resources.file.mappers.FileResourceMapper;
import cana.codelessautomation.api.resources.file.models.MultipartBodyModel;
import cana.codelessautomation.api.services.file.FileService;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api")
public class FileResource {
    @Inject
    FileResourceMapper fileResourceMapper;

    @Inject
    FileService fileService;

    @POST
    @Path("/fileUpload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public ResultModel updateFile(@MultipartForm MultipartBodyModel multipartBodyModel) throws ValidationException {
        var updateFileDto = fileResourceMapper.mapUpdateFileDto(multipartBodyModel);
        var errorMessages = fileService.updateFile(updateFileDto);
        return fileResourceMapper.mapResultModel(errorMessages, updateFileDto);
    }
}
