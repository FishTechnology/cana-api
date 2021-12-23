package cana.codelessautomation.api.resources.file.service.dtos;

import cana.codelessautomation.api.resources.customer.service.repository.daos.CustomDetailDao;
import com.googlecode.jmapper.annotations.JMap;
import lombok.Data;

import java.io.InputStream;
import java.time.OffsetDateTime;

@Data
public class UpdateFileDto {
    private Long id;
    private Long fileSize;
    private String fileName;
    private InputStream file;
    @JMap
    private String content;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    @JMap
    private Boolean isActive;
    private Long userId;
    private CustomDetailDao customDetail;
}
