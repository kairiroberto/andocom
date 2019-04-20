package mappcomercial.roberto.com.mcom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mappcomercial.roberto.com.mcom.db.CategoriaDB;
import mappcomercial.roberto.com.mcom.db.FornecedorDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Fornecedor;


public class FornecedorAddFragment extends Fragment implements View.OnClickListener {

    EditText etNomeFornecedorAdd;
    Button bSalvarAddFornecedor, bCancelarAddFornecedor;
    Fornecedor instancia;
    FornecedorDB db;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fornecedor_add, container, false);
        bSalvarAddFornecedor = (Button) view.findViewById(R.id.bSalvarAddFornecedor);
        bSalvarAddFornecedor.setOnClickListener(this);
        bCancelarAddFornecedor = (Button) view.findViewById(R.id.bCancelarAddFornecedor);
        bCancelarAddFornecedor.setOnClickListener(this);
        etNomeFornecedorAdd = (EditText) view.findViewById(R.id.etNomeFornecedorAdd);
        isNew();
        context = view.getContext();
        db = new FornecedorDB(context);
        return view;
    }

    private void isNew() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            instancia = (Fornecedor) bundle.getParcelable("fornecedor");
            if (instancia != null) {
                etNomeFornecedorAdd.setText(instancia.getNome());//1
            }
        } else {
            instancia = new Fornecedor();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bSalvarAddFornecedor) {
            //saveStatic();
            saveStorage();
        } else if (id == R.id.bCancelarAddFornecedor) {

        }
        FornecedorFragment fragment = new FornecedorFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_right);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

    private void saveStorage() {
        instancia.setNome(etNomeFornecedorAdd.getText().toString()); //1
        db.save(instancia);
    }

    private void saveStatic() {
        Fornecedor c = new Fornecedor();
        c.setNome(etNomeFornecedorAdd.getText().toString()); //1
        if (instancia == null) {
            c.setIdfornecedor((FornecedorFragment.fornecedores.size() + 1));
            FornecedorFragment.fornecedores.add(c);
        } else {
            c.setIdfornecedor(instancia.getIdfornecedor());
            FornecedorFragment.fornecedores.set(FornecedorFragment.fornecedores.indexOf(instancia), c);
        }
    }

}
