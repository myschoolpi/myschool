package app.services;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.Aluno;
import app.models.Aula;

public class AulaService implements BaseService {
	private BD bd;
	private Aula aula;
	private ArrayList<Aluno> alunosAula = new ArrayList<Aluno>();
	private Aluno aluno;

	@Override
	public String create(Object obj) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "INSERT INTO tb_aula(data_aula, descricao_aula, id_turma) VALUES(?, ?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				bd.st.setDate(1, ((Aula) obj).getData());
				bd.st.setString(2, ((Aula) obj).getDescricao());
				bd.st.setInt(3, ((Aula) obj).getIdTurma());

				bd.st.executeUpdate();

				bd.rs = bd.st.getGeneratedKeys();

				// id da aula inserida
				int n;

				if (bd.rs.next())
					n = bd.rs.getInt(1);
				else
					return "Houve um erro ao Lançar Aula";

				// inserção na tabela auxiliar entre aluno e aula
				sql = "INSERT INTO tb_aluno_aula(id_aluno, id_aula) VALUES(?, ?)";
				bd.st = bd.con.prepareStatement(sql);

				int count = 0;
				for (Aluno a : ((Aula) obj).getAlunosAula()) {
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

	/**
	 * Irá realizar uma consulta no banco puxando a aula e os alunos presentes naquela aula,
	 * filtrando por turma e data da aula
	 * @param d - data da aula
	 * @param alunosTurma - todos os alunos da turma
	 * @param idTurma - id da turma
	 * @return - aula com os alunos
	 */
	public Aula getAulaByDate(Date d, ArrayList<Aluno> alunosTurma, int idTurma) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "SELECT * FROM tb_aula au INNER JOIN tb_aluno_aula aa " + "ON au.id_aula = aa.id_aula "
					+ "WHERE au.data_aula = ?" + " AND au.id_turma = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setDate(1, d);
				bd.st.setInt(2, idTurma);
				bd.rs = bd.st.executeQuery();

				int idAula = 0;
				if (bd.rs.next()) {
					idAula = bd.rs.getInt("id_aula");
					aula = new Aula();

					aula.id = bd.rs.getInt("id_aula");
					aula.setData(bd.rs.getDate("data_aula"));
					aula.setDescricao(bd.rs.getString("descricao_aula"));
					aula.setIdTurma(bd.rs.getInt("id_turma"));

					for (int i = 0; i < alunosTurma.size(); i++) {
						sql = "SELECT * FROM tb_aluno_aula WHERE id_aluno = ? " + "AND id_aula = ?";
						bd.st = bd.con.prepareStatement(sql);
						bd.st.setInt(1, alunosTurma.get(i).id);
						bd.st.setInt(2, idAula);

						bd.rs = bd.st.executeQuery();
						if (bd.rs.next()) {
							aluno = new Aluno();

							aluno.id = alunosTurma.get(i).id;
							aluno.setNome(alunosTurma.get(i).getNome());

							alunosAula.add(aluno);
						}
					}
					aula.setAlunosAula(alunosAula);
				}

				return aula;
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

	@Override
	public Object update(Object obj, int id) {
		return null;
	}

	/**
	 * Irá atualizar uma aula
	 * @param aula - aula com nova lista de presença
	 * @param oldAlunosAula - lista de presença antiga
	 * @param id - id da aula
	 * @return - mensagem d resposta
	 */
	public String updateAulaPresenca(Aula aula, ArrayList<Aluno> oldAlunosAula, int id) {
		bd = new BD();
		if (bd.getConnection()) {
			String sql = "UPDATE tb_aula SET descricao_aula = ? WHERE id_aula = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, aula.getDescricao());
				bd.st.setInt(2, id);
				bd.st.executeUpdate();

				for (Aluno aluno : oldAlunosAula) {
					sql = "SELECT id_aluno_aula FROM tb_aluno_aula WHERE id_aluno = ? AND id_aula = ?";
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setInt(1, aluno.id);
					bd.st.setInt(2, id);
					bd.rs = bd.st.executeQuery();

					int idAlunoAula = 0;
					if (bd.rs.next()) {
						idAlunoAula = bd.rs.getInt("id_aluno_aula");

						sql = "DELETE FROM tb_aluno_aula WHERE id_aluno_aula = ?";
						bd.st = bd.con.prepareStatement(sql);
						bd.st.setInt(1, idAlunoAula);

						bd.st.executeUpdate();
					}
				}

				sql = "INSERT INTO tb_aluno_aula(id_aluno, id_aula) VALUES(?,?)";
				bd.st = bd.con.prepareStatement(sql);

				int count = 0;
				for (Aluno aluno : aula.getAlunosAula()) {
					bd.st.setInt(1, aluno.id);
					bd.st.setInt(2, id);
					
					bd.st.addBatch();

					count++;
					if (count % 100 == 0 || count == aula.getAlunosAula().size()) {
						bd.st.executeBatch();
					}
				}

				return "Aula atualizada";
			} catch (SQLException e) {
				return "Houve um erro ao atualizar aula";
			}
		} else {
			return "Houve um erro na conexão com o Banco de dados";
		}
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
