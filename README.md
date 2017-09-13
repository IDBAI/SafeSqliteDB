#### 方便用于Android轻量级的SDK 数据持久化存储SDK，可以避免使用重量级的第三方库，导致跟APP已存在的库冲突问题！


###### 解决了SQL操作的各种多线程并发情况下的不安全问题：
```
         * android.database.sqlite.SQLiteCantOpenDatabaseException: unable to open database file
         * android.database.sqlite.SQLiteDatabaseLockedException: database is locked
         * java.lang.IllegalStateException: attempt to re-open an already-closed object
         * java.lang.NullPointerException: Attempt to invoke virtual method 'void android.database.sqlite.SQLiteDatabase.execSQL(java.lang.String)' on a null object reference

```