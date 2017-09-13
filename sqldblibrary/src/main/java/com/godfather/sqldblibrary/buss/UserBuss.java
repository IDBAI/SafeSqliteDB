package com.godfather.sqldblibrary.buss;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.godfather.sqldblibrary.bean.UserBean;
import com.godfather.sqldblibrary.helper.SqlStatementHelper;
import com.godfather.sqldblibrary.helper.SqliteHelper;


public class UserBuss {
    public static String tableName = "user";

    public synchronized static void createTable(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append(SqlStatementHelper.CreateTablePre)
                .append(tableName)
                .append("(")
                .append(SqlStatementHelper.ID_PRIVATE_KEY)
                .append("userId  TEXT").append(",")
                .append("mobileNum TEXT").append(",")
                .append("communityId TEXT").append(",")
                .append("tag TEXT").append(")");
        db.execSQL(sb.toString());
    }

    /**
     * @param context
     * @param bean
     * @return 返回 最新插入数据的自增长主键ID
     */
    public synchronized static int insertRow(Context context, UserBean bean) {
        int ID = -1;
        if (bean == null)
            return ID;
        SQLiteDatabase db = SqliteHelper.getInstance(context).getSafeWriteable();
        db.beginTransaction();
        try {
            db.execSQL("INSERT INTO  " + tableName + " (userId, mobileNum, communityId,tag)  VALUES(?,?,?,?)", new Object[]{
                    bean.userId, bean.mobileNum, bean.communityId, bean.tag
            });
            Cursor cursor = db.rawQuery("SELECT last_insert_rowid() FROM " + tableName, null);
            if (cursor.moveToFirst()) {
                ID = cursor.getInt(0);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            SqliteHelper.getInstance(context).safeCloseDB();
        }
        return ID;
    }
}
