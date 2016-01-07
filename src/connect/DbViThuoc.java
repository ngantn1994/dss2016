package connect;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Benh;
import model.ViThuoc;

public class DbViThuoc extends connect {
	public List<ViThuoc> loadTen(String keyWord) throws SQLException{
		List<ViThuoc> list = new ArrayList<ViThuoc>();
		connect con = new connect();
		con.ketNoi();
		String word = "N%"+keyWord+"%";
		word = word.toLowerCase();
		String command = "select * form Tbl_ViThuoc where Lower(TenViThuoc) like ?";
		PreparedStatement ps = connection.prepareStatement(command);
		ps.setString(1,word);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ViThuoc vt = new ViThuoc();
			vt.setMaViThuoc(rs.getInt(1));
			vt.setTenViThuoc(rs.getString("TenViThuoc"));
			vt.setChuTri(rs.getString("ChuTri"));
			vt.setKiengKy(rs.getString("KiengKy"));
			vt.setTinhChat(rs.getString("TinhChat"));
			vt.setSoLuotTruyCap(rs.getInt(6));
			
			list.add(vt);
		}
		return list;
	}
	
	public void updateLuotTruyCap(int id) throws SQLException{
		ViThuoc vt = new ViThuoc();
		connect con = new connect();
		con.ketNoi();
		
		String command1 = "select *from TBL_VITHUOC where MAVITHUOC = ?";
		PreparedStatement ps1 = connection.prepareStatement(command1);
		ps1.setInt(1, id);
		ResultSet rs1 = ps1.executeQuery();
		
		while(rs1.next()){
		
			vt.setMaViThuoc(rs1.getInt(1));
			vt.setTenViThuoc(rs1.getString("TenViThuoc"));
			vt.setChuTri(rs1.getString("ChuTri"));
			vt.setKiengKy(rs1.getString("KiengKy"));
			vt.setTinhChat(rs1.getString("TinhChat"));
			vt.setSoLuotTruyCap(rs1.getInt(6));
			
		}
		
		int count = vt.getSoLuotTruyCap();
		count = count++;
		String command2 = "update TBL_VITHUOC set SOLUONGTRUYCAP = ? where MAVITHUOC=? ";
		PreparedStatement ps2 = connection.prepareStatement(command2);
		ps2.setInt(1, count);
		ps2.setInt(2, id);
		
		int rs2 = 0;
		rs2 = ps2.executeUpdate();
		
	}
	
}
