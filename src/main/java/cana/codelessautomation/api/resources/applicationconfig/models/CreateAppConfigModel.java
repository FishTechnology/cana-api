package cana.codelessautomation.api.resources.applicationconfig.models;

import lombok.Data;

@Data
public class CreateAppConfigModel {
    private String key;
    private String value;
    private String userId;
}
