package com.hp.demo.provider.dao.mapper;

import com.hp.demo.provider.dao.entity.HpProviderEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author hp
 * @version 1.0
 * @date 2021/10/31 21:20
 */
@Mapper
public interface HpProvideDo {

    Boolean insert(HpProviderEntity hpProviderEntity);

    HpProviderEntity getById(@Param("id") Long id);
}
