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

package com.liferay.portal.tools.service.builder.test.service.base;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.version.VersionService;
import com.liferay.portal.kernel.service.version.VersionServiceListener;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;
import com.liferay.portal.tools.service.builder.test.model.VersionedEntry;
import com.liferay.portal.tools.service.builder.test.model.VersionedEntryVersion;
import com.liferay.portal.tools.service.builder.test.service.VersionedEntryLocalService;
import com.liferay.portal.tools.service.builder.test.service.persistence.VersionedEntryPersistence;
import com.liferay.portal.tools.service.builder.test.service.persistence.VersionedEntryVersionPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the versioned entry local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.tools.service.builder.test.service.impl.VersionedEntryLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.tools.service.builder.test.service.impl.VersionedEntryLocalServiceImpl
 * @generated
 */
public abstract class VersionedEntryLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements VersionedEntryLocalService, IdentifiableOSGiService,
			   VersionService<VersionedEntry, VersionedEntryVersion> {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>VersionedEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.tools.service.builder.test.service.VersionedEntryLocalServiceUtil</code>.
	 */

	/**
	 * Adds the versioned entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionedEntry the versioned entry
	 * @return the versioned entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public VersionedEntry addVersionedEntry(VersionedEntry versionedEntry) {
		versionedEntry.setNew(true);

		return versionedEntryPersistence.update(versionedEntry);
	}

	/**
	 * Creates a new versioned entry. Does not add the versioned entry to the database.
	 *
	 * @return the new versioned entry
	 */
	@Override
	@Transactional(enabled = false)
	public VersionedEntry create() {
		long primaryKey = counterLocalService.increment(
			VersionedEntry.class.getName());

		VersionedEntry draftVersionedEntry = versionedEntryPersistence.create(
			primaryKey);

		draftVersionedEntry.setHeadId(primaryKey);

		return draftVersionedEntry;
	}

	/**
	 * Deletes the versioned entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionedEntryId the primary key of the versioned entry
	 * @return the versioned entry that was removed
	 * @throws PortalException if a versioned entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public VersionedEntry deleteVersionedEntry(long versionedEntryId)
		throws PortalException {

		VersionedEntry versionedEntry =
			versionedEntryPersistence.fetchByPrimaryKey(versionedEntryId);

		if (versionedEntry != null) {
			delete(versionedEntry);
		}

		return versionedEntry;
	}

	/**
	 * Deletes the versioned entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param versionedEntry the versioned entry
	 * @return the versioned entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public VersionedEntry deleteVersionedEntry(VersionedEntry versionedEntry) {
		try {
			delete(versionedEntry);

			return versionedEntry;
		}
		catch (PortalException pe) {
			throw new SystemException(pe);
		}
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			VersionedEntry.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return versionedEntryPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return versionedEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return versionedEntryPersistence.findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return versionedEntryPersistence.countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection) {

		return versionedEntryPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public VersionedEntry fetchVersionedEntry(long versionedEntryId) {
		return versionedEntryPersistence.fetchByPrimaryKey(versionedEntryId);
	}

	/**
	 * Returns the versioned entry with the primary key.
	 *
	 * @param versionedEntryId the primary key of the versioned entry
	 * @return the versioned entry
	 * @throws PortalException if a versioned entry with the primary key could not be found
	 */
	@Override
	public VersionedEntry getVersionedEntry(long versionedEntryId)
		throws PortalException {

		return versionedEntryPersistence.findByPrimaryKey(versionedEntryId);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(versionedEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(VersionedEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("versionedEntryId");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(
			versionedEntryLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(VersionedEntry.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName(
			"versionedEntryId");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(versionedEntryLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(VersionedEntry.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("versionedEntryId");
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return versionedEntryLocalService.deleteVersionedEntry(
			(VersionedEntry)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return versionedEntryPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the versioned entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.tools.service.builder.test.model.impl.VersionedEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of versioned entries
	 * @param end the upper bound of the range of versioned entries (not inclusive)
	 * @return the range of versioned entries
	 */
	@Override
	public List<VersionedEntry> getVersionedEntries(int start, int end) {
		return versionedEntryPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of versioned entries.
	 *
	 * @return the number of versioned entries
	 */
	@Override
	public int getVersionedEntriesCount() {
		return versionedEntryPersistence.countAll();
	}

	/**
	 * Updates the versioned entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param versionedEntry the versioned entry
	 * @return the versioned entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public VersionedEntry updateVersionedEntry(
			VersionedEntry draftVersionedEntry)
		throws PortalException {

		return updateDraft(draftVersionedEntry);
	}

	/**
	 * Returns the versioned entry local service.
	 *
	 * @return the versioned entry local service
	 */
	public VersionedEntryLocalService getVersionedEntryLocalService() {
		return versionedEntryLocalService;
	}

	/**
	 * Sets the versioned entry local service.
	 *
	 * @param versionedEntryLocalService the versioned entry local service
	 */
	public void setVersionedEntryLocalService(
		VersionedEntryLocalService versionedEntryLocalService) {

		this.versionedEntryLocalService = versionedEntryLocalService;
	}

	/**
	 * Returns the versioned entry persistence.
	 *
	 * @return the versioned entry persistence
	 */
	public VersionedEntryPersistence getVersionedEntryPersistence() {
		return versionedEntryPersistence;
	}

	/**
	 * Sets the versioned entry persistence.
	 *
	 * @param versionedEntryPersistence the versioned entry persistence
	 */
	public void setVersionedEntryPersistence(
		VersionedEntryPersistence versionedEntryPersistence) {

		this.versionedEntryPersistence = versionedEntryPersistence;
	}

	/**
	 * Returns the counter local service.
	 *
	 * @return the counter local service
	 */
	public com.liferay.counter.kernel.service.CounterLocalService
		getCounterLocalService() {

		return counterLocalService;
	}

	/**
	 * Sets the counter local service.
	 *
	 * @param counterLocalService the counter local service
	 */
	public void setCounterLocalService(
		com.liferay.counter.kernel.service.CounterLocalService
			counterLocalService) {

		this.counterLocalService = counterLocalService;
	}

	/**
	 * Returns the versioned entry version persistence.
	 *
	 * @return the versioned entry version persistence
	 */
	public VersionedEntryVersionPersistence
		getVersionedEntryVersionPersistence() {

		return versionedEntryVersionPersistence;
	}

	/**
	 * Sets the versioned entry version persistence.
	 *
	 * @param versionedEntryVersionPersistence the versioned entry version persistence
	 */
	public void setVersionedEntryVersionPersistence(
		VersionedEntryVersionPersistence versionedEntryVersionPersistence) {

		this.versionedEntryVersionPersistence =
			versionedEntryVersionPersistence;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.tools.service.builder.test.model.VersionedEntry",
			versionedEntryLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.tools.service.builder.test.model.VersionedEntry");
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public VersionedEntry checkout(
			VersionedEntry publishedVersionedEntry, int version)
		throws PortalException {

		if (!publishedVersionedEntry.isHead()) {
			throw new IllegalArgumentException(
				"Unable to checkout with unpublished changes " +
					publishedVersionedEntry.getHeadId());
		}

		VersionedEntry draftVersionedEntry =
			versionedEntryPersistence.fetchByHeadId(
				publishedVersionedEntry.getPrimaryKey());

		if (draftVersionedEntry != null) {
			throw new IllegalArgumentException(
				"Unable to checkout with unpublished changes " +
					publishedVersionedEntry.getPrimaryKey());
		}

		VersionedEntryVersion versionedEntryVersion = getVersion(
			publishedVersionedEntry, version);

		draftVersionedEntry = _createDraft(publishedVersionedEntry);

		versionedEntryVersion.populateVersionedModel(draftVersionedEntry);

		draftVersionedEntry = versionedEntryPersistence.update(
			draftVersionedEntry);

		for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterCheckout(draftVersionedEntry, version);
		}

		return draftVersionedEntry;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public VersionedEntry delete(VersionedEntry publishedVersionedEntry)
		throws PortalException {

		if (!publishedVersionedEntry.isHead()) {
			throw new IllegalArgumentException(
				"VersionedEntry is a draft " +
					publishedVersionedEntry.getPrimaryKey());
		}

		VersionedEntry draftVersionedEntry =
			versionedEntryPersistence.fetchByHeadId(
				publishedVersionedEntry.getPrimaryKey());

		if (draftVersionedEntry != null) {
			deleteDraft(draftVersionedEntry);
		}

		for (VersionedEntryVersion versionedEntryVersion :
				getVersions(publishedVersionedEntry)) {

			versionedEntryVersionPersistence.remove(versionedEntryVersion);
		}

		versionedEntryPersistence.remove(publishedVersionedEntry);

		for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDelete(publishedVersionedEntry);
		}

		return publishedVersionedEntry;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public VersionedEntry deleteDraft(VersionedEntry draftVersionedEntry)
		throws PortalException {

		if (draftVersionedEntry.isHead()) {
			throw new IllegalArgumentException(
				"VersionedEntry is not a draft " +
					draftVersionedEntry.getPrimaryKey());
		}

		versionedEntryPersistence.remove(draftVersionedEntry);

		for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDeleteDraft(draftVersionedEntry);
		}

		return draftVersionedEntry;
	}

	@Override
	public VersionedEntryVersion deleteVersion(
			VersionedEntryVersion versionedEntryVersion)
		throws PortalException {

		VersionedEntryVersion latestVersionedEntryVersion =
			versionedEntryVersionPersistence.findByVersionedEntryId_First(
				versionedEntryVersion.getVersionedModelId(), null);

		if (latestVersionedEntryVersion.getVersion() ==
				versionedEntryVersion.getVersion()) {

			throw new IllegalArgumentException(
				"Unable to delete latest version " +
					versionedEntryVersion.getVersion());
		}

		versionedEntryVersion = versionedEntryVersionPersistence.remove(
			versionedEntryVersion);

		for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDeleteVersion(versionedEntryVersion);
		}

		return versionedEntryVersion;
	}

	@Override
	public VersionedEntry fetchDraft(VersionedEntry versionedEntry) {
		if (versionedEntry.isHead()) {
			return versionedEntryPersistence.fetchByHeadId(
				versionedEntry.getPrimaryKey());
		}

		return versionedEntry;
	}

	@Override
	public VersionedEntry fetchDraft(long primaryKey) {
		return versionedEntryPersistence.fetchByHeadId(primaryKey);
	}

	@Override
	public VersionedEntryVersion fetchLatestVersion(
		VersionedEntry versionedEntry) {

		long primaryKey = versionedEntry.getHeadId();

		if (versionedEntry.isHead()) {
			primaryKey = versionedEntry.getPrimaryKey();
		}

		return versionedEntryVersionPersistence.fetchByVersionedEntryId_First(
			primaryKey, null);
	}

	@Override
	public VersionedEntry fetchPublished(VersionedEntry versionedEntry) {
		if (versionedEntry.isHead()) {
			return versionedEntry;
		}

		if (versionedEntry.getHeadId() == versionedEntry.getPrimaryKey()) {
			return null;
		}

		return versionedEntryPersistence.fetchByPrimaryKey(
			versionedEntry.getHeadId());
	}

	@Override
	public VersionedEntry fetchPublished(long primaryKey) {
		VersionedEntry versionedEntry =
			versionedEntryPersistence.fetchByPrimaryKey(primaryKey);

		if ((versionedEntry == null) ||
			(versionedEntry.getHeadId() == versionedEntry.getPrimaryKey())) {

			return null;
		}

		return versionedEntry;
	}

	@Override
	public VersionedEntry getDraft(VersionedEntry versionedEntry)
		throws PortalException {

		if (!versionedEntry.isHead()) {
			return versionedEntry;
		}

		VersionedEntry draftVersionedEntry =
			versionedEntryPersistence.fetchByHeadId(
				versionedEntry.getPrimaryKey());

		if (draftVersionedEntry == null) {
			draftVersionedEntry = versionedEntryLocalService.updateDraft(
				_createDraft(versionedEntry));
		}

		return draftVersionedEntry;
	}

	@Override
	public VersionedEntry getDraft(long primaryKey) throws PortalException {
		VersionedEntry draftVersionedEntry =
			versionedEntryPersistence.fetchByHeadId(primaryKey);

		if (draftVersionedEntry == null) {
			VersionedEntry versionedEntry =
				versionedEntryPersistence.findByPrimaryKey(primaryKey);

			draftVersionedEntry = versionedEntryLocalService.updateDraft(
				_createDraft(versionedEntry));
		}

		return draftVersionedEntry;
	}

	@Override
	public VersionedEntryVersion getVersion(
			VersionedEntry versionedEntry, int version)
		throws PortalException {

		long primaryKey = versionedEntry.getHeadId();

		if (versionedEntry.isHead()) {
			primaryKey = versionedEntry.getPrimaryKey();
		}

		return versionedEntryVersionPersistence.findByVersionedEntryId_Version(
			primaryKey, version);
	}

	@Override
	public List<VersionedEntryVersion> getVersions(
		VersionedEntry versionedEntry) {

		long primaryKey = versionedEntry.getPrimaryKey();

		if (!versionedEntry.isHead()) {
			if (versionedEntry.getHeadId() == versionedEntry.getPrimaryKey()) {
				return Collections.emptyList();
			}

			primaryKey = versionedEntry.getHeadId();
		}

		return versionedEntryVersionPersistence.findByVersionedEntryId(
			primaryKey);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public VersionedEntry publishDraft(VersionedEntry draftVersionedEntry)
		throws PortalException {

		if (draftVersionedEntry.isHead()) {
			throw new IllegalArgumentException(
				"Can only publish drafts " +
					draftVersionedEntry.getPrimaryKey());
		}

		VersionedEntry headVersionedEntry = null;

		int version = 1;

		if (draftVersionedEntry.getHeadId() ==
				draftVersionedEntry.getPrimaryKey()) {

			headVersionedEntry = create();

			draftVersionedEntry.setHeadId(headVersionedEntry.getPrimaryKey());
		}
		else {
			headVersionedEntry = versionedEntryPersistence.findByPrimaryKey(
				draftVersionedEntry.getHeadId());

			VersionedEntryVersion latestVersionedEntryVersion =
				versionedEntryVersionPersistence.findByVersionedEntryId_First(
					draftVersionedEntry.getHeadId(), null);

			version = latestVersionedEntryVersion.getVersion() + 1;
		}

		VersionedEntryVersion versionedEntryVersion =
			versionedEntryVersionPersistence.create(
				counterLocalService.increment(
					VersionedEntryVersion.class.getName()));

		versionedEntryVersion.setVersion(version);
		versionedEntryVersion.setVersionedModelId(
			headVersionedEntry.getPrimaryKey());

		draftVersionedEntry.populateVersionModel(versionedEntryVersion);

		versionedEntryVersionPersistence.update(versionedEntryVersion);

		versionedEntryVersion.populateVersionedModel(headVersionedEntry);

		headVersionedEntry.setHeadId(-headVersionedEntry.getPrimaryKey());

		headVersionedEntry = versionedEntryPersistence.update(
			headVersionedEntry);

		for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterPublishDraft(
				draftVersionedEntry, version);
		}

		deleteDraft(draftVersionedEntry);

		return headVersionedEntry;
	}

	@Override
	public void registerListener(
		VersionServiceListener<VersionedEntry, VersionedEntryVersion>
			versionServiceListener) {

		_versionServiceListeners.add(versionServiceListener);
	}

	@Override
	public void unregisterListener(
		VersionServiceListener<VersionedEntry, VersionedEntryVersion>
			versionServiceListener) {

		_versionServiceListeners.remove(versionServiceListener);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public VersionedEntry updateDraft(VersionedEntry draftVersionedEntry)
		throws PortalException {

		if (draftVersionedEntry.isHead()) {
			throw new IllegalArgumentException(
				"Can only update draft entries " +
					draftVersionedEntry.getPrimaryKey());
		}

		VersionedEntry previousVersionedEntry =
			versionedEntryPersistence.fetchByPrimaryKey(
				draftVersionedEntry.getPrimaryKey());

		draftVersionedEntry = versionedEntryPersistence.update(
			draftVersionedEntry);

		if (previousVersionedEntry == null) {
			for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
					versionServiceListener : _versionServiceListeners) {

				versionServiceListener.afterCreateDraft(draftVersionedEntry);
			}
		}
		else {
			for (VersionServiceListener<VersionedEntry, VersionedEntryVersion>
					versionServiceListener : _versionServiceListeners) {

				versionServiceListener.afterUpdateDraft(draftVersionedEntry);
			}
		}

		return draftVersionedEntry;
	}

	private VersionedEntry _createDraft(VersionedEntry publishedVersionedEntry)
		throws PortalException {

		VersionedEntry draftVersionedEntry = create();

		draftVersionedEntry.setHeadId(publishedVersionedEntry.getPrimaryKey());
		draftVersionedEntry.setGroupId(publishedVersionedEntry.getGroupId());

		draftVersionedEntry.resetOriginalValues();

		return draftVersionedEntry;
	}

	private final Set
		<VersionServiceListener<VersionedEntry, VersionedEntryVersion>>
			_versionServiceListeners = Collections.newSetFromMap(
				new ConcurrentHashMap
					<VersionServiceListener
						<VersionedEntry, VersionedEntryVersion>,
					 Boolean>());

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return VersionedEntryLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return VersionedEntry.class;
	}

	protected String getModelClassName() {
		return VersionedEntry.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = versionedEntryPersistence.getDataSource();

			DB db = DBManagerUtil.getDB();

			sql = db.buildSQL(sql);
			sql = PortalUtil.transformSQL(sql);

			SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(
				dataSource, sql);

			sqlUpdate.update();
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(type = VersionedEntryLocalService.class)
	protected VersionedEntryLocalService versionedEntryLocalService;

	@BeanReference(type = VersionedEntryPersistence.class)
	protected VersionedEntryPersistence versionedEntryPersistence;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(type = VersionedEntryVersionPersistence.class)
	protected VersionedEntryVersionPersistence versionedEntryVersionPersistence;

	@ServiceReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}