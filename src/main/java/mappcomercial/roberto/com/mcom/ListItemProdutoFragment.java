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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class ListItemProdutoFragment extends Fragment {

    Button bSelecionarProduto;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_item_produto, container, false);
        bSelecionarProduto = (Button) view.findViewById(R.id.bSelecionarProduto);
        listView = (ListView) view.findViewById(R.id.lvSelecionarProduto);
        List<String> list = new ArrayList<String>();
        list.add("um");
        list.add("dois");
        list.add("tres");
        list.add("quatro");
        list.add("um");
        list.add("dois");
        list.add("tres");
        list.add("quatro");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                view.getContext(),
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.parseColor("#229988"));
            }
        });
        bSelecionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CompraAddFragment fragment = new CompraAddFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_from_right);
                transaction.replace(R.id.flcontainer, fragment).commit();
            }
        });
        return view;
    }

}
