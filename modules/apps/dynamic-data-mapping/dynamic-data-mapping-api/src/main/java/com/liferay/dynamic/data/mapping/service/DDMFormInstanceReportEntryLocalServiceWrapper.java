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

package com.liferay.dynamic.data.mapping.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DDMFormInstanceReportEntryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntryLocalService
 * @generated
 */
public class DDMFormInstanceReportEntryLocalServiceWrapper
	implements DDMFormInstanceReportEntryLocalService,
			   ServiceWrapper<DDMFormInstanceReportEntryLocalService> {

	public DDMFormInstanceReportEntryLocalServiceWrapper(
		DDMFormInstanceReportEntryLocalService
			ddmFormInstanceReportEntryLocalService) {

		_ddmFormInstanceReportEntryLocalService =
			ddmFormInstanceReportEntryLocalService;
	}

	/**
	 * Adds the ddm form instance report entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was added
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
		addDDMFormInstanceReportEntry(
			com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
				ddmFormInstanceReportEntry) {

		return _ddmFormInstanceReportEntryLocalService.
			addDDMFormInstanceReportEntry(ddmFormInstanceReportEntry);
	}

	/**
	 * Creates a new ddm form instance report entry with the primary key. Does not add the ddm form instance report entry to the database.
	 *
	 * @param formInstanceReportEntryId the primary key for the new ddm form instance report entry
	 * @return the new ddm form instance report entry
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
		createDDMFormInstanceReportEntry(long formInstanceReportEntryId) {

		return _ddmFormInstanceReportEntryLocalService.
			createDDMFormInstanceReportEntry(formInstanceReportEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceReportEntryLocalService.createPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Deletes the ddm form instance report entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
		deleteDDMFormInstanceReportEntry(
			com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
				ddmFormInstanceReportEntry) {

		return _ddmFormInstanceReportEntryLocalService.
			deleteDDMFormInstanceReportEntry(ddmFormInstanceReportEntry);
	}

	/**
	 * Deletes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws PortalException if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			deleteDDMFormInstanceReportEntry(long formInstanceReportEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceReportEntryLocalService.
			deleteDDMFormInstanceReportEntry(formInstanceReportEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceReportEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _ddmFormInstanceReportEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _ddmFormInstanceReportEntryLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _ddmFormInstanceReportEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportEntryModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _ddmFormInstanceReportEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _ddmFormInstanceReportEntryLocalService.dynamicQueryCount(
			dynamicQuery);
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
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _ddmFormInstanceReportEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
		fetchDDMFormInstanceReportEntry(long formInstanceReportEntryId) {

		return _ddmFormInstanceReportEntryLocalService.
			fetchDDMFormInstanceReportEntry(formInstanceReportEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _ddmFormInstanceReportEntryLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the ddm form instance report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance report entries
	 * @param end the upper bound of the range of ddm form instance report entries (not inclusive)
	 * @return the range of ddm form instance report entries
	 */
	@Override
	public java.util.List
		<com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry>
			getDDMFormInstanceReportEntries(int start, int end) {

		return _ddmFormInstanceReportEntryLocalService.
			getDDMFormInstanceReportEntries(start, end);
	}

	/**
	 * Returns the number of ddm form instance report entries.
	 *
	 * @return the number of ddm form instance report entries
	 */
	@Override
	public int getDDMFormInstanceReportEntriesCount() {
		return _ddmFormInstanceReportEntryLocalService.
			getDDMFormInstanceReportEntriesCount();
	}

	/**
	 * Returns the ddm form instance report entry with the primary key.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws PortalException if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			getDDMFormInstanceReportEntry(long formInstanceReportEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceReportEntryLocalService.
			getDDMFormInstanceReportEntry(formInstanceReportEntryId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _ddmFormInstanceReportEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _ddmFormInstanceReportEntryLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _ddmFormInstanceReportEntryLocalService.getPersistedModel(
			primaryKeyObj);
	}

	/**
	 * Updates the ddm form instance report entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was updated
	 */
	@Override
	public com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
		updateDDMFormInstanceReportEntry(
			com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
				ddmFormInstanceReportEntry) {

		return _ddmFormInstanceReportEntryLocalService.
			updateDDMFormInstanceReportEntry(ddmFormInstanceReportEntry);
	}

	@Override
	public DDMFormInstanceReportEntryLocalService getWrappedService() {
		return _ddmFormInstanceReportEntryLocalService;
	}

	@Override
	public void setWrappedService(
		DDMFormInstanceReportEntryLocalService
			ddmFormInstanceReportEntryLocalService) {

		_ddmFormInstanceReportEntryLocalService =
			ddmFormInstanceReportEntryLocalService;
	}

	private DDMFormInstanceReportEntryLocalService
		_ddmFormInstanceReportEntryLocalService;

}