/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tccz.tccz.common.dal.daointerface.DiscountDAO;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.DiscountDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import com.tccz.tccz.dal.util.PageList;
import java.util.Date;
import com.tccz.tccz.common.dal.dataobject.DiscountDO;
import java.util.Map;
import java.util.HashMap;
import com.tccz.tccz.dal.util.Paginator;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

/**
 * An ibatis based implementation of dao interface <tt>com.tccz.tccz.common.dal.daointerface.DiscountDAO</tt>.
 *
 * This file is generated by <tt>paygw-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>paygw</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/discount.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>paygw-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 */
public class IbatisDiscountDAO extends SqlMapClientDaoSupport implements DiscountDAO {
	/**
	 *  Query DB table <tt>discount</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from discount where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return DiscountDO
	 *	@throws DataAccessException
	 */	 
    public DiscountDO getById(int id) throws DataAccessException {
        Integer param = new Integer(id);
        return (DiscountDO) getSqlMapClientTemplate().queryForObject("MS-DISCOUNT-GET-BY-ID", param);

    }

	/**
	 *  Query DB table <tt>discount</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from discount</tt>
	 *
	 *	@return List<DiscountDO>
	 *	@throws DataAccessException
	 */	 
    public List<DiscountDO> getAll() throws DataAccessException {

        return getSqlMapClientTemplate().queryForList("MS-DISCOUNT-GET-ALL", null);

    }

	/**
	 *  Insert one <tt>DiscountDO</tt> object to DB table <tt>discount</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into discount(bandar_note_number,proposer_id,amount,state,expire_date,create_time,modify_time) values (?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)</tt>
	 *
	 *	@param discount
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(DiscountDO discount) throws DataAccessException {
    	if (discount == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-DISCOUNT-INSERT", discount);

        return discount.getId();
    }

	/**
	 *  Delete records from DB table <tt>discount</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from discount where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(int id) throws DataAccessException {
        Integer param = new Integer(id);

        return getSqlMapClientTemplate().delete("MS-DISCOUNT-DELETE", param);
    }

	/**
	 *  Update DB table <tt>discount</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update discount set bandar_note_number=?, proposer_id=?, amount=?, state=?, expire_date=?, modify_time=CURRENT_TIMESTAMP where (id = ?)</tt>
	 *
	 *	@param discount
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(DiscountDO discount) throws DataAccessException {
    	if (discount == null) {
    		throw new IllegalArgumentException("Can't update by a null data object.");
    	}


        return getSqlMapClientTemplate().update("MS-DISCOUNT-UPDATE", discount);
    }

	/**
	 *  Query DB table <tt>discount</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from discount</tt>
	 *
	 *	@param enterpriseName
	 *	@param bandarNoteNumber
	 *	@param showExpire
	 *	@param doPage
	 *	@param pageSize
	 *	@param pageNum
	 *	@return PageList
	 *	@throws DataAccessException
	 */	 
    public PageList getByCondition(String enterpriseName, String bandarNoteNumber, Boolean showExpire, Boolean doPage, int pageSize, int pageNum) throws DataAccessException {
        Map param = new HashMap();

        param.put("enterpriseName", enterpriseName);
        param.put("bandarNoteNumber", bandarNoteNumber);
        param.put("showExpire", showExpire);
        param.put("doPage", doPage);
        param.put("pageSize", new Integer(pageSize));
        param.put("pageNum", new Integer(pageNum));

        Paginator paginator = new Paginator();
        paginator.setItemsPerPage(pageSize);
        paginator.setPage(pageNum / pageSize + 1);

        paginator.setItems(((Integer) getSqlMapClientTemplate().queryForObject("MS-TCCZ-DISCOUNT-GET-BY-CONDITION-COUNT-FOR-PAGING", param)).intValue());
        
        PageList  pageList = new PageList();
        pageList.setPaginator(paginator);
        
        if (paginator.getBeginIndex() <= paginator.getItems()) {
            param.put("startRow", new Integer(paginator.getBeginIndex()));
            param.put("endRow", new Integer(paginator.getEndIndex()));
            pageList.addAll(getSqlMapClientTemplate().queryForList("MS-DISCOUNT-GET-BY-CONDITION", param));
        }
        
        return pageList;
    }

	/**
	 *  Query DB table <tt>discount</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from discount</tt>
	 *
	 *	@param proposerId
	 *	@param expireStart
	 *	@param expireEnd
	 *	@return List<DiscountDO>
	 *	@throws DataAccessException
	 */	 
    public List<DiscountDO> getByExpireDate(int proposerId, Date expireStart, Date expireEnd) throws DataAccessException {
        Map param = new HashMap();

        param.put("proposerId", new Integer(proposerId));
        param.put("expireStart", expireStart);
        param.put("expireEnd", expireEnd);

        return getSqlMapClientTemplate().queryForList("MS-DISCOUNT-GET-BY-EXPIRE-DATE", param);

    }

}