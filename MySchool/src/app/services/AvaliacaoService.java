package app.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.Avaliacao;

public class AvaliacaoService implements BaseService {
	private BD bd = null;
	private Avaliacao a = null;

	@Override
	public String create(Object obj) {
		// TODO Auto-generated method stub
		return null;
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

	public String marcarAvaliacao(Avaliacao a, int idTurma) {
		bd = new BD();
		if(bd.getConnection()) {
			String sql = "INSERT INTO tb_avaliacao(descricao_avaliacao, data_avaliacao) "
					+ "VALUES(?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bd.st.setString(1, a.getDescricao());
				bd.st.setDate(2, a.getData());
				
				bd.st.executeUpdate();

				bd.rs = bd.st.getGeneratedKeys();

				// id da avaliacao inserida
				int n;

				if (bd.rs.next())
					n = bd.rs.getInt(1);
				else
					return "Houve um erro ao marcar Avaliação";
				
				sql = "SELECT id_aluno_turma FROM tb_aluno_turma WHERE id_turma = " + idTurma;
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				ArrayList<Integer> idAlunosTurma = new ArrayList<Integer>();
				
				while(bd.rs.next()) {
					idAlunosTurma.add(bd.rs.getInt("id_aluno_turma"));
				}
				
				sql = "INSERT INTO tb_aluno_turma_avaliacao(id_aluno_turma, id_avaliacao, nota_avaliacao) "
						+ "VALUES(?, ?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				
				int count = 0;
				while(count < idAlunosTurma.size()) {
					bd.st.setInt(1, idAlunosTurma.get(count));
					bd.st.setInt(2, n);
					bd.st.setDouble(3, 0);
					
					bd.st.addBatch();
					count ++;
					
					if (count % 100 == 0 || count == idAlunosTurma.size()) {
						bd.st.executeBatch();
					}
				}
				
				return "Avaliacao Marcada";
				
			} catch (SQLException e)  {
				return "Houve um erro ao Marcar Avaliação";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conexão com o Banco de Dados";
		}
	}

}
