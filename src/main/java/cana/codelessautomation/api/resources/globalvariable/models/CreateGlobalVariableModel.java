package cana.codelessautomation.api.resources.globalvariable.models;

import lombok.Data;

import javax.ws.rs.FormParam;
import java.io.File;


@Data
public class CreateGlobalVariableModel {
    private String key;
    private String value;
    private String valueType;
    @FormParam("file")
    private File file;
    private String comments;
    private Long userId;
}
