package app.services;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import app.models.Aula;

public class AulaService implements BaseService {
	private BD bd = null;

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
				
				return "Aula lançada";

			} catch (SQLException e) {
				return "Houve um erro ao Lançar Aula";
			}
		} else {
			return "Houve um erro na conexão com o banco de dados";
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
