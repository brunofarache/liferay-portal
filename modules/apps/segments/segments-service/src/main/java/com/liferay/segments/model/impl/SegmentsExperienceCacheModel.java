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

package com.liferay.segments.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.segments.model.SegmentsExperience;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The cache model class for representing SegmentsExperience in entity cache.
 *
 * @author Eduardo Garcia
 * @generated
 */
@ProviderType
public class SegmentsExperienceCacheModel
	implements CacheModel<SegmentsExperience>, Externalizable {

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SegmentsExperienceCacheModel)) {
			return false;
		}

		SegmentsExperienceCacheModel segmentsExperienceCacheModel =
			(SegmentsExperienceCacheModel)obj;

		if (segmentsExperienceId ==
				segmentsExperienceCacheModel.segmentsExperienceId) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, segmentsExperienceId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(33);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", segmentsExperienceId=");
		sb.append(segmentsExperienceId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", segmentsExperienceKey=");
		sb.append(segmentsExperienceKey);
		sb.append(", segmentsEntryId=");
		sb.append(segmentsEntryId);
		sb.append(", classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", name=");
		sb.append(name);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", active=");
		sb.append(active);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SegmentsExperience toEntityModel() {
		SegmentsExperienceImpl segmentsExperienceImpl =
			new SegmentsExperienceImpl();

		if (uuid == null) {
			segmentsExperienceImpl.setUuid("");
		}
		else {
			segmentsExperienceImpl.setUuid(uuid);
		}

		segmentsExperienceImpl.setSegmentsExperienceId(segmentsExperienceId);
		segmentsExperienceImpl.setGroupId(groupId);
		segmentsExperienceImpl.setCompanyId(companyId);
		segmentsExperienceImpl.setUserId(userId);

		if (userName == null) {
			segmentsExperienceImpl.setUserName("");
		}
		else {
			segmentsExperienceImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			segmentsExperienceImpl.setCreateDate(null);
		}
		else {
			segmentsExperienceImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			segmentsExperienceImpl.setModifiedDate(null);
		}
		else {
			segmentsExperienceImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (segmentsExperienceKey == null) {
			segmentsExperienceImpl.setSegmentsExperienceKey("");
		}
		else {
			segmentsExperienceImpl.setSegmentsExperienceKey(
				segmentsExperienceKey);
		}

		segmentsExperienceImpl.setSegmentsEntryId(segmentsEntryId);
		segmentsExperienceImpl.setClassNameId(classNameId);
		segmentsExperienceImpl.setClassPK(classPK);

		if (name == null) {
			segmentsExperienceImpl.setName("");
		}
		else {
			segmentsExperienceImpl.setName(name);
		}

		segmentsExperienceImpl.setPriority(priority);
		segmentsExperienceImpl.setActive(active);

		if (lastPublishDate == Long.MIN_VALUE) {
			segmentsExperienceImpl.setLastPublishDate(null);
		}
		else {
			segmentsExperienceImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		segmentsExperienceImpl.resetOriginalValues();

		return segmentsExperienceImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		segmentsExperienceId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		segmentsExperienceKey = objectInput.readUTF();

		segmentsEntryId = objectInput.readLong();

		classNameId = objectInput.readLong();

		classPK = objectInput.readLong();
		name = objectInput.readUTF();

		priority = objectInput.readInt();

		active = objectInput.readBoolean();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(segmentsExperienceId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (segmentsExperienceKey == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(segmentsExperienceKey);
		}

		objectOutput.writeLong(segmentsEntryId);

		objectOutput.writeLong(classNameId);

		objectOutput.writeLong(classPK);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		objectOutput.writeInt(priority);

		objectOutput.writeBoolean(active);
		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long segmentsExperienceId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String segmentsExperienceKey;
	public long segmentsEntryId;
	public long classNameId;
	public long classPK;
	public String name;
	public int priority;
	public boolean active;
	public long lastPublishDate;

}