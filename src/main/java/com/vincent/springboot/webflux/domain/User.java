package com.vincent.springboot.webflux.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vincent.li
 */
@Data
public class User implements Serializable {

    private Long id;
    private String userName;
    private String gender;
    private int status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
