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

package com.liferay.data.engine.rest.client.resource.v2_0;

import com.liferay.data.engine.rest.client.dto.v2_0.DataModelPermission;
import com.liferay.data.engine.rest.client.http.HttpInvoker;
import com.liferay.data.engine.rest.client.pagination.Page;
import com.liferay.data.engine.rest.client.serdes.v2_0.DataModelPermissionSerDes;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Jeyvison Nascimento
 * @generated
 */
@Generated("")
public interface DataModelPermissionResource {

	public static Builder builder() {
		return new Builder();
	}

	public Page<DataModelPermission> getDataDefinitionDataModelPermissionsPage(
			Long dataDefinitionId, String roleNames)
		throws Exception;

	public HttpInvoker.HttpResponse
			getDataDefinitionDataModelPermissionsPageHttpResponse(
				Long dataDefinitionId, String roleNames)
		throws Exception;

	public void putDataDefinitionDataModelPermission(
			Long dataDefinitionId, DataModelPermission[] dataModelPermissions)
		throws Exception;

	public HttpInvoker.HttpResponse
			putDataDefinitionDataModelPermissionHttpResponse(
				Long dataDefinitionId,
				DataModelPermission[] dataModelPermissions)
		throws Exception;

	public Page<DataModelPermission> getDataLayoutDataModelPermissionsPage(
			Long dataLayoutId, String roleNames)
		throws Exception;

	public HttpInvoker.HttpResponse
			getDataLayoutDataModelPermissionsPageHttpResponse(
				Long dataLayoutId, String roleNames)
		throws Exception;

	public void putDataLayoutDataModelPermission(
			Long dataLayoutId, DataModelPermission[] dataModelPermissions)
		throws Exception;

	public HttpInvoker.HttpResponse
			putDataLayoutDataModelPermissionHttpResponse(
				Long dataLayoutId, DataModelPermission[] dataModelPermissions)
		throws Exception;

	public Page<DataModelPermission>
			getDataRecordCollectionDataModelPermissionsPage(
				Long dataRecordCollectionId, String roleNames)
		throws Exception;

	public HttpInvoker.HttpResponse
			getDataRecordCollectionDataModelPermissionsPageHttpResponse(
				Long dataRecordCollectionId, String roleNames)
		throws Exception;

	public void putDataRecordCollectionDataModelPermission(
			Long dataRecordCollectionId,
			DataModelPermission[] dataModelPermissions)
		throws Exception;

	public HttpInvoker.HttpResponse
			putDataRecordCollectionDataModelPermissionHttpResponse(
				Long dataRecordCollectionId,
				DataModelPermission[] dataModelPermissions)
		throws Exception;

	public String getDataRecordCollectionDataModelPermissionByCurrentUser(
			Long dataRecordCollectionId)
		throws Exception;

	public HttpInvoker.HttpResponse
			getDataRecordCollectionDataModelPermissionByCurrentUserHttpResponse(
				Long dataRecordCollectionId)
		throws Exception;

	public void postSiteDataModelPermission(
			Long siteId, DataModelPermission[] dataModelPermissions)
		throws Exception;

	public HttpInvoker.HttpResponse postSiteDataModelPermissionHttpResponse(
			Long siteId, DataModelPermission[] dataModelPermissions)
		throws Exception;

	public static class Builder {

		public Builder authentication(String login, String password) {
			_login = login;
			_password = password;

			return this;
		}

		public DataModelPermissionResource build() {
			return new DataModelPermissionResourceImpl(this);
		}

		public Builder endpoint(String host, int port, String scheme) {
			_host = host;
			_port = port;
			_scheme = scheme;

			return this;
		}

		public Builder header(String key, String value) {
			_headers.put(key, value);

			return this;
		}

		public Builder locale(Locale locale) {
			_locale = locale;

			return this;
		}

		public Builder parameter(String key, String value) {
			_parameters.put(key, value);

			return this;
		}

		private Builder() {
		}

		private Map<String, String> _headers = new LinkedHashMap<>();
		private String _host = "localhost";
		private Locale _locale;
		private String _login = "test@liferay.com";
		private String _password = "test";
		private Map<String, String> _parameters = new LinkedHashMap<>();
		private int _port = 8080;
		private String _scheme = "http";

	}

