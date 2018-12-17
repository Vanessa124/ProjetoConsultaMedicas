package sp.senai.br.consultamedicas.adapters;

import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import sp.senai.br.consultamedicas.R;
import sp.senai.br.consultamedicas.models.Medico;

/**
 * Created by 17170124 on 12/12/2018.
 */

public class MedicosAdapter extends ArrayAdapter<Medico>{

    MedicosAdapter medicosAdapter;

    public MedicosAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<Medico>());
        medicosAdapter= this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.lista_item_medicos, null);
        }

        Medico medico = getItem(position);

        TextView item_nome_medico = v.findViewById(R.id.item_nome_medico);

        item_nome_medico.setText(medico.getNome());

        return v;
    }

}
