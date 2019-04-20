package mappcomercial.roberto.com.mcom.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import mappcomercial.roberto.com.mcom.model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDB {

    // Cliente - Table name
    public static final String TABLE_cliente = "cliente";
    ControleDB controleDB;

    // Cliente - Column names
    private static final String FIELD_cliente_idcliente = "idcliente";
    private static final String FIELD_cliente_nome = "nome";
    private static final String FIELD_cliente_cpf = "cpf";
    private static final String FIELD_cliente_rg = "rg";
    private static final String FIELD_cliente_data_nascimento = "data_nascimento";
    private static final String FIELD_cliente_telefone = "telefone";
    private static final String FIELD_cliente_email = "email";
    private static final String FIELD_cliente_rua = "rua";
    private static final String FIELD_cliente_numero = "numero";
    private static final String FIELD_cliente_bairro = "bairro";
    private static final String FIELD_cliente_cidade = "cidade";

    // Cliente - Table create statement
    public static final String CREATE_TABLE_cliente = "CREATE TABLE " + TABLE_cliente +
            "(" +
            FIELD_cliente_idcliente + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            FIELD_cliente_nome + " TEXT(200), " +
            FIELD_cliente_cpf + " TEXT(11), " +
            FIELD_cliente_rg + " TEXT(50), " +
            FIELD_cliente_data_nascimento + " TEXT(10), " +
            FIELD_cliente_telefone + " TEXT(50), " +
            FIELD_cliente_email + " TEXT(100), " +
            FIELD_cliente_rua + " TEXT(200), " +
            FIELD_cliente_numero + " TEXT(50), " +
            FIELD_cliente_bairro + " TEXT(100), " +
            FIELD_cliente_cidade + " TEXT(150) " +
            ")";

    public ClienteDB(Context context) {
        controleDB = new ControleDB(context);
    }

    public void save(Cliente mCliente) {
        ContentValues values = new ContentValues();
        //values.put(FIELD_cliente_idcliente, mCliente.getIdcliente());
        values.put(FIELD_cliente_nome, mCliente.getNome());
        values.put(FIELD_cliente_cpf, mCliente.getCpf());
        values.put(FIELD_cliente_rg, mCliente.getRg());
        values.put(FIELD_cliente_data_nascimento, mCliente.getDataNascimento());
        values.put(FIELD_cliente_telefone, mCliente.getTelefone());
        values.put(FIELD_cliente_email, mCliente.getEmail());
        values.put(FIELD_cliente_rua, mCliente.getLogradouro());
        values.put(FIELD_cliente_numero, mCliente.getNumero());
        values.put(FIELD_cliente_bairro, mCliente.getBairro());
        values.put(FIELD_cliente_cidade, mCliente.getCidade());
        controleDB.save(values, mCliente.getId(), TABLE_cliente, FIELD_cliente_idcliente);
    }

    public void delete(long id) {
        controleDB.delete(id, TABLE_cliente, FIELD_cliente_idcliente);
    }

    public Cliente getOne(long id) {
        Cliente mCliente = null;
        Cursor cur = controleDB.getOne(id, TABLE_cliente, FIELD_cliente_idcliente);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mCliente = new Cliente();
                mCliente.setId(cur.getInt(cur.getColumnIndex(FIELD_cliente_idcliente)));
                mCliente.setNome(cur.getString(cur.getColumnIndex(FIELD_cliente_nome)));
                mCliente.setCpf(cur.getString(cur.getColumnIndex(FIELD_cliente_cpf)));
                mCliente.setRg(cur.getString(cur.getColumnIndex(FIELD_cliente_rg)));
                mCliente.setDataNascimento(cur.getString(cur.getColumnIndex(FIELD_cliente_data_nascimento)));
                mCliente.setTelefone(cur.getString(cur.getColumnIndex(FIELD_cliente_telefone)));
                mCliente.setEmail(cur.getString(cur.getColumnIndex(FIELD_cliente_email)));
                mCliente.setLogradouro(cur.getString(cur.getColumnIndex(FIELD_cliente_rua)));
                mCliente.setNumero(cur.getString(cur.getColumnIndex(FIELD_cliente_numero)));
                mCliente.setBairro(cur.getString(cur.getColumnIndex(FIELD_cliente_bairro)));
                mCliente.setCidade(cur.getString(cur.getColumnIndex(FIELD_cliente_cidade)));
            }
            cur.close();
        }
        return mCliente;
    }

    public List<Cliente> getAll() {
        List<Cliente> list = new ArrayList<>();
        Cursor cur = controleDB.getAll(TABLE_cliente);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Cliente mCliente = new Cliente();
                    mCliente.setId(cur.getInt(cur.getColumnIndex(FIELD_cliente_idcliente)));
                    mCliente.setNome(cur.getString(cur.getColumnIndex(FIELD_cliente_nome)));
                    mCliente.setCpf(cur.getString(cur.getColumnIndex(FIELD_cliente_cpf)));
                    mCliente.setRg(cur.getString(cur.getColumnIndex(FIELD_cliente_rg)));
                    mCliente.setDataNascimento(cur.getString(cur.getColumnIndex(FIELD_cliente_data_nascimento)));
                    mCliente.setTelefone(cur.getString(cur.getColumnIndex(FIELD_cliente_telefone)));
                    mCliente.setEmail(cur.getString(cur.getColumnIndex(FIELD_cliente_email)));
                    mCliente.setLogradouro(cur.getString(cur.getColumnIndex(FIELD_cliente_rua)));
                    mCliente.setNumero(cur.getString(cur.getColumnIndex(FIELD_cliente_numero)));
                    mCliente.setBairro(cur.getString(cur.getColumnIndex(FIELD_cliente_bairro)));
                    mCliente.setCidade(cur.getString(cur.getColumnIndex(FIELD_cliente_cidade)));
                    list.add(mCliente); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public List<Cliente> getJoker(String value) {
        List<Cliente> list = new ArrayList<>();
        Cursor cur = controleDB.getJoker(TABLE_cliente, FIELD_cliente_nome, value);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Cliente mCliente = new Cliente();
                    mCliente.setId(cur.getInt(cur.getColumnIndex(FIELD_cliente_idcliente)));
                    mCliente.setNome(cur.getString(cur.getColumnIndex(FIELD_cliente_nome)));
                    mCliente.setCpf(cur.getString(cur.getColumnIndex(FIELD_cliente_cpf)));
                    mCliente.setRg(cur.getString(cur.getColumnIndex(FIELD_cliente_rg)));
                    mCliente.setDataNascimento(cur.getString(cur.getColumnIndex(FIELD_cliente_data_nascimento)));
                    mCliente.setTelefone(cur.getString(cur.getColumnIndex(FIELD_cliente_telefone)));
                    mCliente.setEmail(cur.getString(cur.getColumnIndex(FIELD_cliente_email)));
                    mCliente.setLogradouro(cur.getString(cur.getColumnIndex(FIELD_cliente_rua)));
                    mCliente.setNumero(cur.getString(cur.getColumnIndex(FIELD_cliente_numero)));
                    mCliente.setBairro(cur.getString(cur.getColumnIndex(FIELD_cliente_bairro)));
                    mCliente.setCidade(cur.getString(cur.getColumnIndex(FIELD_cliente_cidade)));
                    list.add(mCliente); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

}
