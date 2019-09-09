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

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.segments.model.SegmentsExperiment;
import com.liferay.segments.model.SegmentsExperimentModel;
import com.liferay.segments.model.SegmentsExperimentSoap;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the SegmentsExperiment service. Represents a row in the &quot;SegmentsExperiment&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>SegmentsExperimentModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link SegmentsExperimentImpl}.
 * </p>
 *
 * @author Eduardo Garcia
 * @see SegmentsExperimentImpl
 * @generated
 */
@JSON(strict = true)
public class SegmentsExperimentModelImpl
	extends BaseModelImpl<SegmentsExperiment>
	implements SegmentsExperimentModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a segments experiment model instance should use the <code>SegmentsExperiment</code> interface instead.
	 */
	public static final String TABLE_NAME = "SegmentsExperiment";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"segmentsExperimentId", Types.BIGINT},
		{"groupId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"segmentsEntryId", Types.BIGINT},
		{"segmentsExperienceId", Types.BIGINT},
		{"segmentsExperimentKey", Types.VARCHAR}, {"classNameId", Types.BIGINT},
		{"classPK", Types.BIGINT}, {"name", Types.VARCHAR},
		{"description", Types.VARCHAR}, {"typeSettings", Types.CLOB},
		{"status", Types.INTEGER}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("segmentsExperimentId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("segmentsEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("segmentsExperienceId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("segmentsExperimentKey", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classPK", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("description", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("typeSettings", Types.CLOB);
		TABLE_COLUMNS_MAP.put("status", Types.INTEGER);
	}

	public static final String TABLE_SQL_CREATE =
		"create table SegmentsExperiment (uuid_ VARCHAR(75) null,segmentsExperimentId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,segmentsEntryId LONG,segmentsExperienceId LONG,segmentsExperimentKey VARCHAR(75) null,classNameId LONG,classPK LONG,name VARCHAR(75) null,description STRING null,typeSettings TEXT null,status INTEGER)";

	public static final String TABLE_SQL_DROP = "drop table SegmentsExperiment";

	public static final String ORDER_BY_JPQL =
		" ORDER BY segmentsExperiment.createDate DESC";

	public static final String ORDER_BY_SQL =
		" ORDER BY SegmentsExperiment.createDate DESC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;

	public static final long CLASSPK_COLUMN_BITMASK = 2L;

	public static final long COMPANYID_COLUMN_BITMASK = 4L;

	public static final long GROUPID_COLUMN_BITMASK = 8L;

	public static final long SEGMENTSEXPERIENCEID_COLUMN_BITMASK = 16L;

	public static final long SEGMENTSEXPERIMENTKEY_COLUMN_BITMASK = 32L;

	public static final long STATUS_COLUMN_BITMASK = 64L;

	public static final long UUID_COLUMN_BITMASK = 128L;

	public static final long CREATEDATE_COLUMN_BITMASK = 256L;

	public static void setEntityCacheEnabled(boolean entityCacheEnabled) {
		_entityCacheEnabled = entityCacheEnabled;
	}

	public static void setFinderCacheEnabled(boolean finderCacheEnabled) {
		_finderCacheEnabled = finderCacheEnabled;
	}

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static SegmentsExperiment toModel(SegmentsExperimentSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		SegmentsExperiment model = new SegmentsExperimentImpl();

		model.setUuid(soapModel.getUuid());
		model.setSegmentsExperimentId(soapModel.getSegmentsExperimentId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setSegmentsEntryId(soapModel.getSegmentsEntryId());
		model.setSegmentsExperienceId(soapModel.getSegmentsExperienceId());
		model.setSegmentsExperimentKey(soapModel.getSegmentsExperimentKey());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassPK(soapModel.getClassPK());
		model.setName(soapModel.getName());
		model.setDescription(soapModel.getDescription());
		model.setTypeSettings(soapModel.getTypeSettings());
		model.setStatus(soapModel.getStatus());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<SegmentsExperiment> toModels(
		SegmentsExperimentSoap[] soapModels) {

		if (soapModels == null) {
			return null;
		}

		List<SegmentsExperiment> models = new ArrayList<SegmentsExperiment>(
			soapModels.length);

		for (SegmentsExperimentSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public SegmentsExperimentModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _segmentsExperimentId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setSegmentsExperimentId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _segmentsExperimentId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return SegmentsExperiment.class;
	}

	@Override
	public String getModelClassName() {
		return SegmentsExperiment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<SegmentsExperiment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		for (Map.Entry<String, Function<SegmentsExperiment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsExperiment, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((SegmentsExperiment)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<SegmentsExperiment, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<SegmentsExperiment, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(SegmentsExperiment)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<SegmentsExperiment, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<SegmentsExperiment, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, SegmentsExperiment>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			SegmentsExperiment.class.getClassLoader(), SegmentsExperiment.class,
			ModelWrapper.class);

		try {
			Constructor<SegmentsExperiment> constructor =
				(Constructor<SegmentsExperiment>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<SegmentsExperiment, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<SegmentsExperiment, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<SegmentsExperiment, Object>>
			attributeGetterFunctions =
				new LinkedHashMap
					<String, Function<SegmentsExperiment, Object>>();
		Map<String, BiConsumer<SegmentsExperiment, ?>>
			attributeSetterBiConsumers =
				new LinkedHashMap<String, BiConsumer<SegmentsExperiment, ?>>();

		attributeGetterFunctions.put("uuid", SegmentsExperiment::getUuid);
		attributeSetterBiConsumers.put(
			"uuid",
			(BiConsumer<SegmentsExperiment, String>)
				SegmentsExperiment::setUuid);
		attributeGetterFunctions.put(
			"segmentsExperimentId",
			SegmentsExperiment::getSegmentsExperimentId);
		attributeSetterBiConsumers.put(
			"segmentsExperimentId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setSegmentsExperimentId);
		attributeGetterFunctions.put("groupId", SegmentsExperiment::getGroupId);
		attributeSetterBiConsumers.put(
			"groupId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setGroupId);
		attributeGetterFunctions.put(
			"companyId", SegmentsExperiment::getCompanyId);
		attributeSetterBiConsumers.put(
			"companyId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setCompanyId);
		attributeGetterFunctions.put("userId", SegmentsExperiment::getUserId);
		attributeSetterBiConsumers.put(
			"userId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setUserId);
		attributeGetterFunctions.put(
			"userName", SegmentsExperiment::getUserName);
		attributeSetterBiConsumers.put(
			"userName",
			(BiConsumer<SegmentsExperiment, String>)
				SegmentsExperiment::setUserName);
		attributeGetterFunctions.put(
			"createDate", SegmentsExperiment::getCreateDate);
		attributeSetterBiConsumers.put(
			"createDate",
			(BiConsumer<SegmentsExperiment, Date>)
				SegmentsExperiment::setCreateDate);
		attributeGetterFunctions.put(
			"modifiedDate", SegmentsExperiment::getModifiedDate);
		attributeSetterBiConsumers.put(
			"modifiedDate",
			(BiConsumer<SegmentsExperiment, Date>)
				SegmentsExperiment::setModifiedDate);
		attributeGetterFunctions.put(
			"segmentsEntryId", SegmentsExperiment::getSegmentsEntryId);
		attributeSetterBiConsumers.put(
			"segmentsEntryId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setSegmentsEntryId);
		attributeGetterFunctions.put(
			"segmentsExperienceId",
			SegmentsExperiment::getSegmentsExperienceId);
		attributeSetterBiConsumers.put(
			"segmentsExperienceId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setSegmentsExperienceId);
		attributeGetterFunctions.put(
			"segmentsExperimentKey",
			SegmentsExperiment::getSegmentsExperimentKey);
		attributeSetterBiConsumers.put(
			"segmentsExperimentKey",
			(BiConsumer<SegmentsExperiment, String>)
				SegmentsExperiment::setSegmentsExperimentKey);
		attributeGetterFunctions.put(
			"classNameId", SegmentsExperiment::getClassNameId);
		attributeSetterBiConsumers.put(
			"classNameId",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setClassNameId);
		attributeGetterFunctions.put("classPK", SegmentsExperiment::getClassPK);
		attributeSetterBiConsumers.put(
			"classPK",
			(BiConsumer<SegmentsExperiment, Long>)
				SegmentsExperiment::setClassPK);
		attributeGetterFunctions.put("name", SegmentsExperiment::getName);
		attributeSetterBiConsumers.put(
			"name",
			(BiConsumer<SegmentsExperiment, String>)
				SegmentsExperiment::setName);
		attributeGetterFunctions.put(
			"description", SegmentsExperiment::getDescription);
		attributeSetterBiConsumers.put(
			"description",
			(BiConsumer<SegmentsExperiment, String>)
				SegmentsExperiment::setDescription);
		attributeGetterFunctions.put(
			"typeSettings", SegmentsExperiment::getTypeSettings);
		attributeSetterBiConsumers.put(
			"typeSettings",
			(BiConsumer<SegmentsExperiment, String>)
				SegmentsExperiment::setTypeSettings);
		attributeGetterFunctions.put("status", SegmentsExperiment::getStatus);
		attributeSetterBiConsumers.put(
			"status",
			(BiConsumer<SegmentsExperiment, Integer>)
				SegmentsExperiment::setStatus);

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public long getSegmentsExperimentId() {
		return _segmentsExperimentId;
	}

	@Override
	public void setSegmentsExperimentId(long segmentsExperimentId) {
		_segmentsExperimentId = segmentsExperimentId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_columnBitmask = -1L;

		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getSegmentsEntryId() {
		return _segmentsEntryId;
	}

	@Override
	public void setSegmentsEntryId(long segmentsEntryId) {
		_segmentsEntryId = segmentsEntryId;
	}

	@JSON
	@Override
	public long getSegmentsExperienceId() {
		return _segmentsExperienceId;
	}

	@Override
	public void setSegmentsExperienceId(long segmentsExperienceId) {
		_columnBitmask |= SEGMENTSEXPERIENCEID_COLUMN_BITMASK;

		if (!_setOriginalSegmentsExperienceId) {
			_setOriginalSegmentsExperienceId = true;

			_originalSegmentsExperienceId = _segmentsExperienceId;
		}

		_segmentsExperienceId = segmentsExperienceId;
	}

	public long getOriginalSegmentsExperienceId() {
		return _originalSegmentsExperienceId;
	}

	@JSON
	@Override
	public String getSegmentsExperimentKey() {
		if (_segmentsExperimentKey == null) {
			return "";
		}
		else {
			return _segmentsExperimentKey;
		}
	}

	@Override
	public void setSegmentsExperimentKey(String segmentsExperimentKey) {
		_columnBitmask |= SEGMENTSEXPERIMENTKEY_COLUMN_BITMASK;

		if (_originalSegmentsExperimentKey == null) {
			_originalSegmentsExperimentKey = _segmentsExperimentKey;
		}

		_segmentsExperimentKey = segmentsExperimentKey;
	}

	public String getOriginalSegmentsExperimentKey() {
		return GetterUtil.getString(_originalSegmentsExperimentKey);
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassPK() {
		return _classPK;
	}

	@Override
	public void setClassPK(long classPK) {
		_columnBitmask |= CLASSPK_COLUMN_BITMASK;

		if (!_setOriginalClassPK) {
			_setOriginalClassPK = true;

			_originalClassPK = _classPK;
		}

		_classPK = classPK;
	}

	public long getOriginalClassPK() {
		return _originalClassPK;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_name = name;
	}

	@JSON
	@Override
	public String getDescription() {
		if (_description == null) {
			return "";
		}
		else {
			return _description;
		}
	}

	@Override
	public void setDescription(String description) {
		_description = description;
	}

	@JSON
	@Override
	public String getTypeSettings() {
		if (_typeSettings == null) {
			return "";
		}
		else {
			return _typeSettings;
		}
	}

	@Override
	public void setTypeSettings(String typeSettings) {
		_typeSettings = typeSettings;
	}

	@JSON
	@Override
	public int getStatus() {
		return _status;
	}

	@Override
	public void setStatus(int status) {
		_columnBitmask |= STATUS_COLUMN_BITMASK;

		if (!_setOriginalStatus) {
			_setOriginalStatus = true;

			_originalStatus = _status;
		}

		_status = status;
	}

	public int getOriginalStatus() {
		return _originalStatus;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(SegmentsExperiment.class.getName()),
			getClassNameId());
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), SegmentsExperiment.class.getName(),
			getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public SegmentsExperiment toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, SegmentsExperiment>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		SegmentsExperimentImpl segmentsExperimentImpl =
			new SegmentsExperimentImpl();

		segmentsExperimentImpl.setUuid(getUuid());
		segmentsExperimentImpl.setSegmentsExperimentId(
			getSegmentsExperimentId());
		segmentsExperimentImpl.setGroupId(getGroupId());
		segmentsExperimentImpl.setCompanyId(getCompanyId());
		segmentsExperimentImpl.setUserId(getUserId());
		segmentsExperimentImpl.setUserName(getUserName());
		segmentsExperimentImpl.setCreateDate(getCreateDate());
		segmentsExperimentImpl.setModifiedDate(getModifiedDate());
		segmentsExperimentImpl.setSegmentsEntryId(getSegmentsEntryId());
		segmentsExperimentImpl.setSegmentsExperienceId(
			getSegmentsExperienceId());
		segmentsExperimentImpl.setSegmentsExperimentKey(
			getSegmentsExperimentKey());
		segmentsExperimentImpl.setClassNameId(getClassNameId());
		segmentsExperimentImpl.setClassPK(getClassPK());
		segmentsExperimentImpl.setName(getName());
		segmentsExperimentImpl.setDescription(getDescription());
		segmentsExperimentImpl.setTypeSettings(getTypeSettings());
		segmentsExperimentImpl.setStatus(getStatus());

		segmentsExperimentImpl.resetOriginalValues();

		return segmentsExperimentImpl;
	}

	@Override
	public int compareTo(SegmentsExperiment segmentsExperiment) {
		int value = 0;

		value = DateUtil.compareTo(
			getCreateDate(), segmentsExperiment.getCreateDate());

		value = value * -1;

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SegmentsExperiment)) {
			return false;
		}

		SegmentsExperiment segmentsExperiment = (SegmentsExperiment)obj;

		long primaryKey = segmentsExperiment.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public void resetOriginalValues() {
		SegmentsExperimentModelImpl segmentsExperimentModelImpl = this;

		segmentsExperimentModelImpl._originalUuid =
			segmentsExperimentModelImpl._uuid;

		segmentsExperimentModelImpl._originalGroupId =
			segmentsExperimentModelImpl._groupId;

		segmentsExperimentModelImpl._setOriginalGroupId = false;

		segmentsExperimentModelImpl._originalCompanyId =
			segmentsExperimentModelImpl._companyId;

		segmentsExperimentModelImpl._setOriginalCompanyId = false;

		segmentsExperimentModelImpl._setModifiedDate = false;

		segmentsExperimentModelImpl._originalSegmentsExperienceId =
			segmentsExperimentModelImpl._segmentsExperienceId;

		segmentsExperimentModelImpl._setOriginalSegmentsExperienceId = false;

		segmentsExperimentModelImpl._originalSegmentsExperimentKey =
			segmentsExperimentModelImpl._segmentsExperimentKey;

		segmentsExperimentModelImpl._originalClassNameId =
			segmentsExperimentModelImpl._classNameId;

		segmentsExperimentModelImpl._setOriginalClassNameId = false;

		segmentsExperimentModelImpl._originalClassPK =
			segmentsExperimentModelImpl._classPK;

		segmentsExperimentModelImpl._setOriginalClassPK = false;

		segmentsExperimentModelImpl._originalStatus =
			segmentsExperimentModelImpl._status;

		segmentsExperimentModelImpl._setOriginalStatus = false;

		segmentsExperimentModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<SegmentsExperiment> toCacheModel() {
		SegmentsExperimentCacheModel segmentsExperimentCacheModel =
			new SegmentsExperimentCacheModel();

		segmentsExperimentCacheModel.uuid = getUuid();

		String uuid = segmentsExperimentCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			segmentsExperimentCacheModel.uuid = null;
		}

		segmentsExperimentCacheModel.segmentsExperimentId =
			getSegmentsExperimentId();

		segmentsExperimentCacheModel.groupId = getGroupId();

		segmentsExperimentCacheModel.companyId = getCompanyId();

		segmentsExperimentCacheModel.userId = getUserId();

		segmentsExperimentCacheModel.userName = getUserName();

		String userName = segmentsExperimentCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			segmentsExperimentCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			segmentsExperimentCacheModel.createDate = createDate.getTime();
		}
		else {
			segmentsExperimentCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			segmentsExperimentCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			segmentsExperimentCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		segmentsExperimentCacheModel.segmentsEntryId = getSegmentsEntryId();

		segmentsExperimentCacheModel.segmentsExperienceId =
			getSegmentsExperienceId();

		segmentsExperimentCacheModel.segmentsExperimentKey =
			getSegmentsExperimentKey();

		String segmentsExperimentKey =
			segmentsExperimentCacheModel.segmentsExperimentKey;

		if ((segmentsExperimentKey != null) &&
			(segmentsExperimentKey.length() == 0)) {

			segmentsExperimentCacheModel.segmentsExperimentKey = null;
		}

		segmentsExperimentCacheModel.classNameId = getClassNameId();

		segmentsExperimentCacheModel.classPK = getClassPK();

		segmentsExperimentCacheModel.name = getName();

		String name = segmentsExperimentCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			segmentsExperimentCacheModel.name = null;
		}

		segmentsExperimentCacheModel.description = getDescription();

		String description = segmentsExperimentCacheModel.description;

		if ((description != null) && (description.length() == 0)) {
			segmentsExperimentCacheModel.description = null;
		}

		segmentsExperimentCacheModel.typeSettings = getTypeSettings();

		String typeSettings = segmentsExperimentCacheModel.typeSettings;

		if ((typeSettings != null) && (typeSettings.length() == 0)) {
			segmentsExperimentCacheModel.typeSettings = null;
		}

		segmentsExperimentCacheModel.status = getStatus();

		return segmentsExperimentCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<SegmentsExperiment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<SegmentsExperiment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsExperiment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((SegmentsExperiment)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<SegmentsExperiment, Object>>
			attributeGetterFunctions = getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<SegmentsExperiment, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<SegmentsExperiment, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((SegmentsExperiment)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, SegmentsExperiment>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private static boolean _entityCacheEnabled;
	private static boolean _finderCacheEnabled;

	private String _uuid;
	private String _originalUuid;
	private long _segmentsExperimentId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _segmentsEntryId;
	private long _segmentsExperienceId;
	private long _originalSegmentsExperienceId;
	private boolean _setOriginalSegmentsExperienceId;
	private String _segmentsExperimentKey;
	private String _originalSegmentsExperimentKey;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classPK;
	private long _originalClassPK;
	private boolean _setOriginalClassPK;
	private String _name;
	private String _description;
	private String _typeSettings;
	private int _status;
	private int _originalStatus;
	private boolean _setOriginalStatus;
	private long _columnBitmask;
	private SegmentsExperiment _escapedModel;

}