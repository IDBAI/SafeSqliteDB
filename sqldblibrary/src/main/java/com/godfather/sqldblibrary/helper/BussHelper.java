package com.godfather.sqldblibrary.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


public class BussHelper {
    /**
     * 需要添加 方法锁 synchronized 确保多线程环境下安全
     *
     * @param context
     * @param javaBeanClass 需要转换的为的对象
     * @param tableName     对应的SQLite数据库的表名
     * @param <T>           返回一个 javaBeanClass 对应的列表
     * @return
     */
    public synchronized static <T> List<T> queryAll(Context context, Class<T> javaBeanClass, String tableName) {
        List<T> list = new ArrayList<>();
        SQLiteDatabase db = SqliteHelper.getInstance(context).getSafeReadable();
        db.beginTransaction();
        T bean;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + tableName, null);
            while (cursor.moveToNext()) {
                bean = javaBeanClass.newInstance();
                bindValues(bean, cursor);
                list.add(bean);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null)
                cursor.close();
            db.endTransaction();
            SqliteHelper.getInstance(context).safeCloseDB();
        }
        return list;
    }

    /**
     * 获取游标的值，为泛型对象的成员赋值，
     *
     * @param bean
     * @param cursor
     * @param <T>
     */
    private synchronized static <T> void bindValues(T bean, Cursor cursor) {
        Class<?> aClass = bean.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            Class<?> type = field.getType();
            Object values = null;
            if (type == int.class)
                values = cursor.getInt(cursor.getColumnIndex(name));
            else if (type == float.class)
                values = cursor.getFloat(cursor.getColumnIndex(name));
            else if (type == double.class)
                values = cursor.getDouble(cursor.getColumnIndex(name));
            else if (type == String.class)
                values = cursor.getString(cursor.getColumnIndex(name));
            AssignValueForAttributeHelper.setAttrributeValue(bean, name, values);
        }
    }
}
