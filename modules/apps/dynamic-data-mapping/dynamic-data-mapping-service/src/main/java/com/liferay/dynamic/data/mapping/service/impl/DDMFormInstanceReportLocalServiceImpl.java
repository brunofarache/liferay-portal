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

package com.liferay.dynamic.data.mapping.service.impl;

import com.liferay.dynamic.data.mapping.exception.FormInstanceReportDataException;
import com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport;
import com.liferay.dynamic.data.mapping.service.base.DDMFormInstanceReportLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marcos Martins
 */
@Component(
	property = "model.class.name=com.liferay.dynamic.data.mapping.model.DDMFormInstanceReport",
	service = AopService.class
)
public class DDMFormInstanceReportLocalServiceImpl
	extends DDMFormInstanceReportLocalServiceBaseImpl {

	@Override
	public DDMFormInstanceReport addFormInstanceReport(
			long formInstanceId, String data, long groupId,
			ServiceContext serviceContext)
		throws PortalException {

		validate(data);

		long formInstanceReportId = counterLocalService.increment();

		DDMFormInstanceReport ddmFormInstanceReport =
			ddmFormInstanceReportPersistence.create(formInstanceReportId);

		ddmFormInstanceReport.setGroupId(groupId);
		ddmFormInstanceReport.setCompanyId(serviceContext.getCompanyId());
		ddmFormInstanceReport.setCreateDate(serviceContext.getModifiedDate());
		ddmFormInstanceReport.setFormInstanceId(formInstanceId);
		ddmFormInstanceReport.setData(data);

		return ddmFormInstanceReportPersistence.update(ddmFormInstanceReport);
	}

	@Override
	public DDMFormInstanceReport deleteFormInstanceReport(
		DDMFormInstanceReport ddmFormInstanceReport) {

		ddmFormInstanceReportPersistence.remove(ddmFormInstanceReport);

		return ddmFormInstanceReport;
	}

	@Override
	public DDMFormInstanceReport getFormInstanceReport(long formInstanceId)
		throws PortalException {

		return ddmFormInstanceReportPersistence.findByFormInstanceId(
			formInstanceId);
	}

	@Override
	public DDMFormInstanceReport updateFormInstanceReport(
			String data, long formInstanceReportId,
			ServiceContext serviceContext)
		throws PortalException {

		validate(data);

		DDMFormInstanceReport ddmFormInstanceReport =
			ddmFormInstanceReportPersistence.findByPrimaryKey(
				formInstanceReportId);

		ddmFormInstanceReport.setModifiedDate(
			serviceContext.getModifiedDate(null));

		ddmFormInstanceReport.setData(data);

		return ddmFormInstanceReportPersistence.update(ddmFormInstanceReport);
	}

	protected void validate(String data) throws PortalException {
		if (Validator.isNull(data)) {
			throw new FormInstanceReportDataException(
				"Unable to save formInstanceReport with null data");
		}
	}

}