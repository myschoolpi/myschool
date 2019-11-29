package app.controllers;

import javax.swing.JOptionPane;

import app.models.Disciplina;
import app.services.DisciplinaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CadastrarDisciplinaController {
	private DisciplinaService ds = null;
	private Disciplina d = null;
	
	@FXML
    private TextField nomeTF;

    @FXML
    private TextArea descTF;

    @FXML
    private TextField cargaHorariaTF;

    /**
     * Ir� pegar todos os dados inseridos e criar uma nova disciplina,
     * chamando a fun��o que a insere no banco de dados,
     * mostrando a resposta num di�logo
     * @param event - evento de clique do bot�o
     */
    @FXML
    void cadastrarDisciplina(ActionEvent event) {
    	ds = new DisciplinaService();
    	d = new Disciplina();
    	
    	d.setNome(nomeTF.getText());
    	d.setDescricao(descTF.getText());
    	d.setCargaHoraria(Integer.parseInt(cargaHorariaTF.getText()));
    	
    	JOptionPane.showMessageDialog(null, ds.create(d)); 
    }
}
