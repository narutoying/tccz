/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.dataobject;

// auto generated imports

/**
 * A data object class directly models database table <tt>person_enterprise_relation</tt>.
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
public class PersonEnterpriseRelationDO {
    private static final long serialVersionUID = 741231858441822688L;

    //========== properties ==========

	/**
	 * This property corresponds to db column <tt>id</tt>.
	 */
	private int id;

	/**
	 * This property corresponds to db column <tt>person_id</tt>.
	 */
	private String personId;

	/**
	 * This property corresponds to db column <tt>enterprise_id</tt>.
	 */
	private String enterpriseId;

	/**
	 * This property corresponds to db column <tt>relation_type</tt>.
	 */
	private String relationType;

    //========== getters and setters ==========

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
	public int getId() {
		return id;
	}
	
	/**
	 * Setter method for property <tt>id</tt>.
	 * 
	 * @param id value to be assigned to property id
     */
	public void setId(int id) {
		this.id = id;
	}

    /**
     * Getter method for property <tt>personId</tt>.
     *
     * @return property value of personId
     */
	public String getPersonId() {
		return personId;
	}
	
	/**
	 * Setter method for property <tt>personId</tt>.
	 * 
	 * @param personId value to be assigned to property personId
     */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

    /**
     * Getter method for property <tt>enterpriseId</tt>.
     *
     * @return property value of enterpriseId
     */
	public String getEnterpriseId() {
		return enterpriseId;
	}
	
	/**
	 * Setter method for property <tt>enterpriseId</tt>.
	 * 
	 * @param enterpriseId value to be assigned to property enterpriseId
     */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

    /**
     * Getter method for property <tt>relationType</tt>.
     *
     * @return property value of relationType
     */
	public String getRelationType() {
		return relationType;
	}
	
	/**
	 * Setter method for property <tt>relationType</tt>.
	 * 
	 * @param relationType value to be assigned to property relationType
     */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
}
