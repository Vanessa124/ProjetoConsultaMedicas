package sp.senai.br.consultamedicas.models;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 17170124 on 12/12/2018.
 */

public class HorarioAtendimento {
    private int idHorarioAtendimento;
    private Date horario_inicio;
    private Date horario_fim;
    private int idDiaSemana;
    private int idMedico;
    private ArrayList<Consulta> consultasAgendadas;

    public int getIdHorarioAtendimento() {
        return idHorarioAtendimento;
    }

    public void setIdHorarioAtendimento(int idHorarioAtendimento) {
        this.idHorarioAtendimento = idHorarioAtendimento;
    }

    public Date getHorario_inicio() {
        return horario_inicio;
    }

    public void setHorario_inicio(Date horario_inicio) {
        this.horario_inicio = horario_inicio;
    }

    public Date getHorario_fim() {
        return horario_fim;
    }

    public void setHorario_fim(Date horario_fim) {
        this.horario_fim = horario_fim;
    }

    public int getIdDiaSemana() {
        return idDiaSemana;
    }

    public void setIdDiaSemana(int idDiaSemana) {
        this.idDiaSemana = idDiaSemana;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public ArrayList<Consulta> getConsultasAgendadas() {
        return consultasAgendadas;
    }

    public void setConsultasAgendadas(ArrayList<Consulta> consultasAgendadas) {
        this.consultasAgendadas = consultasAgendadas;
    }
}
