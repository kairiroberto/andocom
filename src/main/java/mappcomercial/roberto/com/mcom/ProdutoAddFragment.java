package mappcomercial.roberto.com.mcom;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mappcomercial.roberto.com.mcom.db.CategoriaDB;
import mappcomercial.roberto.com.mcom.db.ProdutoDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Produto;

import java.util.ArrayList;
import java.util.List;


public class ProdutoAddFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private EditText etNomeProdutoAdd, etUnidadeProdutoAdd, etValorVendaProdutoAdd, etEstoqueProdutoAdd;
    private Spinner sCategoriaProdutoAdd;
    private Button bSalvarAddProduto, bCancelarAddProduto;
    private List<Categoria> categorias = new ArrayList<Categoria>();
    private ArrayAdapter<Categoria> adapter;
    private int categoria = 0;
    private Produto instancia;
    private ProdutoDB db;
    private static CategoriaDB categoriaDB;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produto_add, container, false);
        bSalvarAddProduto = (Button) view.findViewById(R.id.bSalvarAddProduto);
        bSalvarAddProduto.setOnClickListener(this);
        bCancelarAddProduto = (Button) view.findViewById(R.id.bCancelarAddProduto);
        bCancelarAddProduto.setOnClickListener(this);
        etNomeProdutoAdd = (EditText) view.findViewById(R.id.etNomeProdutoAdd);
        sCategoriaProdutoAdd = (Spinner) view.findViewById(R.id.sCategoriaProdutoAdd);
        etUnidadeProdutoAdd = (EditText) view.findViewById(R.id.etUnidadeProdutoAdd);
        etValorVendaProdutoAdd = (EditText) view.findViewById(R.id.etValorVendaProdutoAdd);
        etEstoqueProdutoAdd = (EditText) view.findViewById(R.id.etEstoqueProdutoAdd);
        etEstoqueProdutoAdd.setEnabled(false);
        context = view.getContext();
        db = new ProdutoDB(context);
        categoriaDB = new CategoriaDB(context);
        categorias = categoriaDB.getAll();
        adapter = new ArrayAdapter<Categoria>(context,
                android.R.layout.simple_list_item_1,
                categorias);
        sCategoriaProdutoAdd.setAdapter(adapter);
        sCategoriaProdutoAdd.setOnItemSelectedListener(this);
        isNew();
        return view;
    }

    private void isNew() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            instancia = (Produto) bundle.getParcelable("produto");
            if (instancia != null) {
                etNomeProdutoAdd.setText(instancia.getNome());//1
                Categoria cat = categoriaDB.getOne(instancia.getCategoria());
                int index = adapter.getPosition(cat);
                sCategoriaProdutoAdd.setSelection(index);
                etUnidadeProdutoAdd.setText(instancia.getUnidade());
                etValorVendaProdutoAdd.setText(String.valueOf(instancia.getValorVenda()));
                etEstoqueProdutoAdd.setText(String.valueOf(instancia.getEstoque()));
            }
        } else {
            instancia = new Produto();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bSalvarAddProduto) {
            if (etNomeProdutoAdd.getText().toString().isEmpty() || etNomeProdutoAdd.getText().toString().equals("")) {
                Toast.makeText(context, "O campo nome não pode ser vazio", Toast.LENGTH_LONG).show();
            } else {
                //saveStatic();
                saveStorage();
            }
        } else if (id == R.id.bCancelarAddProduto) {

        }
        ProdutoFragment fragment = new ProdutoFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_right);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

    private void saveStorage() {
        instancia.setNome(etNomeProdutoAdd.getText().toString()); //1
        instancia.setCategoria(categoria); //1
        instancia.setUnidade(etUnidadeProdutoAdd.getText().toString()); //1
        try {
            instancia.setValorVenda(Double.parseDouble(etValorVendaProdutoAdd.getText().toString())); //1
            db.save(instancia);
        } catch (Exception e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Registro não foi salvo!");
            builder.setMessage("Verifique o campo 'Valor de Venda'! \nErro: " + e.getMessage());
            AlertDialog alerta = builder.create();
            alerta.show();
        }
    }

    private void saveStatic() {
        Produto c = new Produto();
        c.setNome(etNomeProdutoAdd.getText().toString()); //1
        c.setCategoria(categoria); //1
        c.setUnidade(etUnidadeProdutoAdd.getText().toString()); //1
        c.setValorVenda(Double.parseDouble(etValorVendaProdutoAdd.getText().toString())); //1
        if (instancia == null) {
            c.setIdproduto((ProdutoFragment.produtos.size() + 1));
            ProdutoFragment.produtos.add(c);
        } else {
            c.setIdproduto(instancia.getIdproduto());
            ProdutoFragment.produtos.set(ProdutoFragment.produtos.indexOf(instancia), c);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        categoria = categorias.get(position).getIdcategoria();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
