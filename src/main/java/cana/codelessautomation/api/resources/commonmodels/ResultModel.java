package cana.codelessautomation.api.resources.commonmodels;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ResultModel {
    private Long Id;
    private UUID uuId;
    private List<ErrorMessageModel> errorMessages;
}
