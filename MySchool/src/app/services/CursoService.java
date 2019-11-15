package app.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.Curso;
import app.models.Disciplina;

public class CursoService implements BaseService {
	private BD bd = null;
	private Curso c = null;
	private Disciplina d = null;

	@Override
	public String create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String createCursoComDisciplina(Curso c) {
		bd = new BD();
		
		if(bd.getConnection()) {
			String sql = "INSERT INTO tb_curso(nome_curso, descricao_curso, duracao_curso) " +
					"VALUES (?, ?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bd.st.setString(1, c.getNome());
				bd.st.setString(2, c.getDescricao());
				bd.st.setString(3, c.getDuracao());
				
				bd.st.executeUpdate();
				
				bd.rs = bd.st.getGeneratedKeys();
				
				//id do curso inserido
				int n;
				
				if(bd.rs.next())
					n = bd.rs.getInt(1);
				else
					return "Houve um erro ao criar o curso";
				
				//inserção na tabela auxiliar entre curso e disciplina
				sql = "INSERT INTO tb_curso_disciplina(id_curso, id_disciplina) " +
						"VALUES(?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				int count = 0;
				
				for(Disciplina d: c.getDisciplinas()) {
					bd.st.setInt(1, n);
					bd.st.setInt(2, d.id);
					
					bd.st.addBatch();
					
					if(count % 100 == 0 || count == c.getDisciplinas().size()) {
						bd.st.executeBatch();
					}
				}
				
				return "Curso criado com sucesso!";
			} catch (SQLException e) {
				return "Houve um erro ao cadastrar o curso";
			}
		} else {
			return "Houve um erro na conexão com o banco de dados";
		}
		
	}

	@Override
	public Object update(Object obj, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getOne(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getMany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
