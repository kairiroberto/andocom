package mappcomercial.roberto.com.mcom;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import mappcomercial.roberto.com.mcom.model.Cliente;
import mappcomercial.roberto.com.mcom.db.ClienteDB;

import java.util.ArrayList;
import java.util.List;


public class ClienteFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    private Button bFindCliente, bAddCliente;
    private List<Cliente> list;
    public static List<Cliente> clientes = new ArrayList<Cliente>();
    private ArrayAdapter<Cliente> adapter;
    private ClienteDB db;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cliente, container, false);
        db = new ClienteDB(view.getContext());
        editText = (EditText) view.findViewById(R.id.etFindCliente);
        bFindCliente = (Button) view.findViewById(R.id.bFindCliente);
        bFindCliente.setOnClickListener(this);
        bAddCliente = (Button) view.findViewById(R.id.bAddCliente);
        bAddCliente.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.lvClientes);
        getList();
        context = view.getContext();
        adapter = new ArrayAdapter<Cliente>(context,
                android.R.layout.simple_list_item_1,
                list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position,convertView,parent);
                if (position % 2 == 0) {
                    view.setBackgroundColor(Color.parseColor("#F2F4F4"));
                }
                return view;
            }
        };
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    private List<Cliente> getList() {
        //list = clientes;
        list = db.getAll();
        return list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bFindCliente) {
            list = db.getJoker(editText.getText().toString());
            //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            adapter = new ArrayAdapter<Cliente>(context,
                    android.R.layout.simple_list_item_1,
                    list){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position,convertView,parent);
                    if (position % 2 == 0) {
                        view.setBackgroundColor(Color.parseColor("#F2F4F4"));
                    }
                    return view;
                }
            };
            listView.setAdapter(adapter);
        } else if (id == R.id.bAddCliente) {
            ClienteAddFragment fragment = new ClienteAddFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
            transaction.replace(R.id.flcontainer, fragment).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Cliente cliente = new Cliente();
        //cliente = ClienteFragment.clientes.get(position);
        Cliente c = (Cliente) listView.getItemAtPosition(position);
        cliente = db.getOne(c.getId());
        ClienteAddFragment fragment = new ClienteAddFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("cliente", cliente);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

}
