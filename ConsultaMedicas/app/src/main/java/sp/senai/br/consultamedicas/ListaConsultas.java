package sp.senai.br.consultamedicas;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import sp.senai.br.consultamedicas.adapters.ConsultaAdapter;
import sp.senai.br.consultamedicas.models.Consulta;
import sp.senai.br.consultamedicas.models.Especialidade;

public class ListaConsultas extends AppCompatActivity {

    ListView list_consultas;
    ConsultaAdapter adapter;

    SimpleDateFormat hr_format = new SimpleDateFormat("HH:mm");
    SimpleDateFormat dt_format_eua = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_consultas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Consultas Agendadas");

        adapter = new ConsultaAdapter(this);
        list_consultas = findViewById(R.id.list_consultas);
        list_consultas.setAdapter(adapter);

        new AsyncTask<Void, Void, String>(){

            String url = "http:/10.0.0.2/INF4T20181/ConsultasMedicasAPI/selecionarTodasConsultas.php";

            @Override
            protected String doInBackground(Void... voids) {
                String json = "";

                json = HttpConnection.get(url);

                return json;
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);

                ArrayList<Consulta> lista = new ArrayList<>();

                if(json != null){

                    try {
                        JSONArray jsonArray = new JSONArray(json);

                        for(int i = 0; i < jsonArray.length(); i++){

                            JSONObject object = jsonArray.getJSONObject(i);
                            Consulta c = new Consulta();
                            c.setNomePaciente(object.getString("nomePaciente"));
                            c.setRgPaciente(object.getString("rgPaciente"));
                            c.setIdMedico(object.getInt("idMedico"));
                            c.setHorario(hr_format.parse(object.getString("horario")));
                            c.setIdEspecialidade(object.getInt("idEspecialidade"));
                            c.setNomeMedico(object.getString("nome"));
                            c.setEspecialidade(object.getString("especialidade"));
                            c.setIdConsulta(object.getInt("idConsulta"));
                            c.setData(dt_format_eua.parse(object.getString("data")));

                            lista.add(c);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    adapter.clear();
                    adapter.addAll(lista);

                }
            }
        }.execute();

    }

}
