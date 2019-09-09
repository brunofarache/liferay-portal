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

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The base model interface for the PortletItem service. Represents a row in the &quot;PortletItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation <code>com.liferay.portal.model.impl.PortletItemModelImpl</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in <code>com.liferay.portal.model.impl.PortletItemImpl</code>.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PortletItem
 * @generated
 */
@ProviderType
public interface PortletItemModel
	extends BaseModel<PortletItem>, GroupedModel, MVCCModel, ShardedModel,
			TypedModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a portlet item model instance should use the {@link PortletItem} interface instead.
	 */

	/**
	 * Returns the primary key of this portlet item.
	 *
	 * @return the primary key of this portlet item
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this portlet item.
	 *
	 * @param primaryKey the primary key of this portlet item
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this portlet item.
	 *
	 * @return the mvcc version of this portlet item
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this portlet item.
	 *
	 * @param mvccVersion the mvcc version of this portlet item
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the portlet item ID of this portlet item.
	 *
	 * @return the portlet item ID of this portlet item
	 */
	public long getPortletItemId();

	/**
	 * Sets the portlet item ID of this portlet item.
	 *
	 * @param portletItemId the portlet item ID of this portlet item
	 */
	public void setPortletItemId(long portletItemId);

	/**
	 * Returns the group ID of this portlet item.
	 *
	 * @return the group ID of this portlet item
	 */
	@Override
	public long getGroupId();

	/**
	 * Sets the group ID of this portlet item.
	 *
	 * @param groupId the group ID of this portlet item
	 */
	@Override
	public void setGroupId(long groupId);

	/**
	 * Returns the company ID of this portlet item.
	 *
	 * @return the company ID of this portlet item
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this portlet item.
	 *
	 * @param companyId the company ID of this portlet item
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this portlet item.
	 *
	 * @return the user ID of this portlet item
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this portlet item.
	 *
	 * @param userId the user ID of this portlet item
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this portlet item.
	 *
	 * @return the user uuid of this portlet item
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this portlet item.
	 *
	 * @param userUuid the user uuid of this portlet item
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this portlet item.
	 *
	 * @return the user name of this portlet item
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this portlet item.
	 *
	 * @param userName the user name of this portlet item
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this portlet item.
	 *
	 * @return the create date of this portlet item
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this portlet item.
	 *
	 * @param createDate the create date of this portlet item
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this portlet item.
	 *
	 * @return the modified date of this portlet item
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this portlet item.
	 *
	 * @param modifiedDate the modified date of this portlet item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the name of this portlet item.
	 *
	 * @return the name of this portlet item
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this portlet item.
	 *
	 * @param name the name of this portlet item
	 */
	public void setName(String name);

	/**
	 * Returns the portlet ID of this portlet item.
	 *
	 * @return the portlet ID of this portlet item
	 */
	@AutoEscape
	public String getPortletId();

	/**
	 * Sets the portlet ID of this portlet item.
	 *
	 * @param portletId the portlet ID of this portlet item
	 */
	public void setPortletId(String portletId);

	/**
	 * Returns the fully qualified class name of this portlet item.
	 *
	 * @return the fully qualified class name of this portlet item
	 */
	@Override
	public String getClassName();

	public void setClassName(String className);

	/**
	 * Returns the class name ID of this portlet item.
	 *
	 * @return the class name ID of this portlet item
	 */
	@Override
	public long getClassNameId();

	/**
	 * Sets the class name ID of this portlet item.
	 *
	 * @param classNameId the class name ID of this portlet item
	 */
	@Override
	public void setClassNameId(long classNameId);

}