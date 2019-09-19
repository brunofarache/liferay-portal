<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String editEntryContainerElementId = renderResponse.getNamespace() + "container";
String editEntryRootElementId = renderResponse.getNamespace() + "-app-builder-edit-entry";

long dataDefinitionId = ParamUtil.getLong(request, "dataDefinitionId");
long dataLayoutId = ParamUtil.getLong(request, "dataLayoutId");
%>

<div class="app-builder-root">
	<aui:form>
		<portlet:renderURL var="basePortletURL" />

		<liferay-data-engine:data-layout-renderer
			containerId="<%= editEntryContainerElementId %>"
			dataLayoutId="<%= dataLayoutId %>"
			namespace="<%= renderResponse.getNamespace() %>"
		/>

		<div id="<%= editEntryRootElementId %>">

			<%
			Map<String, Object> data = new HashMap<>();

			data.put("basePortletURL", basePortletURL.toString());
			data.put("dataDefinitionId", dataDefinitionId);
			data.put("editEntryContainerElementId", editEntryContainerElementId);
			%>

			<react:component
				data="<%= data %>"
				module="js/pages/entry/EditEntryApp.es"
			/>
		</div>
	</aui:form>
</div>