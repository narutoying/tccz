/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.ibatis;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.tccz.tccz.common.dal.daointerface.PersonEnterpriseRelationDAO;

// auto generated imports
import com.tccz.tccz.common.dal.dataobject.PersonEnterpriseRelationDO;
import org.springframework.dao.DataAccessException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.tccz.tccz.common.dal.dataobject.PersonEnterpriseRelationDO;

import java.util.HashMap;
import java.util.Map;

/**
 * An ibatis based implementation of dao interface <tt>com.tccz.tccz.common.dal.daointerface.PersonEnterpriseRelationDAO</tt>.
 *
 * This file is generated by <tt>paygw-dalgen</tt>, a DAL (Data Access Layer)
 * code generation utility specially developed for <tt>paygw</tt> project.
 * 
 * PLEASE DO NOT MODIFY THIS FILE MANUALLY, or else your modification may
 * be OVERWRITTEN by someone else. To modify the file, you should go to 
 * directory <tt>(project-home)/biz/dal/src/conf/dalgen</tt>, and 
 * find the corresponding configuration file (<tt>tables/person_enterprise_relation.xml</tt>). 
 * Modify the configuration file according to your needs, then run <tt>paygw-dalgen</tt> 
 * to generate this file.
 *
 * @author Cheng Li
 */
public class IbatisPersonEnterpriseRelationDAO extends SqlMapClientDaoSupport implements PersonEnterpriseRelationDAO {
	/**
	 *  Insert one <tt>PersonEnterpriseRelationDO</tt> object to DB table <tt>person_enterprise_relation</tt>, return primary key
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>insert into person_enterprise_relation(person_id,enterprise_id,relation_type) values (?, ?, ?)</tt>
	 *
	 *	@param personEnterpriseRelation
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int insert(PersonEnterpriseRelationDO personEnterpriseRelation) throws DataAccessException {
    	if (personEnterpriseRelation == null) {
    		throw new IllegalArgumentException("Can't insert a null data object into db.");
    	}
    	
        getSqlMapClientTemplate().insert("MS-PERSON-ENTERPRISE-RELATION-INSERT", personEnterpriseRelation);

        return personEnterpriseRelation.getId();
    }

	/**
	 *  Delete records from DB table <tt>person_enterprise_relation</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from person_enterprise_relation where (enterprise_id = ?)</tt>
	 *
	 *	@param enterpriseId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByEnterpriseId(String enterpriseId) throws DataAccessException {

        return getSqlMapClientTemplate().delete("MS-PERSON-ENTERPRISE-RELATION-DELETE-BY-ENTERPRISE-ID", enterpriseId);
    }

	/**
	 *  Delete records from DB table <tt>person_enterprise_relation</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>delete from person_enterprise_relation where ((person_id = ?) AND (enterprise_id = ?))</tt>
	 *
	 *	@param personId
	 *	@param enterpriseId
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int deleteByEnterpriseAndPersonId(String personId, String enterpriseId) throws DataAccessException {
        Map param = new HashMap();

        param.put("personId", personId);
        param.put("enterpriseId", enterpriseId);

        return getSqlMapClientTemplate().delete("MS-PERSON-ENTERPRISE-RELATION-DELETE-BY-ENTERPRISE-AND-PERSON-ID", param);
    }

	/**
	 *  Update DB table <tt>person_enterprise_relation</tt>.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>update person_enterprise_relation set person_id=? where ((enterprise_id = ?) AND (relation_type = ?))</tt>
	 *
	 *	@param personId
	 *	@param enterpriseId
	 *	@param relationType
	 *	@return int
	 *	@throws DataAccessException
	 */	 
    public int updateByEnterpriseAndType(String personId, String enterpriseId, String relationType) throws DataAccessException {
        Map param = new HashMap();

        param.put("personId", personId);
        param.put("enterpriseId", enterpriseId);
        param.put("relationType", relationType);

        return getSqlMapClientTemplate().update("MS-PERSON-ENTERPRISE-RELATION-UPDATE-BY-ENTERPRISE-AND-TYPE", param);
    }

	/**
	 *  Query DB table <tt>person_enterprise_relation</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from person_enterprise_relation where (id = ?)</tt>
	 *
	 *	@param id
	 *	@return PersonEnterpriseRelationDO
	 *	@throws DataAccessException
	 */	 
    public PersonEnterpriseRelationDO getById(int id) throws DataAccessException {
        Integer param = new Integer(id);
        return (PersonEnterpriseRelationDO) getSqlMapClientTemplate().queryForObject("MS-PERSON-ENTERPRISE-RELATION-GET-BY-ID", param);

    }

	/**
	 *  Query DB table <tt>person_enterprise_relation</tt> for records.
	 *
   	 *  <p>
   	 *  Description for this operation is<br>
   	 *  <tt></tt>
	 *  <p>
	 *  The sql statement for this operation is <br>
	 *  <tt>select * from person_enterprise_relation</tt>
	 *
	 *	@param enterpriseId
	 *	@param personId
	 *	@param relationType
	 *	@return List<PersonEnterpriseRelationDO>
	 *	@throws DataAccessException
	 */	 
    public List<PersonEnterpriseRelationDO> getByCondition(String enterpriseId, String personId, String relationType) throws DataAccessException {
        Map param = new HashMap();

        param.put("enterpriseId", enterpriseId);
        param.put("personId", personId);
        param.put("relationType", relationType);

        return getSqlMapClientTemplate().queryForList("MS-PERSON-ENTERPRISE-RELATION-GET-BY-CONDITION", param);

    }

}