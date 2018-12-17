package sp.senai.br.consultamedicas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import sp.senai.br.consultamedicas.models.Consulta;
import sp.senai.br.consultamedicas.models.Especialidade;
import sp.senai.br.consultamedicas.models.HorarioAtendimento;

public class CadastroConsulta extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    /** Variáveis vindas pela tela da listagem dos médicos **/
    Intent intent;
    int idMedico;
    int idEspecialidade;
    String nomeMedico;

    /** Variáveis do XML / Layout da Activity **/
    TextView consultaNomeMedico;
    TextView txtNomeDia;
    EditText txtNomePaciente;
    EditText txtRgPaciente;
    Button btnAbrirCalendario;
    Spinner selectHoraConsulta;

    //Lista do select "selectHoraConsulta"
    List<String> horasSelect = new ArrayList<>();
    ArrayAdapter<String> adaptadorTipo;
    SimpleDateFormat hr_format = new SimpleDateFormat("HH:mm"); //Horário que aparecerá no selectHoraConsulta

    Context ctx = this;

    /** Variáveis auxiliares para manipulação da data do calendário escolhida pelo usuário **/
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat dt_format = new SimpleDateFormat("dd-MM-yyyy"); //Data que aparecerá para o usuário
    SimpleDateFormat dt_format_eua = new SimpleDateFormat("yyyy-MM-dd"); //Data que será guardada no banco
    String dataEscolhida = "";
    int idSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_consulta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Agendar Consulta");

        /** Setando valores nas váriaveis vindas pela tela de listagem de médicos **/
        intent = getIntent();
        idMedico = intent.getIntExtra("idMedico", 0);
        idEspecialidade = intent.getIntExtra("idEspecialidade", 0);
        nomeMedico = intent.getStringExtra("nomeMedico");

        /** Layout **/
        consultaNomeMedico = findViewById(R.id.consultaNomeMedico);
        txtNomeDia = findViewById(R.id.txtNomeDia);
        txtNomePaciente = findViewById(R.id.txtNomePaciente);
        txtRgPaciente = findViewById(R.id.txtRgPaciente);
        btnAbrirCalendario = findViewById(R.id.btnAbrirCalendario);
        selectHoraConsulta = findViewById(R.id.selectHoraConsulta);
        consultaNomeMedico.setText(nomeMedico);

        adaptadorTipo = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, horasSelect);

        adaptadorTipo.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        selectHoraConsulta.setAdapter(adaptadorTipo);

        //Calendário
        final DatePickerDialog datePickerDialog = new DatePickerDialog(ctx, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        btnAbrirCalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(txtNomePaciente.getText().toString().equals("")){
                    txtNomePaciente.setError("Preencha esse campo.");
                } else if(txtRgPaciente.getText().toString().equals("")){
                    txtRgPaciente.setError("Preencha esse campo");
                } else if(btnAbrirCalendario.getText().toString().equals("")){
                    Toast.makeText(ctx, "Selecione uma data", Toast.LENGTH_SHORT).show();
                } else if(selectHoraConsulta.getSelectedItem() == null){
                    Toast.makeText(ctx, "Selecione um horário", Toast.LENGTH_SHORT).show();
                } else{

                    //Salvando a consulta

                    new AsyncTask<Void, Void, String>(){

                        String url = "http:/10.0.0.2/INF4T20181/ConsultasMedicasAPI/salvarConsulta.php";

                        @Override
                        protected String doInBackground(Void... voids) {
                            String json = "";

                            HashMap<String, String> dados = new HashMap<>();
                            dados.put("data", dataEscolhida);
                            dados.put("horario", selectHoraConsulta.getSelectedItem().toString() + ":00");
                            dados.put("idMedico", String.valueOf(idMedico));
                            dados.put("nomePaciente", txtNomePaciente.getText().toString());
                            dados.put("rgPaciente", txtRgPaciente.getText().toString());
                            dados.put("idEspecialidade", String.valueOf(idEspecialidade));

                           json = HttpConnection.post(url, dados);
                           return json;
                        }

                        @Override
                        protected void onPostExecute(String json) {
                            super.onPostExecute(json);

                            if(json != null){
                                try {
                                    JSONObject sucesso = new JSONObject(json);

                                    if(sucesso.getBoolean("sucesso")){
                                        Toast.makeText(ctx, "Consulta agendada com sucesso!!!", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(ctx, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(ctx, "Erro ao agendar consulta!!", Toast.LENGTH_LONG).show();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                    }.execute();

                }

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    /** Função do calendário **/
    @Override
    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
        /*Pegando o que foi selecionado do calendário*/
        calendar.set(Calendar.YEAR, ano);
        calendar.set(Calendar.MONTH, mes);
        calendar.set(Calendar.DAY_OF_MONTH, dia);

        Date dataSelecionada = calendar.getTime();
        String data = dt_format.format(dataSelecionada);

        dataEscolhida = dt_format_eua.format(dataSelecionada);

        btnAbrirCalendario.setText(data);
        idSemana = calendar.get(Calendar.DAY_OF_WEEK);

        if(idSemana == 1){
            txtNomeDia.setText("Domingo");
        }else if(idSemana == 2){
            txtNomeDia.setText("Segunda - Feira");
        }else if(idSemana == 3){
            txtNomeDia.setText("Terça- Feira");
        }else if(idSemana == 4){
            txtNomeDia.setText("Quarta - Feira");
        }else if(idSemana == 5){
            txtNomeDia.setText("Quinta - Feira");
        }else if(idSemana == 6){
            txtNomeDia.setText("Sexta- Feira");
        }else if(idSemana == 7){
            txtNomeDia.setText("Sábado");
        }

        horasSelect.clear();
        new HorariosDisponiveisAPI().execute();

    }

    private void preencherSelectHoras(HorarioAtendimento h){

        ArrayList<Consulta> listaConsultas = h.getConsultasAgendadas();

        //Pegando o horário inicial que o médico trabalha
        calendar.setTime(h.getHorario_inicio());
        int hora_inicio = calendar.get(Calendar.HOUR_OF_DAY);

        //Pegando o horário final que o médico trabalha
        calendar.setTime(h.getHorario_fim());
        int hora_fim = calendar.get(Calendar.HOUR_OF_DAY);

        for(int i = hora_inicio; i <= hora_fim; i++){

            Boolean horaNaoDisponivel = false;

            //Verifica as consultas já marcadas do médico e remove o horário dessa consulta
            //do select, fazendo aparecer somente os horários disponíveis / quando ele não
            //tem consulta
            for(Consulta c : listaConsultas){
                calendar.setTime(c.getHorario());
                int horaConsulta = calendar.get(Calendar.HOUR_OF_DAY);

                if(horaConsulta == i){
                    horaNaoDisponivel = true;
                }
            }

            if(horaNaoDisponivel == false){
                horasSelect.add(String.valueOf(i));
            }

        }

        adaptadorTipo.notifyDataSetChanged();

    }

    private class HorariosDisponiveisAPI extends AsyncTask<Void, Void, String>{

        String url = "http://10.0.0.2/INF4T20181/ConsultasMedicasAPI/buscarHorasAtendimento.php?idMedico=" + idMedico + "&idDia=" + idSemana + "&dataEscolhida=" + dataEscolhida;

        @Override
        protected String doInBackground(Void... voids) {
            String json = "";

            json = HttpConnection.get(url);

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            if (json != null) {

                try {
                    JSONArray jsonArray = new JSONArray(json);

                    if(jsonArray.length()==0){
                        txtNomeDia.setText(nomeMedico + " não trabalha neste dia. Selecione um outro dia!");
                    }else{

                        for (int i = 0; i < jsonArray.length(); i++) {

                            ArrayList<Consulta> listaConsulta = new ArrayList<>();

                            JSONObject object = jsonArray.getJSONObject(i);
                            HorarioAtendimento h = new HorarioAtendimento();
                            h.setIdMedico(object.getInt("idMedico"));
                            h.setIdDiaSemana(object.getInt("idDiaSemana"));
                            h.setHorario_inicio(hr_format.parse(object.getString("horario_inicio")));
                            h.setHorario_fim(hr_format.parse(object.getString("horario_fim")));
                            h.setIdHorarioAtendimento(object.getInt("idHorarioAtendimento"));

                            JSONArray jsonArrayConsultas = object.getJSONArray("consultas");

                            for(int x =0; x < jsonArrayConsultas.length(); x++){
                                JSONObject objectConsulta = jsonArrayConsultas.getJSONObject(x);

                                Consulta c = new Consulta();
                                c.setHorario(hr_format.parse(objectConsulta.getString("horario")));
                                c.setIdConsulta(objectConsulta.getInt("idConsulta"));
                                c.setIdEspecialidade(objectConsulta.getInt("idEspecialidade"));
                                c.setIdMedico(objectConsulta.getInt("idMedico"));
                                c.setNomePaciente(objectConsulta.getString("nomePaciente"));
                                c.setRgPaciente(objectConsulta.getString("rgPaciente"));

                                listaConsulta.add(c);
                            }

                            h.setConsultasAgendadas(listaConsulta);

                            preencherSelectHoras(h);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}
