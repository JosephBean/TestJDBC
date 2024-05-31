package test4;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection openDB(String URL, String USER, String PASSWORD) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			System.out.println("JDBC Connection Open!");
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean 테이블생성(Connection conn, String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			pstmt.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<DbTable> 데이터가져오기(Connection conn, String sql) {
		List<DbTable> list = new ArrayList<DbTable>();
		DbTable data;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				data = new DbTable();

				// 데이터를 매핑하시오.
				data.setEmpno(rs.getString("empno"));
				data.setName(rs.getString("name"));
				data.setEmail(rs.getString("email"));
				data.setDeptname(rs.getString("deptname"));
				data.setPosition(rs.getString("position"));
				data.setPhone(rs.getString("phone"));
				data.setAge(rs.getInt("age"));
				data.setIsused2(rs.getString("isused2"));
				data.setCreatedate(rs.getString("createdate"));
				data.setUpdatedate(rs.getString("updatedate"));

				list.add(data);
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean 데이터입력하기(Connection conn, String sql, DbTable data) {
		try {
			pstmt = conn.prepareStatement(sql);

			// 데이터를 매핑하시오.
			pstmt.setString(1, data.getEmpno());
			pstmt.setString(2, data.getName());
			pstmt.setString(3, data.getEmail());
			pstmt.setString(4, data.getDeptname());
			pstmt.setString(5, data.getPosition());
			pstmt.setString(6, data.getPhone());
			pstmt.setInt(7, data.getAge());
			pstmt.setBoolean(8, data.isIsused());
			pstmt.setString(9, data.getCreatedate());
			pstmt.setString(10, data.getUpdatedate());

			int state = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			if(state == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean 데이터수정하기(Connection conn, String sql, DbTable data) {
		try {
			pstmt = conn.prepareStatement(sql);

			// 데이터를 매핑하시오.
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getDeptname());
			pstmt.setString(3, data.getPhone());
			pstmt.setBoolean(4, data.isIsused());
			pstmt.setString(5, data.getUpdatedate());
			pstmt.setString(6, data.getEmpno());

			int state = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			if(state == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public boolean 데이터삭제하기(Connection conn, String sql, DbTable data) {
		try {
			pstmt = conn.prepareStatement(sql);

			// 데이터를 매핑하시오.
			pstmt.setString(1, data.getUpdatedate());
			pstmt.setBoolean(2, data.isIsused());
			pstmt.setString(3, data.getEmpno());

			int state = pstmt.executeUpdate();
			pstmt.close();
			conn.commit();
			if(state == 1) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
