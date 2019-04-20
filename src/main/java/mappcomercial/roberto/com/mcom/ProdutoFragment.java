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
import mappcomercial.roberto.com.mcom.db.ProdutoDB;
import mappcomercial.roberto.com.mcom.model.Categoria;
import mappcomercial.roberto.com.mcom.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView listView;
    private EditText editText;
    private Button bFindProduto, bAddProduto;
    private List<Produto> list;
    public static List<Produto> produtos = new ArrayList<Produto>();
    private ArrayAdapter<Produto> adapter;
    private ProdutoDB db;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_produto, container, false);
        db = new ProdutoDB(view.getContext());
        editText = (EditText) view.findViewById(R.id.etFindProduto);
        bFindProduto = (Button) view.findViewById(R.id.bFindProduto);
        bFindProduto.setOnClickListener(this);
        bAddProduto = (Button) view.findViewById(R.id.bAddProduto);
        bAddProduto.setOnClickListener(this);
        listView = (ListView) view.findViewById(R.id.lvProduto);
        getList();
        context = view.getContext();
        adapter = new ArrayAdapter<Produto>(context,
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

    private List<Produto> getList() {
        //list = produtos;
        list = db.getAll();
        return list;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bFindProduto) {
            list = db.getJoker(editText.getText().toString());
            adapter = new ArrayAdapter<Produto>(context,
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
        } else if (id == R.id.bAddProduto) {
            ProdutoAddFragment fragment = new ProdutoAddFragment();
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
            transaction.replace(R.id.flcontainer, fragment).commit();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Produto produto = new Produto();
        //produto = ProdutoFragment.produtos.get(position);
        Produto c = (Produto) listView.getItemAtPosition(position);
        produto = db.getOne(c.getIdproduto());
        ProdutoAddFragment fragment = new ProdutoAddFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("produto", produto);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
        transaction.replace(R.id.flcontainer, fragment).commit();
    }

}
