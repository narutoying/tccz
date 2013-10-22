/**
 * Taicang mscz Inc.
 * Copyright (c) 2010-2013 All Rights Reserved.
 */
package com.tccz.tccz.common.dal.manual.daointerface;

import java.util.List;

import com.tccz.tccz.common.dal.dataobject.PersonDO;

/**
 * 
 * @author narutoying09@gmail.com
 * @version $Id: PersonExtDAO.java, v 0.1 2013-10-22 上午11:22:16
 *          narutoying09@gmail.com Exp $
 */
public interface PersonExtDAO {
	void batchInsert(List<PersonDO> data);
}
