package cana.codelessautomation.api.services.globalvariable.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableType;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class UpdateGlobalVariableDto {
    private Long globalVariableId;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private GlobalVariableType valueType;
    @JMap
    private String comments;
    @JMap
    private Long userId;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private CustomDetailDao customDetail;
    private List<GlobalVariableDao> globalVariables;
    private GlobalVariableDao globalVariable;
}
