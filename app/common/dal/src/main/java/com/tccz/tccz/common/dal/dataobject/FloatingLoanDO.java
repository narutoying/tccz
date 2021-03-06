/*
 * Tceon.com Inc.
 * Copyright (c) 2009 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.dataobject;

// auto generated imports
import java.util.Date;

/**
 * A data object class directly models database table <tt>floating_loan</tt>.
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
public class FloatingLoanDO {
    private static final long serialVersionUID = 741231858441822688L;

    //========== properties ==========

	/**
	 * This property corresponds to db column <tt>id</tt>.
	 */
	private int id;

	/**
	 * This property corresponds to db column <tt>loaner_id</tt>.
	 */
	private String loanerId;

	/**
	 * This property corresponds to db column <tt>biz_side_type</tt>.
	 */
	private String bizSideType;

	/**
	 * This property corresponds to db column <tt>amount</tt>.
	 */
	private long amount;

	/**
	 * This property corresponds to db column <tt>release_date</tt>.
	 */
	private Date releaseDate;

	/**
	 * This property corresponds to db column <tt>expire_date</tt>.
	 */
	private Date expireDate;

	/**
	 * This property corresponds to db column <tt>create_time</tt>.
	 */
	private Date createTime;

	/**
	 * This property corresponds to db column <tt>modify_time</tt>.
	 */
	private Date modifyTime;

	/**
	 * This property corresponds to db column <tt>has_repayed</tt>.
	 */
	private String hasRepayed;

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
     * Getter method for property <tt>loanerId</tt>.
     *
     * @return property value of loanerId
     */
	public String getLoanerId() {
		return loanerId;
	}
	
	/**
	 * Setter method for property <tt>loanerId</tt>.
	 * 
	 * @param loanerId value to be assigned to property loanerId
     */
	public void setLoanerId(String loanerId) {
		this.loanerId = loanerId;
	}

    /**
     * Getter method for property <tt>bizSideType</tt>.
     *
     * @return property value of bizSideType
     */
	public String getBizSideType() {
		return bizSideType;
	}
	
	/**
	 * Setter method for property <tt>bizSideType</tt>.
	 * 
	 * @param bizSideType value to be assigned to property bizSideType
     */
	public void setBizSideType(String bizSideType) {
		this.bizSideType = bizSideType;
	}

    /**
     * Getter method for property <tt>amount</tt>.
     *
     * @return property value of amount
     */
	public long getAmount() {
		return amount;
	}
	
	/**
	 * Setter method for property <tt>amount</tt>.
	 * 
	 * @param amount value to be assigned to property amount
     */
	public void setAmount(long amount) {
		this.amount = amount;
	}

    /**
     * Getter method for property <tt>releaseDate</tt>.
     *
     * @return property value of releaseDate
     */
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	/**
	 * Setter method for property <tt>releaseDate</tt>.
	 * 
	 * @param releaseDate value to be assigned to property releaseDate
     */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

    /**
     * Getter method for property <tt>expireDate</tt>.
     *
     * @return property value of expireDate
     */
	public Date getExpireDate() {
		return expireDate;
	}
	
	/**
	 * Setter method for property <tt>expireDate</tt>.
	 * 
	 * @param expireDate value to be assigned to property expireDate
     */
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

    /**
     * Getter method for property <tt>createTime</tt>.
     *
     * @return property value of createTime
     */
	public Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * Setter method for property <tt>createTime</tt>.
	 * 
	 * @param createTime value to be assigned to property createTime
     */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    /**
     * Getter method for property <tt>modifyTime</tt>.
     *
     * @return property value of modifyTime
     */
	public Date getModifyTime() {
		return modifyTime;
	}
	
	/**
	 * Setter method for property <tt>modifyTime</tt>.
	 * 
	 * @param modifyTime value to be assigned to property modifyTime
     */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

    /**
     * Getter method for property <tt>hasRepayed</tt>.
     *
     * @return property value of hasRepayed
     */
	public String getHasRepayed() {
		return hasRepayed;
	}
	
	/**
	 * Setter method for property <tt>hasRepayed</tt>.
	 * 
	 * @param hasRepayed value to be assigned to property hasRepayed
     */
	public void setHasRepayed(String hasRepayed) {
		this.hasRepayed = hasRepayed;
	}
}
