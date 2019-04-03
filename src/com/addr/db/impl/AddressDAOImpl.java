package com.addr.db.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.addr.db.AddressDAO;

import db.DBCon;

public class AddressDAOImpl implements AddressDAO {
	private static final String INSERT_ADDRESS = "insert into address"
			+ " values(seq_ad_num.nextval,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override
	public int insertAddressList(List<Map<String, String>> addrList) {
		int cnt = 0;
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_ADDRESS);
			for (Map<String, String> addr : addrList) {
				ps.setString(1, addr.get("ad_code"));
				ps.setString(2, addr.get("ad_sido"));
				ps.setString(3, addr.get("ad_gugun"));
				ps.setString(4, addr.get("ad_dong"));
				ps.setString(5, addr.get("ad_lee"));
				ps.setString(6, addr.get("ad_bunji"));
				ps.setString(7, addr.get("ad_ho"));
				ps.setString(8, addr.get("ad_roadcode"));
				ps.setString(9, addr.get("ad_isbase"));
				ps.setString(10, addr.get("ad_orgnum"));
				ps.setString(11, addr.get("ad_subnum"));
				ps.setString(12, addr.get("ad_jinum"));
				ps.addBatch();
				//숫자를 머리속에 기억시키고, 있는 것임.
				//아직은 oracle에서 하는 것이 아니라, JVM에서만 하는 것이야.
				// 사실 엄밀히 말하면 두 경계선이지만, ... ㅋㅋ
				// 모든 쿼리문을 다 써놓고 있는 것이라고 생각하면 될 것 같다. 
			}
			int[] resultCnts = ps.executeBatch();
			for(int resultCnt : resultCnts) {
				if(resultCnt==-2) {
					cnt++;
				}
			}
			return cnt;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.conClose();
		}
		return 0;
	}

}
