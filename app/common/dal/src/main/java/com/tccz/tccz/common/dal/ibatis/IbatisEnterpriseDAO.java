/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tccz.tccz.common.dal.daointerface.EnterpriseDAO;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.EnterpriseDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import com.tccz.tccz.dal.util.PageList;
import com.tccz.tccz.common.dal.dataobject.EnterpriseDO;
import java.util.Map;
import java.util.HashMap;
import com.tccz.tccz.dal.util.Paginator;

import java.util.HashMap;
import java.util.Map;

/**
 * An ibatis based implementation of dao interface <tt>com.tccz.tccz.common.dal.daointerface.EnterpriseDAO</tt>.
 *
 * This file is generated by <tt>paygw-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>paygw</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/enterprise.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>paygw-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 */
public class IbatisEnterpriseDAO extends SqlMapClientDaoSupport implements EnterpriseDAO {
	/**
	 *  Query DB table <tt>enterprise</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from enterprise where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return EnterpriseDO
	 *	@throws DataAccessException
	 */	 
    public EnterpriseDO getById(int id) throws DataAccessException {
        Integer param = new Integer(id);
        return (EnterpriseDO) getSqlMapClientTemplate().queryForObject("MS-ENTERPRISE-GET-BY-ID", param);

    }

	/**
	 *  Query DB table <tt>enterprise</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from enterprise where (institution_code = ?)</tt>
	 *
	 *	@param institutionCode
	 *	@return EnterpriseDO
	 *	@throws DataAccessException
	 */	 
    public EnterpriseDO getEnterpriseByCode(String institutionCode) throws DataAccessException {

        return (EnterpriseDO) getSqlMapClientTemplate().queryForObject("MS-ENTERPRISE-GET-ENTERPRISE-BY-CODE", institutionCode);

    }

	/**
	 *  Query DB table <tt>enterprise</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select e.* from enterprise</tt>
	 *
	 *	@param legalPersonIdCard
	 *	@return List<EnterpriseDO>
	 *	@throws DataAccessException
	 */	 
    public List<EnterpriseDO> getByLegalPersonIdCard(String legalPersonIdCard) throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-ENTERPRISE-GET-BY-LEGAL-PERSON-ID-CARD", legalPersonIdCard);

    }

	/**
	 *  Query DB table <tt>enterprise</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from enterprise</tt>
	 *
	 *	@param enterpriseName
	 *	@return List<EnterpriseDO>
	 *	@throws DataAccessException
	 */	 
    public List<EnterpriseDO> fuzzyQueryByName(String enterpriseName) throws DataAccessException {
	    Map param = new HashMap();

					        param.put("enterpriseName", enterpriseName);
					        return getSqlMapClientTemplate().queryForList("MS-ENTERPRISE-FUZZY-QUERY-BY-NAME", param);

    }

	/**
	 *  Query DB table <tt>enterprise</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from enterprise</tt>
	 *
	 *	@param enterpriseName
	 *	@param pageSize
	 *	@param pageNum
	 *	@return PageList
	 *	@throws DataAccessException
	 */	 
    public PageList fuzzyPageQueryByName(String enterpriseName, int pageSize, int pageNum) throws DataAccessException {
        Map param = new HashMap();

        param.put("enterpriseName", enterpriseName);
        param.put("pageSize", new Integer(pageSize));
        param.put("pageNum", new Integer(pageNum));

        Paginator paginator = new Paginator();
        paginator.setItemsPerPage(pageSize);
        paginator.setPage(pageNum / pageSize + 1);

        paginator.setItems(((Integer) getSqlMapClientTemplate().queryForObject("MS-TCCZ-ENTERPRISE-FUZZY-PAGE-QUERY-BY-NAME-COUNT-FOR-PAGING", param)).intValue());
        
        PageList  pageList = new PageList();
        pageList.setPaginator(paginator);
        
        if (paginator.getBeginIndex() <= paginator.getItems()) {
            param.put("startRow", new Integer(paginator.getBeginIndex()));
            param.put("endRow", new Integer(paginator.getEndIndex()));
            pageList.addAll(getSqlMapClientTemplate().queryForList("MS-ENTERPRISE-FUZZY-PAGE-QUERY-BY-NAME", param));
        }
        
        return pageList;
    }

	/**
	 *  Insert one <tt>EnterpriseDO</tt> object to DB table <tt>enterprise</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into enterprise(name,institution_code,account_number,create_time,modify_time) values (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)</tt>
	 *
	 *	@param enterprise
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(EnterpriseDO enterprise) throws DataAccessException {
    	if (enterprise == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-ENTERPRISE-INSERT", enterprise);

        return enterprise.getId();
    }

	/**
	 *  Delete records from DB table <tt>enterprise</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from enterprise where (institution_code = ?)</tt>
	 *
	 *	@param institutionCode
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(String institutionCode) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-ENTERPRISE-DELETE", institutionCode);
    }

	/**
	 *  Update DB table <tt>enterprise</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update enterprise set name=?, account_number=?, modify_time=CURRENT_TIMESTAMP where (institution_code = ?)</tt>
	 *
	 *	@param enterprise
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(EnterpriseDO enterprise) throws DataAccessException {
    	if (enterprise == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-ENTERPRISE-UPDATE", enterprise);
    }

}