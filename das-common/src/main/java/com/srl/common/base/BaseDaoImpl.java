package com.srl.common.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.srl.common.db.C3p0Utils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.RowProcessor;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

    private QueryRunner qRunner;
    private Class<T> type;

    {
        qRunner = new QueryRunner(C3p0Utils.getDataSource());
        // getGenericSuperclass获取带有泛型的父类
        // Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
        Type superClas = getClass().getGenericSuperclass();
        ParameterizedType p = (ParameterizedType) superClas;
        // getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        type = (Class<T>) p.getActualTypeArguments()[0];

    }

    @Override
    public Object querySignleCloumn(Connection conn, String sql, Object... params) throws SQLException {
        return qRunner.query(conn, sql, new ScalarHandler<Object>(), params);
    }

    @Override
    public Object querySignleCloumn(String sql, Object... params) throws SQLException {
        return qRunner.query(sql, new ScalarHandler<Object>(), params);
    }

    @Override
    public List<T> queryBeanList(Connection conn, String sql, Object... params) throws SQLException {
        BeanProcessor bProcessor = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bProcessor);
        return qRunner.query(conn, sql, new BeanListHandler<T>(type, processor), params);
    }

    @Override
    public List<T> queryBeanList(String sql, Object... params) throws SQLException {
        BeanProcessor bProcessor = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bProcessor);
        return qRunner.query(sql, new BeanListHandler<T>(type, processor), params);
    }

    @Override
    public T getBy(Connection conn, String sql, Object... params) throws SQLException {
        BeanProcessor bProcessor = new GenerousBeanProcessor();
        RowProcessor processor = new BasicRowProcessor(bProcessor);
        return qRunner.query(conn, sql, new BeanHandler<>(type, processor), params);
    }

    @Override
    public int update(Connection conn, String sql, Object... params) throws SQLException {
        return qRunner.update(conn, sql, params);
    }

    @Override
    public int update(String sql, Object... params) throws SQLException {
        return qRunner.update(sql, params);
    }

}
