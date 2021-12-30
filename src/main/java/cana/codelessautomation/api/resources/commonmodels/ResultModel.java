package cana.codelessautomation.api.resources.commonmodels;

import lombok.Data;

import java.util.List;

@Data
public class ResultModel {
    private String Id;
    private List<ErrorMessageModel> errorMessages;
}
