package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraDB {

    // Compra - Table name
    public static final String TABLE_compra = "compra";
    ControleDB controleDB;

    // Compra - Column names
    private static final String FIELD_compra_idcompra = "idcompra";
    private static final String FIELD_compra_nota = "nota";
    private static final String FIELD_compra_data = "data";
    private static final String FIELD_compra_fornecedor = "fornecedor";

    // Compra - Table create statement
    public static final String CREATE_TABLE_compra = "CREATE TABLE " + TABLE_compra +
            "(" +
            FIELD_compra_idcompra + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_compra_nota + " TEXT(50), " +
            FIELD_compra_data + " TEXT(10), " +
            FIELD_compra_fornecedor + " INTEGER(10) " +
            ")";

    public CompraDB(Context context) {
        controleDB = new ControleDB(context);
    }

    public void save(Compra mCompra) {
        ContentValues values = new ContentValues();
        values.put(FIELD_compra_nota, mCompra.getNota());
        values.put(FIELD_compra_data, mCompra.getData());
        values.put(FIELD_compra_fornecedor, mCompra.getFornecedor());
        controleDB.save(values, mCompra.getIdcompra(), TABLE_compra, FIELD_compra_idcompra);
    }

    public void delete(long id) {
        controleDB.delete(id, TABLE_compra, FIELD_compra_idcompra);
    }

    public Compra getOne(long id) {
        Compra mCompra = null;
        Cursor cur = controleDB.getOne(id, TABLE_compra, FIELD_compra_idcompra);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mCompra = new Compra();
                mCompra.setIdcompra(cur.getInt(cur.getColumnIndex(FIELD_compra_idcompra)));
                mCompra.setNota(cur.getString(cur.getColumnIndex(FIELD_compra_nota)));
                mCompra.setData(cur.getString(cur.getColumnIndex(FIELD_compra_data)));
                mCompra.setFornecedor(cur.getString(cur.getColumnIndex(FIELD_compra_fornecedor)));
            }
            cur.close();
        }
        return mCompra;
    }

    public List<Compra> getAll() {
        List<Compra> list = new ArrayList<>();
        Cursor cur = controleDB.getAll(TABLE_compra);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Compra mCompra = new Compra();
                    mCompra.setIdcompra(cur.getInt(cur.getColumnIndex(FIELD_compra_idcompra)));
                    mCompra.setNota(cur.getString(cur.getColumnIndex(FIELD_compra_nota)));
                    mCompra.setData(cur.getString(cur.getColumnIndex(FIELD_compra_data)));
                    mCompra.setFornecedor(cur.getString(cur.getColumnIndex(FIELD_compra_fornecedor)));
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public List<Compra> getJoker(String value) {
        List<Compra> list = new ArrayList<>();
        Cursor cur = controleDB.getJoker(TABLE_compra, FIELD_compra_data, value);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Compra mCompra = new Compra();
                    mCompra.setIdcompra(cur.getInt(cur.getColumnIndex(FIELD_compra_idcompra)));
                    mCompra.setNota(cur.getString(cur.getColumnIndex(FIELD_compra_nota)));
                    mCompra.setData(cur.getString(cur.getColumnIndex(FIELD_compra_data)));
                    mCompra.setFornecedor(cur.getString(cur.getColumnIndex(FIELD_compra_fornecedor)));
                    list.add(mCompra); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

}
