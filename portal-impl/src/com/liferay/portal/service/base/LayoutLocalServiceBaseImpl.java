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

package com.liferay.portal.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.asset.kernel.service.persistence.AssetEntryFinder;
import com.liferay.asset.kernel.service.persistence.AssetEntryPersistence;
import com.liferay.expando.kernel.service.persistence.ExpandoRowPersistence;
import com.liferay.exportimport.kernel.lar.ExportImportHelperUtil;
import com.liferay.exportimport.kernel.lar.ManifestSummary;
import com.liferay.exportimport.kernel.lar.PortletDataContext;
import com.liferay.exportimport.kernel.lar.StagedModelDataHandlerUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.exportimport.kernel.service.persistence.ExportImportConfigurationFinder;
import com.liferay.exportimport.kernel.service.persistence.ExportImportConfigurationPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DefaultActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.dao.orm.Property;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutVersion;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalServiceImpl;
import com.liferay.portal.kernel.service.LayoutLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalServiceRegistry;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.GroupFinder;
import com.liferay.portal.kernel.service.persistence.GroupPersistence;
import com.liferay.portal.kernel.service.persistence.ImagePersistence;
import com.liferay.portal.kernel.service.persistence.LayoutFinder;
import com.liferay.portal.kernel.service.persistence.LayoutFriendlyURLPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutPrototypePersistence;
import com.liferay.portal.kernel.service.persistence.LayoutSetPersistence;
import com.liferay.portal.kernel.service.persistence.LayoutSetPrototypePersistence;
import com.liferay.portal.kernel.service.persistence.LayoutVersionPersistence;
import com.liferay.portal.kernel.service.persistence.PluginSettingPersistence;
import com.liferay.portal.kernel.service.persistence.PortalPreferencesPersistence;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesFinder;
import com.liferay.portal.kernel.service.persistence.PortletPreferencesPersistence;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionFinder;
import com.liferay.portal.kernel.service.persistence.ResourcePermissionPersistence;
import com.liferay.portal.kernel.service.persistence.UserFinder;
import com.liferay.portal.kernel.service.persistence.UserGroupFinder;
import com.liferay.portal.kernel.service.persistence.UserGroupPersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.service.version.VersionService;
import com.liferay.portal.kernel.service.version.VersionServiceListener;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsFinder;
import com.liferay.ratings.kernel.service.persistence.RatingsStatsPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the layout local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.portal.service.impl.LayoutLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.portal.service.impl.LayoutLocalServiceImpl
 * @generated
 */
