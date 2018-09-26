/**
 * 
 */
package com.srl.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.srl.common.annotation.TableCloumn;
import com.srl.common.annotation.TableId;
import com.srl.common.annotation.TableName;
import org.apache.commons.lang3.StringUtils;

/**
 * @Description:
 * @author: zwl
 * @date: 2018年8月19日 下午3:38:21
 */
public class BeanToSqlUtils {

	private static String tableName;

	private static List<String> fieldList;

	private static String tableId;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void reflectivBean(Class cls) {
		// 获取字节码对象类的注解
		boolean isAnnoPresent = cls.isAnnotationPresent(TableName.class);
		tableName = null;
		if (isAnnoPresent) {
			TableName tableNameAnno = (TableName) cls.getAnnotation(TableName.class);
			// 获取表名
			tableName = tableNameAnno.name();
		}

		// 获取表的普通字段名称
		Field[] fields = cls.getDeclaredFields();
		fieldList = new ArrayList<>();
		TableCloumn tableCloumn = null;
		TableId tableIdAnno = null;
		tableId = null;
		for (Field field : fields) {
			// 获取列的名称
			field.setAccessible(true);
			// 判断属性中是否有这个TableCloumn注解
			boolean isFieldAnno = field.isAnnotationPresent(TableCloumn.class);
			if (isFieldAnno) {
				tableCloumn = field.getAnnotation(TableCloumn.class);
				fieldList.add(tableCloumn.value());
			}

			// 判断属性中是否有这个TableId注解
			isFieldAnno = field.isAnnotationPresent(TableId.class);
			if (isFieldAnno) {
				tableIdAnno = field.getAnnotation(TableId.class);
				tableId = tableIdAnno.value();
			}
		}

		// 获取父类的字段
		Class superclass = cls.getSuperclass();// 向上遍历父类
		fields = superclass.getDeclaredFields();
		for (Field field : fields) {
			// 获取列的名称
			field.setAccessible(true);
			// 判断属性中是否有这个注解
			boolean isFieldAnno = field.isAnnotationPresent(TableCloumn.class);
			if (isFieldAnno) {
				tableCloumn = field.getAnnotation(TableCloumn.class);
				fieldList.add(tableCloumn.value());
			}

			// 判断属性中是否有这个TableId注解
			isFieldAnno = field.isAnnotationPresent(TableId.class);
			if (isFieldAnno) {
				tableIdAnno = field.getAnnotation(TableId.class);
				tableId = tableIdAnno.value();
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public static String queryAllSql(Class cls) {
		StringBuilder sBuilder = new StringBuilder();
		reflectivBean(cls);
		// 拼接sequel
		if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(tableId) && fieldList != null
				&& fieldList.size() > 0) {
			sBuilder.append("select * from  ").append(tableName);
		}
		return sBuilder.toString();
	}
	
	@SuppressWarnings({ "rawtypes" })
	public static String querySql(Class cls) {
		StringBuilder sBuilder = new StringBuilder();
		reflectivBean(cls);
		// 拼接sequel
		if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(tableId) && fieldList != null
				&& fieldList.size() > 0) {
			sBuilder.append("select ");
			String str = StringUtils.join(fieldList, ",");
			sBuilder.append(str);
			sBuilder.append(" from ").append(tableName);
		}
		return sBuilder.toString();
	}

	@SuppressWarnings({ "rawtypes" })
	public static String updateSql(Class cls) {
		StringBuilder sBuilder = new StringBuilder();
		reflectivBean(cls);
		// 拼接sequel
		if (StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(tableId) && fieldList != null
				&& fieldList.size() > 0) {
			sBuilder.append("update ").append(tableName);
			sBuilder.append(" set ");
			String str = StringUtils.join(fieldList, "=?,");
			sBuilder.append(str);
			sBuilder.append("=?");
			sBuilder.append(" where ").append(tableId);
			sBuilder.append("=?");
		}

		return sBuilder.toString();
	}

	/**
	 * 传递一个实体字节码对象，通过反射生成insert的语句 insert into 表名(列名,列名) values(?,?,?,?)
	 * 
	 * @param cls
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String insertSql(Class cls) {
		StringBuilder sBuilder = new StringBuilder();
        reflectivBean(cls);

		// 拼接sql
		if (StringUtils.isNotEmpty(tableName) && fieldList != null && fieldList.size() > 0) {
			sBuilder.append("insert into ").append(tableName);
			// StringUtils.join(fieldList, ",")把list的值以，连接
			sBuilder.append("(").append(StringUtils.join(fieldList, ","));
			sBuilder.append(") values(");
			for (int i = 0; i < fieldList.size(); i++) {
				if (i != fieldList.size() - 1) {
					sBuilder.append("?,");
				} else {
					sBuilder.append("?");
				}
			}
			sBuilder.append(")");
		}

		return sBuilder.toString();
	}
}
