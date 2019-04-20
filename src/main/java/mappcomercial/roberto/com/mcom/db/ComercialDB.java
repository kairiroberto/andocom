package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import mappcomercial.roberto.com.mcom.model.Cliente;

public class ComercialDB extends SQLiteOpenHelper {

    Context context;

    //Log file
    protected static final String LOG = "bd_venda.log";

    //Database
    protected static final String DATABASE_NAME = "bd_venda.db";
    protected static final int DATABASE_VERSION = 1;

    public ComercialDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Log.i(LOG, "Executou ComercialDB");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ClienteDB.CREATE_TABLE_cliente);
        db.execSQL(CategoriaDB.CREATE_TABLE_categoria);
        db.execSQL(FornecedorDB.CREATE_TABLE_fornecedor);
        db.execSQL(ProdutoDB.CREATE_TABLE_produto);
        db.execSQL(CompraDB.CREATE_TABLE_compra);
        Log.i(LOG, "Executou onCreate ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen()) db.close();
    }

}
