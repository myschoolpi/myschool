package app.services;
import java.sql.SQLException;
import java.util.ArrayList;

import app.models.Aluno;
import app.models.User;

public class AlunoService implements BaseService {
	private Aluno aluno = null;
	private BD bd = null;

	@Override
	public String create(Object obj) {
		bd = new BD();
		if(bd.getConnection()) {
			String sql = "INSERT INTO tb_aluno(nome_aluno, endereco_aluno, email_aluno, cpf_aluno, rg_aluno, senha)" +
					"VALUES(?, ?, ?, ?, ?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, ((Aluno) obj).getNome());
				bd.st.setString(2, ((Aluno) obj).getEndereco());
				bd.st.setString(3, ((Aluno) obj).getEmail());
				bd.st.setString(4, ((Aluno) obj).getCpf());
				bd.st.setString(5, ((Aluno) obj).getRg());
				bd.st.setString(6, ((Aluno) obj).getSenha());
				
				int n = bd.st.executeUpdate();
				
				if(n == 1)
					return "Aluno criado Com sucesso!";
				else
					return "Houve um erro ao cadastrar o Aluno";
			} catch(SQLException e) {
				return "Houve um erro ao cadastrar o Aluno";
			} finally {
				bd.close();
			}
		} else {
			return "Houve um erro na conex�o com o Banco de dados";
		}
		
	}

	@Override
	public Aluno update(Object obj, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno getOne(int id) {
		BD bd = new BD();
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_aluno WHERE tb_aluno.id_aluno = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					aluno = new Aluno();
					
					aluno.setNome(bd.rs.getString("nome_aluno"));
					aluno.setEmail(bd.rs.getString("email_aluno"));
					aluno.setCpf(bd.rs.getString("cpf_aluno"));
					aluno.setRg(bd.rs.getString("rg_aluno"));
					aluno.setEndereco(bd.rs.getString("endereco_aluno"));
				}
				return aluno;
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
	public ArrayList<Object> getMany() {
		bd = new BD();
		ArrayList<Object> listaAlunos = new ArrayList<Object>();
		
		if(bd.getConnection()) {
			String sql  = "SELECT * FROM tb_aluno";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					aluno = new Aluno();
					
					aluno.id = bd.rs.getInt("id_aluno");
					aluno.setNome(bd.rs.getString("nome_aluno"));
					aluno.setEndereco(bd.rs.getString("endereco_aluno"));
					aluno.setEmail(bd.rs.getString("email_aluno"));
					aluno.setCpf(bd.rs.getString("cpf_aluno"));
					aluno.setRg(bd.rs.getString("rg_aluno"));
					
					listaAlunos.add(aluno);
				}
				
				return listaAlunos;
			} catch (SQLException e) {
				return null;
			} finally {
				bd.close();
			}
		} else {
			return null;
		}
		
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * Ir� realizar uma consulta na tabela de alunos, como filtro o email e senha
	 * @param email - email do aluno
	 * @param senha - senha do aluno
	 * @return - null ou o Aluno est� se logando
	 */
	public Aluno alunoLogin(String email, String senha) {
		bd = new BD();
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_aluno WHERE tb_aluno.email_aluno = ? AND tb_aluno.senha = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, email);
				bd.st.setString(2, senha);
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					aluno = new Aluno();
					
					aluno.id = bd.rs.getInt("id_aluno");
					aluno.setNome(bd.rs.getString("nome_aluno"));
					aluno.setEmail(bd.rs.getString("email_aluno"));
					aluno.setCpf(bd.rs.getString("cpf_aluno"));
					aluno.setRg(bd.rs.getString("rg_aluno"));
					aluno.setEndereco(bd.rs.getString("endereco_aluno"));
				}
				return aluno;
			} catch(SQLException e) {
				return null;
			} finally {
				bd.close();
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Ir� realizar uma consulta no banco de dados,
	 * puxando todos os alunos de uma determiada turma
	 * @param idTurma - id da turma
	 * @return - lista de alunos da turma
	 */
	public ArrayList<Aluno> getAlunosTurma(int idTurma) {
		bd = new BD();
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_aluno a INNER JOIN tb_aluno_turma t " +
					"ON a.id_aluno = t.id_aluno "+
					"WHERE id_turma = " + idTurma;
			ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					aluno = new Aluno();
					
					aluno.id = bd.rs.getInt("id_aluno");
					aluno.setNome(bd.rs.getString("nome_aluno"));
					
					listaAlunos.add(aluno);
				}
				
				return listaAlunos;
				
			} catch(SQLException e) {
				return null;
			} finally {
				bd.close();
			}
		} else {
			return null;
		}
	}

	
}
