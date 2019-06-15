package com.nextclassai.logindemo.module.web.controller;

import com.nextclassai.logindemo.common.WrapMapper;
import com.nextclassai.logindemo.common.Wrapper;
import com.nextclassai.logindemo.module.web.entity.dto.LoginDto;
import com.nextclassai.logindemo.module.web.entity.model.NcUser;
import com.nextclassai.logindemo.module.web.entity.vo.LoginVo;
import com.nextclassai.logindemo.module.web.service.NcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ststorytony
 * @date 2019/6/14 16:36
 * Description:
 */
@RestController
@RequestMapping("user")
public class NcUserController {
    private final NcUserService ncUserService;

    @Autowired
    public NcUserController(NcUserService ncUserService) {
        this.ncUserService = ncUserService;
    }

    @PostMapping("/register")
    public Wrapper<Boolean> registerUser(@RequestBody NcUser user){
        boolean flag = ncUserService.saveUser(user);
        return WrapMapper.ok(flag);
    }

    @GetMapping("/validate")
    public Wrapper<Boolean> validate(@RequestParam String name){
        boolean flag = ncUserService.validateUserName(name);
        return WrapMapper.ok(flag);
    }

    @PostMapping("/login")
    public Wrapper login(@RequestBody LoginDto loginDto){
        LoginVo vo  = ncUserService.validateUser(loginDto);
        if (vo == null){
            return WrapMapper.ok(false);
        }else{
            return WrapMapper.ok(vo);
        }
    }
}
