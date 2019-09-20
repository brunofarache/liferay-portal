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

package com.liferay.commerce.product.type.virtual.order.service.base;

import com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem;
import com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemService;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemFinder;
import com.liferay.commerce.product.type.virtual.order.service.persistence.CommerceVirtualOrderItemPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBManagerUtil;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;
import com.liferay.portal.kernel.service.persistence.ClassNamePersistence;
import com.liferay.portal.kernel.service.persistence.UserPersistence;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import javax.sql.DataSource;

/**
 * Provides the base implementation for the commerce virtual order item remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.commerce.product.type.virtual.order.service.impl.CommerceVirtualOrderItemServiceImpl}.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see com.liferay.commerce.product.type.virtual.order.service.impl.CommerceVirtualOrderItemServiceImpl
 * @generated
 */
public abstract class CommerceVirtualOrderItemServiceBaseImpl
	extends BaseServiceImpl
	implements CommerceVirtualOrderItemService, IdentifiableOSGiService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>CommerceVirtualOrderItemService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemServiceUtil</code>.
	 */

	/**
	 * Returns the commerce virtual order item local service.
	 *
	 * @return the commerce virtual order item local service
	 */
	public com.liferay.commerce.product.type.virtual.order.service.
		CommerceVirtualOrderItemLocalService
			getCommerceVirtualOrderItemLocalService() {

		return commerceVirtualOrderItemLocalService;
	}

	/**
	 * Sets the commerce virtual order item local service.
	 *
	 * @param commerceVirtualOrderItemLocalService the commerce virtual order item local service
	 */
	public void setCommerceVirtualOrderItemLocalService(
		com.liferay.commerce.product.type.virtual.order.service.
			CommerceVirtualOrderItemLocalService
				commerceVirtualOrderItemLocalService) {

		this.commerceVirtualOrderItemLocalService =
			commerceVirtualOrderItemLocalService;
	}

	/**
	 * Returns the commerce virtual order item remote service.
	 *
	 * @return the commerce virtual order item remote service
	 */
	public CommerceVirtualOrderItemService
		getCommerceVirtualOrderItemService() {

		return commerceVirtualOrderItemService;
	}

	/**
	 * Sets the commerce virtual order item remote service.
	 *
	 * @param commerceVirtualOrderItemService the commerce virtual order item remote service
	 */
	public void setCommerceVirtualOrderItemService(
		CommerceVirtualOrderItemService commerceVirtualOrderItemService) {

		this.commerceVirtualOrderItemService = commerceVirtualOrderItemService;
	}

	/**
	 * Returns the commerce virtual order item persistence.
	 *
	 * @return the commerce virtual order item persistence
	 */
	public CommerceVirtualOrderItemPersistence
		getCommerceVirtualOrderItemPersistence() {

		return commerceVirtualOrderItemPersistence;
	}

	/**
	 * Sets the commerce virtual order item persistence.
	 *
	 * @param commerceVirtualOrderItemPersistence the commerce virtual order item persistence
	 */
	public void setCommerceVirtualOrderItemPersistence(
		CommerceVirtualOrderItemPersistence
			commerceVirtualOrderItemPersistence) {

		this.commerceVirtualOrderItemPersistence =
			commerceVirtualOrderItemPersistence;
	}

	/**
	 * Returns the commerce virtual order item finder.
	 *
	 * @return the commerce virtual order item finder
	 */
	public CommerceVirtualOrderItemFinder getCommerceVirtualOrderItemFinder() {
		return commerceVirtualOrderItemFinder;
	}

	/**
	 * Sets the commerce virtual order item finder.
	 *
	 * @param commerceVirtualOrderItemFinder the commerce virtual order item finder
	 */
	public void setCommerceVirtualOrderItemFinder(
		CommerceVirtualOrderItemFinder commerceVirtualOrderItemFinder) {

		this.commerceVirtualOrderItemFinder = commerceVirtualOrderItemFinder;
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
	 * Returns the class name remote service.
	 *
	 * @return the class name remote service
	 */
	public com.liferay.portal.kernel.service.ClassNameService
		getClassNameService() {

		return classNameService;
	}

	/**
	 * Sets the class name remote service.
	 *
	 * @param classNameService the class name remote service
	 */
	public void setClassNameService(
		com.liferay.portal.kernel.service.ClassNameService classNameService) {

		this.classNameService = classNameService;
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
	 * Returns the user remote service.
	 *
	 * @return the user remote service
	 */
	public com.liferay.portal.kernel.service.UserService getUserService() {
		return userService;
	}

	/**
	 * Sets the user remote service.
	 *
	 * @param userService the user remote service
	 */
	public void setUserService(
		com.liferay.portal.kernel.service.UserService userService) {

		this.userService = userService;
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
	 * Returns the dl app local service.
	 *
	 * @return the dl app local service
	 */
	public com.liferay.document.library.kernel.service.DLAppLocalService
		getDLAppLocalService() {

		return dlAppLocalService;
	}

	/**
	 * Sets the dl app local service.
	 *
	 * @param dlAppLocalService the dl app local service
	 */
	public void setDLAppLocalService(
		com.liferay.document.library.kernel.service.DLAppLocalService
			dlAppLocalService) {

		this.dlAppLocalService = dlAppLocalService;
	}

	/**
	 * Returns the dl app remote service.
	 *
	 * @return the dl app remote service
	 */
	public com.liferay.document.library.kernel.service.DLAppService
		getDLAppService() {

		return dlAppService;
	}

	/**
	 * Sets the dl app remote service.
	 *
	 * @param dlAppService the dl app remote service
	 */
	public void setDLAppService(
		com.liferay.document.library.kernel.service.DLAppService dlAppService) {

		this.dlAppService = dlAppService;
	}

	public void afterPropertiesSet() {
	}

	public void destroy() {
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return CommerceVirtualOrderItemService.class.getName();
	}

	protected Class<?> getModelClass() {
		return CommerceVirtualOrderItem.class;
	}

	protected String getModelClassName() {
		return CommerceVirtualOrderItem.class.getName();
	}

	/**
	 * Performs a SQL query.
	 *
	 * @param sql the sql query
	 */
	protected void runSQL(String sql) {
		try {
			DataSource dataSource =
				commerceVirtualOrderItemPersistence.getDataSource();

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

	@BeanReference(
		type = com.liferay.commerce.product.type.virtual.order.service.CommerceVirtualOrderItemLocalService.class
	)
	protected com.liferay.commerce.product.type.virtual.order.service.
		CommerceVirtualOrderItemLocalService
			commerceVirtualOrderItemLocalService;

	@BeanReference(type = CommerceVirtualOrderItemService.class)
	protected CommerceVirtualOrderItemService commerceVirtualOrderItemService;

	@BeanReference(type = CommerceVirtualOrderItemPersistence.class)
	protected CommerceVirtualOrderItemPersistence
		commerceVirtualOrderItemPersistence;

	@BeanReference(type = CommerceVirtualOrderItemFinder.class)
	protected CommerceVirtualOrderItemFinder commerceVirtualOrderItemFinder;

	@ServiceReference(
		type = com.liferay.counter.kernel.service.CounterLocalService.class
	)
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameLocalService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameLocalService
		classNameLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ClassNameService.class
	)
	protected com.liferay.portal.kernel.service.ClassNameService
		classNameService;

	@ServiceReference(type = ClassNamePersistence.class)
	protected ClassNamePersistence classNamePersistence;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.ResourceLocalService.class
	)
	protected com.liferay.portal.kernel.service.ResourceLocalService
		resourceLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserLocalService.class
	)
	protected com.liferay.portal.kernel.service.UserLocalService
		userLocalService;

	@ServiceReference(
		type = com.liferay.portal.kernel.service.UserService.class
	)
	protected com.liferay.portal.kernel.service.UserService userService;

	@ServiceReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;

	@ServiceReference(
		type = com.liferay.document.library.kernel.service.DLAppLocalService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppLocalService
		dlAppLocalService;

	@ServiceReference(
		type = com.liferay.document.library.kernel.service.DLAppService.class
	)
	protected com.liferay.document.library.kernel.service.DLAppService
		dlAppService;

}