package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import data.DbManager;

public class VotoDaoImpl implements VotoDao{
	@Override
	public boolean inserisciVotoReferendum(Voto v) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.voto(r_quesito, id_sessione) VALUES(?, ?)");
			String rQuesito;
			if(v.getR_quesito() == null) rQuesito = "X";
			else if(v.getR_quesito() == true) rQuesito = "S";
			else rQuesito = "N";
			ps.setString(1, rQuesito);
			ps.setInt(2, v.getS().getId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			return false;
		}finally {
			dbM.close(c);
		}
	}
}
