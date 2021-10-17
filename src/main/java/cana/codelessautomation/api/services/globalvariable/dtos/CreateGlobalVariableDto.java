package cana.codelessautomation.api.services.globalvariable.dtos;

import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.globalvariable.repositories.daos.GlobalVariableDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CreateGlobalVariableDto {
    private Long id;
    @JMap
    private String key;
    @JMap
    private String value;
    @JMap
    private String valueType;
    @JMap
    private String content;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    @JMap
    private Boolean isActive;
    @JMap
    private String comments;
    @JMap
    private Long userId;
    private CustomDetailDao customDetail;
    private List<GlobalVariableDao> globalVariables;
}