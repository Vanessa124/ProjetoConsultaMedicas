package sp.senai.br.consultamedicas.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import sp.senai.br.consultamedicas.R;
import sp.senai.br.consultamedicas.models.Especialidade;

/**
 * Created by 17170124 on 12/12/2018.
 */

public class EspecialidadesAdapter extends ArrayAdapter<Especialidade> {

    EspecialidadesAdapter especialidadesAdapter;

    public EspecialidadesAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<Especialidade>());
        especialidadesAdapter= this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.lista_item_especialidades, null);
        }

        Especialidade especialidade = getItem(position);

        TextView item_nome_especialidade = v.findViewById(R.id.item_nome_especialidade);

        item_nome_especialidade.setText(especialidade.getNome());

        return v;
    }
}
