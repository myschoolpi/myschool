package app.services;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.Aluno;
import app.models.Aula;

public class AulaService implements BaseService {
	private BD bd = null;
	private Aula aula = null;

	@Override
	public String create(Object obj) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "INSERT INTO tb_aula(data_aula, descricao_aula) VALUES(?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bd.st.setDate(1, ((Aula) obj).getData());
				bd.st.setString(2, ((Aula) obj).getDescricao());

				bd.st.executeUpdate();

				bd.rs = bd.st.getGeneratedKeys();

				// id da aula inserida
				int n;

				if (bd.rs.next())
					n = bd.rs.getInt(1);
				else
					return "Houve um erro ao Lançar Aula";
				
				//inserção na tabela auxiliar entre aluno e aula
				sql = "INSERT INTO tb_aluno_aula(id_aluno, id_aula) VALUES(?, ?)";
				bd.st = bd.con.prepareStatement(sql);
				
				int count = 0;
				for(Aluno a: ((Aula) obj).getAlunosAula()) {
					bd.st.setInt(1, a.id);
					bd.st.setInt(2, n);

					bd.st.addBatch();
					count++;

					if (count % 100 == 0 || count == ((Aula) obj).getAlunosAula().size()) {
						bd.st.executeBatch();
					}
				}
				
				return "Aula lançada";

			} catch (SQLException e) {
				return "Houve um erro ao Lançar Aula";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conexão com o banco de dados";
		}
	}
	
	public Aula getAulaByDate(Date d, int idTurma) {
		bd = new BD();
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_aula au INNER JOIN tb_aluno_aula aa "
						+ "ON au.id_aula = aa.id_aula "
						+ "WHERE au.data_aula = ?"
						+ " AND au.id_turma = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setDate(1, d);
				bd.st.setInt(2, idTurma);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					aula = new Aula();
					
					aula.setData(bd.rs.getDate("data_aula"));
					aula.setDescricao(bd.rs.getString("descricao_aula"));
					aula.setIdTurma(bd.rs.getInt("id_turma"));
				}
				
				return aula;
			} catch(SQLException e) {
				return null;
			} finally {
				bd.close();
			}
		} else {
			return null;
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
