package com.nie.tool.server.test;

import com.mybatisflex.core.query.QueryWrapper;
import com.nie.tool.server.dao.test.AccountMapper;
import com.nie.tool.server.model.entity.test.Account;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.nie.tool.server.model.entity.test.table.AccountTableDef.ACCOUNT;


/**
 * @author njy
 * @since 2024/6/28 13:02
 */
@SpringBootTest
public class MybatisFlexTestApplicationTests {
    @Resource
    AccountMapper accountMapper;


    @Test
    void contextLoads() {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .select()
                .where(ACCOUNT.AGE.eq(18));
        Account account = accountMapper.selectOneByQuery(queryWrapper);
        System.out.println(account);
    }

}
