package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import mappcomercial.roberto.com.mcom.model.Cliente;

public class ControleDB {

    SQLiteDatabase db;
    ComercialDB comercialDB;

    public ControleDB(Context context) {
        comercialDB = new ComercialDB(context);
    }

    public long save(ContentValues values, int idobj, String TABLE, String FIELD_id) {
        db = comercialDB.getWritableDatabase();
        long id = 0;
        if (idobj > 0) {
            // updating row
            db.update(TABLE, values, FIELD_id + "=?", new String[] {String.valueOf(idobj)});
            id = idobj;
        } else {
            // inserting row
            id = db.insert(TABLE, null, values);
        }
        return id;
    }

    /*
     *  delete a cliente record by ID
     */
    public void delete(long id, String TABLE, String FIELD_id) {
        if (id > 0) {
            db = comercialDB.getWritableDatabase();
            // deleting row
            db.delete(TABLE, FIELD_id + "=?", new String[] {String.valueOf(id)});
        }
    }

    /*
     *  getting a single Cliente object by ID
     */
    public Cursor getOne(long id, String TABLE, String FIELD_id) {
        Cliente mCliente = null;
        db = comercialDB.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE + " WHERE " + FIELD_id + "=" + id;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            return cur;
        } else {
            return null;
        }
    }

    /*
     *  getting all Cliente objects
     */
    public Cursor getAll(String TABLE) {
        db = comercialDB.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            return cur;
        } else {
            return null;
        }
    }

    public Cursor getJoker(String TABLE, String FIELD, String value) {
        db = comercialDB.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE + " WHERE " + FIELD + " LIKE " + "'%" + value + "%'";
        Cursor cur = db.rawQuery(selectQuery, null);
        Log.i("SQL", selectQuery + cur.getCount());
        if (cur != null) {
            return cur;
        } else {
            return null;
        }
    }

}
