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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDMFormInstanceReportEntrySoap implements Serializable {

	public static DDMFormInstanceReportEntrySoap toSoapModel(
		DDMFormInstanceReportEntry model) {

		DDMFormInstanceReportEntrySoap soapModel =
			new DDMFormInstanceReportEntrySoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setFormInstanceReportEntryId(
			model.getFormInstanceReportEntryId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setFormInstanceId(model.getFormInstanceId());
		soapModel.setSummary(model.getSummary());

		return soapModel;
	}

	public static DDMFormInstanceReportEntrySoap[] toSoapModels(
		DDMFormInstanceReportEntry[] models) {

		DDMFormInstanceReportEntrySoap[] soapModels =
			new DDMFormInstanceReportEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static DDMFormInstanceReportEntrySoap[][] toSoapModels(
		DDMFormInstanceReportEntry[][] models) {

		DDMFormInstanceReportEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new DDMFormInstanceReportEntrySoap
					[models.length][models[0].length];
		}
		else {
			soapModels = new DDMFormInstanceReportEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static DDMFormInstanceReportEntrySoap[] toSoapModels(
		List<DDMFormInstanceReportEntry> models) {

		List<DDMFormInstanceReportEntrySoap> soapModels =
			new ArrayList<DDMFormInstanceReportEntrySoap>(models.size());

		for (DDMFormInstanceReportEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(
			new DDMFormInstanceReportEntrySoap[soapModels.size()]);
	}

	public DDMFormInstanceReportEntrySoap() {
	}

	public long getPrimaryKey() {
		return _formInstanceReportEntryId;
	}

	public void setPrimaryKey(long pk) {
		setFormInstanceReportEntryId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getFormInstanceReportEntryId() {
		return _formInstanceReportEntryId;
	}

	public void setFormInstanceReportEntryId(long formInstanceReportEntryId) {
		_formInstanceReportEntryId = formInstanceReportEntryId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getFormInstanceId() {
		return _formInstanceId;
	}

	public void setFormInstanceId(long formInstanceId) {
		_formInstanceId = formInstanceId;
	}

	public String getSummary() {
		return _summary;
	}

	public void setSummary(String summary) {
		_summary = summary;
	}

	private long _mvccVersion;
	private long _formInstanceReportEntryId;
	private long _groupId;
	private long _companyId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _formInstanceId;
	private String _summary;

}