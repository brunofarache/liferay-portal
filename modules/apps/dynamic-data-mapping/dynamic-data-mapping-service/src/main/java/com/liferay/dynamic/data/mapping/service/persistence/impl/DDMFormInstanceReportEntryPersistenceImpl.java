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

package com.liferay.dynamic.data.mapping.service.persistence.impl;

import com.liferay.dynamic.data.mapping.exception.NoSuchFormInstanceReportEntryException;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry;
import com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportEntryImpl;
import com.liferay.dynamic.data.mapping.model.impl.DDMFormInstanceReportEntryModelImpl;
import com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceReportEntryPersistence;
import com.liferay.dynamic.data.mapping.service.persistence.impl.constants.DDMPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the ddm form instance report entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@Component(service = DDMFormInstanceReportEntryPersistence.class)
public class DDMFormInstanceReportEntryPersistenceImpl
	extends BasePersistenceImpl<DDMFormInstanceReportEntry>
	implements DDMFormInstanceReportEntryPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DDMFormInstanceReportEntryUtil</code> to access the ddm form instance report entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DDMFormInstanceReportEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByFormInstanceId;
	private FinderPath _finderPathCountByFormInstanceId;

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or throws a <code>NoSuchFormInstanceReportEntryException</code> if it could not be found.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a matching ddm form instance report entry could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry findByFormInstanceId(long formInstanceId)
		throws NoSuchFormInstanceReportEntryException {

		DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
			fetchByFormInstanceId(formInstanceId);

		if (ddmFormInstanceReportEntry == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("formInstanceId=");
			sb.append(formInstanceId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchFormInstanceReportEntryException(sb.toString());
		}

		return ddmFormInstanceReportEntry;
	}

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the matching ddm form instance report entry, or <code>null</code> if a matching ddm form instance report entry could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry fetchByFormInstanceId(
		long formInstanceId) {

		return fetchByFormInstanceId(formInstanceId, true);
	}

	/**
	 * Returns the ddm form instance report entry where formInstanceId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param formInstanceId the form instance ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching ddm form instance report entry, or <code>null</code> if a matching ddm form instance report entry could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry fetchByFormInstanceId(
		long formInstanceId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {formInstanceId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByFormInstanceId, finderArgs, this);
		}

		if (result instanceof DDMFormInstanceReportEntry) {
			DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
				(DDMFormInstanceReportEntry)result;

			if (formInstanceId !=
					ddmFormInstanceReportEntry.getFormInstanceId()) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_DDMFORMINSTANCEREPORTENTRY_WHERE);

			sb.append(_FINDER_COLUMN_FORMINSTANCEID_FORMINSTANCEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceId);

				List<DDMFormInstanceReportEntry> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByFormInstanceId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {formInstanceId};
							}

							_log.warn(
								"DDMFormInstanceReportEntryPersistenceImpl.fetchByFormInstanceId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
						list.get(0);

					result = ddmFormInstanceReportEntry;

					cacheResult(ddmFormInstanceReportEntry);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByFormInstanceId, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DDMFormInstanceReportEntry)result;
		}
	}

	/**
	 * Removes the ddm form instance report entry where formInstanceId = &#63; from the database.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the ddm form instance report entry that was removed
	 */
	@Override
	public DDMFormInstanceReportEntry removeByFormInstanceId(
			long formInstanceId)
		throws NoSuchFormInstanceReportEntryException {

		DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
			findByFormInstanceId(formInstanceId);

		return remove(ddmFormInstanceReportEntry);
	}

	/**
	 * Returns the number of ddm form instance report entries where formInstanceId = &#63;.
	 *
	 * @param formInstanceId the form instance ID
	 * @return the number of matching ddm form instance report entries
	 */
	@Override
	public int countByFormInstanceId(long formInstanceId) {
		FinderPath finderPath = _finderPathCountByFormInstanceId;

		Object[] finderArgs = new Object[] {formInstanceId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_DDMFORMINSTANCEREPORTENTRY_WHERE);

			sb.append(_FINDER_COLUMN_FORMINSTANCEID_FORMINSTANCEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(formInstanceId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FORMINSTANCEID_FORMINSTANCEID_2 =
		"ddmFormInstanceReportEntry.formInstanceId = ?";

	public DDMFormInstanceReportEntryPersistenceImpl() {
		setModelClass(DDMFormInstanceReportEntry.class);

		setModelImplClass(DDMFormInstanceReportEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the ddm form instance report entry in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReportEntry the ddm form instance report entry
	 */
	@Override
	public void cacheResult(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		entityCache.putResult(
			entityCacheEnabled, DDMFormInstanceReportEntryImpl.class,
			ddmFormInstanceReportEntry.getPrimaryKey(),
			ddmFormInstanceReportEntry);

		finderCache.putResult(
			_finderPathFetchByFormInstanceId,
			new Object[] {ddmFormInstanceReportEntry.getFormInstanceId()},
			ddmFormInstanceReportEntry);

		ddmFormInstanceReportEntry.resetOriginalValues();
	}

	/**
	 * Caches the ddm form instance report entries in the entity cache if it is enabled.
	 *
	 * @param ddmFormInstanceReportEntries the ddm form instance report entries
	 */
	@Override
	public void cacheResult(
		List<DDMFormInstanceReportEntry> ddmFormInstanceReportEntries) {

		for (DDMFormInstanceReportEntry ddmFormInstanceReportEntry :
				ddmFormInstanceReportEntries) {

			if (entityCache.getResult(
					entityCacheEnabled, DDMFormInstanceReportEntryImpl.class,
					ddmFormInstanceReportEntry.getPrimaryKey()) == null) {

				cacheResult(ddmFormInstanceReportEntry);
			}
			else {
				ddmFormInstanceReportEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ddm form instance report entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DDMFormInstanceReportEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ddm form instance report entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		entityCache.removeResult(
			entityCacheEnabled, DDMFormInstanceReportEntryImpl.class,
			ddmFormInstanceReportEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(DDMFormInstanceReportEntryModelImpl)ddmFormInstanceReportEntry,
			true);
	}

	@Override
	public void clearCache(
		List<DDMFormInstanceReportEntry> ddmFormInstanceReportEntries) {

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DDMFormInstanceReportEntry ddmFormInstanceReportEntry :
				ddmFormInstanceReportEntries) {

			entityCache.removeResult(
				entityCacheEnabled, DDMFormInstanceReportEntryImpl.class,
				ddmFormInstanceReportEntry.getPrimaryKey());

			clearUniqueFindersCache(
				(DDMFormInstanceReportEntryModelImpl)ddmFormInstanceReportEntry,
				true);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				entityCacheEnabled, DDMFormInstanceReportEntryImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DDMFormInstanceReportEntryModelImpl
			ddmFormInstanceReportEntryModelImpl) {

		Object[] args = new Object[] {
			ddmFormInstanceReportEntryModelImpl.getFormInstanceId()
		};

		finderCache.putResult(
			_finderPathCountByFormInstanceId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByFormInstanceId, args,
			ddmFormInstanceReportEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DDMFormInstanceReportEntryModelImpl ddmFormInstanceReportEntryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				ddmFormInstanceReportEntryModelImpl.getFormInstanceId()
			};

			finderCache.removeResult(_finderPathCountByFormInstanceId, args);
			finderCache.removeResult(_finderPathFetchByFormInstanceId, args);
		}

		if ((ddmFormInstanceReportEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByFormInstanceId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				ddmFormInstanceReportEntryModelImpl.getOriginalFormInstanceId()
			};

			finderCache.removeResult(_finderPathCountByFormInstanceId, args);
			finderCache.removeResult(_finderPathFetchByFormInstanceId, args);
		}
	}

	/**
	 * Creates a new ddm form instance report entry with the primary key. Does not add the ddm form instance report entry to the database.
	 *
	 * @param formInstanceReportEntryId the primary key for the new ddm form instance report entry
	 * @return the new ddm form instance report entry
	 */
	@Override
	public DDMFormInstanceReportEntry create(long formInstanceReportEntryId) {
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
			new DDMFormInstanceReportEntryImpl();

		ddmFormInstanceReportEntry.setNew(true);
		ddmFormInstanceReportEntry.setPrimaryKey(formInstanceReportEntryId);

		ddmFormInstanceReportEntry.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return ddmFormInstanceReportEntry;
	}

	/**
	 * Removes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry remove(long formInstanceReportEntryId)
		throws NoSuchFormInstanceReportEntryException {

		return remove((Serializable)formInstanceReportEntryId);
	}

	/**
	 * Removes the ddm form instance report entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry that was removed
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry remove(Serializable primaryKey)
		throws NoSuchFormInstanceReportEntryException {

		Session session = null;

		try {
			session = openSession();

			DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
				(DDMFormInstanceReportEntry)session.get(
					DDMFormInstanceReportEntryImpl.class, primaryKey);

			if (ddmFormInstanceReportEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFormInstanceReportEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(ddmFormInstanceReportEntry);
		}
		catch (NoSuchFormInstanceReportEntryException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DDMFormInstanceReportEntry removeImpl(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ddmFormInstanceReportEntry)) {
				ddmFormInstanceReportEntry =
					(DDMFormInstanceReportEntry)session.get(
						DDMFormInstanceReportEntryImpl.class,
						ddmFormInstanceReportEntry.getPrimaryKeyObj());
			}

			if (ddmFormInstanceReportEntry != null) {
				session.delete(ddmFormInstanceReportEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (ddmFormInstanceReportEntry != null) {
			clearCache(ddmFormInstanceReportEntry);
		}

		return ddmFormInstanceReportEntry;
	}

	@Override
	public DDMFormInstanceReportEntry updateImpl(
		DDMFormInstanceReportEntry ddmFormInstanceReportEntry) {

		boolean isNew = ddmFormInstanceReportEntry.isNew();

		if (!(ddmFormInstanceReportEntry instanceof
				DDMFormInstanceReportEntryModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(ddmFormInstanceReportEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					ddmFormInstanceReportEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in ddmFormInstanceReportEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DDMFormInstanceReportEntry implementation " +
					ddmFormInstanceReportEntry.getClass());
		}

		DDMFormInstanceReportEntryModelImpl
			ddmFormInstanceReportEntryModelImpl =
				(DDMFormInstanceReportEntryModelImpl)ddmFormInstanceReportEntry;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (ddmFormInstanceReportEntry.getCreateDate() == null)) {
			if (serviceContext == null) {
				ddmFormInstanceReportEntry.setCreateDate(now);
			}
			else {
				ddmFormInstanceReportEntry.setCreateDate(
					serviceContext.getCreateDate(now));
			}
		}

		if (!ddmFormInstanceReportEntryModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				ddmFormInstanceReportEntry.setModifiedDate(now);
			}
			else {
				ddmFormInstanceReportEntry.setModifiedDate(
					serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (ddmFormInstanceReportEntry.isNew()) {
				session.save(ddmFormInstanceReportEntry);

				ddmFormInstanceReportEntry.setNew(false);
			}
			else {
				ddmFormInstanceReportEntry =
					(DDMFormInstanceReportEntry)session.merge(
						ddmFormInstanceReportEntry);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, DDMFormInstanceReportEntryImpl.class,
			ddmFormInstanceReportEntry.getPrimaryKey(),
			ddmFormInstanceReportEntry, false);

		clearUniqueFindersCache(ddmFormInstanceReportEntryModelImpl, false);
		cacheUniqueFindersCache(ddmFormInstanceReportEntryModelImpl);

		ddmFormInstanceReportEntry.resetOriginalValues();

		return ddmFormInstanceReportEntry;
	}

	/**
	 * Returns the ddm form instance report entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFormInstanceReportEntryException {

		DDMFormInstanceReportEntry ddmFormInstanceReportEntry =
			fetchByPrimaryKey(primaryKey);

		if (ddmFormInstanceReportEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFormInstanceReportEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return ddmFormInstanceReportEntry;
	}

	/**
	 * Returns the ddm form instance report entry with the primary key or throws a <code>NoSuchFormInstanceReportEntryException</code> if it could not be found.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry
	 * @throws NoSuchFormInstanceReportEntryException if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry findByPrimaryKey(
			long formInstanceReportEntryId)
		throws NoSuchFormInstanceReportEntryException {

		return findByPrimaryKey((Serializable)formInstanceReportEntryId);
	}

	/**
	 * Returns the ddm form instance report entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param formInstanceReportEntryId the primary key of the ddm form instance report entry
	 * @return the ddm form instance report entry, or <code>null</code> if a ddm form instance report entry with the primary key could not be found
	 */
	@Override
	public DDMFormInstanceReportEntry fetchByPrimaryKey(
		long formInstanceReportEntryId) {

		return fetchByPrimaryKey((Serializable)formInstanceReportEntryId);
	}

	/**
	 * Returns all the ddm form instance report entries.
	 *
	 * @return the ddm form instance report entries
	 */
	@Override
	public List<DDMFormInstanceReportEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<DDMFormInstanceReportEntry> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<DDMFormInstanceReportEntry> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceReportEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<DDMFormInstanceReportEntry> findAll(
		int start, int end,
		OrderByComparator<DDMFormInstanceReportEntry> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<DDMFormInstanceReportEntry> list = null;

		if (useFinderCache) {
			list = (List<DDMFormInstanceReportEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_DDMFORMINSTANCEREPORTENTRY);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_DDMFORMINSTANCEREPORTENTRY;

				sql = sql.concat(
					DDMFormInstanceReportEntryModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<DDMFormInstanceReportEntry>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the ddm form instance report entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DDMFormInstanceReportEntry ddmFormInstanceReportEntry :
				findAll()) {

			remove(ddmFormInstanceReportEntry);
		}
	}

	/**
	 * Returns the number of ddm form instance report entries.
	 *
	 * @return the number of ddm form instance report entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_DDMFORMINSTANCEREPORTENTRY);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "formInstanceReportEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_DDMFORMINSTANCEREPORTENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DDMFormInstanceReportEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ddm form instance report entry persistence.
	 */
	@Activate
	public void activate() {
		DDMFormInstanceReportEntryModelImpl.setEntityCacheEnabled(
			entityCacheEnabled);
		DDMFormInstanceReportEntryModelImpl.setFinderCacheEnabled(
			finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			DDMFormInstanceReportEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			DDMFormInstanceReportEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByFormInstanceId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled,
			DDMFormInstanceReportEntryImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByFormInstanceId", new String[] {Long.class.getName()},
			DDMFormInstanceReportEntryModelImpl.FORMINSTANCEID_COLUMN_BITMASK);

		_finderPathCountByFormInstanceId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFormInstanceId",
			new String[] {Long.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(DDMFormInstanceReportEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = DDMPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.liferay.dynamic.data.mapping.model.DDMFormInstanceReportEntry"),
			true);
	}

	@Override
	@Reference(
		target = DDMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = DDMPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DDMFORMINSTANCEREPORTENTRY =
		"SELECT ddmFormInstanceReportEntry FROM DDMFormInstanceReportEntry ddmFormInstanceReportEntry";

	private static final String _SQL_SELECT_DDMFORMINSTANCEREPORTENTRY_WHERE =
		"SELECT ddmFormInstanceReportEntry FROM DDMFormInstanceReportEntry ddmFormInstanceReportEntry WHERE ";

	private static final String _SQL_COUNT_DDMFORMINSTANCEREPORTENTRY =
		"SELECT COUNT(ddmFormInstanceReportEntry) FROM DDMFormInstanceReportEntry ddmFormInstanceReportEntry";

	private static final String _SQL_COUNT_DDMFORMINSTANCEREPORTENTRY_WHERE =
		"SELECT COUNT(ddmFormInstanceReportEntry) FROM DDMFormInstanceReportEntry ddmFormInstanceReportEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"ddmFormInstanceReportEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DDMFormInstanceReportEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DDMFormInstanceReportEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DDMFormInstanceReportEntryPersistenceImpl.class);

	static {
		try {
			Class.forName(DDMPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException classNotFoundException) {
			throw new ExceptionInInitializerError(classNotFoundException);
		}
	}

}