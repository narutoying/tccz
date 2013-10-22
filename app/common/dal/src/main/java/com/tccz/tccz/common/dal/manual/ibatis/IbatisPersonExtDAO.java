/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.manual.ibatis;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.util.CollectionUtils;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.tccz.tccz.common.dal.dataobject.PersonDO;
import com.tccz.tccz.common.dal.manual.daointerface.PersonExtDAO;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: IbatisPersonExtDAO.java, v 0.1 2013-10-22 下午1:20:54
 *          narutoying09@gmail.com Exp $
 */
public class IbatisPersonExtDAO extends SqlMapClientDaoSupport implements
		PersonExtDAO {

	/**
	 * @see com.tccz.tccz.common.dal.manual.daointerface.PersonExtDAO#batchInsert(java.util.List)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void batchInsert(final List<PersonDO> dataList) {
		if (!CollectionUtils.isEmpty(dataList)) {
			this.getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
				public Object doInSqlMapClient(SqlMapExecutor executor)
						throws SQLException {
					executor.startBatch();
					for (PersonDO data : dataList) {
						executor.insert("MS-PERSON-INSERT", data);
					}
					executor.executeBatch();
					return null;
				}
			});
		}
	}

}
