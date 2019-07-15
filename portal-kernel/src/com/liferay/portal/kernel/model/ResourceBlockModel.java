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

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.bean.AutoEscape;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ResourceBlock service. Represents a row in the &quot;ResourceBlock&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ResourceBlockModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ResourceBlockImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlock
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@ProviderType
public interface ResourceBlockModel
	extends BaseModel<ResourceBlock>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a resource block model instance should use the {@link ResourceBlock} interface instead.
	 */

	/**
	 * Returns the primary key of this resource block.
	 *
	 * @return the primary key of this resource block
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this resource block.
	 *
	 * @param primaryKey the primary key of this resource block
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this resource block.
	 *
	 * @return the mvcc version of this resource block
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this resource block.
	 *
	 * @param mvccVersion the mvcc version of this resource block
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the resource block ID of this resource block.
	 *
	 * @return the resource block ID of this resource block
	 */
	public long getResourceBlockId();

	/**
	 * Sets the resource block ID of this resource block.
	 *
	 * @param resourceBlockId the resource block ID of this resource block
	 */
	public void setResourceBlockId(long resourceBlockId);

	/**
	 * Returns the company ID of this resource block.
	 *
	 * @return the company ID of this resource block
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this resource block.
	 *
	 * @param companyId the company ID of this resource block
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this resource block.
	 *
	 * @return the group ID of this resource block
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this resource block.
	 *
	 * @param groupId the group ID of this resource block
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the name of this resource block.
	 *
	 * @return the name of this resource block
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this resource block.
	 *
	 * @param name the name of this resource block
	 */
	public void setName(String name);

	/**
	 * Returns the permissions hash of this resource block.
	 *
	 * @return the permissions hash of this resource block
	 */
	@AutoEscape
	public String getPermissionsHash();

	/**
	 * Sets the permissions hash of this resource block.
	 *
	 * @param permissionsHash the permissions hash of this resource block
	 */
	public void setPermissionsHash(String permissionsHash);

	/**
	 * Returns the reference count of this resource block.
	 *
	 * @return the reference count of this resource block
	 */
	public long getReferenceCount();

	/**
	 * Sets the reference count of this resource block.
	 *
	 * @param referenceCount the reference count of this resource block
	 */
	public void setReferenceCount(long referenceCount);

}