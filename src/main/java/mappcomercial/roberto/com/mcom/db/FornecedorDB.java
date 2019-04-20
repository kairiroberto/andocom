package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mappcomercial.roberto.com.mcom.model.Cliente;
import mappcomercial.roberto.com.mcom.model.Fornecedor;

import java.util.ArrayList;
import java.util.List;

import static mappcomercial.roberto.com.mcom.db.ClienteDB.TABLE_cliente;

public class FornecedorDB {

    // Fornecedor - Table name
    public static final String TABLE_fornecedor = "fornecedor";
    ControleDB controleDB;

    // Fornecedor - Column names
    private static final String FIELD_fornecedor_idfornecedor = "idfornecedor";
    private static final String FIELD_fornecedor_nome = "nome";

    // Fornecedor - Table create statement
    public static final String CREATE_TABLE_fornecedor = "CREATE TABLE " + TABLE_fornecedor +
            "(" +
            FIELD_fornecedor_idfornecedor + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_fornecedor_nome + " TEXT(100) " +
            ")";

    public FornecedorDB(Context context) {
        controleDB = new ControleDB(context);
    }

    public void save(Fornecedor mFornecedor) {
        ContentValues values = new ContentValues();
        values.put(FIELD_fornecedor_nome, mFornecedor.getNome());
        controleDB.save(values, mFornecedor.getIdfornecedor(), TABLE_fornecedor, FIELD_fornecedor_idfornecedor);
    }

    public void delete(long id) {
        controleDB.delete(id, TABLE_fornecedor, FIELD_fornecedor_idfornecedor);
    }

    public Fornecedor getOne(long id) {
        Fornecedor mFornecedor = null;
        Cursor cur = controleDB.getOne(id, TABLE_fornecedor, FIELD_fornecedor_idfornecedor);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mFornecedor = new Fornecedor();
                mFornecedor.setIdfornecedor(cur.getInt(cur.getColumnIndex(FIELD_fornecedor_idfornecedor)));
                mFornecedor.setNome(cur.getString(cur.getColumnIndex(FIELD_fornecedor_nome)));
            }
            cur.close();
        }
        return mFornecedor;
    }

    public List<Fornecedor> getAll() {
        List<Fornecedor> list = new ArrayList<>();
        Cursor cur = controleDB.getAll(TABLE_fornecedor);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Fornecedor mFornecedor = new Fornecedor();
                    mFornecedor.setIdfornecedor(cur.getInt(cur.getColumnIndex(FIELD_fornecedor_idfornecedor)));
                    mFornecedor.setNome(cur.getString(cur.getColumnIndex(FIELD_fornecedor_nome)));;
                    list.add(mFornecedor); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public List<Fornecedor> getJoker(String value) {
        List<Fornecedor> list = new ArrayList<>();
        Cursor cur = controleDB.getJoker(TABLE_fornecedor, FIELD_fornecedor_nome, value);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Fornecedor mFornecedor = new Fornecedor();
                    mFornecedor.setIdfornecedor(cur.getInt(cur.getColumnIndex(FIELD_fornecedor_idfornecedor)));
                    mFornecedor.setNome(cur.getString(cur.getColumnIndex(FIELD_fornecedor_nome)));
                    list.add(mFornecedor); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

}
