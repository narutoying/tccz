/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.daointerface;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.PersonDO;
import org.springframework.dao.DataAccessException;
import java.util.List;

/**
 * A dao interface provides methods to access database table <tt>person</tt>.
 *
 * This file is generated by <tt>paygw-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>paygw</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/person.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>paygw-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 */
public interface PersonDAO {
	/**
	 *  Query DB table <tt>person</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from person where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return PersonDO
	 *	@throws DataAccessException
	 */	 
    public PersonDO getById(int id) throws DataAccessException;

	/**
	 *  Query DB table <tt>person</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from person</tt>
	 *
	 *	@param fuzzyName
	 *	@return List<PersonDO>
	 *	@throws DataAccessException
	 */	 
    public List<PersonDO> fuzzyQueryByName(String fuzzyName) throws DataAccessException;

}