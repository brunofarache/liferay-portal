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

package com.liferay.headless.form.internal.graphql.mutation.v1_0;

import com.liferay.headless.form.dto.v1_0.Form;
import com.liferay.headless.form.dto.v1_0.FormRecord;
import com.liferay.headless.form.resource.v1_0.FormDocumentResource;
import com.liferay.headless.form.resource.v1_0.FormRecordResource;
import com.liferay.headless.form.resource.v1_0.FormResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;

import graphql.annotations.annotationTypes.GraphQLField;
import graphql.annotations.annotationTypes.GraphQLInvokeDetached;
import graphql.annotations.annotationTypes.GraphQLName;

import javax.annotation.Generated;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Javier Gamarra
 * @generated
 */
@Generated("")
public class Mutation {

	public static void setFormResourceComponentServiceObjects(
		ComponentServiceObjects<FormResource>
			formResourceComponentServiceObjects) {

		_formResourceComponentServiceObjects =
			formResourceComponentServiceObjects;
	}

	public static void setFormDocumentResourceComponentServiceObjects(
		ComponentServiceObjects<FormDocumentResource>
			formDocumentResourceComponentServiceObjects) {

		_formDocumentResourceComponentServiceObjects =
			formDocumentResourceComponentServiceObjects;
	}

	public static void setFormRecordResourceComponentServiceObjects(
		ComponentServiceObjects<FormRecordResource>
			formRecordResourceComponentServiceObjects) {

		_formRecordResourceComponentServiceObjects =
			formRecordResourceComponentServiceObjects;
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Form postFormEvaluateContext(
			@GraphQLName("formId") Long formId, @GraphQLName("Form") Form form)
		throws Exception {

		return _applyComponentServiceObjects(
			_formResourceComponentServiceObjects,
			this::_populateResourceContext,
			formResource -> formResource.postFormEvaluateContext(formId, form));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public Form postFormUploadFile(
			@GraphQLName("formId") Long formId, @GraphQLName("Form") Form form)
		throws Exception {

		return _applyComponentServiceObjects(
			_formResourceComponentServiceObjects,
			this::_populateResourceContext,
			formResource -> formResource.postFormUploadFile(formId, form));
	}

	@GraphQLInvokeDetached
	public void deleteFormDocument(
			@GraphQLName("formDocumentId") Long formDocumentId)
		throws Exception {

		_applyVoidComponentServiceObjects(
			_formDocumentResourceComponentServiceObjects,
			this::_populateResourceContext,
			formDocumentResource -> formDocumentResource.deleteFormDocument(
				formDocumentId));
	}

	@GraphQLInvokeDetached
	public FormRecord putFormRecord(
			@GraphQLName("formRecordId") Long formRecordId,
			@GraphQLName("FormRecord") FormRecord formRecord)
		throws Exception {

		return _applyComponentServiceObjects(
			_formRecordResourceComponentServiceObjects,
			this::_populateResourceContext,
			formRecordResource -> formRecordResource.putFormRecord(
				formRecordId, formRecord));
	}

	@GraphQLField
	@GraphQLInvokeDetached
	public FormRecord postFormFormRecord(
			@GraphQLName("formId") Long formId,
			@GraphQLName("FormRecord") FormRecord formRecord)
		throws Exception {

		return _applyComponentServiceObjects(
			_formRecordResourceComponentServiceObjects,
			this::_populateResourceContext,
			formRecordResource -> formRecordResource.postFormFormRecord(
				formId, formRecord));
	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(FormResource formResource)
		throws Exception {

		formResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(
			FormDocumentResource formDocumentResource)
		throws Exception {

		formDocumentResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private void _populateResourceContext(FormRecordResource formRecordResource)
		throws Exception {

		formRecordResource.setContextCompany(
			CompanyLocalServiceUtil.getCompany(
				CompanyThreadLocal.getCompanyId()));
	}

	private static ComponentServiceObjects<FormResource>
		_formResourceComponentServiceObjects;
	private static ComponentServiceObjects<FormDocumentResource>
		_formDocumentResourceComponentServiceObjects;
	private static ComponentServiceObjects<FormRecordResource>
		_formRecordResourceComponentServiceObjects;

}