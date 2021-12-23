package cana.codelessautomation.api.resources.testplan.service.dtos;

import cana.codelessautomation.api.resources.testplan.service.repositories.daos.TestplanDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateTestplanDto {
    @JMap
    private Long userId;
    @JMap
    private String name;
    @JMap
    private String comments;
    private Long testplanId;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private TestplanDao testplan;
}
