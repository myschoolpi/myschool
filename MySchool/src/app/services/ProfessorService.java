package app.services;

import java.sql.SQLException;

import app.models.Aluno;
import app.models.Professor;

public class ProfessorService implements BaseService {
	private Professor prof = null;

	@Override
	public String create(Object obj) {
		BD bd = new BD();
		if (bd.getConnection()) {
			String sql = "INSERT INTO tb_professor"
					+ "(nome_professor, endereco_professor, email_professor, telefone_professor, cpf_professor, rg_professor, senha) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, ((Professor) obj).getNome());
				bd.st.setString(2, ((Professor) obj).getEndereco());
				bd.st.setString(3, ((Professor) obj).getEmail());
				bd.st.setString(4, ((Professor) obj).getTelefone());
				bd.st.setString(5, ((Professor) obj).getCpf());
				bd.st.setString(6, ((Professor) obj).getRg());
				bd.st.setString(7, ((Professor) obj).getSenha());

				int n = bd.st.executeUpdate();

				if (n == 1)
					return "Professor criado Com sucesso!";
				else
					return "Houve um erro ao cadastrar o Professor";
			} catch (SQLException e) {
				return "Houve um erro ao cadastrar o Professor";
			}
		} else {
			return "Houve um erro na conexão com o Banco de dados";
		}
	}

	@Override
	public Professor update(Object obj, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor getOne(int id) {
		BD bd = new BD();
		if (bd.getConnection()) {
			String sql = "SELECT * FROM tb_professor WHERE tb_professor.id_professor = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);

				bd.rs = bd.st.executeQuery();

				while (bd.rs.next()) {
					prof = new Professor();

					prof.id = bd.rs.getInt("id_professor");
					prof.setNome(bd.rs.getString("nome_professor"));
					prof.setEmail(bd.rs.getString("email_professor"));
					prof.setCpf(bd.rs.getString("cpf_professor"));
					prof.setRg(bd.rs.getString("rg_professor"));
					prof.setTelefone(bd.rs.getString("telefone_professor"));
					prof.setEndereco(bd.rs.getString("endereco_professor"));
				}
				return prof;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public Professor[] getMany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Professor professorLogin(String email, String senha) {
		BD bd = new BD();
		if (bd.getConnection()) {
			String sql = "SELECT * FROM tb_professor WHERE tb_professor.email_professor = ? AND tb_professor.senha = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, email);
				bd.st.setString(2, senha);

				bd.rs = bd.st.executeQuery();

				while (bd.rs.next()) {
					prof = new Professor();

					prof.id = bd.rs.getInt("id_professor");
					prof.setNome(bd.rs.getString("nome_professor"));
					prof.setEmail(bd.rs.getString("email_professor"));
					prof.setCpf(bd.rs.getString("cpf_professor"));
					prof.setRg(bd.rs.getString("rg_professor"));
					prof.setTelefone(bd.rs.getString("telefone_professor"));
					prof.setEndereco(bd.rs.getString("endereco_professor"));
				}
				return prof;
			} catch (SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
