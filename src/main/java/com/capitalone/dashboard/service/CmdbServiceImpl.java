package com.capitalone.dashboard.service;

import com.capitalone.dashboard.model.BaseModel;
import com.capitalone.dashboard.model.Cmdb;
import com.capitalone.dashboard.repository.CmdbRepository;
import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmdbServiceImpl implements CmdbService {

    private final CmdbRepository cmdbRepository;

    @Autowired
    public CmdbServiceImpl(CmdbRepository cmdbRepository) {
        this.cmdbRepository = cmdbRepository;

    }

    @Override
    public Page<Cmdb> configurationItemsByTypeWithFilter(String itemType, String filter, Pageable pageable) {

        Page<Cmdb> configItemString;

        if( StringUtils.isNotEmpty( filter ) ){

            List<Cmdb> cmdbList = cmdbRepository.findAllByConfigurationItemContainingOrCommonNameContainingAllIgnoreCase(filter,filter);

            List<ObjectId> cmdbIdsList = cmdbList.stream().map(BaseModel::getId).collect(Collectors.toList());

            configItemString = cmdbRepository.findAllByItemTypeAndValidConfigItemAndIdIn(itemType,true, cmdbIdsList, pageable);

        }else{
            configItemString = cmdbRepository.findAllByItemTypeAndConfigurationItemContainingIgnoreCaseAndValidConfigItem(
                    itemType, filter, pageable, true);
        }
        return configItemString;
    }
    @Override
    public String configurationItemNameByObjectId(ObjectId objectId){
        Cmdb cmdb = configurationItemsByObjectId(objectId);
        return cmdb.getConfigurationItem();
    }
    @Override
    public Cmdb configurationItemsByObjectId(ObjectId objectId){
        return cmdbRepository.findOne(objectId);
    }
    @Override
    public Cmdb configurationItemByConfigurationItem(String configItem){
        return cmdbRepository.findByConfigurationItemIgnoreCase(configItem);
    }
    @Override
    public List<Cmdb> getAllBusServices(){
        return cmdbRepository.findAllByItemType("app");
    }
}
