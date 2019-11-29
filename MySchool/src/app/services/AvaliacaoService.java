package app.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.AlunoNota;
import app.models.Avaliacao;

public class AvaliacaoService implements BaseService {
	private BD bd = null;
	private Avaliacao a = null;
	private AlunoNota alunoNota;
	private ArrayList<AlunoNota> listaAlunoNotas = new ArrayList<AlunoNota>();

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

	/**
	 * Irá realizar uma consulta das notas de uma prova dos alunos de uma determinada turma
	 * @param avaliacaoDesc - descrição da avaliação
	 * @param idTurma - id da turma
	 * @return lista de notas dos alunos da turma
	 */
	public ArrayList<AlunoNota> getAvaliacaoNotas(String avaliacaoDesc, int idTurma) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "SELECT id_avaliacao FROM tb_avaliacao WHERE descricao_avaliacao = ? " + "AND id_turma = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, avaliacaoDesc);
				bd.st.setInt(2, idTurma);
				bd.rs = bd.st.executeQuery();

				if (bd.rs.next()) {
					int idAvaliacao = 0;
					idAvaliacao = bd.rs.getInt("id_avaliacao");

					sql = "SELECT id_turma_avaliacao, nota_avaliacao, nome_aluno" + " FROM tb_aluno_turma_avaliacao "
							+ "INNER JOIN tb_aluno_turma "
							+ "ON tb_aluno_turma_avaliacao.id_aluno_turma = tb_aluno_turma.id_aluno_turma "
							+ "INNER JOIN tb_aluno ON tb_aluno.id_aluno = tb_aluno_turma.id_aluno WHERE id_avaliacao = ?";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setInt(1, idAvaliacao);
					bd.rs = bd.st.executeQuery();

					while (bd.rs.next()) {
						alunoNota = new AlunoNota();

						alunoNota.setIdAlunoNota(bd.rs.getInt("id_turma_avaliacao"));
						alunoNota.setNota(bd.rs.getDouble("nota_avaliacao"));
						alunoNota.setNome(bd.rs.getString("nome_aluno"));

						listaAlunoNotas.add(alunoNota);
					}

					return listaAlunoNotas;
				}

				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				bd.close();
			}
		} else {
			return null;
		}
	}

	/**
	 * Irá atualizar as notas dos alunos
	 * @param alunosNotas - lista das notas atualizadas
	 * @return - mensagem de resposta
	 */
	public String updateAlunoNotas(ArrayList<AlunoNota> alunosNotas) {
		bd = new BD();

		if (bd.getConnection()) {
			try {
				for (AlunoNota alunoNota : alunosNotas) {
					String sql = "UPDATE tb_aluno_turma_avaliacao SET nota_avaliacao = ? "
							+ "WHERE id_turma_avaliacao = ?";

					bd.st = bd.con.prepareStatement(sql);
					bd.st.setDouble(1, alunoNota.getNota());
					bd.st.setInt(2, alunoNota.getIdAlunoNota());

					bd.st.executeUpdate();
				}
			} catch (SQLException e) {
				return "Houve um erro ao atualizar as notas";
			} finally {
				bd.close();
			}

			return "Notas Atualizadas";
		} else {
			return "Houve um erro na conexão com o Banco de Dados";
		}
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

	/**
	 * Irá inserir uma avaliação marcada numa determinada data
	 * @param a - avaliação
	 * @param idTurma - id da turma onde será aplicada a avaliação
	 * @return - mensagem de resposta
	 */
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
				return "Houve um erro ao Marcar Avaliação";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conexão com o Banco de Dados";
		}
	}

	/**
	 * Irá inserir as notas de uma prova dos alunos de uma determinada turma
	 * @param a - avaliação
	 * @param alunoNotas - notas dos alunos
	 * @param idTurma - id da turma
	 * @return - mensagem de resposta
	 */
	public String lancarNotas(Avaliacao a, ArrayList<AlunoNota> alunoNotas, int idTurma) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "SELECT id_avaliacao FROM tb_avaliacao WHERE id_turma = ?" + " AND descricao_avaliacao = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, idTurma);
				bd.st.setString(2, a.getDescricao());
				bd.rs = bd.st.executeQuery();

				int idAvaliacao = 0;
				if (bd.rs.next()) {
					idAvaliacao = bd.rs.getInt("id_avaliacao");

					for (AlunoNota an : alunoNotas) {
						sql = "SELECT id_aluno_turma FROM tb_aluno_turma WHERE id_aluno = ?" + " AND id_turma = ?";
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

					return "Notas Lançadas";
				}

				return "Houve um erro ao lançar as notas";
			} catch (SQLException e) {
				e.printStackTrace();
				return "Houve um erro ao lançar as notas";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conexão com o banco de dados";
		}
	}

}
