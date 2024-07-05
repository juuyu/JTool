package com.nie.tool.server.model.entity.test;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.keygen.KeyGenerators;
import lombok.Data;

import java.util.Date;

/**
 * @author njy
 * @since 2024/6/28 12:59
 */
@Data
@Table("tb_account")
public class Account {
    @Id(keyType = KeyType.Generator, value = KeyGenerators.snowFlakeId)
    private Long    id;
    private String  userName;
    private Integer age;
    private Date    birthday;
}
