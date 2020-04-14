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

package com.liferay.dynamic.data.mapping.service.persistence;

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the ddm form instance report entry service. This utility wraps <code>com.liferay.dynamic.data.mapping.service.persistence.impl.DDMFormInstanceReportEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntryPersistence
 * @generated
 */
public class DDMFormInstanceReportEntryUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		getPersistence().clearCache(ddmFormInstanceReportEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, DDMFormInstanceReportEntry>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<DDMFormInstanceReportEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<DDMFormInstanceReportEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<DDMFormInstanceReportEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<DDMFormInstanceReportEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static DDMFormInstanceReportEntry update(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		return getPersistence().update(ddmFormInstanceReportEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static DDMFormInstanceReportEntry update(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry,
		ServiceContext serviceContext) {

		return getPersistence().update(
			ddmFormInstanceReportEntry, serviceContext);
	}

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or throws a <code>NoSuchFormInstanceReportEntryException</code> if it could not be found.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a matching ddm form instance report entry could not be found
	 */
	public static DDMFormInstanceReportEntry findByFormInstanceId(
			long formInstanceId)
		throws com.liferay.dynamic.data.mapping.exception.
			NoSuchFormInstanceReportEntryException {

		return getPersistence().findByFormInstanceId(formInstanceId);
	}

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report entry, or <code>null</code> if a matching ddm form instance report entry could not be found
	 */
	public static DDMFormInstanceReportEntry fetchByFormInstanceId(
		long formInstanceId) {

		return getPersistence().fetchByFormInstanceId(formInstanceId);
	}

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm form instance report entry, or <code>null</code> if a matching ddm form instance report entry could not be found
	 */
	public static DDMFormInstanceReportEntry fetchByFormInstanceId(
		long formInstanceId, boolean useFinderCache) {

		return getPersistence().fetchByFormInstanceId(
			formInstanceId, useFinderCache);
	}

	/**
	 * Removes the ddm form instance report entry where formInstanceId = &#63; from the database.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the ddm form instance report entry that was removed
	 */
	public static DDMFormInstanceReportEntry removeByFormInstanceId(
			long formInstanceId)
		throws com.liferay.dynamic.data.mapping.exception.
			NoSuchFormInstanceReportEntryException {

		return getPersistence().removeByFormInstanceId(formInstanceId);
	}

	/**
	 * Returns the number of ddm form instance report entries where formInstanceId = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the number of matching ddm form instance report entries
	 */
	public static int countByFormInstanceId(long formInstanceId) {
		return getPersistence().countByFormInstanceId(formInstanceId);
	}

	/**
	 * Caches the ddm form instance report entry in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 */
	public static void cacheResult(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		getPersistence().cacheResult(ddmFormInstanceReportEntry);
	}

	/**
	 * Caches the ddm form instance report entries in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReportEntries the ddm form instance report entries
	 */
	public static void cacheResult(
		List<DDMFormInstanceReportEntry> ddmFormInstanceReportEntries) {

		getPersistence().cacheResult(ddmFormInstanceReportEntries);
	}

	/**
	 * Creates a new ddm form instance report entry with the primary key. Does not add the ddm form instance report entry to the database.
	 *
	 * @param formInstanceReportEntryId the primary key for the new ddm form instance report entry
	 * @return the new ddm form instance report entry
	 */
	public static DDMFormInstanceReportEntry create(
		long formInstanceReportEntryId) {

		return getPersistence().create(formInstanceReportEntryId);
	}

	/**
	 * Removes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	public static DDMFormInstanceReportEntry remove(
			long formInstanceReportEntryId)
		throws com.liferay.dynamic.data.mapping.exception.
			NoSuchFormInstanceReportEntryException {

		return getPersistence().remove(formInstanceReportEntryId);
	}

	public static DDMFormInstanceReportEntry updateImpl(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		return getPersistence().updateImpl(ddmFormInstanceReportEntry);
	}

	/**
	 * Returns the ddm form instance report entry with the primary key or throws a <code>NoSuchFormInstanceReportEntryException</code> if it could not be found.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	public static DDMFormInstanceReportEntry findByPrimaryKey(
			long formInstanceReportEntryId)
		throws com.liferay.dynamic.data.mapping.exception.
			NoSuchFormInstanceReportEntryException {

		return getPersistence().findByPrimaryKey(formInstanceReportEntryId);
	}

	/**
	 * Returns the ddm form instance report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry, or <code>null</code> if a ddm form instance report entry with the primary key could not be found
	 */
	public static DDMFormInstanceReportEntry fetchByPrimaryKey(
		long formInstanceReportEntryId) {

		return getPersistence().fetchByPrimaryKey(formInstanceReportEntryId);
	}

	/**
	 * Returns all the ddm form instance report entries.
	 *
	 * @return the ddm form instance report entries
	 */
	public static List<DDMFormInstanceReportEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the ddm form instance report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceReportEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance report entries
	 * @param end the upper bound of the range of ddm form instance report entries (not inclusive)
	 * @return the range of ddm form instance report entries
	 */
	public static List<DDMFormInstanceReportEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the ddm form instance report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceReportEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance report entries
	 * @param end the upper bound of the range of ddm form instance report entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ddm form instance report entries
	 */
	public static List<DDMFormInstanceReportEntry> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceReportEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the ddm form instance report entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DDMFormInstanceReportEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of ddm form instance report entries
	 * @param end the upper bound of the range of ddm form instance report entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of ddm form instance report entries
	 */
	public static List<DDMFormInstanceReportEntry> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceReportEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the ddm form instance report entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of ddm form instance report entries.
	 *
	 * @return the number of ddm form instance report entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static DDMFormInstanceReportEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<DDMFormInstanceReportEntryPersistence,
		 DDMFormInstanceReportEntryPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			DDMFormInstanceReportEntryPersistence.class);

		ServiceTracker
			<DDMFormInstanceReportEntryPersistence,
			 DDMFormInstanceReportEntryPersistence> serviceTracker =
				new ServiceTracker
					<DDMFormInstanceReportEntryPersistence,
					 DDMFormInstanceReportEntryPersistence>(
						 bundle.getBundleContext(),
						 DDMFormInstanceReportEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}