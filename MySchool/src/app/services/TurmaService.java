package app.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.Aluno;
import app.models.Turma;

public class TurmaService implements BaseService {
	private BD bd = null;
	private Turma t = null;

	@Override
	public String create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String createFullTurma(Turma t) {
		BD bd = new BD();
		if(bd.getConnection()) {
			String sql = "INSERT INTO tb_turma(dias_turma, horario_turma, id_curso) VALUES(?, ?, ?)";
			try{
				bd.st = bd.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bd.st.setString(1, t.getDiasSemana());
				bd.st.setTime(2, t.getPeriodoTime());
				bd.st.setInt(3, t.getCursoTurma().id);
				
				bd.st.executeUpdate();
				
				bd.rs = bd.st.getGeneratedKeys();
				
				//id da turma inserida
				int n;
				
				if(bd.rs.next())
					n = bd.rs.getInt(1);
				else
					return "Houve um erro ao cadastrar a Turma";
				
				//inserção na tabela auxiliar entre turma e professor
				sql = "INSERT INTO turma_professor(id_professor, id_turma) VALUES(?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, t.getProfTurma().id);
				bd.st.setInt(2, n);
				
				bd.st.executeUpdate();
				
				//inserção na tabela auxiliar entre turma e alunos
				sql = "INSERT INTO tb_aluno_turma(id_aluno, id_turma) VALUES(?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				
				int count = 0;
				for(Aluno a: t.getAlunosTurma()){
					bd.st.setInt(1, a.id);
					bd.st.setInt(2, n);
					
					bd.st.addBatch();
					count ++;
					
					if(count % 100 == 0 || count == t.getAlunosTurma().size()) {
						bd.st.executeBatch();
					}
				}
				
				return "Turma criada com sucesso";
				
				
			} catch (SQLException e) {
				return "Houve um erro ao cadastrar a Turma";
			} finally {
				bd.close();
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
