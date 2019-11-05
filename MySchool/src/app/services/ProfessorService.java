package app.services;
import java.sql.SQLException;

import app.models.Professor;

public class ProfessorService implements BaseService {
	private Professor prof = null;

	@Override
	public Professor create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor update(Object obj, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor getOne(int id) {
			BD bd = new BD();
			if(bd.getConnection()) {
				String sql = "SELECT * FROM tb_professor WHERE tb_professor.id_professor = ?";
				try {
					bd.st = bd.con.prepareStatement(sql);
					bd.st.setInt(1, id);
					
					bd.rs = bd.st.executeQuery();
					
					while(bd.rs.next()) {
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
				} catch(SQLException e) {
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
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_professor WHERE tb_professor.email_professor = ? AND tb_professor.senha = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, email);
				bd.st.setString(2, senha);
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
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
			} catch(SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}


}
