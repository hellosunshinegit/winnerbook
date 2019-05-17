package com.winnerbook.base.frame.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.winnerbook.base.common.GlobalConfigure;
import com.winnerbook.base.common.util.DateTimeUtils;
import com.winnerbook.base.frame.dao.BaseDAO;
import com.winnerbook.base.frame.dao.JdbcDao;

@Repository("jdbcDao")
public class JdbcDaoImpl extends BaseDAO implements JdbcDao{
	
	@Override
	public List queryData(String sql) {
		ArrayList resultList = null;
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = this.sqlSession.getConnection();
			stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功'
            resultList = FromResultToArrayList(rs,"","LIST");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
		}
		
		return resultList;
	}
	
	public ArrayList FromResultToArrayList(ResultSet resultSet,String strDefalut,String transType) {
		ArrayList dataList = new ArrayList();
		
		if (resultSet == null)
			return dataList;
		try {

			// get the strut of the ResultSet
			ResultSetMetaData resultmd = resultSet.getMetaData();
			if (resultmd == null)
				return dataList;

			// get the total count of column
			int colCount = resultmd.getColumnCount();

			// deal with all data one by one		
			while (resultSet.next()) {
				Object row = null;
				if ("MAP".equalsIgnoreCase(transType)){
					row = new HashMap();
				}else{
					row = new ArrayList(colCount);
				}				
				
				for (int i = 1; i <= colCount; i++) {
					// get type of column
					int type  = resultmd.getColumnType(i);
					String colName = resultmd.getColumnName(i);
					// get data
					if ("MAP".equalsIgnoreCase(transType)){
						((HashMap)row).put(colName.toUpperCase(), ObjectToString(getValue(resultSet, i, type),
							strDefalut));
					}else{
						((ArrayList)row).add(ObjectToString(getValue(resultSet, i, type),
							strDefalut));
					}
				}
				dataList.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return dataList;
	}
	
	private Object getValue(final ResultSet rs, int colNum, int type)
			throws SQLException {
		switch (type) {
			case Types.ARRAY:
			case Types.BLOB:
			case Types.CLOB:
			case Types.DISTINCT:
			case Types.LONGVARBINARY:
			case Types.VARBINARY:
			case Types.BINARY:
			case Types.REF:
			case Types.STRUCT:
				return null;
			default: {
				Object value = rs.getObject(colNum);
				if (rs.wasNull() || (value == null))
					return null;
				else
					return value;
			}
		}
	}
	
	/**
	 * change the object to String type
	 * 
	 * @param obj
	 * @param strDefalut
	 * @return
	 */
	private String ObjectToString(Object obj, String strDefalut) {
		if (obj == null)
			return strDefalut;

		String strResult = strDefalut;
		// date type java.sql.Date
		if (obj instanceof java.sql.Date) {
			strResult = DateTimeUtils.DateToString((java.sql.Date) obj,
					GlobalConfigure.CURR_JAVA_DATE_FORMATTER, strDefalut);
			// date type java.sql.T
		} else if (obj instanceof Timestamp) {
			strResult = DateTimeUtils.TimestampToString(
					(java.sql.Timestamp) obj,
					GlobalConfigure.CURR_JAVA_DATETIME_FORMATTER, strDefalut);
			// numeral type
		} else if (obj instanceof java.math.BigDecimal) {
			strResult = ((java.math.BigDecimal) obj).toString();
		} else {
			strResult = obj.toString();
		}

		if (strResult != null)
			strResult = strResult.trim();
		return strResult;
	}
	
}
