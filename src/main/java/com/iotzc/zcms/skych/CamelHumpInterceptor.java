package com.iotzc.zcms.skych;

import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import com.iotzc.zcms.util.StringUtil;

/**
 * 将返回结果map的key统一转成java驼峰形式
 * */
@Intercepts(
        @Signature(
                args = {Statement.class},
                method = "handleResultSets",
                type = ResultSetHandler.class
                )
        )
public class CamelHumpInterceptor implements Interceptor {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> list = (List<Object>)invocation.proceed();//返回值类型参考@Signature中指定的方法
        for (Object object : list) {
            if (object instanceof Map) {
                processMap((Map)object);
            } else {
                continue;
            }
        }
        return list;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {}

    private void processMap(Map<String, Object> map) {
        Set<String> keys = new HashSet<>(map.keySet());//不能直接使用Set<String> keys = map.keySet();，然后在循环中更新map
        String camelKey = null;
        for (String key : keys) {//实际使用for(Iterator it = keys.iterator;it.hasNext();){}
            if (key.equals(camelKey = StringUtil.underLine2CamelCase(key))) {
                continue;
            }
            map.put(camelKey, map.remove(key));
        }
    }
}
