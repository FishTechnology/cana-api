package cana.codelessautomation.api.resources.action.mappers;

import cana.codelessautomation.api.resources.action.models.CreateActionModel;
import cana.codelessautomation.api.resources.action.models.CreateActionOptionModel;
import cana.codelessautomation.api.resources.commonmodels.ResultModel;
import cana.codelessautomation.api.services.action.dtos.CreateActionDto;
import cana.codelessautomation.api.services.action.dtos.CreateActionOptionDto;
import cana.codelessautomation.api.services.action.repositories.daos.ActionOptionTypeDao;
import cana.codelessautomation.api.services.action.repositories.daos.ActionTypeDao;
import cana.codelessautomation.api.services.common.dtos.ErrorMessageDto;
import cana.codelessautomation.api.services.utilities.CanaUtility;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.EnumUtils;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ActionResourceMapperImpl implements ActionResourceMapper {
    @Override
    public CreateActionDto mapCreateActionDto(CreateActionModel createActionModel, Long testCaseId) {
        CreateActionDto createActionDto = new CreateActionDto();
        createActionDto.setTestCaseId(testCaseId);
        createActionDto.setUserId(createActionModel.getUserId());
        createActionDto.setKey(createActionModel.getKey());
        createActionDto.setValue(createActionModel.getValue());
        createActionDto.setType(EnumUtils.getEnumIgnoreCase(ActionTypeDao.class, createActionModel.getType()));
        createActionDto.setComments(createActionModel.getComments());
        List<CreateActionOptionDto> createActionOptionDtos = new ArrayList<>();
        if (CollectionUtils.isEmpty(createActionModel.getOptionModels())) {
            return createActionDto;
        }
        for (CreateActionOptionModel createActionOptionModel : createActionModel.getOptionModels()) {
            CreateActionOptionDto createActionOptionDto = new CreateActionOptionDto();
            createActionOptionDto.setOptionType(EnumUtils.getEnumIgnoreCase(ActionOptionTypeDao.class, createActionOptionModel.getOptionType()));
            createActionOptionDto.setWaitDuration(createActionOptionModel.getWaitDuration());
            createActionOptionDto.setOrder(createActionOptionModel.getOrder());
            createActionOptionDtos.add(createActionOptionDto);
        }
        createActionDto.setOptionDtos(createActionOptionDtos);
        return createActionDto;
    }

    @Override
    public ResultModel mapResultModel(CreateActionDto createActionDto, List<ErrorMessageDto> errorMessages) {
        ResultModel resultModel = new ResultModel();
        if (CollectionUtils.isNotEmpty(errorMessages)) {
            resultModel.setErrorMessages(CanaUtility.getErrorMessageModels(errorMessages));
            return resultModel;
        }
        resultModel.setId(createActionDto.getId());
        return resultModel;
    }
}
