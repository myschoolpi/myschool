package app.models;

import javax.swing.JOptionPane;

public class Disciplina extends BaseModel{
	private String descricao;
	private String nome;
	private int cargaHoraria;
	
	public Disciplina(int id) {
		this.id = id;
	}
	
	public Disciplina() {
		
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(int cargaHoraria) {
		try {			
			this.cargaHoraria = cargaHoraria;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido!");
		}
	}
	
	
	
}
