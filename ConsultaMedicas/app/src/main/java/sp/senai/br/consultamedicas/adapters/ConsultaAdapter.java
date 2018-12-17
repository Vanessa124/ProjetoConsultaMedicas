package sp.senai.br.consultamedicas.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import sp.senai.br.consultamedicas.R;
import sp.senai.br.consultamedicas.models.Consulta;
import sp.senai.br.consultamedicas.models.Especialidade;

public class ConsultaAdapter extends ArrayAdapter<Consulta> {

    ConsultaAdapter consultaAdapter;

    public ConsultaAdapter(@NonNull Context context) {
        super(context, 0, new ArrayList<Consulta>());
        consultaAdapter= this;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.lista_item_consulta, null);
        }

        Consulta c = getItem(position);

        TextView txtDetalhesMedico = v.findViewById(R.id.txtDetalhesMedico);
        TextView txtDetalhesDia = v.findViewById(R.id.txtDetalhesDia);
        TextView txtDetalhesPaciente = v.findViewById(R.id.txtDetalhesPaciente);

        txtDetalhesMedico.setText("Dr(a). " + c.getNomeMedico() + " - " + c.getEspecialidade());
        txtDetalhesDia.setText(c.getDataFormatada() + ", ás " + c.getHorarioFormatado() + " horas.");
        txtDetalhesPaciente.setText("Paciente: " + c.getNomePaciente());
        return v;

    }
}
