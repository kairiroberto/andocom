package mappcomercial.roberto.com.mcom;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import mappcomercial.roberto.com.mcom.db.CategoriaDB;
import mappcomercial.roberto.com.mcom.db.ClienteDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Cliente;


public class CategoriaAddFragment extends Fragment implements View.OnClickListener {

    EditText etNomeCategoriaAdd;
    Button bSalvarAddCategoria, bCancelarAddCategoria;
    Categoria instancia;
    CategoriaDB db;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categoria_add, container, false);
        bSalvarAddCategoria = (Button) view.findViewById(R.id.bSalvarAddCategoria);
        bSalvarAddCategoria.setOnClickListener(this);
        bCancelarAddCategoria = (Button) view.findViewById(R.id.bCancelarAddCategoria);
        bCancelarAddCategoria.setOnClickListener(this);
        etNomeCategoriaAdd = (EditText) view.findViewById(R.id.etNomeCategoriaAdd);
        isNew();
        context = view.getContext();
        db = new CategoriaDB(context);
        return view;
    }

    private void isNew() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            instancia = (Categoria) bundle.getParcelable("categoria");
            if (instancia != null) {
                etNomeCategoriaAdd.setText(instancia.getNome());//1
            }
        } else {
            instancia = new Categoria();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bSalvarAddCategoria) {
            //saveStatic();
            saveStorage();
        } else if (id == R.id.bCancelarAddCategoria) {

        }
        CategoriaFragment fragment = new CategoriaFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_right);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

    private void saveStorage() {
        instancia.setNome(etNomeCategoriaAdd.getText().toString()); //1
        db.save(instancia);
    }

    private void saveStatic() {
        Categoria c = new Categoria();
        c.setNome(etNomeCategoriaAdd.getText().toString()); //1
        if (instancia == null) {
            c.setIdcategoria((CategoriaFragment.categorias.size() + 1));
            CategoriaFragment.categorias.add(c);
        } else {
            c.setIdcategoria(instancia.getIdcategoria());
            CategoriaFragment.categorias.set(CategoriaFragment.categorias.indexOf(instancia), c);
        }
    }

}
