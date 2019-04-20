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
import android.widget.TextView;

import mappcomercial.roberto.com.mcom.db.CategoriaDB;
import mappcomercial.roberto.com.mcom.db.CompraDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Compra;

import java.util.ArrayList;
import java.util.List;

public class CompraFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    private Button bFindCompra, bAddCompra;
    private List<Compra> list;
    public static List<Compra> compras = new ArrayList<Compra>();
    private ArrayAdapter<Compra> adapter;
    private CompraDB db;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_compra, container, false);
        db = new CompraDB(view.getContext());
        editText = (EditText) view.findViewById(R.id.etFindCompra);
        bFindCompra = (Button) view.findViewById(R.id.bFindCompra);
        bFindCompra.setOnClickListener(this);
        bAddCompra = (Button) view.findViewById(R.id.bAddCompra);
        bAddCompra.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.lvCompra);
        getList();
        context = view.getContext();
        adapter = new ArrayAdapter<Compra>(context,
                android.R.layout.simple_list_item_1,
                list) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
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

    private List<Compra> getList() {
        //list = compras;
        list = db.getAll();
        return list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bFindCompra) {
            list = db.getJoker(editText.getText().toString());
            //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            adapter = new ArrayAdapter<Compra>(context,
                    android.R.layout.simple_list_item_1,
                    list){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    if (position % 2 == 0) {
                        view.setBackgroundColor(Color.parseColor("#F2F4F4"));
                    }
                    return view;
                }
            };
            listView.setAdapter(adapter);
        } else if (id == R.id.bAddCompra) {
            CompraAddFragment fragment = new CompraAddFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
            transaction.replace(R.id.flcontainer, fragment).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
