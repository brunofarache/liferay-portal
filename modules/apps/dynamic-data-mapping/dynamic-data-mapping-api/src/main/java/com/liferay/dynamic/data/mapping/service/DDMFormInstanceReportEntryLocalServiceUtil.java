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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for DDMFormInstanceReportEntry. This utility wraps
 * <code>com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceReportEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntryLocalService
 * @generated
 */
public class DDMFormInstanceReportEntryLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceReportEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the ddm form instance report entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was added
	 */
	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			addDDMFormInstanceReportEntry(
				com.liferay.dynamic.data.mapping.model.
					DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		return getService().addDDMFormInstanceReportEntry(
			ddmFormInstanceReportEntry);
	}

	/**
	 * Creates a new ddm form instance report entry with the primary key. Does not add the ddm form instance report entry to the database.
	 *
	 * @param formInstanceReportEntryId the primary key for the new ddm form instance report entry
	 * @return the new ddm form instance report entry
	 */
	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			createDDMFormInstanceReportEntry(long formInstanceReportEntryId) {

		return getService().createDDMFormInstanceReportEntry(
			formInstanceReportEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			createPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the ddm form instance report entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 */
	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			deleteDDMFormInstanceReportEntry(
				com.liferay.dynamic.data.mapping.model.
					DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		return getService().deleteDDMFormInstanceReportEntry(
			ddmFormInstanceReportEntry);
	}

	/**
	 * Deletes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws PortalException if a ddm form instance report entry with the primary key could not be found
	 */
	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
				deleteDDMFormInstanceReportEntry(long formInstanceReportEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteDDMFormInstanceReportEntry(
			formInstanceReportEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			fetchDDMFormInstanceReportEntry(long formInstanceReportEntryId) {

		return getService().fetchDDMFormInstanceReportEntry(
			formInstanceReportEntryId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
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
	public static java.util.List
		<com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry>
			getDDMFormInstanceReportEntries(int start, int end) {

		return getService().getDDMFormInstanceReportEntries(start, end);
	}

	/**
	 * Returns the number of ddm form instance report entries.
	 *
	 * @return the number of ddm form instance report entries
	 */
	public static int getDDMFormInstanceReportEntriesCount() {
		return getService().getDDMFormInstanceReportEntriesCount();
	}

	/**
	 * Returns the ddm form instance report entry with the primary key.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws PortalException if a ddm form instance report entry with the primary key could not be found
	 */
	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
				getDDMFormInstanceReportEntry(long formInstanceReportEntryId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getDDMFormInstanceReportEntry(
			formInstanceReportEntryId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the ddm form instance report entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was updated
	 */
	public static
		com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry
			updateDDMFormInstanceReportEntry(
				com.liferay.dynamic.data.mapping.model.
					DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		return getService().updateDDMFormInstanceReportEntry(
			ddmFormInstanceReportEntry);
	}

	public static DDMFormInstanceReportEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<DDMFormInstanceReportEntryLocalService,
		 DDMFormInstanceReportEntryLocalService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			DDMFormInstanceReportEntryLocalService.class);

		ServiceTracker
			<DDMFormInstanceReportEntryLocalService,
			 DDMFormInstanceReportEntryLocalService> serviceTracker =
				new ServiceTracker
					<DDMFormInstanceReportEntryLocalService,
					 DDMFormInstanceReportEntryLocalService>(
						 bundle.getBundleContext(),
						 DDMFormInstanceReportEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}