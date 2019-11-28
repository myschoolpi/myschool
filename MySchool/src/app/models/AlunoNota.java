package app.models;

public class AlunoNota {
	private int idAlunoNota;
	private int idAluno;
	private String nome;
	private double nota;
	
	public AlunoNota() {
		
	}

	public AlunoNota(int idAluno, String nomeAluno, double notaAluno) {
		this.idAluno = idAluno;
		this.nome = nomeAluno;
		this.nota = notaAluno;
	}

	public int getIdAluno() {
		return idAluno;
	}

	public void setIdAluno(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

	public int getIdAlunoNota() {
		return idAlunoNota;
	}

	public void setIdAlunoNota(int idAlunoNota) {
		this.idAlunoNota = idAlunoNota;
	}
	
	
}
