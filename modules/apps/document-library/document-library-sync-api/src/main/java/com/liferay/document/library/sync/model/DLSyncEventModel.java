/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.sync.model;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.ShardedModel;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the DLSyncEvent service. Represents a row in the &quot;DLSyncEvent&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.document.library.sync.model.impl.DLSyncEventModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.document.library.sync.model.impl.DLSyncEventImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DLSyncEvent
 * @generated
 */
@ProviderType
public interface DLSyncEventModel extends BaseModel<DLSyncEvent>, ShardedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a dl sync event model instance should use the {@link DLSyncEvent} interface instead.
	 */

	/**
	 * Returns the primary key of this dl sync event.
	 *
	 * @return the primary key of this dl sync event
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this dl sync event.
	 *
	 * @param primaryKey the primary key of this dl sync event
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the sync event ID of this dl sync event.
	 *
	 * @return the sync event ID of this dl sync event
	 */
	public long getSyncEventId();

	/**
	 * Sets the sync event ID of this dl sync event.
	 *
	 * @param syncEventId the sync event ID of this dl sync event
	 */
	public void setSyncEventId(long syncEventId);

	/**
	 * Returns the company ID of this dl sync event.
	 *
	 * @return the company ID of this dl sync event
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this dl sync event.
	 *
	 * @param companyId the company ID of this dl sync event
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the modified time of this dl sync event.
	 *
	 * @return the modified time of this dl sync event
	 */
	public long getModifiedTime();

	/**
	 * Sets the modified time of this dl sync event.
	 *
	 * @param modifiedTime the modified time of this dl sync event
	 */
	public void setModifiedTime(long modifiedTime);

	/**
	 * Returns the event of this dl sync event.
	 *
	 * @return the event of this dl sync event
	 */
	@AutoEscape
	public String getEvent();

	/**
	 * Sets the event of this dl sync event.
	 *
	 * @param event the event of this dl sync event
	 */
	public void setEvent(String event);

	/**
	 * Returns the type of this dl sync event.
	 *
	 * @return the type of this dl sync event
	 */
	@AutoEscape
	public String getType();

	/**
	 * Sets the type of this dl sync event.
	 *
	 * @param type the type of this dl sync event
	 */
	public void setType(String type);

	/**
	 * Returns the type pk of this dl sync event.
	 *
	 * @return the type pk of this dl sync event
	 */
	public long getTypePK();

	/**
	 * Sets the type pk of this dl sync event.
	 *
	 * @param typePK the type pk of this dl sync event
	 */
	public void setTypePK(long typePK);

}