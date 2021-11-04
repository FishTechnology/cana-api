package cana.codelessautomation.api.services.action.dtos;

import cana.codelessautomation.api.services.action.repositories.daos.ActionTypeDao;
import cana.codelessautomation.api.services.action.repositories.daos.UIActionTypeDao;
import cana.codelessautomation.api.services.customer.repository.daos.CustomDetailDao;
import cana.codelessautomation.api.services.testcase.repositories.daos.TestCaseDao;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class CreateActionDto {
    private Long id;
    private Long testCaseId;
    private String key;
    private String value;
    private ActionTypeDao type;
    private UIActionTypeDao uiActionType;
    private Long userId;
    private String comments;
    private CustomDetailDao customDetail;
    private Boolean isActive;
    private Long order;
    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private String createdBy;
    private String modifiedBy;
    private TestCaseDao testCaseDao;
    private List<CreateActionOptionDto> optionDtos;
    private BrowserDetailDto browserDetailDto;
}
