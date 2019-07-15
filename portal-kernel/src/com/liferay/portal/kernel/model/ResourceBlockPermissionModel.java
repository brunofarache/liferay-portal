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

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the ResourceBlockPermission service. Represents a row in the &quot;ResourceBlockPermission&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.ResourceBlockPermissionModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.ResourceBlockPermissionImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceBlockPermission
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@ProviderType
public interface ResourceBlockPermissionModel
	extends BaseModel<ResourceBlockPermission>, MVCCModel, ShardedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a resource block permission model instance should use the {@link ResourceBlockPermission} interface instead.
	 */

	/**
	 * Returns the primary key of this resource block permission.
	 *
	 * @return the primary key of this resource block permission
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this resource block permission.
	 *
	 * @param primaryKey the primary key of this resource block permission
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this resource block permission.
	 *
	 * @return the mvcc version of this resource block permission
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this resource block permission.
	 *
	 * @param mvccVersion the mvcc version of this resource block permission
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the resource block permission ID of this resource block permission.
	 *
	 * @return the resource block permission ID of this resource block permission
	 */
	public long getResourceBlockPermissionId();

	/**
	 * Sets the resource block permission ID of this resource block permission.
	 *
	 * @param resourceBlockPermissionId the resource block permission ID of this resource block permission
	 */
	public void setResourceBlockPermissionId(long resourceBlockPermissionId);

	/**
	 * Returns the company ID of this resource block permission.
	 *
	 * @return the company ID of this resource block permission
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this resource block permission.
	 *
	 * @param companyId the company ID of this resource block permission
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the resource block ID of this resource block permission.
	 *
	 * @return the resource block ID of this resource block permission
	 */
	public long getResourceBlockId();

	/**
	 * Sets the resource block ID of this resource block permission.
	 *
	 * @param resourceBlockId the resource block ID of this resource block permission
	 */
	public void setResourceBlockId(long resourceBlockId);

	/**
	 * Returns the role ID of this resource block permission.
	 *
	 * @return the role ID of this resource block permission
	 */
	public long getRoleId();

	/**
	 * Sets the role ID of this resource block permission.
	 *
	 * @param roleId the role ID of this resource block permission
	 */
	public void setRoleId(long roleId);

	/**
	 * Returns the action IDs of this resource block permission.
	 *
	 * @return the action IDs of this resource block permission
	 */
	public long getActionIds();

	/**
	 * Sets the action IDs of this resource block permission.
	 *
	 * @param actionIds the action IDs of this resource block permission
	 */
	public void setActionIds(long actionIds);

}