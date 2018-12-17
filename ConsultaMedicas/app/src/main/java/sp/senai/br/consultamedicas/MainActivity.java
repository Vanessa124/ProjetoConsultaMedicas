package sp.senai.br.consultamedicas;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import sp.senai.br.consultamedicas.adapters.EspecialidadesAdapter;
import sp.senai.br.consultamedicas.models.Especialidade;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView list_especialidades;
    EspecialidadesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new EspecialidadesAdapter(this);
        list_especialidades = (ListView) findViewById(R.id.list_especialidades);
        list_especialidades.setAdapter(adapter);

        list_especialidades.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        new EspecialidadeAPI().execute();

    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Especialidade especialidade = adapter.getItem(i);

        Intent intent = new Intent(this, ListaMedicos.class);

        intent.putExtra("idEspecialidade", especialidade.getIdEspecialidade());
        intent.putExtra("nomeEspecialidade", especialidade.getNome());
        intent.putExtra("descEspecialidade", especialidade.getDescricao());

        startActivity(intent);
    }

    public void abrirTelaConsultas(View view) {
        Intent intent = new Intent(this, ListaConsultas.class);
        startActivity(intent);

    }

    private class EspecialidadeAPI extends AsyncTask<Void, Void, String>{

        String url = "http:/10.0.0.2/INF4T20181/ConsultasMedicasAPI/selecionarTodasEspecialidades.php";

        @Override
        protected String doInBackground(Void... voids) {

            String json = "";

            json = HttpConnection.get(url);

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);

            ArrayList<Especialidade> lista = new ArrayList<>();

            if(json != null){

                try {
                    JSONArray jsonArray = new JSONArray(json);

                    for(int i = 0; i < jsonArray.length(); i++){

                        JSONObject object = jsonArray.getJSONObject(i);
                        Especialidade e = new Especialidade();
                        e.setIdEspecialidade(object.getInt("idEspecialidade"));
                        e.setDescricao(object.getString("descricao"));
                        e.setNome(object.getString("nome"));

                        lista.add(e);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.clear();
                adapter.addAll(lista);

            }

        }
    }


}
