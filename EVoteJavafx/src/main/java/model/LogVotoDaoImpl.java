package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import data.DbManager;

public class LogVotoDaoImpl implements LogVotoDao{

	@Override
	public boolean inserisciLog(LogVoto l) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.log_voto VALUES(?, ?, ?)");
			ps.setInt(1, l.getIdSessione());
			ps.setString(2, l.getCodFiscale());
			ps.setObject(3, LocalDateTime.now());
			return ps.executeUpdate() > 0;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			dbM.close(c);
		}
	}

}
