package com.godfather.safesqlitedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.godfather.sqldblibrary.bean.UserBean;
import com.godfather.sqldblibrary.buss.UserBuss;
import com.godfather.sqldblibrary.helper.BussHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void DBTest(){


        UserBean bean = new UserBean();
        /**
         * 构造Bean对象
         */

        /**支持多线程并发的插入操作
         *
         * android.database.sqlite.SQLiteCantOpenDatabaseException: unable to open database file
         * android.database.sqlite.SQLiteDatabaseLockedException: database is locked
         * java.lang.IllegalStateException: attempt to re-open an already-closed object
         * java.lang.NullPointerException: Attempt to invoke virtual method 'void android.database.sqlite.SQLiteDatabase.execSQL(java.lang.String)' on a null object reference

         */
        UserBuss.insertRow(this.getApplicationContext(),bean);

        //支持多线程并发的通用查询方法
        List<UserBean> userBeen = BussHelper.queryAll(this.getApplicationContext(), UserBean.class, UserBuss.tableName);



    }
}
