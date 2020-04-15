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

package com.liferay.dynamic.data.mapping.model.impl;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing DDMFormInstanceReportEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class DDMFormInstanceReportEntryCacheModel
	implements CacheModel<DDMFormInstanceReportEntry>, Externalizable,
			   MVCCModel {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DDMFormInstanceReportEntryCacheModel)) {
			return false;
		}

		DDMFormInstanceReportEntryCacheModel
			ddmFormInstanceReportEntryCacheModel =
				(DDMFormInstanceReportEntryCacheModel)obj;

		if ((formInstanceReportEntryId ==
				ddmFormInstanceReportEntryCacheModel.
					formInstanceReportEntryId) &&
			(mvccVersion == ddmFormInstanceReportEntryCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, formInstanceReportEntryId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", formInstanceReportEntryId=");
		sb.append(formInstanceReportEntryId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", formInstanceId=");
		sb.append(formInstanceId);
		sb.append(", summary=");
		sb.append(summary);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public DDMFormInstanceReportEntry toEntityModel() {
		DDMFormInstanceReportEntryImpl ddmFormInstanceReportEntryImpl =
			new DDMFormInstanceReportEntryImpl();

		ddmFormInstanceReportEntryImpl.setMvccVersion(mvccVersion);
		ddmFormInstanceReportEntryImpl.setFormInstanceReportEntryId(
			formInstanceReportEntryId);
		ddmFormInstanceReportEntryImpl.setGroupId(groupId);
		ddmFormInstanceReportEntryImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			ddmFormInstanceReportEntryImpl.setCreateDate(null);
		}
		else {
			ddmFormInstanceReportEntryImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			ddmFormInstanceReportEntryImpl.setModifiedDate(null);
		}
		else {
			ddmFormInstanceReportEntryImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		ddmFormInstanceReportEntryImpl.setFormInstanceId(formInstanceId);

		if (summary == null) {
			ddmFormInstanceReportEntryImpl.setSummary("");
		}
		else {
			ddmFormInstanceReportEntryImpl.setSummary(summary);
		}

		ddmFormInstanceReportEntryImpl.resetOriginalValues();

		return ddmFormInstanceReportEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		formInstanceReportEntryId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		formInstanceId = objectInput.readLong();
		summary = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(formInstanceReportEntryId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(formInstanceId);

		if (summary == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(summary);
		}
	}

	public long mvccVersion;
	public long formInstanceReportEntryId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public long formInstanceId;
	public String summary;

}