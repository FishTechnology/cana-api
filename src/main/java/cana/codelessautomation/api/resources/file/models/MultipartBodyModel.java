package cana.codelessautomation.api.resources.file.models;

import lombok.Data;
import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Data
public class MultipartBodyModel {
    @FormParam("file")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    private InputStream file;

    @FormParam("fileName")
    @PartType(MediaType.TEXT_PLAIN)
    private String fileName;

    @FormParam("userId")
    @PartType(MediaType.TEXT_PLAIN)
    private Long userId;

    @FormParam("size")
    @PartType(MediaType.TEXT_PLAIN)
    private Long fileSize;
}
