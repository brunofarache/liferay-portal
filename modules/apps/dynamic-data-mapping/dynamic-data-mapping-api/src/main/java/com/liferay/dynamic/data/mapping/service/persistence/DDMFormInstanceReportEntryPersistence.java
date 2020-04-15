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

import com.liferay.dynamic.data.mapping.exception.NoSuchFormInstanceReportEntryException;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the ddm form instance report entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntryUtil
 * @generated
 */
@ProviderType
public interface DDMFormInstanceReportEntryPersistence
	extends BasePersistence<DDMFormInstanceReportEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDMFormInstanceReportEntryUtil} to access the ddm form instance report entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or throws a <code>NoSuchFormInstanceReportEntryException</code> if it could not be found.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a matching ddm form instance report entry could not be found
	 */
	public DDMFormInstanceReportEntry findByFormInstanceId(long formInstanceId)
		throws NoSuchFormInstanceReportEntryException;

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report entry, or <code>null</code> if a matching ddm form instance report entry could not be found
	 */
	public DDMFormInstanceReportEntry fetchByFormInstanceId(
		long formInstanceId);

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm form instance report entry, or <code>null</code> if a matching ddm form instance report entry could not be found
	 */
	public DDMFormInstanceReportEntry fetchByFormInstanceId(
		long formInstanceId, boolean useFinderCache);

	/**
	 * Removes the ddm form instance report entry where formInstanceId = &#63; from the database.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the ddm form instance report entry that was removed
	 */
	public DDMFormInstanceReportEntry removeByFormInstanceId(
			long formInstanceId)
		throws NoSuchFormInstanceReportEntryException;

	/**
	 * Returns the number of ddm form instance report entries where formInstanceId = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the number of matching ddm form instance report entries
	 */
	public int countByFormInstanceId(long formInstanceId);

	/**
	 * Caches the ddm form instance report entry in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 */
	public void cacheResult(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry);

	/**
	 * Caches the ddm form instance report entries in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReportEntries the ddm form instance report entries
	 */
	public void cacheResult(
		java.util.List<DDMFormInstanceReportEntry>
			ddmFormInstanceReportEntries);

	/**
	 * Creates a new ddm form instance report entry with the primary key. Does not add the ddm form instance report entry to the database.
	 *
	 * @param formInstanceReportEntryId the primary key for the new ddm form instance report entry
	 * @return the new ddm form instance report entry
	 */
	public DDMFormInstanceReportEntry create(long formInstanceReportEntryId);

	/**
	 * Removes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	public DDMFormInstanceReportEntry remove(long formInstanceReportEntryId)
		throws NoSuchFormInstanceReportEntryException;

	public DDMFormInstanceReportEntry updateImpl(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry);

	/**
	 * Returns the ddm form instance report entry with the primary key or throws a <code>NoSuchFormInstanceReportEntryException</code> if it could not be found.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	public DDMFormInstanceReportEntry findByPrimaryKey(
			long formInstanceReportEntryId)
		throws NoSuchFormInstanceReportEntryException;

	/**
	 * Returns the ddm form instance report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry, or <code>null</code> if a ddm form instance report entry with the primary key could not be found
	 */
	public DDMFormInstanceReportEntry fetchByPrimaryKey(
		long formInstanceReportEntryId);

	/**
	 * Returns all the ddm form instance report entries.
	 *
	 * @return the ddm form instance report entries
	 */
	public java.util.List<DDMFormInstanceReportEntry> findAll();

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
	public java.util.List<DDMFormInstanceReportEntry> findAll(
		int start, int end);

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
	public java.util.List<DDMFormInstanceReportEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DDMFormInstanceReportEntry> orderByComparator);

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
	public java.util.List<DDMFormInstanceReportEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<DDMFormInstanceReportEntry> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the ddm form instance report entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of ddm form instance report entries.
	 *
	 * @return the number of ddm form instance report entries
	 */
	public int countAll();

}