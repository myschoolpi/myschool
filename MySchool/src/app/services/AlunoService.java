package app.services;
import java.sql.SQLException;

import app.models.Aluno;

public class AlunoService implements BaseService {
	private Aluno aluno = null;

	@Override
	public Aluno create(Object obj) {
		// TODO Auto-generated method stub
		return null;
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
			}
		} else {
			return null;
		}
	}

	@Override
	public Aluno[] getMany() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Aluno alunoLogin(String email, String senha) {
		BD bd = new BD();
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
			}
		} else {
			return null;
		}
	}

	
}
