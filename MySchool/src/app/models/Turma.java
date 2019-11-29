package app.models;

import java.sql.Time;
import java.util.ArrayList;

public class Turma extends BaseModel {
	private final String MANHA = "Manhã";
	private final String TARDE = "Tarde";
	private final String NOITE = "Noite";

	private String periodo;
	private Time periodoTime;
	private String diasSemana;
	private Curso cursoTurma;
	private Professor profTurma;
	private ArrayList<Aluno> alunosTurma;
	
	public String getPeriodo() {
		return periodo;
	}
	
	/**
	 * Irá criar um horário de acordo com o período selecionado
	 * @return - horário do perído informado
	 */
	public Time getPeriodoTime() {
		switch(periodo) {
			case MANHA:
				periodoTime = Time.valueOf("07:00:00");
				break;
			case TARDE:
				periodoTime = Time.valueOf("13:00:00");
				break;
			case NOITE:
				periodoTime = Time.valueOf("19:00:00");
				break;
		}
		
		return periodoTime;
	}
	
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public String getDiasSemana() {
		return diasSemana;
	}
	
	public void setDiasSemana(String diasSemana) {
		this.diasSemana = diasSemana;
	}
	
	public Curso getCursoTurma() {
		return cursoTurma;
	}
	
	public void setCursoTurma(Curso cursoTurma) {
		this.cursoTurma = cursoTurma;
	}
	
	public Professor getProfTurma() {
		return profTurma;
	}
	
	public void setProfTurma(Professor profTurma) {
		this.profTurma = profTurma;
	}
	
	public ArrayList<Aluno> getAlunosTurma() {
		return alunosTurma;
	}
	
	public void setAlunosTurma(ArrayList<Aluno> alunosTurma) {
		this.alunosTurma = alunosTurma;
	}
	
	@Override
	public String toString() {
		return this.getCursoTurma().getNome() + " - " + id;
	}
	
	
}
