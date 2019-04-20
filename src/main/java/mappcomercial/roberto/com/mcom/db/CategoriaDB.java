package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import mappcomercial.roberto.com.mcom.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaDB {

    // Categoria - Table name
    public static final String TABLE_categoria = "categoria";
    ControleDB controleDB;

    // Categoria - Column names
    private static final String FIELD_categoria_idcategoria = "idcategoria";
    private static final String FIELD_categoria_nome = "nome";

    // Categoria - Table create statement
    public static final String CREATE_TABLE_categoria = "CREATE TABLE " + TABLE_categoria +
            "(" +
            FIELD_categoria_idcategoria + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_categoria_nome + " TEXT(100) " +
            ")";

    public CategoriaDB(Context context) {
        controleDB = new ControleDB(context);
    }

    public void save(Categoria mCategoria) {
        ContentValues values = new ContentValues();
        values.put(FIELD_categoria_nome, mCategoria.getNome());
        controleDB.save(values, mCategoria.getIdcategoria(), TABLE_categoria, FIELD_categoria_idcategoria);
    }

    public void delete(long id) {
        controleDB.delete(id, TABLE_categoria, FIELD_categoria_idcategoria);
    }

    public Categoria getOne(long id) {
        Categoria mCategoria = null;
        Cursor cur = controleDB.getOne(id, TABLE_categoria, FIELD_categoria_idcategoria);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mCategoria = new Categoria();
                mCategoria.setIdcategoria(cur.getInt(cur.getColumnIndex(FIELD_categoria_idcategoria)));
                mCategoria.setNome(cur.getString(cur.getColumnIndex(FIELD_categoria_nome)));
            }
            cur.close();
        }
        return mCategoria;
    }

    public List<Categoria> getAll() {
        List<Categoria> list = new ArrayList<>();
        Cursor cur = controleDB.getAll(TABLE_categoria);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Categoria mCategoria = new Categoria();
                    mCategoria.setIdcategoria(cur.getInt(cur.getColumnIndex(FIELD_categoria_idcategoria)));
                    mCategoria.setNome(cur.getString(cur.getColumnIndex(FIELD_categoria_nome)));
                    list.add(mCategoria); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public List<Categoria> getJoker(String value) {
        List<Categoria> list = new ArrayList<>();
        Cursor cur = controleDB.getJoker(TABLE_categoria, FIELD_categoria_nome, value);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Categoria mCategoria = new Categoria();
                    mCategoria.setIdcategoria(cur.getInt(cur.getColumnIndex(FIELD_categoria_idcategoria)));
                    mCategoria.setNome(cur.getString(cur.getColumnIndex(FIELD_categoria_nome)));
                    list.add(mCategoria); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

}
