package app.controllers;

import app.models.Aluno;

public class HomeAlunoController {
	private Aluno user = null;
	
	/**
	 * Ir� puxar o usu�rio logado
	 * @param a - Aluno que se logou
	 */
	public void initData(Aluno a) {
		user = a;
	}
}
