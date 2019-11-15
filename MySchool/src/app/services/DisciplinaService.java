package app.services;

import java.sql.SQLException;
import java.util.ArrayList;

import app.models.Disciplina;

public class DisciplinaService implements BaseService {
	private BD bd = null;
	private Disciplina d = null;
	
	@Override
	public String create(Object obj) {
		BD bd = new BD();
		if(bd.getConnection()) {
			String sql = "INSERT INTO tb_disciplina(descricao_disciplina, nome_disciplina, carga_horaria) " +
					"VALUES(?, ?, ?)";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.st.setString(1, ((Disciplina) obj).getDescricao());
				bd.st.setString(2, ((Disciplina) obj).getNome());
				bd.st.setInt(3, ((Disciplina) obj).getCargaHoraria());
				
				int n = bd.st.executeUpdate();
				
				if(n==1)
					return "Disciplina criada com sucesso!";
				else
					return "Houve um erro ao cadastrar a Disciplina";
			} catch(SQLException e) {
				return "Houve um erro ao cadastrar a Disciplina";
			}
		} else {			
			return "Houve um erro na conexão com o Banco de Dados";
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
		bd = new BD();
		ArrayList<Object> listaDisciplinas = new ArrayList<Object>();
		
		if(bd.getConnection()) {
			String sql = "SELECT * FROM tb_disciplina";
			try {
				bd.st = bd.con.prepareStatement(sql);
				bd.rs = bd.st.executeQuery();
				
				while(bd.rs.next()) {
					d = new Disciplina();
					
					d.id = bd.rs.getInt("id_disciplina");
					d.setNome(bd.rs.getString("nome_disciplina"));
					d.setDescricao(bd.rs.getString("descricao_disciplina"));
					d.setCargaHoraria(bd.rs.getInt("carga_horaria"));
					
					listaDisciplinas.add(d);
				}
				
				return listaDisciplinas;
			} catch (SQLException e) {
				return null;
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

}
