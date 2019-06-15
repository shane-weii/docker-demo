package com.nextclassai.logindemo.module.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.nextclassai.logindemo.module.web.entity.dto.LoginDto;
import com.nextclassai.logindemo.module.web.entity.model.NcUser;
import com.nextclassai.logindemo.module.web.entity.vo.LoginVo;

/**
 * @author Ststorytony
 * @date 2019/6/14 16:37
 * Description:
 */
public interface NcUserService extends IService<NcUser> {
    boolean saveUser(NcUser user);

    LoginVo validateUser(LoginDto loginDto);

    boolean validateUserName(String name);
}
