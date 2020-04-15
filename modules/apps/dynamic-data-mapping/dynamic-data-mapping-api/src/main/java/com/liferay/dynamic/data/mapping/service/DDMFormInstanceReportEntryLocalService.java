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

import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for DDMFormInstanceReportEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see DDMFormInstanceReportEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface DDMFormInstanceReportEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link DDMFormInstanceReportEntryLocalServiceUtil} to access the ddm form instance report entry local service. Add custom service methods to <code>com.liferay.dynamic.data.mapping.service.impl.DDMFormInstanceReportEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the ddm form instance report entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDMFormInstanceReportEntry addDDMFormInstanceReportEntry(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry);

	/**
	 * Creates a new ddm form instance report entry with the primary key. Does not add the ddm form instance report entry to the database.
	 *
	 * @param formInstanceReportEntryId the primary key for the new ddm form instance report entry
	 * @return the new ddm form instance report entry
	 */
	@Transactional(enabled = false)
	public DDMFormInstanceReportEntry createDDMFormInstanceReportEntry(
		long formInstanceReportEntryId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the ddm form instance report entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public DDMFormInstanceReportEntry deleteDDMFormInstanceReportEntry(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry);

	/**
	 * Deletes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws PortalException if a ddm form instance report entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public DDMFormInstanceReportEntry deleteDDMFormInstanceReportEntry(
			long formInstanceReportEntryId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceReportEntry fetchDDMFormInstanceReportEntry(
		long formInstanceReportEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<DDMFormInstanceReportEntry> getDDMFormInstanceReportEntries(
		int start, int end);

	/**
	 * Returns the number of ddm form instance report entries.
	 *
	 * @return the number of ddm form instance report entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getDDMFormInstanceReportEntriesCount();

	/**
	 * Returns the ddm form instance report entry with the primary key.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws PortalException if a ddm form instance report entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DDMFormInstanceReportEntry getDDMFormInstanceReportEntry(
			long formInstanceReportEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Updates the ddm form instance report entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 * @return the ddm form instance report entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public DDMFormInstanceReportEntry updateDDMFormInstanceReportEntry(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry);

}