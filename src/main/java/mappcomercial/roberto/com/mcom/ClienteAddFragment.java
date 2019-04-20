package mappcomercial.roberto.com.mcom;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import mappcomercial.roberto.com.mcom.model.Cliente;
import mappcomercial.roberto.com.mcom.db.ClienteDB;


public class ClienteAddFragment extends Fragment implements View.OnClickListener {

    EditText etNomeClienteAdd, etCpfClienteAdd, etRgClienteAdd, etDataNascimentoClienteAdd, etTelefoneClienteAdd, etEmailClienteAdd,
            etRuaClienteAdd, etNumeroClienteAdd, etBairroClienteAdd, etCidadeClienteAdd;
    Button bSalvarAddCliente, bCancelarAddCliente;
    Cliente instancia;
    ClienteDB db;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente_add, container, false);
        bSalvarAddCliente = (Button) view.findViewById(R.id.bSalvarAddCliente);
        bSalvarAddCliente.setOnClickListener(this);
        bCancelarAddCliente = (Button) view.findViewById(R.id.bCancelarAddCliente);
        bCancelarAddCliente.setOnClickListener(this);
        etNomeClienteAdd = (EditText) view.findViewById(R.id.etNomeClienteAdd);
        etCpfClienteAdd = (EditText) view.findViewById(R.id.etCpfClienteAdd);
        etRgClienteAdd = (EditText) view.findViewById(R.id.etRgClienteAdd);
        etDataNascimentoClienteAdd = (EditText) view.findViewById(R.id.etDataNascimentoClienteAdd);
        etTelefoneClienteAdd = (EditText) view.findViewById(R.id.etTelefoneClienteAdd);
        etEmailClienteAdd = (EditText) view.findViewById(R.id.etEmailClienteAdd);
        etRuaClienteAdd = (EditText) view.findViewById(R.id.etRuaClienteAdd);
        etNumeroClienteAdd = (EditText) view.findViewById(R.id.etNumeroClienteAdd);
        etBairroClienteAdd = (EditText) view.findViewById(R.id.etBairroClienteAdd);
        etCidadeClienteAdd = (EditText) view.findViewById(R.id.etCidadeClienteAdd);
        isNew();
        context = view.getContext();
        db = new ClienteDB(context);
        return view;
    }

    private void isNew() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            instancia = (Cliente) bundle.getParcelable("cliente");
            if (instancia != null) {
                etNomeClienteAdd.setText(instancia.getNome());//1
                etCpfClienteAdd.setText(instancia.getCpf());//2
                etRgClienteAdd.setText(instancia.getRg());//3
                etDataNascimentoClienteAdd.setText(instancia.getDataNascimento());//4
                etTelefoneClienteAdd.setText(instancia.getTelefone());//5
                etEmailClienteAdd.setText(instancia.getEmail());//6
                etRuaClienteAdd.setText(instancia.getLogradouro());//7
                etNumeroClienteAdd.setText(instancia.getNumero());//8
                etBairroClienteAdd.setText(instancia.getBairro());//9
                etCidadeClienteAdd.setText(instancia.getCidade());//10
            }
        } else {
            instancia = new Cliente();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bSalvarAddCliente) {
            //saveStatic();
            saveStorage();
        } else if (id == R.id.bCancelarAddCliente) {

        }
        ClienteFragment fragment = new ClienteFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_right);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

    private void saveStorage() {
        instancia.setNome(etNomeClienteAdd.getText().toString()); //1
        instancia.setCpf(etCpfClienteAdd.getText().toString()); //2
        instancia.setRg(etRgClienteAdd.getText().toString()); //3
        instancia.setDataNascimento(etDataNascimentoClienteAdd.getText().toString()); //4
        instancia.setTelefone(etTelefoneClienteAdd.getText().toString()); //5
        instancia.setEmail(etEmailClienteAdd.getText().toString()); //6
        instancia.setLogradouro(etRuaClienteAdd.getText().toString()); //7
        instancia.setNumero(etNumeroClienteAdd.getText().toString()); //8
        instancia.setBairro(etBairroClienteAdd.getText().toString()); //9
        instancia.setCidade(etCidadeClienteAdd.getText().toString()); //10
        db.save(instancia);
    }

    private void saveStatic() {
        Cliente c = new Cliente();
        c.setNome(etNomeClienteAdd.getText().toString()); //1
        c.setCpf(etCpfClienteAdd.getText().toString()); //2
        c.setRg(etRgClienteAdd.getText().toString()); //3
        c.setDataNascimento(etDataNascimentoClienteAdd.getText().toString()); //4
        c.setTelefone(etTelefoneClienteAdd.getText().toString()); //5
        c.setEmail(etEmailClienteAdd.getText().toString()); //6
        c.setLogradouro(etRuaClienteAdd.getText().toString()); //7
        c.setNumero(etNumeroClienteAdd.getText().toString()); //8
        c.setBairro(etBairroClienteAdd.getText().toString()); //9
        c.setCidade(etCidadeClienteAdd.getText().toString()); //10
        if (instancia == null) {
            c.setId((ClienteFragment.clientes.size() + 1));
            ClienteFragment.clientes.add(c);
        } else {
            c.setId(instancia.getId());
            ClienteFragment.clientes.set(ClienteFragment.clientes.indexOf(instancia), c);
        }
    }

}
