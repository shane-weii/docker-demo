package com.nextclassai.logindemo.module.web.entity.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Ststorytony
 * @date 2019/6/14 16:42
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class NcUser extends Model<NcUser> {
    private static final long serialVersionUID = -5567511087080543676L;

    private Long id;

    private String name;

    private String password;

    private Integer gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
