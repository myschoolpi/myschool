package app.controllers;

import app.models.Aluno;

public class HomeAlunoController {
	private Aluno user = null;
	
	/**
	 * Irá puxar o usuário logado
	 * @param a - Aluno que se logou
	 */
	public void initData(Aluno a) {
		user = a;
	}
}
