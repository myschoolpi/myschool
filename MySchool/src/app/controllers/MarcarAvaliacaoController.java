package app.controllers;

import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import app.models.Avaliacao;
import app.models.Professor;
import app.models.Turma;
import app.services.AvaliacaoService;
import app.services.TurmaService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MarcarAvaliacaoController {
	private Professor user = null;
	private ArrayList<Turma> listaTurmas;
	
	private Avaliacao a = null;
	
	private TurmaService ts = null;
	private AvaliacaoService as = null;
	
	@FXML
    private ChoiceBox<Turma> turmaSelect;

    @FXML
    private DatePicker dataAvaliacao;

    @FXML
    private TextArea descAvaliacaoTA;

    @FXML
    private Button saveButton;
    
    @FXML
    private void initialize() {
    	ts = new TurmaService();
    	
    	listaTurmas = ts.getTurmasProfessor(user.id);
    	
    	for(int i = 0; i < listaTurmas.size(); i++) {
			turmaSelect.getItems().add(listaTurmas.get(i));
		}
    }
    
    public void initData(Professor prof) {
    	user = prof;
    }
    
    @FXML
    void getAvaliacao(ActionEvent event) {

    }

    @FXML
    void marcarAvaliacao(ActionEvent event) {
    	a = new Avaliacao();
    	as = new AvaliacaoService();
    	
    	Date d = Date.valueOf(dataAvaliacao.getValue());
    	int idTurma = turmaSelect.getSelectionModel().getSelectedItem().id;
    	
    	a.setDescricao(descAvaliacaoTA.getText());
    	a.setData(d);
    	
    	JOptionPane.showMessageDialog(null, as.marcarAvaliacao(a, idTurma));
    }
}
