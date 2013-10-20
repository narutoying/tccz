/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tccz.tccz.common.dal.daointerface.FloatingLoanDAO;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.FloatingLoanDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Date;
import com.tccz.tccz.dal.util.PageList;
import com.tccz.tccz.common.dal.dataobject.FloatingLoanDO;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import com.tccz.tccz.dal.util.Paginator;

import java.util.HashMap;
import java.util.Map;

/**
 * An ibatis based implementation of dao interface <tt>com.tccz.tccz.common.dal.daointerface.FloatingLoanDAO</tt>.
 *
 * This file is generated by <tt>paygw-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>paygw</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/floating_loan.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>paygw-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 */
public class IbatisFloatingLoanDAO extends SqlMapClientDaoSupport implements FloatingLoanDAO {
	/**
	 *  Query DB table <tt>floating_loan</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from floating_loan where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return FloatingLoanDO
	 *	@throws DataAccessException
	 */	 
    public FloatingLoanDO getById(int id) throws DataAccessException {
        Integer param = new Integer(id);
        return (FloatingLoanDO) getSqlMapClientTemplate().queryForObject("MS-FLOATING-LOAN-GET-BY-ID", param);

    }

	/**
	 *  Query DB table <tt>floating_loan</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from floating_loan</tt>
	 *
	 *	@return List<FloatingLoanDO>
	 *	@throws DataAccessException
	 */	 
    public List<FloatingLoanDO> getAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-FLOATING-LOAN-GET-ALL", null);

    }

	/**
	 *  Insert one <tt>FloatingLoanDO</tt> object to DB table <tt>floating_loan</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into floating_loan(loaner_id,biz_side_type,amount,release_date,create_time,modify_time,expire_date) values (?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?)</tt>
	 *
	 *	@param floatingLoan
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(FloatingLoanDO floatingLoan) throws DataAccessException {
    	if (floatingLoan == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-FLOATING-LOAN-INSERT", floatingLoan);

        return floatingLoan.getId();
    }

	/**
	 *  Delete records from DB table <tt>floating_loan</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from floating_loan where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(int id) throws DataAccessException {
        Integer param = new Integer(id);

        return getSqlMapClientTemplate().delete("MS-FLOATING-LOAN-DELETE", param);
    }

	/**
	 *  Update DB table <tt>floating_loan</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update floating_loan set loaner_id=?, biz_side_type=?, amount=?, release_date=?, expire_date=?, modify_time=CURRENT_TIMESTAMP where (id = ?)</tt>
	 *
	 *	@param floatingLoan
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(FloatingLoanDO floatingLoan) throws DataAccessException {
    	if (floatingLoan == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-FLOATING-LOAN-UPDATE", floatingLoan);
    }

	/**
	 *  Query DB table <tt>floating_loan</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from floating_loan</tt>
	 *
	 *	@param loanerName
	 *	@param bizSideType
	 *	@param showExpire
	 *	@param expireStart
	 *	@param expireEnd
	 *	@param pageSize
	 *	@param pageNum
	 *	@return PageList
	 *	@throws DataAccessException
	 */	 
    public PageList getByCondition(String loanerName, String bizSideType, Boolean showExpire, Date expireStart, Date expireEnd, int pageSize, int pageNum) throws DataAccessException {
        Map param = new HashMap();

        param.put("loanerName", loanerName);
        param.put("bizSideType", bizSideType);
        param.put("showExpire", showExpire);
        param.put("expireStart", expireStart);
        param.put("expireEnd", expireEnd);
        param.put("pageSize", new Integer(pageSize));
        param.put("pageNum", new Integer(pageNum));

        Paginator paginator = new Paginator();
        paginator.setItemsPerPage(pageSize);
        paginator.setPage(pageNum / pageSize + 1);

        paginator.setItems(((Integer) getSqlMapClientTemplate().queryForObject("MS-TCCZ-FLOATING-LOAN-GET-BY-CONDITION-COUNT-FOR-PAGING", param)).intValue());
        
        PageList  pageList = new PageList();
        pageList.setPaginator(paginator);
        
        if (paginator.getBeginIndex() <= paginator.getItems()) {
            param.put("startRow", new Integer(paginator.getBeginIndex()));
            param.put("endRow", new Integer(paginator.getEndIndex()));
            pageList.addAll(getSqlMapClientTemplate().queryForList("MS-FLOATING-LOAN-GET-BY-CONDITION", param));
        }
        
        return pageList;
    }

	/**
	 *  Query DB table <tt>floating_loan</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from floating_loan</tt>
	 *
	 *	@param loanerId
	 *	@param bizSideType
	 *	@param expireStart
	 *	@param expireEnd
	 *	@return List<FloatingLoanDO>
	 *	@throws DataAccessException
	 */	 
    public List<FloatingLoanDO> getByExpireDate(String loanerId, String bizSideType, Date expireStart, Date expireEnd) throws DataAccessException {
        Map param = new HashMap();

        param.put("loanerId", loanerId);
        param.put("bizSideType", bizSideType);
        param.put("expireStart", expireStart);
        param.put("expireEnd", expireEnd);

        return getSqlMapClientTemplate().queryForList("MS-FLOATING-LOAN-GET-BY-EXPIRE-DATE", param);

    }

}