package mappcomercial.roberto.com.mcom;

import android.content.Context;
import android.graphics.Color;
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
import android.widget.ListView;

import mappcomercial.roberto.com.mcom.db.CategoriaDB;
import mappcomercial.roberto.com.mcom.db.FornecedorDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Fornecedor;

import java.util.ArrayList;
import java.util.List;


public class FornecedorFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    private Button bFindFornecedor, bAddFornecedor;
    private List<Fornecedor> list;
    public static List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
    private ArrayAdapter<Fornecedor> adapter;
    private FornecedorDB db;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fornecedor, container, false);
        db = new FornecedorDB(view.getContext());
        editText = (EditText) view.findViewById(R.id.etFindFornecedor);
        bFindFornecedor = (Button) view.findViewById(R.id.bFindFornecedor);
        bFindFornecedor.setOnClickListener(this);
        bAddFornecedor = (Button) view.findViewById(R.id.bAddFornecedor);
        bAddFornecedor.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.lvFornecedor);
        getList();
        context = view.getContext();
        adapter = new ArrayAdapter<Fornecedor>(context,
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

    private List<Fornecedor> getList() {
        //list = fornecedor;
        list = db.getAll();
        return list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bFindFornecedor) {
            list = db.getJoker(editText.getText().toString());
            //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            adapter = new ArrayAdapter<Fornecedor>(context,
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
        } else if (id == R.id.bAddFornecedor) {
            FornecedorAddFragment fragment = new FornecedorAddFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
            transaction.replace(R.id.flcontainer, fragment).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fornecedor fornecedor = new Fornecedor();
        //fornecedor = FornecedorFragment.fornecedores.get(position);
        Fornecedor c = (Fornecedor) listView.getItemAtPosition(position);
        fornecedor = db.getOne(c.getIdfornecedor());
        FornecedorAddFragment fragment = new FornecedorAddFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("fornecedor", fornecedor);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

}
