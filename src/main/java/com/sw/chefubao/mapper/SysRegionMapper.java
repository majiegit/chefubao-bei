package com.sw.chefubao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sw.chefubao.entity.SysRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mj
 * 地区
 */
public interface SysRegionMapper extends BaseMapper<SysRegion> {

    List<SysRegion> selectListByPermissionId(@Param("id") Integer id);

    List<SysRegion>  getList(@Param("pid") Integer pid);

}
