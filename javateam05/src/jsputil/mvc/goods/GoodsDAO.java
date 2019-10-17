package jsputil.mvc.goods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import jsputil.mvc.mem.ZipCodeDAO;

public class GoodsDAO {
private static GoodsDAO instance = new GoodsDAO();
    
    public static GoodsDAO getInstance() {
        return instance;
    }
     
    private GoodsDAO() {
    }
    
    private Connection getConnection() throws Exception {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/pool01"); 
      return ds.getConnection();
    }
	
	// 아이디 존재 1 아이디 없으면 0 리턴 
	public int goodsDupChk(String code) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from goods where code = ? ";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = 1;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{ 
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(Exception e){}
		}
		return result;
	}
	
	public void goodsIns(GoodsVO goods){
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "insert into goods(code, name, price, color, regDate)" 
					  +"values(?,?,?,?,now())";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, goods.getCode());
			pstmt.setString(2, goods.getName());
			pstmt.setInt(3, goods.getPrice());
			pstmt.setString(4,goods.getColor());
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{ 
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(Exception e){}
		}
		
	}
	public GoodsVO getGoodsInfo(String code){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsVO goods = null;
		String query = "select * from goods where code = ?";
		
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while(rs.next()){
				goods =new GoodsVO();
				goods.setCode(rs.getString("code"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getInt("price"));
				goods.setColor(rs.getString("color"));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{ 
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(Exception e){}
		}
		
		return goods;
	}
	public List<GoodsVO> getGoodsList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "select * from goods";
		List <GoodsVO>goodsList = new ArrayList<GoodsVO>();
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()){
				GoodsVO goods = new GoodsVO();
				goods.setCode(rs.getString("code"));
				goods.setName(rs.getString("name"));
				goods.setPrice(rs.getInt("price"));
				goods.setColor(rs.getString("color"));
				goods.setRegDate(rs.getTimestamp("regDate"));
				//goods.setQuantity(rs.getInt("quantity"));
				goodsList.add(goods);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{ 
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(Exception e){}
		}
		return goodsList;
	}
	public void goodsUpt(GoodsVO goods){
		Connection con = null;
		PreparedStatement pstmt = null;
		String query = "update goods " +
						"set name = ?,"+
						" price= ?," +
						" color =  ? " +
						"where code = ? ";
		try{
			con = getConnection();
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, goods.getName());
			pstmt.setInt(2, goods.getPrice());
			pstmt.setString(3,goods.getColor());
			pstmt.setString(4, goods.getCode());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			try{ 
				if(pstmt != null) pstmt.close();
				if(con != null)con.close();
			}catch(Exception e){}
		}
	}
	public void goodsDel(String code){
		
	}	
	
} 
