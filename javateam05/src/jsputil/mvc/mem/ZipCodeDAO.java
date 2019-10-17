package jsputil.mvc.mem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ZipCodeDAO {

	public static final int PAGE_SET_SIZE = 5; // page set Size
	public static final int PAGE_LIST_SIZE = 10; // page list size
	
	private static ZipCodeDAO instance = new ZipCodeDAO();
    
    public static ZipCodeDAO getInstance() {
        return instance;
    }
     
    private ZipCodeDAO() {
    }
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/pool01"); 
      return ds.getConnection();
    }

	public List<ZipCodeVO> search(String sido, String doro_name, int pageNum) {
		int stRow = (pageNum - 1) * PAGE_LIST_SIZE;//
		int rowCnt = PAGE_LIST_SIZE;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ZipCodeVO> listZipcode = null;
		System.out.println(sido+":"+doro_name+":"+pageNum);
		String query = "select * from zipcode where sido = ? and doro_name like ? limit ?, ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, sido);
			pstmt.setString(2, "%" +doro_name + "%");
			pstmt.setInt(3, stRow);
			pstmt.setInt(4, rowCnt);
			rs = pstmt.executeQuery();
			listZipcode = new ArrayList<ZipCodeVO>();
			while (rs.next()) {
				ZipCodeVO zipcode = new ZipCodeVO();
				zipcode.setZip_code(rs.getString("zip_code"));
				zipcode.setSido(rs.getString("sido"));
				zipcode.setGugun(rs.getString("gugun"));
				zipcode.setDoro_name(rs.getString("doro_name"));
				zipcode.setBuild_num1(rs.getString("build_num1"));
				zipcode.setBuild_num2(rs.getString("build_num2"));
				listZipcode.add(zipcode);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) 	pstmt.close();
				if (con != null) con.close();
			} catch (Exception e) {	}
		}
		return listZipcode;
	}

	public int getZipCount(String sido, String doro_name)
			throws Exception {
		String query = 
"select count(*) from zipcode where sido = ? and doro_name like ?";
		int dataCnt = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, sido);
			pstmt.setString(2, doro_name + "%");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dataCnt = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
			}
		}

		return dataCnt;
	}

}
