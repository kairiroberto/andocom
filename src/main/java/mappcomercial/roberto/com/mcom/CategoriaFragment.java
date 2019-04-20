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
import mappcomercial.roberto.com.mcom.db.ClienteDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Cliente;

import java.util.ArrayList;
import java.util.List;


public class CategoriaFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    private Button bFindCategoria, bAddCategoria;
    private List<Categoria> list;
    public static List<Categoria> categorias = new ArrayList<Categoria>();
    private ArrayAdapter<Categoria> adapter;
    private CategoriaDB db;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categoria, container, false);
        db = new CategoriaDB(view.getContext());
        editText = (EditText) view.findViewById(R.id.etFindCategoria);
        bFindCategoria = (Button) view.findViewById(R.id.bFindCategoria);
        bFindCategoria.setOnClickListener(this);
        bAddCategoria = (Button) view.findViewById(R.id.bAddCategoria);
        bAddCategoria.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.lvCategoria);
        getList();
        context = view.getContext();
        adapter = new ArrayAdapter<Categoria>(context,
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

    private List<Categoria> getList() {
        //list = categorias;
        list = db.getAll();
        return list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bFindCategoria) {
            list = db.getJoker(editText.getText().toString());
            //((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
            adapter = new ArrayAdapter<Categoria>(context,
                    android.R.layout.simple_list_item_1,
                    list) {
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
        } else if (id == R.id.bAddCategoria) {
            CategoriaAddFragment fragment = new CategoriaAddFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
            transaction.replace(R.id.flcontainer, fragment).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categoria categoria = new Categoria();
        //categoria = CategoriaFragment.categorias.get(position);
        Categoria c = (Categoria) listView.getItemAtPosition(position);
        categoria = db.getOne(c.getIdcategoria());
        CategoriaAddFragment fragment = new CategoriaAddFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("categoria", categoria);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }
}
