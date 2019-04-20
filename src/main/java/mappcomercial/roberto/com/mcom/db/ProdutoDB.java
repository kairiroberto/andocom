package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Produto;

import java.util.ArrayList;
import java.util.List;

import static mappcomercial.roberto.com.mcom.db.CategoriaDB.TABLE_categoria;

public class ProdutoDB {

    // Produto - Table name
    public static final String TABLE_produto = "produto";
    ControleDB controleDB;

    // Produto - Column names
    private static final String FIELD_produto_idproduto = "idproduto";
    private static final String FIELD_produto_nome = "nome";
    private static final String FIELD_produto_categoria = "categoria";
    private static final String FIELD_produto_unidade = "unidade";
    private static final String FIELD_produto_valor_venda = "valor_venda";
    private static final String FIELD_produto_estoque = "estoque";

    // Produto - Table create statement
    public static final String CREATE_TABLE_produto = "CREATE TABLE " + TABLE_produto +
            "(" +
            FIELD_produto_idproduto + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_produto_nome + " TEXT(100), " +
            FIELD_produto_categoria + " INTEGER(10), " +
            FIELD_produto_unidade + " TEXT(50), " +
            FIELD_produto_valor_venda + " REAL(10,0), " +
            FIELD_produto_estoque + " REAL(10,0) " +
            ")";

    public ProdutoDB(Context context) {
        controleDB = new ControleDB(context);
    }

    public void save(Produto mProduto) {
        ContentValues values = new ContentValues();
        values.put(FIELD_produto_nome, mProduto.getNome());
        values.put(FIELD_produto_categoria, mProduto.getCategoria());
        values.put(FIELD_produto_unidade, mProduto.getUnidade());
        values.put(FIELD_produto_valor_venda, mProduto.getValorVenda());
        values.put(FIELD_produto_estoque, mProduto.getEstoque());
        controleDB.save(values, mProduto.getIdproduto(), TABLE_produto, FIELD_produto_idproduto);
    }

    public void delete(long id) {
        controleDB.delete(id, TABLE_produto, FIELD_produto_idproduto);
    }

    public Produto getOne(long id) {
        Produto mProduto = null;
        Cursor cur = controleDB.getOne(id, TABLE_produto, FIELD_produto_idproduto);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mProduto = new Produto();
                mProduto.setIdproduto(cur.getInt(cur.getColumnIndex(FIELD_produto_idproduto)));
                mProduto.setNome(cur.getString(cur.getColumnIndex(FIELD_produto_nome)));
                mProduto.setCategoria(cur.getInt(cur.getColumnIndex(FIELD_produto_categoria)));
                mProduto.setUnidade(cur.getString(cur.getColumnIndex(FIELD_produto_unidade)));
                mProduto.setValorVenda(cur.getDouble(cur.getColumnIndex(FIELD_produto_valor_venda)));
                mProduto.setEstoque(cur.getDouble(cur.getColumnIndex(FIELD_produto_estoque)));
            }
            cur.close();
        }
        return mProduto;
    }

    public List<Produto> getAll() {
        List<Produto> list = new ArrayList<>();
        Cursor cur = controleDB.getAll(TABLE_produto);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Produto mProduto = new Produto();
                    mProduto.setIdproduto(cur.getInt(cur.getColumnIndex(FIELD_produto_idproduto)));
                    mProduto.setNome(cur.getString(cur.getColumnIndex(FIELD_produto_nome)));
                    mProduto.setCategoria(cur.getInt(cur.getColumnIndex(FIELD_produto_categoria)));
                    mProduto.setUnidade(cur.getString(cur.getColumnIndex(FIELD_produto_unidade)));
                    mProduto.setValorVenda(cur.getDouble(cur.getColumnIndex(FIELD_produto_valor_venda)));
                    mProduto.setEstoque(cur.getDouble(cur.getColumnIndex(FIELD_produto_estoque)));
                    list.add(mProduto); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public List<Produto> getJoker(String value) {
        List<Produto> list = new ArrayList<>();
        Cursor cur = controleDB.getJoker(TABLE_produto, FIELD_produto_nome, value);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Produto mProduto = new Produto();
                    mProduto.setIdproduto(cur.getInt(cur.getColumnIndex(FIELD_produto_idproduto)));
                    mProduto.setNome(cur.getString(cur.getColumnIndex(FIELD_produto_nome)));
                    mProduto.setCategoria(cur.getInt(cur.getColumnIndex(FIELD_produto_categoria)));
                    mProduto.setUnidade(cur.getString(cur.getColumnIndex(FIELD_produto_unidade)));
                    mProduto.setValorVenda(cur.getDouble(cur.getColumnIndex(FIELD_produto_valor_venda)));
                    mProduto.setEstoque(cur.getDouble(cur.getColumnIndex(FIELD_produto_estoque)));
                    list.add(mProduto); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

}
