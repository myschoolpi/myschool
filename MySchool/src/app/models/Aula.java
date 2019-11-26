package app.models;

import java.sql.Date;
import java.util.ArrayList;

public class Aula extends BaseModel{
	private Date data;
	private String descricao;
	private ArrayList<Aluno> alunosAula;
	private int idTurma;
	
	public Date getData() {
		return data;
	}
	
	public void setData(Date data) {
		this.data = data;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Aluno> getAlunosAula() {
		return alunosAula;
	}

	public void setAlunosAula(ArrayList<Aluno> alunosAula) {
		this.alunosAula = alunosAula;
	}

	public int getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(int idTurma) {
		this.idTurma = idTurma;
	}
	
}
