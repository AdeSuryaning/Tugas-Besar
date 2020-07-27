package tubes.ikb.mytubesbaru.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="mynote.db";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="tb_note";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_DATE="date";
    public static final String COLUMN_NOTE="note";


    public DataHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COLUMN_TITLE + " TEXT NOT NULL, " +
                COLUMN_DATE + " TEXT NOT NULL, " +
                COLUMN_NOTE + " TEXT NOT NULL " +
                " )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_V, int new_V) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<HashMap<String, Object>> GetAllData(){
        ArrayList<HashMap<String, Object>> NoteData;
        NoteData = new ArrayList<>();
        String SELECT_QUERY="SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_QUERY, null);

        if (cursor.moveToFirst()){
            do {
                HashMap<String, Object> Map= new HashMap<>();
                Map.put(COLUMN_ID, cursor.getInt(0));
                Map.put(COLUMN_TITLE, cursor.getString(1));
                Map.put(COLUMN_DATE, cursor.getString(2));
                Map.put(COLUMN_NOTE, cursor.getString(3));
                NoteData.add(Map);
            }while (cursor.moveToNext());

        }

        Log.e("SELECT TABLE DATA ", "" + NoteData);
        cursor.close();
        db.close();
        return NoteData;
    }

    public void insert(String title, String note){
        SQLiteDatabase database = this.getWritableDatabase();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String insertQuery = "INSERT INTO " + TABLE_NAME + " (title, date, note)" + " VALUES ('" + title + "','" + date + "','" +note+"')";

        Log.e("INSERT TABLE DATA", "" + insertQuery);
        database.execSQL(insertQuery);
        database.close();
    }

    public void update(int id, String title, String note){
        SQLiteDatabase database = this.getWritableDatabase();
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String updateQuery = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_TITLE + " = '" + title + "', " +
                COLUMN_DATE + " = '" + date + "', " +
                COLUMN_NOTE + " = '" + note + "'" +
                " WHERE " + COLUMN_ID + " = '" + id + "'";

        Log.e("UPDATE TABLE DATA", "" + updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete (int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE "+ COLUMN_ID + " = '" + id + "'";

        Log.e("DELETE TABLE DATA", "" +deleteQuery);
        database.execSQL(deleteQuery);
        database.close();
    }
}
