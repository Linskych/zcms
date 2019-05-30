package com.iotzc.zcms.skych;

import java.sql.Types;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

public class SkychJavaTypeResolver extends JavaTypeResolverDefaultImpl {

    public SkychJavaTypeResolver() {
        super();
        //覆盖父类中将数据库的tinyint转为java的byte类型
        typeMap.put(Types.TINYINT, new JdbcTypeInformation("TINYINT", //$NON-NLS-1$
                new FullyQualifiedJavaType(Integer.class.getName())));
    }
}
