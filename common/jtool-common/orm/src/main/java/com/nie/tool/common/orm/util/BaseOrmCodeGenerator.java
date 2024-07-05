package com.nie.tool.common.orm.util;

import com.mybatisflex.codegen.Generator;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author njy
 * @since 2024/6/28 13:10
 */
public abstract class BaseOrmCodeGenerator {


    public void start() {
        HikariDataSource dataSource = new HikariDataSource();
        // 配置数据源
        setDataSourceConfig(dataSource);
        //

        //创建配置内容，两种风格都可以。
        GlobalConfigUseStyle globalConfigUseStyle = new GlobalConfigUseStyle();
        setGlobalConfigUseStyle(globalConfigUseStyle);

//        GlobalConfig globalConfig = createGlobalConfigUseStyle1();
        //GlobalConfig globalConfig = createGlobalConfigUseStyle2();

        //通过 datasource 和 globalConfig 创建代码生成器
        Generator generator = new Generator(dataSource, null);

        //生成代码
        generator.generate();
    }


    /**
     * 配置数据源
     * <p>
     * dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/your-database?characterEncoding=utf-8");
     * dataSource.setUsername("root");
     * dataSource.setPassword("******");
     * </p>
     *
     * @param dataSource dataSource
     */
    abstract void setDataSourceConfig(HikariDataSource dataSource);

    abstract void setGlobalConfigUseStyle(GlobalConfigUseStyle globalConfigUseStyle);


    public static class GlobalConfigUseStyle {
        String  basePackage         = "com.test";
        String  tablePrefix         = "t_";
        boolean genEntity           = true;
        boolean genEntityWithLombok = true;
        int     jdkVersion          = 21;
        boolean genMapper           = true;

    }
}
