package cana.codelessautomation.api.resources.commonmodels;

import cana.codelessautomation.api.resources.envvariable.models.EnvVariableModel;
import lombok.Data;

import java.util.List;

@Data
public class PageSetDetailModel {
    private int pageNumber;
    private int pageSize;
    private int totalPageCount;
    private List<EnvVariableModel> items;
}
