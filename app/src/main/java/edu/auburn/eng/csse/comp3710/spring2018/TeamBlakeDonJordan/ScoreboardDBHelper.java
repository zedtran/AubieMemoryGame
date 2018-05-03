package edu.auburn.eng.csse.comp3710.spring2018.TeamBlakeDonJordan;

/**
 * Created by donaldtran on 4/29/18.
 *
 * A helper class to manage database creation and version management.
 * You create a subclass implementing onCreate(SQLiteDatabase), onUpgrade(SQLiteDatabase, int, int)
 * and optionally onOpen(SQLiteDatabase), and this class takes care of opening the database if it exists,
 * creating it if it does not, and upgrading it as necessary. Transactions are used to make sure the
 * database is always in a sensible state. This class makes it easy for ContentProvider implementations
 * to defer opening and upgrading the database until first use, to avoid blocking application startup
 * with long-running database upgrades.
 *
 */


import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;



public class ScoreboardDBHelper extends SQLiteOpenHelper {

    // The SQLite where clause for user score update, replace, and delete
    private static final String WHERE_CLAUSE =
            ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES + " = ? AND " +
            ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES + "= ? AND " +
            ScoreboardDBContract.ScoreboardEntry.COLUMN_DATE_ADDED + " = ?";

    // Public constructor for instantiation and use in different classes
    public ScoreboardDBHelper(Context context) {
        super(context, ScoreboardDBContract.DATABASE_NAME, null, ScoreboardDBContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ScoreboardDBContract.ScoreboardEntry.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    // This database is only a cache for data, so its upgrade policy is
    // to simply to discard the data and start over
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ScoreboardDBContract.ScoreboardEntry.DELETE_TABLE);
        onCreate(db);
    }

    // Add a new user score
    public void addUserScore(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES, user.getUserName());
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES, user.getScore());
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_DATE_ADDED, user.getDateUserAdded());
        user.setID(db.insertOrThrow(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, null, values));
        Log.d("addUserScore", user.toString());
        db.close();
    }

    // Get Top 10 User Scores
    public ArrayList<User> getTopTenUsers(){
        ArrayList<User> userArrayList = new ArrayList<>();

        // This query should be the equivalent of getting us the top 10 high scores
        String query = "SELECT * FROM " + ScoreboardDBContract.ScoreboardEntry.TABLE_NAME +
                      " ORDER BY " + ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES + " ASC LIMIT 10";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user;
        if (cursor.moveToFirst()) {
            do{
                user = new User(cursor.getString(0), cursor.getLong(1), cursor.getInt(2));
                userArrayList.add(user);
            } while (cursor.moveToNext());
            cursor.close();
        }
        Log.d("getTopTenUsers", userArrayList.toString());
        return userArrayList;
    }

    // Update existing user score
    public long updateUserScore(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = new String[] {String.valueOf(user.getUserName()),
                String.valueOf(user.getScore()),
                String.valueOf(user.getDateUserAdded())
        };
        ContentValues values = new ContentValues();
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES, user.getUserName());
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES, user.getScore());
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_DATE_ADDED, user.getDateUserAdded());
        user.setID(db.update(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, values, WHERE_CLAUSE, whereArgs));
        db.close();
        Log.d("updateUserScore", user.toString());
        return user.getID();
    }

    // Replace all values of oldUser with that of newUser
    public long replaceUserScore(User oldUser, User newUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = new String[] {String.valueOf(oldUser.getUserName()),
                String.valueOf(oldUser.getScore()),
                String.valueOf(oldUser.getDateUserAdded())
        };
        ContentValues values = new ContentValues();
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_USERNAMES, newUser.getUserName());
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_SCORES, newUser.getScore());
        values.put(ScoreboardDBContract.ScoreboardEntry.COLUMN_DATE_ADDED, newUser.getDateUserAdded());
        newUser.setID(db.update(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, values, WHERE_CLAUSE, whereArgs));
        db.close();
        Log.d("replaceUserScore", oldUser.toString() + " replaced with " + newUser.toString());
        return newUser.getID();
    }


    // Delete existing user score
    public void deleteUserScore(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereArgs[] = new String[] {String.valueOf(user.getUserName()),
                String.valueOf(user.getScore()),
                String.valueOf(user.getDateUserAdded())
        };
        user.setID(db.delete(ScoreboardDBContract.ScoreboardEntry.TABLE_NAME, WHERE_CLAUSE, whereArgs));
        db.close();
        Log.d("deleteUserScore", user.toString());
    }

}
