/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.daointerface;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.FloatingLoanDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Date;
import com.tccz.tccz.dal.util.PageList;

/**
 * A dao interface provides methods to access database table <tt>floating_loan</tt>.
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
public interface FloatingLoanDAO {
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
    public FloatingLoanDO getById(int id) throws DataAccessException;

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
    public List<FloatingLoanDO> getAll() throws DataAccessException;

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
    public int insert(FloatingLoanDO floatingLoan) throws DataAccessException;

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
    public int delete(int id) throws DataAccessException;

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
    public int update(FloatingLoanDO floatingLoan) throws DataAccessException;

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
    public PageList getByCondition(String loanerName, String bizSideType, Boolean showExpire, Date expireStart, Date expireEnd, int pageSize, int pageNum) throws DataAccessException;

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
    public List<FloatingLoanDO> getByExpireDate(int loanerId, String bizSideType, Date expireStart, Date expireEnd) throws DataAccessException;

}