package app.controllers;

import app.models.Aluno;

public class HomeAlunoController {
	private Aluno user = null;
	
	public void initData(Aluno a) {
		user = a;
	}
}
