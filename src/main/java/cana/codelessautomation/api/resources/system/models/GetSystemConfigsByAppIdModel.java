package cana.codelessautomation.api.resources.system.models;

import cana.codelessautomation.api.resources.commonmodels.ErrorMessageModel;
import lombok.Data;

import java.util.List;

@Data
public class GetSystemConfigsByAppIdModel {
    private List<ErrorMessageModel> errors;
    private List<SystemConfigModel> systemConfigs;
}
