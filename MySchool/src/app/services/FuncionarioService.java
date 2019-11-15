package app.services;

import app.models.Funcionario;
import app.models.Cargo;
import java.sql.SQLException;
import java.util.ArrayList;


public class FuncionarioService implements BaseService {
	private Funcionario func = null;
	private Cargo cargo = null;

	@Override
	public String create(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario update(Object obj, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Funcionario getOne(int id) {
		BD bd = new BD();
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_funcionarios " +
					"INNER JOIN tb_cargo ON tb_funcionarios.id_cargo = tb_cargo.id_cargo " +
					"WHERE tb_funcionarios.id_funcionario = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setInt(1, id);
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					func = new Funcionario();
					cargo = new Cargo();
					
					func.setNome(bd.rs.getString("nome_funcionarios"));
					func.setEmail(bd.rs.getString("email_funcionario"));
					func.setCpf(bd.rs.getString("cpf_funcionario"));
					
					cargo.id = bd.rs.getInt("id_cargo");
					cargo.setNome(bd.rs.getString("nome_cargo"));
					func.setCargo(cargo);
				}
				return func;
			} catch(SQLException e) {
				return null;
			}
		} else {
			return null;
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
	
	public Funcionario funcionarioLogin(String email, String senha) {
		BD bd = new BD();
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_funcionarios " +
					"INNER JOIN tb_cargo ON tb_funcionarios.id_cargo = tb_cargo.id_cargo " +
					"WHERE tb_funcionarios.email_funcionario = ? AND tb_funcionarios.senha = ?";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, email);
				bd.st.setString(2, senha);
				
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					func = new Funcionario();
					cargo = new Cargo();
					
					func.id = bd.rs.getInt("id_funcionario");
					func.setNome(bd.rs.getString("nome_funcionarios"));
					func.setEmail(bd.rs.getString("email_funcionario"));
					func.setCpf(bd.rs.getString("cpf_funcionario"));
					
					cargo.id = bd.rs.getInt("id_cargo");
					cargo.setNome(bd.rs.getString("nome_cargo"));
					func.setCargo(cargo);
				}
				return func;
			} catch(SQLException e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
