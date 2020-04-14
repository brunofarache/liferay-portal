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

package com.liferay.dynamic.data.mapping.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DDMFormInstanceReportEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntry
 * @generated
 */
public class DDMFormInstanceReportEntryWrapper
	extends BaseModelWrapper<DDMFormInstanceReportEntry>
	implements DDMFormInstanceReportEntry,
			   ModelWrapper<DDMFormInstanceReportEntry> {

	public DDMFormInstanceReportEntryWrapper(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		super(ddmFormInstanceReportEntry);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put(
			"formInstanceReportEntryId", getFormInstanceReportEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("formInstanceId", getFormInstanceId());
		attributes.put("summary", getSummary());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long formInstanceReportEntryId = (Long)attributes.get(
			"formInstanceReportEntryId");

		if (formInstanceReportEntryId != null) {
			setFormInstanceReportEntryId(formInstanceReportEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long formInstanceId = (Long)attributes.get("formInstanceId");

		if (formInstanceId != null) {
			setFormInstanceId(formInstanceId);
		}

		String summary = (String)attributes.get("summary");

		if (summary != null) {
			setSummary(summary);
		}
	}

	/**
	 * Returns the company ID of this ddm form instance report entry.
	 *
	 * @return the company ID of this ddm form instance report entry
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this ddm form instance report entry.
	 *
	 * @return the create date of this ddm form instance report entry
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the form instance ID of this ddm form instance report entry.
	 *
	 * @return the form instance ID of this ddm form instance report entry
	 */
	@Override
	public long getFormInstanceId() {
		return model.getFormInstanceId();
	}

	/**
	 * Returns the form instance report entry ID of this ddm form instance report entry.
	 *
	 * @return the form instance report entry ID of this ddm form instance report entry
	 */
	@Override
	public long getFormInstanceReportEntryId() {
		return model.getFormInstanceReportEntryId();
	}

	/**
	 * Returns the group ID of this ddm form instance report entry.
	 *
	 * @return the group ID of this ddm form instance report entry
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the modified date of this ddm form instance report entry.
	 *
	 * @return the modified date of this ddm form instance report entry
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this ddm form instance report entry.
	 *
	 * @return the mvcc version of this ddm form instance report entry
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this ddm form instance report entry.
	 *
	 * @return the primary key of this ddm form instance report entry
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the summary of this ddm form instance report entry.
	 *
	 * @return the summary of this ddm form instance report entry
	 */
	@Override
	public String getSummary() {
		return model.getSummary();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this ddm form instance report entry.
	 *
	 * @param companyId the company ID of this ddm form instance report entry
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this ddm form instance report entry.
	 *
	 * @param createDate the create date of this ddm form instance report entry
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the form instance ID of this ddm form instance report entry.
	 *
	 * @param formInstanceId the form instance ID of this ddm form instance report entry
	 */
	@Override
	public void setFormInstanceId(long formInstanceId) {
		model.setFormInstanceId(formInstanceId);
	}

	/**
	 * Sets the form instance report entry ID of this ddm form instance report entry.
	 *
	 * @param formInstanceReportEntryId the form instance report entry ID of this ddm form instance report entry
	 */
	@Override
	public void setFormInstanceReportEntryId(long formInstanceReportEntryId) {
		model.setFormInstanceReportEntryId(formInstanceReportEntryId);
	}

	/**
	 * Sets the group ID of this ddm form instance report entry.
	 *
	 * @param groupId the group ID of this ddm form instance report entry
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the modified date of this ddm form instance report entry.
	 *
	 * @param modifiedDate the modified date of this ddm form instance report entry
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this ddm form instance report entry.
	 *
	 * @param mvccVersion the mvcc version of this ddm form instance report entry
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this ddm form instance report entry.
	 *
	 * @param primaryKey the primary key of this ddm form instance report entry
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the summary of this ddm form instance report entry.
	 *
	 * @param summary the summary of this ddm form instance report entry
	 */
	@Override
	public void setSummary(String summary) {
		model.setSummary(summary);
	}

	@Override
	protected DDMFormInstanceReportEntryWrapper wrap(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		return new DDMFormInstanceReportEntryWrapper(
			ddmFormInstanceReportEntry);
	}

}