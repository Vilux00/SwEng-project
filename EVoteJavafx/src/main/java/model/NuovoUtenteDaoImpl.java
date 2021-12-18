package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import org.apache.commons.codec.digest.DigestUtils;
import data.DbManager;

public class NuovoUtenteDaoImpl implements NuovoUtenteDao{
	@Override
	public boolean register(NuovoUtente n) {
		DbManager dbM = DbManager.getInstance();
		Connection c = dbM.open();
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO evoting.elettore VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, n.getCodF());
			ps.setString(2, n.getNome());
			ps.setString(3, n.getCognome());
			ps.setObject(4, n.getDataNascita());
			ps.setInt(5, Period.between(n.getDataNascita(), LocalDate.now()).getYears());
			ps.setString(6, n.getNazioneNascita());
			ps.setString(7, n.getPaeseNascita());
			ps.setString(8, n.getSesso() + "");
			ps.setString(9, DigestUtils.sha256Hex(n.getPassword()));
			ps.setString(10, n.getPrivilegio() + "");
			return ps.executeUpdate() > 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			dbM.close(c);
		}
	}
}
