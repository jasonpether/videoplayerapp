package iTube.App.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import iTube.App.model.User;
import iTube.App.util.Util;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_USER_TABLE = "CREATE TABLE " +
                Util.TABLE_NAME +
                "(" + Util.USER_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Util.USERNAME + " TEXT, " + Util.PASSWORD + " TEXT)";

         sqLiteDatabase.execSQL(CREATE_USER_TABLE);

        String CREATE_URL_PLAYLIST_TABLE = "CREATE TABLE " + Util.SECONDTABLE_NAME + "("
                + Util.PLAYLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Util.USER_ID_FK + " INTEGER,"
                + Util.YOUTUBE_LINK + " TEXT,"
                + "FOREIGN KEY(" + Util.USER_ID_FK + ") REFERENCES " + Util.TABLE_NAME + "(" + Util.USER_ID + ")" + ")";
        sqLiteDatabase.execSQL(CREATE_URL_PLAYLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});
        String DROP_URL_TABLE = "DROP TABLE IF EXISTS ";
        sqLiteDatabase.execSQL(DROP_URL_TABLE, new String[]{Util.SECONDTABLE_NAME});

        onCreate(sqLiteDatabase);
    }

    public long insertUser(User user)
    {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Util.USERNAME, user.getUsername());
        cv.put(Util.PASSWORD, user.getPassword());

        long newRowId = myDB.insert(Util.TABLE_NAME, null, cv);
        myDB.close();

        return newRowId;
    }

    public int fetchUser(String username, String password) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        int userId = -1; // Default value if user not found

        Cursor cursor = myDB.query(
                Util.TABLE_NAME,
                new String[]{Util.USER_ID},
                Util.USERNAME + "=? AND " + Util.PASSWORD + "=?",
                new String[]{username, password},
                null,
                null,
                null
        );

        // Check if the cursor has results
        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex(Util.USER_ID));
        }

        cursor.close();
        myDB.close();

        return userId;
    }


    public Cursor getAllLinks() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                Util.PLAYLIST_ID,
                Util.USER_ID_FK,
                Util.YOUTUBE_LINK
        };

        Cursor cursor = db.query(
                Util.SECONDTABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }


    public long insertVideoLink(int userId, String youtubeLink) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Util.USER_ID_FK, userId);
        values.put(Util.YOUTUBE_LINK, youtubeLink);

        long newRowId = db.insert(Util.SECONDTABLE_NAME, null, values);
        db.close();
        return newRowId;
    }

}
