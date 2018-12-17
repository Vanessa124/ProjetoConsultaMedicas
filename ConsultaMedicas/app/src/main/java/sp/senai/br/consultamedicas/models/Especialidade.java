package sp.senai.br.consultamedicas.models;

/**
 * Created by 17170124 on 12/12/2018.
 */

public class Especialidade {
    private int idEspecialidade;
    private String nome;
    private String descricao;

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
