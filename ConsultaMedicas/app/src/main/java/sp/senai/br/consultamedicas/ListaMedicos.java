package sp.senai.br.consultamedicas;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import sp.senai.br.consultamedicas.adapters.MedicosAdapter;
import sp.senai.br.consultamedicas.models.Medico;

public class ListaMedicos extends AppCompatActivity implements AdapterView.OnItemClickListener{

    ListView list_medicos;
    MedicosAdapter adapter;

    TextView txtNomeEspecialidade;
    TextView txtDescEspecialidade;
    TextView txtStatusLista;

    int idEspecialidade;
    String nomeEspecialidade;
    String descEspecialidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicos);

        adapter = new MedicosAdapter(this);

        list_medicos = findViewById(R.id.list_medicos);
        list_medicos.setAdapter(adapter);
        list_medicos.setOnItemClickListener(this);

        txtNomeEspecialidade = findViewById(R.id.txtNomeEspecialidade);
        txtDescEspecialidade = findViewById(R.id.txtDescEspecialidade);
        txtStatusLista = findViewById(R.id.txtStatusLista);

        Intent intent = getIntent();
        idEspecialidade = intent.getIntExtra("idEspecialidade", 0);
        nomeEspecialidade = intent.getStringExtra("nomeEspecialidade");
        descEspecialidade = intent.getStringExtra("descEspecialidade");

        txtNomeEspecialidade.setText(nomeEspecialidade);
        txtDescEspecialidade.setText(descEspecialidade);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new MedicosAPI().execute();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Medico medico = adapter.getItem(i);
        Intent intent = new Intent(this, CadastroConsulta.class);
        intent.putExtra("idMedico", medico.getIdMedico());
        intent.putExtra("nomeMedico", medico.getNome());
        intent.putExtra("idEspecialidade", idEspecialidade);
        startActivity(intent);
    }

    private class MedicosAPI extends AsyncTask<Void, Void, String>{

        String url = "http://10.0.0.2/INF4T20181/ConsultasMedicasAPI/selecionarMedicosPorEspecialidade.php?idEspecialidade=" + idEspecialidade;

        @Override
        protected String doInBackground(Void... voids) {

            String json = "";

            json = HttpConnection.get(url);

            return json;

        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);

            Log.d("json: ", json);

            ArrayList<Medico> lista = new ArrayList<>();

            if(json != null){

                try {
                    JSONArray jsonArray = new JSONArray(json);

                    for(int i = 0; i < jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        Medico m = new Medico();
                        m.setNome(object.getString("nome"));
                        m.setIdMedico(object.getInt("idMedico"));
                        Log.d("Medico:", m.getNome());

                        lista.add(m);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.clear();
                adapter.addAll(lista);

            }

            if(lista.size() == 0){
                txtStatusLista.setText("Nenhum médico disponível!");
            } else {
                txtStatusLista.setText("Médicos da área");
            }

        }
    }
}
