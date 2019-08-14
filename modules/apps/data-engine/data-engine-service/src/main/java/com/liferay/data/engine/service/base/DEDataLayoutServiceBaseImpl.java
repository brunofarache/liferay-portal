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

package com.liferay.data.engine.service.base;

import com.liferay.data.engine.model.DEDataLayout;
import com.liferay.data.engine.service.DEDataLayoutService;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.module.framework.service.IdentifiableOSGiService;
import com.liferay.portal.kernel.service.BaseServiceImpl;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the de data layout remote service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.liferay.data.engine.service.impl.DEDataLayoutServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.data.engine.service.impl.DEDataLayoutServiceImpl
 * @generated
 */
public abstract class DEDataLayoutServiceBaseImpl
	extends BaseServiceImpl
	implements DEDataLayoutService, AopService, IdentifiableOSGiService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Use <code>DEDataLayoutService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.data.engine.service.DEDataLayoutServiceUtil</code>.
	 */
	@Override
	public Class<?>[] getAopInterfaces() {
		return new Class<?>[] {
			DEDataLayoutService.class, IdentifiableOSGiService.class
		};
	}

	@Override
	public void setAopProxy(Object aopProxy) {
		deDataLayoutService = (DEDataLayoutService)aopProxy;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return DEDataLayoutService.class.getName();
	}

	protected Class<?> getModelClass() {
		return DEDataLayout.class;
	}

	protected String getModelClassName() {
		return DEDataLayout.class.getName();
	}

	@Reference
	protected com.liferay.data.engine.service.DEDataLayoutLocalService
		deDataLayoutLocalService;

	protected DEDataLayoutService deDataLayoutService;

	@Reference
	protected com.liferay.counter.kernel.service.CounterLocalService
		counterLocalService;

}