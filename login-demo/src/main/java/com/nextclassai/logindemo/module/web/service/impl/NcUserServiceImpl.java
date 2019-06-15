package com.nextclassai.logindemo.module.web.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nextclassai.logindemo.module.web.entity.dto.LoginDto;
import com.nextclassai.logindemo.module.web.entity.model.NcUser;
import com.nextclassai.logindemo.module.web.entity.vo.LoginVo;
import com.nextclassai.logindemo.module.web.mapper.NcUserMapper;
import com.nextclassai.logindemo.module.web.service.NcUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author Ststorytony
 * @date 2019/6/14 16:37
 * Description:
 */
@Service
public class NcUserServiceImpl extends ServiceImpl<NcUserMapper, NcUser> implements NcUserService {
    @Override
    public boolean saveUser(NcUser user) {
        if (user == null || StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())){
            throw new IllegalArgumentException();
        }
        user.setId(null);
        user.setCreatedTime(new Date());
        user.setUpdateTime(new Date());
        return save(user);
    }

    @Override
    public LoginVo validateUser(LoginDto loginDto) {
        if (loginDto == null || StringUtils.isEmpty(loginDto.getName()) || StringUtils.isEmpty(loginDto.getPassword())){
            throw new IllegalArgumentException();
        }
        NcUser user = getOne(Wrappers.<NcUser>lambdaQuery().eq(NcUser::getName, loginDto.getName()));
        if (user == null || !Objects.equals(user.getPassword(),loginDto.getPassword())){
            return null;
        }
        return LoginVo.builder()
                .id(user.getId())
                .name(user.getName())
                .gender(user.getGender())
                .createdTime(user.getCreatedTime())
                .updateTime(user.getUpdateTime())
                .build();
    }

    @Override
    public boolean validateUserName(String name) {
        if (StringUtils.isEmpty(name)){
            throw new IllegalArgumentException();
        }
        int count = count(Wrappers.<NcUser>lambdaQuery().eq(NcUser::getName, name));
        return count <= 0;
    }
}
