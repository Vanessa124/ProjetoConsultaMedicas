package sp.senai.br.consultamedicas.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Consulta {
    private int idConsulta;
    private Date horario;
    private String nomePaciente;
    private String rgPaciente;
    private int idMedico;
    private int idEspecialidade;
    private String especialidade;
    private String nomeMedico;
    private Date data;

    SimpleDateFormat dt_format = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat hr_format = new SimpleDateFormat("HH:mm");

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getHorario() {
        return horario;
    }

    public void setHorario(Date horario) {
        this.horario = horario;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getRgPaciente() {
        return rgPaciente;
    }

    public void setRgPaciente(String rgPaciente) {
        this.rgPaciente = rgPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDataFormatada() {
        return dt_format.format(data);
    }

    public String getHorarioFormatado() {
        return hr_format.format(horario);
    }
}
