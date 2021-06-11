package com.hansung.android.teamproject2;

//데이터베이스 저장을 위한 스키마(계약클래스) 06.12
import android.provider.BaseColumns;

public final class UserContract {
    public static final String DB_NAME = "user.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = "TEXT";
    private static final String COMMA_SEP = ",";

    private UserContract() {}

    public static class Users implements BaseColumns {
        public static final String TABLE_NAME = "Users";
        public static final String KEY_HOUR = "hour";
        public static final String KEY_MINUTE = "minute";
        public static final String KEY_AMPM = "AMPM";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY" +
                COMMA_SEP + KEY_HOUR + TEXT_TYPE + COMMA_SEP + KEY_MINUTE + TEXT_TYPE + KEY_AMPM + TEXT_TYPE + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
