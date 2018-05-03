package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

import android.provider.BaseColumns;

/**
 * Created by donaldtran on 4/29/18.
 *
 * DEFINES THE Database Contract and Underlying Schema for the Aubie Memory Game Leader Board
 *
 * FROM: https://developer.android.com/training/data-storage/sqlite#java
 *
 * A formal declaration of how the database is organized. This contract class
 * explicitly specifies the layout of the schema in a systematic and self-documenting way.
 * A contract class is a container for constants that define names for URIs, tables, and columns.
 * The contract class allows you to use the same constants across all the other classes in the
 * same package. This lets you change a column name in one place and have it propagate
 * throughout your code.
 *
 */

public final class ScoreboardDBContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "highscores.db";
    private static final String TEXT_TYPE          = "TEXT";
    private static final String INT_TYPE           = "INT";
    private static final String COMMA              = ",";
    private static final String SPACE              = " ";

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private ScoreboardDBContract() {}

    // Using Hungarian notation to specify types
    public static abstract class ScoreboardEntry implements BaseColumns  {
        public static final String TABLE_NAME           = "tbl_aubiescores";
        public static final String COLUMN_USERNAMES     = "txt_username";
        public static final String COLUMN_SCORES        = "int_scores";
        public static final String COLUMN_DATE_ADDED    = "date_dateadded";
        public static final String CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME +
                " (" + _ID + " INTEGER PRIMARY KEY" + COMMA + SPACE +
                COLUMN_USERNAMES + SPACE + TEXT_TYPE + COMMA + SPACE +
                COLUMN_SCORES + SPACE + INT_TYPE + COMMA + SPACE +
                COLUMN_DATE_ADDED + SPACE + INT_TYPE + " )";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}