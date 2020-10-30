package com.sw.chefubao.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sw.chefubao.common.enums.DrivingLicenceEnum;
import com.sw.chefubao.common.finals.DrivingLicenceFinal;
import com.sw.chefubao.entity.CarType;
import com.sw.chefubao.entity.DrivingLicense;
import com.sw.chefubao.entity.User;
import com.sw.chefubao.mapper.CarTypeMapper;
import com.sw.chefubao.mapper.DrivingLicenseMapper;
import com.sw.chefubao.mapper.UserCarMapper;
import com.sw.chefubao.mapper.UserMapper;
import com.sw.chefubao.service.CarTypeService;
import com.sw.chefubao.service.DrivingLicenseService;
import com.sw.chefubao.service.UserCarService;
import com.sw.chefubao.service.UserService;
import com.sw.chefubao.vo.UserCarParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DrivingLicenseServiceImpl extends ServiceImpl<DrivingLicenseMapper, DrivingLicense> implements DrivingLicenseService {
    @Override
    public int createDrivingLicense(UserCarParamVo userCarParamVo,Integer carId) {
        Integer userId = userCarParamVo.getUserId();
        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setUserId(userId);
        drivingLicense.setCarId(carId);
        drivingLicense.setStamp(DrivingLicenceFinal.SET_STAMP);
        drivingLicense.setTitle(DrivingLicenceFinal.SET_TITLE);
        DateTime date = DateUtil.date();
        DateTime dateTime = DateUtil.offsetMonth(date, DrivingLicenceFinal.SET_VALIDITY);
        drivingLicense.setDateOfIssue(DateUtil.format(date, "yyyy-MM-dd HH:mm:ss"));
        drivingLicense.setValidity(DateUtil.format(dateTime, "yyyy-MM-dd"));
        drivingLicense.setCarNum(null);
        drivingLicense.setCarNumStatus(DrivingLicenceEnum.CAR_NUM_NOT.getKey());
        return baseMapper.insert(drivingLicense);
    }
}
