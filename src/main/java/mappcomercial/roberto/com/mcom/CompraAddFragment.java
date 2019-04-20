package mappcomercial.roberto.com.mcom;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CompraAddFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button button;
    Context context;
    int inicio = 0;
    List<String> list = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_compra_add, container, false);
        context = view.getContext();
        spinner = (Spinner) view.findViewById(R.id.sItensAddCompra);
        list.add("um - 3x R$ 1000,00 - R$ 3000,00");
        list.add("dois - 3x R$ 1000,00 - R$ 3000,00");
        list.add("tres - 3x R$ 1000,00 - R$ 3000,00");
        list.add("quatro - 3x R$ 1000,00 - R$ 3000,00");
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
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        button = (Button) view.findViewById(R.id.bProdutoAddCompra);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListItemProdutoFragment fragment = new ListItemProdutoFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                //transaction.setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out, android.R.anim.fade_in,android.R.anim.fade_out);
                transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_from_left);
                transaction.replace(R.id.flcontainer, fragment).commit();
            }
        });
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        final LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        TextView textView = new TextView(context);
        textView.setText("Quantidade: ");
        EditText editText = new EditText(context);
        layout.addView(textView);
        layout.addView(editText);
        if (inicio > 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Alterar: " + list.get(position));
            builder.setView(layout);
            builder.setPositiveButton("Alterar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(context, "positivo=" + arg1, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("Excluir", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(context, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Toast.makeText(context, "negativo=" + arg1, Toast.LENGTH_SHORT).show();
                }
            });
            AlertDialog alerta = builder.create();
            alerta.show();
        } else {
            inicio = 1;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // TODO: Rename method, update argument and hook method into UI event

}
