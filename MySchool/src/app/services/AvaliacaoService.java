package app.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.AlunoNota;
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
		if (bd.getConnection()) {
			String sql = "INSERT INTO tb_avaliacao(descricao_avaliacao, data_avaliacao, id_turma) " + "VALUES(?, ?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, a.getDescricao());
				bd.st.setDate(2, a.getData());
				bd.st.setInt(3, idTurma);

				bd.st.executeUpdate();

				return "Avaliacao Marcada";

			} catch (SQLException e) {
				return "Houve um erro ao Marcar Avalia��o";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conex�o com o Banco de Dados";
		}
	}

	public String lancarNotas(Avaliacao a, ArrayList<AlunoNota> alunoNotas, int idTurma) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "SELECT id_avaliacao FROM tb_avaliacao WHERE id_turma = ?"
					+ " AND descricao_avaliacao = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, idTurma);
				bd.st.setString(2, a.getDescricao());
				bd.rs = bd.st.executeQuery();

				int idAvaliacao = 0;
				if (bd.rs.next()) {
					idAvaliacao = bd.rs.getInt("id_avaliacao");

					for (AlunoNota an : alunoNotas) {
						sql = "SELECT id_aluno_turma FROM tb_aluno_turma WHERE id_aluno = ?"
								+ " AND id_turma = ?"; 
						bd.st = bd.con.prepareStatement(sql);
						bd.st.setInt(1, an.getIdAluno());
						bd.st.setInt(2, idTurma);
						bd.rs = bd.st.executeQuery();

						int idAlunoTurma = 0;
						if (bd.rs.next()) {
							idAlunoTurma = bd.rs.getInt("id_aluno_turma");

							sql = "INSERT INTO tb_aluno_turma_avaliacao(id_aluno_turma, id_avaliacao, nota_avaliacao) "
									+ "VALUES(?, ?, ?)";
							bd.st = bd.con.prepareStatement(sql);
							bd.st.setInt(1, idAlunoTurma);
							bd.st.setInt(2, idAvaliacao);
							bd.st.setDouble(3, an.getNota());

							bd.st.executeUpdate();

						}
					}

					return "Notas Lan�adas";
				}

				return "Houve um erro ao lan�ar as notas";
			} catch (SQLException e) {
				e.printStackTrace();
				return "Houve um erro ao lan�ar as notas";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conex�o com o banco de dados";
		}
	}

}