@ProviderType
public abstract class LayoutLocalServiceBaseImpl
	extends BaseLocalServiceImpl
	implements LayoutLocalService, IdentifiableOSGiService,
			   VersionService<Layout, LayoutVersion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>LayoutLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.portal.kernel.service.LayoutLocalServiceUtil</code>.
	 */

	/**
	 * Adds the layout to the database. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout addLayout(Layout layout) {
		layout.setNew(true);

		return layoutPersistence.update(layout);
	}

	/**
	 * Creates a new layout. Does not add the layout to the database.
	 *
	 * @return the new layout
	 */
	@Override
	@Transactional(enabled = false)
	public Layout create() {
		long primaryKey = counterLocalService.increment(Layout.class.getName());

		Layout draftLayout = layoutPersistence.create(primaryKey);

		draftLayout.setHeadId(primaryKey);

		return draftLayout;
	}

	/**
	 * Deletes the layout with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param plid the primary key of the layout
	 * @return the layout that was removed
	 * @throws PortalException if a layout with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Layout deleteLayout(long plid) throws PortalException {
		Layout layout = layoutPersistence.fetchByPrimaryKey(plid);

		if (layout != null) {
			delete(layout);
		}

		return layout;
	}

	/**
	 * Deletes the layout from the database. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@Override
	public Layout deleteLayout(Layout layout) throws PortalException {
		delete(layout);

		return layout;
	}

	@Override
	public DynamicQuery dynamicQuery() {
		Class<?> clazz = getClass();

		return DynamicQueryFactoryUtil.forClass(
			Layout.class, clazz.getClassLoader());
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return layoutPersistence.findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return layoutPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return layoutPersistence.findWithDynamicQuery(
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
		return layoutPersistence.countWithDynamicQuery(dynamicQuery);
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

		return layoutPersistence.countWithDynamicQuery(
			dynamicQuery, projection);
	}

	@Override
	public Layout fetchLayout(long plid) {
		return layoutPersistence.fetchByPrimaryKey(plid);
	}

	/**
	 * Returns the layout with the primary key.
	 *
	 * @param plid the primary key of the layout
	 * @return the layout
	 * @throws PortalException if a layout with the primary key could not be found
	 */
	@Override
	public Layout getLayout(long plid) throws PortalException {
		return layoutPersistence.findByPrimaryKey(plid);
	}

	@Override
	public ActionableDynamicQuery getActionableDynamicQuery() {
		ActionableDynamicQuery actionableDynamicQuery =
			new DefaultActionableDynamicQuery();

		actionableDynamicQuery.setBaseLocalService(layoutLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Layout.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("plid");

		return actionableDynamicQuery;
	}

	@Override
	public IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			new IndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setBaseLocalService(layoutLocalService);
		indexableActionableDynamicQuery.setClassLoader(getClassLoader());
		indexableActionableDynamicQuery.setModelClass(Layout.class);

		indexableActionableDynamicQuery.setPrimaryKeyPropertyName("plid");

		return indexableActionableDynamicQuery;
	}

	protected void initActionableDynamicQuery(
		ActionableDynamicQuery actionableDynamicQuery) {

		actionableDynamicQuery.setBaseLocalService(layoutLocalService);
		actionableDynamicQuery.setClassLoader(getClassLoader());
		actionableDynamicQuery.setModelClass(Layout.class);

		actionableDynamicQuery.setPrimaryKeyPropertyName("plid");
	}

	@Override
	public ExportActionableDynamicQuery getExportActionableDynamicQuery(
		final PortletDataContext portletDataContext) {

		final ExportActionableDynamicQuery exportActionableDynamicQuery =
			new ExportActionableDynamicQuery() {

				@Override
				public long performCount() throws PortalException {
					ManifestSummary manifestSummary =
						portletDataContext.getManifestSummary();

					StagedModelType stagedModelType = getStagedModelType();

					long modelAdditionCount = super.performCount();

					manifestSummary.addModelAdditionCount(
						stagedModelType, modelAdditionCount);

					long modelDeletionCount =
						ExportImportHelperUtil.getModelDeletionCount(
							portletDataContext, stagedModelType);

					manifestSummary.addModelDeletionCount(
						stagedModelType, modelDeletionCount);

					return modelAdditionCount;
				}

			};

		initActionableDynamicQuery(exportActionableDynamicQuery);

		exportActionableDynamicQuery.setAddCriteriaMethod(
			new ActionableDynamicQuery.AddCriteriaMethod() {

				@Override
				public void addCriteria(DynamicQuery dynamicQuery) {
					portletDataContext.addDateRangeCriteria(
						dynamicQuery, "modifiedDate");

					StagedModelType stagedModelType =
						exportActionableDynamicQuery.getStagedModelType();

					long referrerClassNameId =
						stagedModelType.getReferrerClassNameId();

					Property classNameIdProperty = PropertyFactoryUtil.forName(
						"classNameId");

					if ((referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ALL) &&
						(referrerClassNameId !=
							StagedModelType.REFERRER_CLASS_NAME_ID_ANY)) {

						dynamicQuery.add(
							classNameIdProperty.eq(
								stagedModelType.getReferrerClassNameId()));
					}
					else if (referrerClassNameId ==
								StagedModelType.REFERRER_CLASS_NAME_ID_ANY) {

						dynamicQuery.add(classNameIdProperty.isNotNull());
					}
				}

			});

		exportActionableDynamicQuery.setCompanyId(
			portletDataContext.getCompanyId());

		exportActionableDynamicQuery.setGroupId(
			portletDataContext.getScopeGroupId());

		exportActionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<Layout>() {

				@Override
				public void performAction(Layout layout)
					throws PortalException {

					StagedModelDataHandlerUtil.exportStagedModel(
						portletDataContext, layout);
				}

			});
		exportActionableDynamicQuery.setStagedModelType(
			new StagedModelType(
				PortalUtil.getClassNameId(Layout.class.getName()),
				StagedModelType.REFERRER_CLASS_NAME_ID_ALL));

		return exportActionableDynamicQuery;
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException {

		return layoutLocalService.deleteLayout((Layout)persistedModel);
	}

	@Override
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return layoutPersistence.findByPrimaryKey(primaryKeyObj);
	}

	/**
	 * Returns a range of all the layouts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.LayoutModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of layouts
	 * @param end the upper bound of the range of layouts (not inclusive)
	 * @return the range of layouts
	 */
	@Override
	public List<Layout> getLayouts(int start, int end) {
		return layoutPersistence.findAll(start, end);
	}

	/**
	 * Returns the number of layouts.
	 *
	 * @return the number of layouts
	 */
	@Override
	public int getLayoutsCount() {
		return layoutPersistence.countAll();
	}

	/**
	 * Updates the layout in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param layout the layout
	 * @return the layout that was updated
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout updateLayout(Layout draftLayout) throws PortalException {
		return updateDraft(draftLayout);
	}

	/**
	 * Returns the layout local service.
	 *
	 * @return the layout local service
	 */
	public LayoutLocalService getLayoutLocalService() {
		return layoutLocalService;
	}

	/**
	 * Sets the layout local service.
	 *
	 * @param layoutLocalService the layout local service
	 */
	public void setLayoutLocalService(LayoutLocalService layoutLocalService) {
		this.layoutLocalService = layoutLocalService;
	}

	/**
	 * Returns the layout persistence.
	 *
	 * @return the layout persistence
	 */
	public LayoutPersistence getLayoutPersistence() {
		return layoutPersistence;
	}

	/**
	 * Sets the layout persistence.
	 *
	 * @param layoutPersistence the layout persistence
	 */
	public void setLayoutPersistence(LayoutPersistence layoutPersistence) {
		this.layoutPersistence = layoutPersistence;
	}

	/**
	 * Returns the layout finder.
	 *
	 * @return the layout finder
	 */
	public LayoutFinder getLayoutFinder() {
		return layoutFinder;
	}

	/**
	 * Sets the layout finder.
	 *
	 * @param layoutFinder the layout finder
	 */
	public void setLayoutFinder(LayoutFinder layoutFinder) {
		this.layoutFinder = layoutFinder;
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
	 * Returns the class name local service.
	 *
	 * @return the class name local service
	 */
	public com.liferay.portal.kernel.service.ClassNameLocalService
		getClassNameLocalService() {

		return classNameLocalService;
	}

	/**
	 * Sets the class name local service.
	 *
	 * @param classNameLocalService the class name local service
	 */
	public void setClassNameLocalService(
		com.liferay.portal.kernel.service.ClassNameLocalService
			classNameLocalService) {

		this.classNameLocalService = classNameLocalService;
	}

	/**
	 * Returns the class name persistence.
	 *
	 * @return the class name persistence
	 */
	public ClassNamePersistence getClassNamePersistence() {
		return classNamePersistence;
	}

	/**
	 * Sets the class name persistence.
	 *
	 * @param classNamePersistence the class name persistence
	 */
	public void setClassNamePersistence(
		ClassNamePersistence classNamePersistence) {

		this.classNamePersistence = classNamePersistence;
	}

	/**
	 * Returns the group local service.
	 *
	 * @return the group local service
	 */
	public com.liferay.portal.kernel.service.GroupLocalService
		getGroupLocalService() {

		return groupLocalService;
	}

	/**
	 * Sets the group local service.
	 *
	 * @param groupLocalService the group local service
	 */
	public void setGroupLocalService(
		com.liferay.portal.kernel.service.GroupLocalService groupLocalService) {

		this.groupLocalService = groupLocalService;
	}

	/**
	 * Returns the group persistence.
	 *
	 * @return the group persistence
	 */
	public GroupPersistence getGroupPersistence() {
		return groupPersistence;
	}

	/**
	 * Sets the group persistence.
	 *
	 * @param groupPersistence the group persistence
	 */
	public void setGroupPersistence(GroupPersistence groupPersistence) {
		this.groupPersistence = groupPersistence;
	}

	/**
	 * Returns the group finder.
	 *
	 * @return the group finder
	 */
	public GroupFinder getGroupFinder() {
		return groupFinder;
	}

	/**
	 * Sets the group finder.
	 *
	 * @param groupFinder the group finder
	 */
	public void setGroupFinder(GroupFinder groupFinder) {
		this.groupFinder = groupFinder;
	}

	/**
	 * Returns the image local service.
	 *
	 * @return the image local service
	 */
	public com.liferay.portal.kernel.service.ImageLocalService
		getImageLocalService() {

		return imageLocalService;
	}

	/**
	 * Sets the image local service.
	 *
	 * @param imageLocalService the image local service
	 */
	public void setImageLocalService(
		com.liferay.portal.kernel.service.ImageLocalService imageLocalService) {

		this.imageLocalService = imageLocalService;
	}

	/**
	 * Returns the image persistence.
	 *
	 * @return the image persistence
	 */
	public ImagePersistence getImagePersistence() {
		return imagePersistence;
	}

	/**
	 * Sets the image persistence.
	 *
	 * @param imagePersistence the image persistence
	 */
	public void setImagePersistence(ImagePersistence imagePersistence) {
		this.imagePersistence = imagePersistence;
	}

	/**
	 * Returns the asset entry local service.
	 *
	 * @return the asset entry local service
	 */
	public com.liferay.asset.kernel.service.AssetEntryLocalService
		getAssetEntryLocalService() {

		return assetEntryLocalService;
	}

	/**
	 * Sets the asset entry local service.
	 *
	 * @param assetEntryLocalService the asset entry local service
	 */
	public void setAssetEntryLocalService(
		com.liferay.asset.kernel.service.AssetEntryLocalService
			assetEntryLocalService) {

		this.assetEntryLocalService = assetEntryLocalService;
	}

	/**
	 * Returns the asset entry persistence.
	 *
	 * @return the asset entry persistence
	 */
	public AssetEntryPersistence getAssetEntryPersistence() {
		return assetEntryPersistence;
	}

	/**
	 * Sets the asset entry persistence.
	 *
	 * @param assetEntryPersistence the asset entry persistence
	 */
	public void setAssetEntryPersistence(
		AssetEntryPersistence assetEntryPersistence) {

		this.assetEntryPersistence = assetEntryPersistence;
	}

	/**
	 * Returns the asset entry finder.
	 *
	 * @return the asset entry finder
	 */
	public AssetEntryFinder getAssetEntryFinder() {
		return assetEntryFinder;
	}

	/**
	 * Sets the asset entry finder.
	 *
	 * @param assetEntryFinder the asset entry finder
	 */
	public void setAssetEntryFinder(AssetEntryFinder assetEntryFinder) {
		this.assetEntryFinder = assetEntryFinder;
	}

	/**
	 * Returns the expando row local service.
	 *
	 * @return the expando row local service
	 */
	public com.liferay.expando.kernel.service.ExpandoRowLocalService
		getExpandoRowLocalService() {

		return expandoRowLocalService;
	}

	/**
	 * Sets the expando row local service.
	 *
	 * @param expandoRowLocalService the expando row local service
	 */
	public void setExpandoRowLocalService(
		com.liferay.expando.kernel.service.ExpandoRowLocalService
			expandoRowLocalService) {

		this.expandoRowLocalService = expandoRowLocalService;
	}

	/**
	 * Returns the expando row persistence.
	 *
	 * @return the expando row persistence
	 */
	public ExpandoRowPersistence getExpandoRowPersistence() {
		return expandoRowPersistence;
	}

	/**
	 * Sets the expando row persistence.
	 *
	 * @param expandoRowPersistence the expando row persistence
	 */
	public void setExpandoRowPersistence(
		ExpandoRowPersistence expandoRowPersistence) {

		this.expandoRowPersistence = expandoRowPersistence;
	}

	/**
	 * Returns the export import configuration local service.
	 *
	 * @return the export import configuration local service
	 */
	public com.liferay.exportimport.kernel.service.
		ExportImportConfigurationLocalService
			getExportImportConfigurationLocalService() {

		return exportImportConfigurationLocalService;
	}

	/**
	 * Sets the export import configuration local service.
	 *
	 * @param exportImportConfigurationLocalService the export import configuration local service
	 */
	public void setExportImportConfigurationLocalService(
		com.liferay.exportimport.kernel.service.
			ExportImportConfigurationLocalService
				exportImportConfigurationLocalService) {

		this.exportImportConfigurationLocalService =
			exportImportConfigurationLocalService;
	}

	/**
	 * Returns the export import configuration persistence.
	 *
	 * @return the export import configuration persistence
	 */
	public ExportImportConfigurationPersistence
		getExportImportConfigurationPersistence() {

		return exportImportConfigurationPersistence;
	}

	/**
	 * Sets the export import configuration persistence.
	 *
	 * @param exportImportConfigurationPersistence the export import configuration persistence
	 */
	public void setExportImportConfigurationPersistence(
		ExportImportConfigurationPersistence
			exportImportConfigurationPersistence) {

		this.exportImportConfigurationPersistence =
			exportImportConfigurationPersistence;
	}

	/**
	 * Returns the export import configuration finder.
	 *
	 * @return the export import configuration finder
	 */
	public ExportImportConfigurationFinder
		getExportImportConfigurationFinder() {

		return exportImportConfigurationFinder;
	}

	/**
	 * Sets the export import configuration finder.
	 *
	 * @param exportImportConfigurationFinder the export import configuration finder
	 */
	public void setExportImportConfigurationFinder(
		ExportImportConfigurationFinder exportImportConfigurationFinder) {

		this.exportImportConfigurationFinder = exportImportConfigurationFinder;
	}

	/**
	 * Returns the ratings stats local service.
	 *
	 * @return the ratings stats local service
	 */
	public com.liferay.ratings.kernel.service.RatingsStatsLocalService
		getRatingsStatsLocalService() {

		return ratingsStatsLocalService;
	}

	/**
	 * Sets the ratings stats local service.
	 *
	 * @param ratingsStatsLocalService the ratings stats local service
	 */
	public void setRatingsStatsLocalService(
		com.liferay.ratings.kernel.service.RatingsStatsLocalService
			ratingsStatsLocalService) {

		this.ratingsStatsLocalService = ratingsStatsLocalService;
	}

	/**
	 * Returns the ratings stats persistence.
	 *
	 * @return the ratings stats persistence
	 */
	public RatingsStatsPersistence getRatingsStatsPersistence() {
		return ratingsStatsPersistence;
	}

	/**
	 * Sets the ratings stats persistence.
	 *
	 * @param ratingsStatsPersistence the ratings stats persistence
	 */
	public void setRatingsStatsPersistence(
		RatingsStatsPersistence ratingsStatsPersistence) {

		this.ratingsStatsPersistence = ratingsStatsPersistence;
	}

	/**
	 * Returns the ratings stats finder.
	 *
	 * @return the ratings stats finder
	 */
	public RatingsStatsFinder getRatingsStatsFinder() {
		return ratingsStatsFinder;
	}

	/**
	 * Sets the ratings stats finder.
	 *
	 * @param ratingsStatsFinder the ratings stats finder
	 */
	public void setRatingsStatsFinder(RatingsStatsFinder ratingsStatsFinder) {
		this.ratingsStatsFinder = ratingsStatsFinder;
	}

	/**
	 * Returns the layout version persistence.
	 *
	 * @return the layout version persistence
	 */
	public LayoutVersionPersistence getLayoutVersionPersistence() {
		return layoutVersionPersistence;
	}

	/**
	 * Sets the layout version persistence.
	 *
	 * @param layoutVersionPersistence the layout version persistence
	 */
	public void setLayoutVersionPersistence(
		LayoutVersionPersistence layoutVersionPersistence) {

		this.layoutVersionPersistence = layoutVersionPersistence;
	}

	/**
	 * Returns the layout friendly url local service.
	 *
	 * @return the layout friendly url local service
	 */
	public com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService
		getLayoutFriendlyURLLocalService() {

		return layoutFriendlyURLLocalService;
	}

	/**
	 * Sets the layout friendly url local service.
	 *
	 * @param layoutFriendlyURLLocalService the layout friendly url local service
	 */
	public void setLayoutFriendlyURLLocalService(
		com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService
			layoutFriendlyURLLocalService) {

		this.layoutFriendlyURLLocalService = layoutFriendlyURLLocalService;
	}

	/**
	 * Returns the layout friendly url persistence.
	 *
	 * @return the layout friendly url persistence
	 */
	public LayoutFriendlyURLPersistence getLayoutFriendlyURLPersistence() {
		return layoutFriendlyURLPersistence;
	}

	/**
	 * Sets the layout friendly url persistence.
	 *
	 * @param layoutFriendlyURLPersistence the layout friendly url persistence
	 */
	public void setLayoutFriendlyURLPersistence(
		LayoutFriendlyURLPersistence layoutFriendlyURLPersistence) {

		this.layoutFriendlyURLPersistence = layoutFriendlyURLPersistence;
	}

	/**
	 * Returns the layout prototype local service.
	 *
	 * @return the layout prototype local service
	 */
	public com.liferay.portal.kernel.service.LayoutPrototypeLocalService
		getLayoutPrototypeLocalService() {

		return layoutPrototypeLocalService;
	}

	/**
	 * Sets the layout prototype local service.
	 *
	 * @param layoutPrototypeLocalService the layout prototype local service
	 */
	public void setLayoutPrototypeLocalService(
		com.liferay.portal.kernel.service.LayoutPrototypeLocalService
			layoutPrototypeLocalService) {

		this.layoutPrototypeLocalService = layoutPrototypeLocalService;
	}

	/**
	 * Returns the layout prototype persistence.
	 *
	 * @return the layout prototype persistence
	 */
	public LayoutPrototypePersistence getLayoutPrototypePersistence() {
		return layoutPrototypePersistence;
	}

	/**
	 * Sets the layout prototype persistence.
	 *
	 * @param layoutPrototypePersistence the layout prototype persistence
	 */
	public void setLayoutPrototypePersistence(
		LayoutPrototypePersistence layoutPrototypePersistence) {

		this.layoutPrototypePersistence = layoutPrototypePersistence;
	}

	/**
	 * Returns the layout set local service.
	 *
	 * @return the layout set local service
	 */
	public com.liferay.portal.kernel.service.LayoutSetLocalService
		getLayoutSetLocalService() {

		return layoutSetLocalService;
	}

	/**
	 * Sets the layout set local service.
	 *
	 * @param layoutSetLocalService the layout set local service
	 */
	public void setLayoutSetLocalService(
		com.liferay.portal.kernel.service.LayoutSetLocalService
			layoutSetLocalService) {

		this.layoutSetLocalService = layoutSetLocalService;
	}

	/**
	 * Returns the layout set persistence.
	 *
	 * @return the layout set persistence
	 */
	public LayoutSetPersistence getLayoutSetPersistence() {
		return layoutSetPersistence;
	}

	/**
	 * Sets the layout set persistence.
	 *
	 * @param layoutSetPersistence the layout set persistence
	 */
	public void setLayoutSetPersistence(
		LayoutSetPersistence layoutSetPersistence) {

		this.layoutSetPersistence = layoutSetPersistence;
	}

	/**
	 * Returns the layout set prototype local service.
	 *
	 * @return the layout set prototype local service
	 */
	public com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService
		getLayoutSetPrototypeLocalService() {

		return layoutSetPrototypeLocalService;
	}

	/**
	 * Sets the layout set prototype local service.
	 *
	 * @param layoutSetPrototypeLocalService the layout set prototype local service
	 */
	public void setLayoutSetPrototypeLocalService(
		com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService
			layoutSetPrototypeLocalService) {

		this.layoutSetPrototypeLocalService = layoutSetPrototypeLocalService;
	}

	/**
	 * Returns the layout set prototype persistence.
	 *
	 * @return the layout set prototype persistence
	 */
	public LayoutSetPrototypePersistence getLayoutSetPrototypePersistence() {
		return layoutSetPrototypePersistence;
	}

	/**
	 * Sets the layout set prototype persistence.
	 *
	 * @param layoutSetPrototypePersistence the layout set prototype persistence
	 */
	public void setLayoutSetPrototypePersistence(
		LayoutSetPrototypePersistence layoutSetPrototypePersistence) {

		this.layoutSetPrototypePersistence = layoutSetPrototypePersistence;
	}

	/**
	 * Returns the plugin setting local service.
	 *
	 * @return the plugin setting local service
	 */
	public com.liferay.portal.kernel.service.PluginSettingLocalService
		getPluginSettingLocalService() {

		return pluginSettingLocalService;
	}

	/**
	 * Sets the plugin setting local service.
	 *
	 * @param pluginSettingLocalService the plugin setting local service
	 */
	public void setPluginSettingLocalService(
		com.liferay.portal.kernel.service.PluginSettingLocalService
			pluginSettingLocalService) {

		this.pluginSettingLocalService = pluginSettingLocalService;
	}

	/**
	 * Returns the plugin setting persistence.
	 *
	 * @return the plugin setting persistence
	 */
	public PluginSettingPersistence getPluginSettingPersistence() {
		return pluginSettingPersistence;
	}

	/**
	 * Sets the plugin setting persistence.
	 *
	 * @param pluginSettingPersistence the plugin setting persistence
	 */
	public void setPluginSettingPersistence(
		PluginSettingPersistence pluginSettingPersistence) {

		this.pluginSettingPersistence = pluginSettingPersistence;
	}

	/**
	 * Returns the portal preferences local service.
	 *
	 * @return the portal preferences local service
	 */
	public com.liferay.portal.kernel.service.PortalPreferencesLocalService
		getPortalPreferencesLocalService() {

		return portalPreferencesLocalService;
	}

	/**
	 * Sets the portal preferences local service.
	 *
	 * @param portalPreferencesLocalService the portal preferences local service
	 */
	public void setPortalPreferencesLocalService(
		com.liferay.portal.kernel.service.PortalPreferencesLocalService
			portalPreferencesLocalService) {

		this.portalPreferencesLocalService = portalPreferencesLocalService;
	}

	/**
	 * Returns the portal preferences persistence.
	 *
	 * @return the portal preferences persistence
	 */
	public PortalPreferencesPersistence getPortalPreferencesPersistence() {
		return portalPreferencesPersistence;
	}

	/**
	 * Sets the portal preferences persistence.
	 *
	 * @param portalPreferencesPersistence the portal preferences persistence
	 */
	public void setPortalPreferencesPersistence(
		PortalPreferencesPersistence portalPreferencesPersistence) {

		this.portalPreferencesPersistence = portalPreferencesPersistence;
	}

	/**
	 * Returns the portlet preferences local service.
	 *
	 * @return the portlet preferences local service
	 */
	public com.liferay.portal.kernel.service.PortletPreferencesLocalService
		getPortletPreferencesLocalService() {

		return portletPreferencesLocalService;
	}

	/**
	 * Sets the portlet preferences local service.
	 *
	 * @param portletPreferencesLocalService the portlet preferences local service
	 */
	public void setPortletPreferencesLocalService(
		com.liferay.portal.kernel.service.PortletPreferencesLocalService
			portletPreferencesLocalService) {

		this.portletPreferencesLocalService = portletPreferencesLocalService;
	}

	/**
	 * Returns the portlet preferences persistence.
	 *
	 * @return the portlet preferences persistence
	 */
	public PortletPreferencesPersistence getPortletPreferencesPersistence() {
		return portletPreferencesPersistence;
	}

	/**
	 * Sets the portlet preferences persistence.
	 *
	 * @param portletPreferencesPersistence the portlet preferences persistence
	 */
	public void setPortletPreferencesPersistence(
		PortletPreferencesPersistence portletPreferencesPersistence) {

		this.portletPreferencesPersistence = portletPreferencesPersistence;
	}

	/**
	 * Returns the portlet preferences finder.
	 *
	 * @return the portlet preferences finder
	 */
	public PortletPreferencesFinder getPortletPreferencesFinder() {
		return portletPreferencesFinder;
	}

	/**
	 * Sets the portlet preferences finder.
	 *
	 * @param portletPreferencesFinder the portlet preferences finder
	 */
	public void setPortletPreferencesFinder(
		PortletPreferencesFinder portletPreferencesFinder) {

		this.portletPreferencesFinder = portletPreferencesFinder;
	}

	/**
	 * Returns the resource local service.
	 *
	 * @return the resource local service
	 */
	public com.liferay.portal.kernel.service.ResourceLocalService
		getResourceLocalService() {

		return resourceLocalService;
	}

	/**
	 * Sets the resource local service.
	 *
	 * @param resourceLocalService the resource local service
	 */
	public void setResourceLocalService(
		com.liferay.portal.kernel.service.ResourceLocalService
			resourceLocalService) {

		this.resourceLocalService = resourceLocalService;
	}

	/**
	 * Returns the resource permission local service.
	 *
	 * @return the resource permission local service
	 */
	public com.liferay.portal.kernel.service.ResourcePermissionLocalService
		getResourcePermissionLocalService() {

		return resourcePermissionLocalService;
	}

	/**
	 * Sets the resource permission local service.
	 *
	 * @param resourcePermissionLocalService the resource permission local service
	 */
	public void setResourcePermissionLocalService(
		com.liferay.portal.kernel.service.ResourcePermissionLocalService
			resourcePermissionLocalService) {

		this.resourcePermissionLocalService = resourcePermissionLocalService;
	}

	/**
	 * Returns the resource permission persistence.
	 *
	 * @return the resource permission persistence
	 */
	public ResourcePermissionPersistence getResourcePermissionPersistence() {
		return resourcePermissionPersistence;
	}

	/**
	 * Sets the resource permission persistence.
	 *
	 * @param resourcePermissionPersistence the resource permission persistence
	 */
	public void setResourcePermissionPersistence(
		ResourcePermissionPersistence resourcePermissionPersistence) {

		this.resourcePermissionPersistence = resourcePermissionPersistence;
	}

	/**
	 * Returns the resource permission finder.
	 *
	 * @return the resource permission finder
	 */
	public ResourcePermissionFinder getResourcePermissionFinder() {
		return resourcePermissionFinder;
	}

	/**
	 * Sets the resource permission finder.
	 *
	 * @param resourcePermissionFinder the resource permission finder
	 */
	public void setResourcePermissionFinder(
		ResourcePermissionFinder resourcePermissionFinder) {

		this.resourcePermissionFinder = resourcePermissionFinder;
	}

	/**
	 * Returns the user local service.
	 *
	 * @return the user local service
	 */
	public com.liferay.portal.kernel.service.UserLocalService
		getUserLocalService() {

		return userLocalService;
	}

	/**
	 * Sets the user local service.
	 *
	 * @param userLocalService the user local service
	 */
	public void setUserLocalService(
		com.liferay.portal.kernel.service.UserLocalService userLocalService) {

		this.userLocalService = userLocalService;
	}

	/**
	 * Returns the user persistence.
	 *
	 * @return the user persistence
	 */
	public UserPersistence getUserPersistence() {
		return userPersistence;
	}

	/**
	 * Sets the user persistence.
	 *
	 * @param userPersistence the user persistence
	 */
	public void setUserPersistence(UserPersistence userPersistence) {
		this.userPersistence = userPersistence;
	}

	/**
	 * Returns the user finder.
	 *
	 * @return the user finder
	 */
	public UserFinder getUserFinder() {
		return userFinder;
	}

	/**
	 * Sets the user finder.
	 *
	 * @param userFinder the user finder
	 */
	public void setUserFinder(UserFinder userFinder) {
		this.userFinder = userFinder;
	}

	/**
	 * Returns the user group local service.
	 *
	 * @return the user group local service
	 */
	public com.liferay.portal.kernel.service.UserGroupLocalService
		getUserGroupLocalService() {

		return userGroupLocalService;
	}

	/**
	 * Sets the user group local service.
	 *
	 * @param userGroupLocalService the user group local service
	 */
	public void setUserGroupLocalService(
		com.liferay.portal.kernel.service.UserGroupLocalService
			userGroupLocalService) {

		this.userGroupLocalService = userGroupLocalService;
	}

	/**
	 * Returns the user group persistence.
	 *
	 * @return the user group persistence
	 */
	public UserGroupPersistence getUserGroupPersistence() {
		return userGroupPersistence;
	}

	/**
	 * Sets the user group persistence.
	 *
	 * @param userGroupPersistence the user group persistence
	 */
	public void setUserGroupPersistence(
		UserGroupPersistence userGroupPersistence) {

		this.userGroupPersistence = userGroupPersistence;
	}

	/**
	 * Returns the user group finder.
	 *
	 * @return the user group finder
	 */
	public UserGroupFinder getUserGroupFinder() {
		return userGroupFinder;
	}

	/**
	 * Sets the user group finder.
	 *
	 * @param userGroupFinder the user group finder
	 */
	public void setUserGroupFinder(UserGroupFinder userGroupFinder) {
		this.userGroupFinder = userGroupFinder;
	}

	public void afterPropertiesSet() {
		persistedModelLocalServiceRegistry.register(
			"com.liferay.portal.kernel.model.Layout", layoutLocalService);
	}

	public void destroy() {
		persistedModelLocalServiceRegistry.unregister(
			"com.liferay.portal.kernel.model.Layout");
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout checkout(Layout publishedLayout, int version)
		throws PortalException {

		if (!publishedLayout.isHead()) {
			throw new IllegalArgumentException(
				"Unable to checkout with unpublished changes " +
					publishedLayout.getHeadId());
		}

		Layout draftLayout = layoutPersistence.fetchByHeadId(
			publishedLayout.getPrimaryKey());

		if (draftLayout != null) {
			throw new IllegalArgumentException(
				"Unable to checkout with unpublished changes " +
					publishedLayout.getPrimaryKey());
		}

		LayoutVersion layoutVersion = getVersion(publishedLayout, version);

		draftLayout = _createDraft(publishedLayout);

		layoutVersion.populateVersionedModel(draftLayout);

		draftLayout = layoutPersistence.update(draftLayout);

		for (VersionServiceListener<Layout, LayoutVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterCheckout(draftLayout, version);
		}

		return draftLayout;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Layout delete(Layout publishedLayout) throws PortalException {
		if (!publishedLayout.isHead()) {
			throw new IllegalArgumentException(
				"Layout is a draft " + publishedLayout.getPrimaryKey());
		}

		Layout draftLayout = layoutPersistence.fetchByHeadId(
			publishedLayout.getPrimaryKey());

		if (draftLayout != null) {
			deleteDraft(draftLayout);
		}

		for (LayoutVersion layoutVersion : getVersions(publishedLayout)) {
			layoutVersionPersistence.remove(layoutVersion);
		}

		layoutPersistence.remove(publishedLayout);

		for (VersionServiceListener<Layout, LayoutVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDelete(publishedLayout);
		}

		return publishedLayout;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public Layout deleteDraft(Layout draftLayout) throws PortalException {
		if (draftLayout.isHead()) {
			throw new IllegalArgumentException(
				"Layout is not a draft " + draftLayout.getPrimaryKey());
		}

		layoutPersistence.remove(draftLayout);

		for (VersionServiceListener<Layout, LayoutVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDeleteDraft(draftLayout);
		}

		return draftLayout;
	}

	@Override
	public LayoutVersion deleteVersion(LayoutVersion layoutVersion)
		throws PortalException {

		LayoutVersion latestLayoutVersion =
			layoutVersionPersistence.findByPlid_First(
				layoutVersion.getVersionedModelId(), null);

		if (latestLayoutVersion.getVersion() == layoutVersion.getVersion()) {
			throw new IllegalArgumentException(
				"Unable to delete latest version " +
					layoutVersion.getVersion());
		}

		layoutVersion = layoutVersionPersistence.remove(layoutVersion);

		for (VersionServiceListener<Layout, LayoutVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterDeleteVersion(layoutVersion);
		}

		return layoutVersion;
	}

	@Override
	public Layout fetchDraft(Layout layout) {
		if (layout.isHead()) {
			return layoutPersistence.fetchByHeadId(layout.getPrimaryKey());
		}

		return layout;
	}

	@Override
	public Layout fetchDraft(long primaryKey) {
		return layoutPersistence.fetchByHeadId(primaryKey);
	}

	@Override
	public LayoutVersion fetchLatestVersion(Layout layout) {
		long primaryKey = layout.getHeadId();

		if (layout.isHead()) {
			primaryKey = layout.getPrimaryKey();
		}

		return layoutVersionPersistence.fetchByPlid_First(primaryKey, null);
	}

	@Override
	public Layout fetchPublished(Layout layout) {
		if (layout.isHead()) {
			return layout;
		}

		if (layout.getHeadId() == layout.getPrimaryKey()) {
			return null;
		}

		return layoutPersistence.fetchByPrimaryKey(layout.getHeadId());
	}

	@Override
	public Layout fetchPublished(long primaryKey) {
		Layout layout = layoutPersistence.fetchByPrimaryKey(primaryKey);

		if ((layout == null) ||
			(layout.getHeadId() == layout.getPrimaryKey())) {

			return null;
		}

		return layout;
	}

	@Override
	public Layout getDraft(Layout layout) throws PortalException {
		if (!layout.isHead()) {
			return layout;
		}

		Layout draftLayout = layoutPersistence.fetchByHeadId(
			layout.getPrimaryKey());

		if (draftLayout == null) {
			draftLayout = layoutLocalService.updateDraft(_createDraft(layout));
		}

		return draftLayout;
	}

	@Override
	public Layout getDraft(long primaryKey) throws PortalException {
		Layout draftLayout = layoutPersistence.fetchByHeadId(primaryKey);

		if (draftLayout == null) {
			Layout layout = layoutPersistence.findByPrimaryKey(primaryKey);

			draftLayout = layoutLocalService.updateDraft(_createDraft(layout));
		}

		return draftLayout;
	}

	@Override
	public LayoutVersion getVersion(Layout layout, int version)
		throws PortalException {

		long primaryKey = layout.getHeadId();

		if (layout.isHead()) {
			primaryKey = layout.getPrimaryKey();
		}

		return layoutVersionPersistence.findByPlid_Version(primaryKey, version);
	}

	@Override
	public List<LayoutVersion> getVersions(Layout layout) {
		long primaryKey = layout.getPrimaryKey();

		if (!layout.isHead()) {
			if (layout.getHeadId() == layout.getPrimaryKey()) {
				return Collections.emptyList();
			}

			primaryKey = layout.getHeadId();
		}

		return layoutVersionPersistence.findByPlid(primaryKey);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout publishDraft(Layout draftLayout) throws PortalException {
		if (draftLayout.isHead()) {
			throw new IllegalArgumentException(
				"Can only publish drafts " + draftLayout.getPrimaryKey());
		}

		Layout headLayout = null;

		int version = 1;

		if (draftLayout.getHeadId() == draftLayout.getPrimaryKey()) {
			headLayout = create();

			draftLayout.setHeadId(headLayout.getPrimaryKey());
		}
		else {
			headLayout = layoutPersistence.findByPrimaryKey(
				draftLayout.getHeadId());

			LayoutVersion latestLayoutVersion =
				layoutVersionPersistence.findByPlid_First(
					draftLayout.getHeadId(), null);

			version = latestLayoutVersion.getVersion() + 1;
		}

		LayoutVersion layoutVersion = layoutVersionPersistence.create(
			counterLocalService.increment(LayoutVersion.class.getName()));

		layoutVersion.setVersion(version);
		layoutVersion.setVersionedModelId(headLayout.getPrimaryKey());

		draftLayout.populateVersionModel(layoutVersion);

		layoutVersionPersistence.update(layoutVersion);

		layoutVersion.populateVersionedModel(headLayout);

		headLayout.setHeadId(-headLayout.getPrimaryKey());

		headLayout = layoutPersistence.update(headLayout);

		for (VersionServiceListener<Layout, LayoutVersion>
				versionServiceListener : _versionServiceListeners) {

			versionServiceListener.afterPublishDraft(draftLayout, version);
		}

		deleteDraft(draftLayout);

		return headLayout;
	}

	@Override
	public void registerListener(
		VersionServiceListener<Layout, LayoutVersion> versionServiceListener) {

		_versionServiceListeners.add(versionServiceListener);
	}

	@Override
	public void unregisterListener(
		VersionServiceListener<Layout, LayoutVersion> versionServiceListener) {

		_versionServiceListeners.remove(versionServiceListener);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public Layout updateDraft(Layout draftLayout) throws PortalException {
		if (draftLayout.isHead()) {
			throw new IllegalArgumentException(
				"Can only update draft entries " + draftLayout.getPrimaryKey());
		}

		Layout previousLayout = layoutPersistence.fetchByPrimaryKey(
			draftLayout.getPrimaryKey());

		draftLayout = layoutPersistence.update(draftLayout);

		if (previousLayout == null) {
			for (VersionServiceListener<Layout, LayoutVersion>
					versionServiceListener : _versionServiceListeners) {

				versionServiceListener.afterCreateDraft(draftLayout);
			}
		}
		else {
			for (VersionServiceListener<Layout, LayoutVersion>
					versionServiceListener : _versionServiceListeners) {

				versionServiceListener.afterUpdateDraft(draftLayout);
			}
		}

		return draftLayout;
	}

	private Layout _createDraft(Layout publishedLayout) throws PortalException {
		Layout draftLayout = create();

		draftLayout.setUuid(publishedLayout.getUuid());
		draftLayout.setHeadId(publishedLayout.getPrimaryKey());
		draftLayout.setGroupId(publishedLayout.getGroupId());
		draftLayout.setCompanyId(publishedLayout.getCompanyId());
		draftLayout.setUserId(publishedLayout.getUserId());
		draftLayout.setUserName(publishedLayout.getUserName());
		draftLayout.setCreateDate(publishedLayout.getCreateDate());
		draftLayout.setModifiedDate(publishedLayout.getModifiedDate());
		draftLayout.setParentPlid(publishedLayout.getParentPlid());
		draftLayout.setPrivateLayout(publishedLayout.getPrivateLayout());
		draftLayout.setLayoutId(publishedLayout.getLayoutId());
		draftLayout.setParentLayoutId(publishedLayout.getParentLayoutId());
		draftLayout.setClassNameId(publishedLayout.getClassNameId());
		draftLayout.setClassPK(publishedLayout.getClassPK());
		draftLayout.setName(publishedLayout.getName());
		draftLayout.setTitle(publishedLayout.getTitle());
		draftLayout.setDescription(publishedLayout.getDescription());
		draftLayout.setKeywords(publishedLayout.getKeywords());
		draftLayout.setRobots(publishedLayout.getRobots());
		draftLayout.setType(publishedLayout.getType());
		draftLayout.setTypeSettings(publishedLayout.getTypeSettings());
		draftLayout.setHidden(publishedLayout.getHidden());
		draftLayout.setSystem(publishedLayout.getSystem());
		draftLayout.setFriendlyURL(publishedLayout.getFriendlyURL());
		draftLayout.setIconImageId(publishedLayout.getIconImageId());
		draftLayout.setThemeId(publishedLayout.getThemeId());
		draftLayout.setColorSchemeId(publishedLayout.getColorSchemeId());
		draftLayout.setCss(publishedLayout.getCss());
		draftLayout.setPriority(publishedLayout.getPriority());
		draftLayout.setLayoutPrototypeUuid(
			publishedLayout.getLayoutPrototypeUuid());
		draftLayout.setLayoutPrototypeLinkEnabled(
			publishedLayout.getLayoutPrototypeLinkEnabled());
		draftLayout.setSourcePrototypeLayoutUuid(
			publishedLayout.getSourcePrototypeLayoutUuid());
		draftLayout.setPublishDate(publishedLayout.getPublishDate());
		draftLayout.setLastPublishDate(publishedLayout.getLastPublishDate());

		draftLayout.resetOriginalValues();

		return draftLayout;
	}

	private final Set<VersionServiceListener<Layout, LayoutVersion>>
		_versionServiceListeners = Collections.newSetFromMap(
			new ConcurrentHashMap
				<VersionServiceListener<Layout, LayoutVersion>, Boolean>());

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return LayoutLocalService.class.getName();
	}

	protected Class<?> getModelClass() {
		return Layout.class;
	}

	protected String getModelClassName() {
		return Layout.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource = layoutPersistence.getDataSource();

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

	@BeanReference(type = LayoutLocalService.class)
	protected LayoutLocalService layoutLocalService;

	@BeanReference(type = LayoutPersistence.class)
	protected LayoutPersistence layoutPersistence;

	@BeanReference(type = LayoutFinder.class)
	protected LayoutFinder layoutFinder;

	@BeanReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@BeanReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.GroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.GroupLocalService
		groupLocalService;

	@BeanReference(type = GroupPersistence.class)
	protected GroupPersistence groupPersistence;

	@BeanReference(type = GroupFinder.class)
	protected GroupFinder groupFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ImageLocalService.class
	)
	protected com.liferay.portal.kernel.service.ImageLocalService
		imageLocalService;

	@BeanReference(type = ImagePersistence.class)
	protected ImagePersistence imagePersistence;

	@BeanReference(
		type = com.liferay.asset.kernel.service.AssetEntryLocalService.class
	)
	protected com.liferay.asset.kernel.service.AssetEntryLocalService
		assetEntryLocalService;

	@BeanReference(type = AssetEntryPersistence.class)
	protected AssetEntryPersistence assetEntryPersistence;

	@BeanReference(type = AssetEntryFinder.class)
	protected AssetEntryFinder assetEntryFinder;

	@BeanReference(
		type = com.liferay.expando.kernel.service.ExpandoRowLocalService.class
	)
	protected com.liferay.expando.kernel.service.ExpandoRowLocalService
		expandoRowLocalService;

	@BeanReference(type = ExpandoRowPersistence.class)
	protected ExpandoRowPersistence expandoRowPersistence;

	@BeanReference(
		type = com.liferay.exportimport.kernel.service.ExportImportConfigurationLocalService.class
	)
	protected com.liferay.exportimport.kernel.service.
		ExportImportConfigurationLocalService
			exportImportConfigurationLocalService;

	@BeanReference(type = ExportImportConfigurationPersistence.class)
	protected ExportImportConfigurationPersistence
		exportImportConfigurationPersistence;

	@BeanReference(type = ExportImportConfigurationFinder.class)
	protected ExportImportConfigurationFinder exportImportConfigurationFinder;

	@BeanReference(
		type = com.liferay.ratings.kernel.service.RatingsStatsLocalService.class
	)
	protected com.liferay.ratings.kernel.service.RatingsStatsLocalService
		ratingsStatsLocalService;

	@BeanReference(type = RatingsStatsPersistence.class)
	protected RatingsStatsPersistence ratingsStatsPersistence;

	@BeanReference(type = RatingsStatsFinder.class)
	protected RatingsStatsFinder ratingsStatsFinder;

	@BeanReference(type = LayoutVersionPersistence.class)
	protected LayoutVersionPersistence layoutVersionPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutFriendlyURLLocalService
		layoutFriendlyURLLocalService;

	@BeanReference(type = LayoutFriendlyURLPersistence.class)
	protected LayoutFriendlyURLPersistence layoutFriendlyURLPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.LayoutPrototypeLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutPrototypeLocalService
		layoutPrototypeLocalService;

	@BeanReference(type = LayoutPrototypePersistence.class)
	protected LayoutPrototypePersistence layoutPrototypePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.LayoutSetLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutSetLocalService
		layoutSetLocalService;

	@BeanReference(type = LayoutSetPersistence.class)
	protected LayoutSetPersistence layoutSetPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService.class
	)
	protected com.liferay.portal.kernel.service.LayoutSetPrototypeLocalService
		layoutSetPrototypeLocalService;

	@BeanReference(type = LayoutSetPrototypePersistence.class)
	protected LayoutSetPrototypePersistence layoutSetPrototypePersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.PluginSettingLocalService.class
	)
	protected com.liferay.portal.kernel.service.PluginSettingLocalService
		pluginSettingLocalService;

	@BeanReference(type = PluginSettingPersistence.class)
	protected PluginSettingPersistence pluginSettingPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.PortalPreferencesLocalService.class
	)
	protected com.liferay.portal.kernel.service.PortalPreferencesLocalService
		portalPreferencesLocalService;

	@BeanReference(type = PortalPreferencesPersistence.class)
	protected PortalPreferencesPersistence portalPreferencesPersistence;

	@BeanReference(
		type = com.liferay.portal.kernel.service.PortletPreferencesLocalService.class
	)
	protected com.liferay.portal.kernel.service.PortletPreferencesLocalService
		portletPreferencesLocalService;

	@BeanReference(type = PortletPreferencesPersistence.class)
	protected PortletPreferencesPersistence portletPreferencesPersistence;

	@BeanReference(type = PortletPreferencesFinder.class)
	protected PortletPreferencesFinder portletPreferencesFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@BeanReference(
		type = com.liferay.portal.kernel.service.ResourcePermissionLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourcePermissionLocalService
		resourcePermissionLocalService;

	@BeanReference(type = ResourcePermissionPersistence.class)
	protected ResourcePermissionPersistence resourcePermissionPersistence;

	@BeanReference(type = ResourcePermissionFinder.class)
	protected ResourcePermissionFinder resourcePermissionFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@BeanReference(type = UserFinder.class)
	protected UserFinder userFinder;

	@BeanReference(
		type = com.liferay.portal.kernel.service.UserGroupLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserGroupLocalService
		userGroupLocalService;

	@BeanReference(type = UserGroupPersistence.class)
	protected UserGroupPersistence userGroupPersistence;

	@BeanReference(type = UserGroupFinder.class)
	protected UserGroupFinder userGroupFinder;

	@BeanReference(type = PersistedModelLocalServiceRegistry.class)
	protected PersistedModelLocalServiceRegistry
		persistedModelLocalServiceRegistry;

}