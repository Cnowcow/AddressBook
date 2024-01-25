package com.spring.biz.addressbook.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.biz.addressbook.AddressbookDTO;
import com.spring.biz.common.JDBCUtil;

@Repository("addressbookDAO")
public class AddressbookDAO {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	private final String INSERT = "insert into book(name, email, pass, comdept, birth, tel, memo) values(?,?,?,?,?,?,?)";
	private final String UPDATE = "update book set  name=?, email=?, tel = ?, comdept=? ,memo=? where id=? ";
	private final String DELETE = "delete from book where id=?";
	private final String LIST = "select id, name, email, comdept, birth, tel, memo from book order by id";
	private final String SELECT = "select id, name, email, pass,comdept, birth,tel,memo from book where id=";
	private final String EXIST = "select name, email from book where email=? and pass=?";
	private final String SEARCH_NAME = "select id, name, email, comdept, birth, tel, memo from book where name like ?";
	private final String SEARCH_TEL = "select id, name, email, comdept, birth, tel, memo from book where tel like ?";

	public List<AddressbookDTO> selectList(){
		List<AddressbookDTO> List = new ArrayList();
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(LIST);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				AddressbookDTO addressbook = new AddressbookDTO();
				addressbook.setId(rs.getInt("id"));
				addressbook.setName(rs.getString("name"));
				addressbook.setEmail(rs.getString("email"));
				addressbook.setComdept(rs.getString("comdept"));
				addressbook.setBirth(rs.getString("birth"));
				addressbook.setTel(rs.getString("tel"));
				addressbook.setMemo(rs.getString("memo"));

				List.add(addressbook);
			}
		}catch(Exception e) {
			
		}finally {
	        JDBCUtil.close(rs, pstmt, conn);
		}
		return List;
	}		
	
	
	public void insert(AddressbookDTO member) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPass());
			pstmt.setString(4, member.getComdept());
			pstmt.setString(5, member.getBirth());
			pstmt.setString(6, member.getTel());
			pstmt.setString(7, member.getMemo());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	

	public void delete(AddressbookDTO member) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, member.getId());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	
	public void update(AddressbookDTO address) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, address.getName());
			pstmt.setString(2, address.getEmail());
			pstmt.setString(3, address.getTel());
			pstmt.setString(4, address.getComdept());
			pstmt.setString(5, address.getMemo());
			pstmt.setInt(6, address.getId());
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	
	public AddressbookDTO selectOne(AddressbookDTO address) {
		AddressbookDTO addressbook = null;

		try {
			conn = JDBCUtil.getConnection();
		      pstmt = conn.prepareStatement(SELECT);
		      pstmt.setInt(1, address.getId());
		      rs = pstmt.executeQuery();
				
		      if(rs.next()) {
		    	  addressbook = new AddressbookDTO();
		    	  addressbook.setId(rs.getInt("id")); //테이블의 필드명
		    	  addressbook.setName(rs.getString("name"));
		    	  addressbook.setEmail(rs.getString("email"));
		    	  addressbook.setPass(rs.getString("pass"));
		    	  addressbook.setComdept(rs.getString("comdept"));
		    	  addressbook.setBirth(rs.getString("birth"));
		    	  addressbook.setTel(rs.getString("tel"));
		    	  addressbook.setMemo(rs.getString("memo"));
		      }else {
		            throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
		      }
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
	        JDBCUtil.close(rs, pstmt, conn);
		}
			return addressbook;
	}
	
	
	public AddressbookDTO exist(String email, String password) {
	    try {
	        conn = JDBCUtil.getConnection();
	        pstmt = conn.prepareStatement(EXIST);
	        pstmt.setString(1, email);
	        pstmt.setString(2, password);

	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            AddressbookDTO user = new AddressbookDTO();
	            user.setId(rs.getInt("id"));
	            user.setName(rs.getString("name"));
	            user.setEmail(rs.getString("email"));
	            return user;
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null; // 예외 발생 시 null 반환
	    } finally {
	        JDBCUtil.close(rs, pstmt, conn);
	    }
	}
	
	
	
	public ArrayList<AddressbookDTO> searchName(String name) {
		ArrayList<AddressbookDTO> result = null;
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SEARCH_NAME);
			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();
			
			result = new ArrayList<AddressbookDTO>();
			
			while (rs.next()) {
				AddressbookDTO addressbook = new AddressbookDTO();
				addressbook.setId(rs.getInt("id"));
				addressbook.setName(rs.getString("name"));
				addressbook.setEmail(rs.getString("email"));
				addressbook.setComdept(rs.getString("comdept"));
				addressbook.setBirth(rs.getString("birth"));
				addressbook.setTel(rs.getString("tel"));
				addressbook.setMemo(rs.getString("memo"));
				
				result.add(addressbook);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return result;
	}
	
	
	public ArrayList<AddressbookDTO> searchTel(String tel) {
        ArrayList<AddressbookDTO> result = null;

        try {
            conn = JDBCUtil.getConnection();
            pstmt = conn.prepareStatement(SEARCH_TEL);
            pstmt.setString(1, "%" + tel + "%");
            rs = pstmt.executeQuery();

            result = new ArrayList<AddressbookDTO>();

            while (rs.next()) {
                AddressbookDTO addressbook = new AddressbookDTO();
                addressbook.setId(rs.getInt("id"));
                addressbook.setName(rs.getString("name"));
                addressbook.setEmail(rs.getString("email"));
                addressbook.setComdept(rs.getString("comdept"));
                addressbook.setBirth(rs.getString("birth"));
                addressbook.setTel(rs.getString("tel"));
                addressbook.setMemo(rs.getString("memo"));

                result.add(addressbook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn);
        }

        return result;
    }
	
}