	public static class DataModelPermissionResourceImpl
		implements DataModelPermissionResource {

		public Page<DataModelPermission>
				getDataDefinitionDataModelPermissionsPage(
					Long dataDefinitionId, String roleNames)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getDataDefinitionDataModelPermissionsPageHttpResponse(
					dataDefinitionId, roleNames);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, DataModelPermissionSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getDataDefinitionDataModelPermissionsPageHttpResponse(
					Long dataDefinitionId, String roleNames)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (roleNames != null) {
				httpInvoker.parameter("roleNames", String.valueOf(roleNames));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-definitions/{dataDefinitionId}/data-model-permissions",
				dataDefinitionId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putDataDefinitionDataModelPermission(
				Long dataDefinitionId,
				DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putDataDefinitionDataModelPermissionHttpResponse(
					dataDefinitionId, dataModelPermissions);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putDataDefinitionDataModelPermissionHttpResponse(
					Long dataDefinitionId,
					DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					dataModelPermissions
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-definitions/{dataDefinitionId}/data-model-permissions",
				dataDefinitionId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<DataModelPermission> getDataLayoutDataModelPermissionsPage(
				Long dataLayoutId, String roleNames)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getDataLayoutDataModelPermissionsPageHttpResponse(
					dataLayoutId, roleNames);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, DataModelPermissionSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getDataLayoutDataModelPermissionsPageHttpResponse(
					Long dataLayoutId, String roleNames)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (roleNames != null) {
				httpInvoker.parameter("roleNames", String.valueOf(roleNames));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-layouts/{dataLayoutId}/data-model-permissions",
				dataLayoutId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putDataLayoutDataModelPermission(
				Long dataLayoutId, DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putDataLayoutDataModelPermissionHttpResponse(
					dataLayoutId, dataModelPermissions);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putDataLayoutDataModelPermissionHttpResponse(
					Long dataLayoutId,
					DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					dataModelPermissions
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-layouts/{dataLayoutId}/data-model-permissions",
				dataLayoutId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public Page<DataModelPermission>
				getDataRecordCollectionDataModelPermissionsPage(
					Long dataRecordCollectionId, String roleNames)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getDataRecordCollectionDataModelPermissionsPageHttpResponse(
					dataRecordCollectionId, roleNames);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return Page.of(content, DataModelPermissionSerDes::toDTO);
		}

		public HttpInvoker.HttpResponse
				getDataRecordCollectionDataModelPermissionsPageHttpResponse(
					Long dataRecordCollectionId, String roleNames)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			if (roleNames != null) {
				httpInvoker.parameter("roleNames", String.valueOf(roleNames));
			}

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-record-collections/{dataRecordCollectionId}/data-model-permissions",
				dataRecordCollectionId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void putDataRecordCollectionDataModelPermission(
				Long dataRecordCollectionId,
				DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				putDataRecordCollectionDataModelPermissionHttpResponse(
					dataRecordCollectionId, dataModelPermissions);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse
				putDataRecordCollectionDataModelPermissionHttpResponse(
					Long dataRecordCollectionId,
					DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					dataModelPermissions
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.PUT);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-record-collections/{dataRecordCollectionId}/data-model-permissions",
				dataRecordCollectionId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public String getDataRecordCollectionDataModelPermissionByCurrentUser(
				Long dataRecordCollectionId)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				getDataRecordCollectionDataModelPermissionByCurrentUserHttpResponse(
					dataRecordCollectionId);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());

			return content;
		}

		public HttpInvoker.HttpResponse
				getDataRecordCollectionDataModelPermissionByCurrentUserHttpResponse(
					Long dataRecordCollectionId)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.GET);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/data-record-collections/{dataRecordCollectionId}/data-model-permissions/by-current-user",
				dataRecordCollectionId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		public void postSiteDataModelPermission(
				Long siteId, DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker.HttpResponse httpResponse =
				postSiteDataModelPermissionHttpResponse(
					siteId, dataModelPermissions);

			String content = httpResponse.getContent();

			_logger.fine("HTTP response content: " + content);

			_logger.fine("HTTP response message: " + httpResponse.getMessage());
			_logger.fine(
				"HTTP response status code: " + httpResponse.getStatusCode());
		}

		public HttpInvoker.HttpResponse postSiteDataModelPermissionHttpResponse(
				Long siteId, DataModelPermission[] dataModelPermissions)
			throws Exception {

			HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

			httpInvoker.body(
				Stream.of(
					dataModelPermissions
				).map(
					value -> String.valueOf(value)
				).collect(
					Collectors.toList()
				).toString(),
				"application/json");

			if (_builder._locale != null) {
				httpInvoker.header(
					"Accept-Language", _builder._locale.toLanguageTag());
			}

			for (Map.Entry<String, String> entry :
					_builder._headers.entrySet()) {

				httpInvoker.header(entry.getKey(), entry.getValue());
			}

			for (Map.Entry<String, String> entry :
					_builder._parameters.entrySet()) {

				httpInvoker.parameter(entry.getKey(), entry.getValue());
			}

			httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);

			httpInvoker.path(
				_builder._scheme + "://" + _builder._host + ":" +
					_builder._port +
						"/o/data-engine/v2.0/sites/{siteId}/data-model-permissions",
				siteId);

			httpInvoker.userNameAndPassword(
				_builder._login + ":" + _builder._password);

			return httpInvoker.invoke();
		}

		private DataModelPermissionResourceImpl(Builder builder) {
			_builder = builder;
		}

		private static final Logger _logger = Logger.getLogger(
			DataModelPermissionResource.class.getName());

		private Builder _builder;

	}

}