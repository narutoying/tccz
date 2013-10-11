/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.daointerface;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.BandarNoteDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Date;
import com.tccz.tccz.dal.util.PageList;

/**
 * A dao interface provides methods to access database table <tt>bandar_note</tt>.
 *
 * This file is generated by <tt>paygw-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>paygw</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/bandar_note.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>paygw-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 */
public interface BandarNoteDAO {
	/**
	 *  Query DB table <tt>bandar_note</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from bandar_note where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return BandarNoteDO
	 *	@throws DataAccessException
	 */	 
    public BandarNoteDO getById(int id) throws DataAccessException;

	/**
	 *  Query DB table <tt>bandar_note</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from bandar_note</tt>
	 *
	 *	@return List<BandarNoteDO>
	 *	@throws DataAccessException
	 */	 
    public List<BandarNoteDO> getAll() throws DataAccessException;

	/**
	 *  Insert one <tt>BandarNoteDO</tt> object to DB table <tt>bandar_note</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into bandar_note(bandar_note_number,enterprise_id,type,amount,draw_date,margin_amount,exposure_amount,exposure_close_amount,create_time,modify_time) values (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)</tt>
	 *
	 *	@param bandarNote
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(BandarNoteDO bandarNote) throws DataAccessException;

	/**
	 *  Delete records from DB table <tt>bandar_note</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from bandar_note where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int delete(int id) throws DataAccessException;

	/**
	 *  Update DB table <tt>bandar_note</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update bandar_note set bandar_note_number=?, enterprise_id=?, type=?, amount=?, draw_date=?, margin_amount=?, exposure_amount=?, exposure_close_amount=?, modify_time=CURRENT_TIMESTAMP where (id = ?)</tt>
	 *
	 *	@param bandarNote
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int update(BandarNoteDO bandarNote) throws DataAccessException;

	/**
	 *  Query DB table <tt>bandar_note</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from bandar_note</tt>
	 *
	 *	@param enterpriseName
	 *	@param bandarNoteNumber
	 *	@param bandarNoteType
	 *	@param showExpire
	 *	@param expireStart
	 *	@param expireEnd
	 *	@param pageSize
	 *	@param pageNum
	 *	@return PageList
	 *	@throws DataAccessException
	 */	 
    public PageList getByCondition(String enterpriseName, String bandarNoteNumber, String bandarNoteType, Boolean showExpire, Date expireStart, Date expireEnd, int pageSize, int pageNum) throws DataAccessException;

	/**
	 *  Query DB table <tt>bandar_note</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from bandar_note</tt>
	 *
	 *	@param enterpriseId
	 *	@param expireStart
	 *	@param expireEnd
	 *	@return List<BandarNoteDO>
	 *	@throws DataAccessException
	 */	 
    public List<BandarNoteDO> getByExpireDate(int enterpriseId, Date expireStart, Date expireEnd) throws DataAccessException;

}