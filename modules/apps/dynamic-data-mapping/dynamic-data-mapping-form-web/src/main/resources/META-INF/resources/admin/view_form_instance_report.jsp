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

<%@ include file="/admin/init.jsp" %>

<%
DDMFormViewFormInstanceRecordsDisplayContext ddmFormViewFormInstanceRecordsDisplayContext = ddmFormAdminDisplayContext.getFormViewRecordsDisplayContext();

int totalItems = ddmFormViewFormInstanceRecordsDisplayContext.getTotalItems();
%>

<div class="ddm-form-report hide">
	<div class="ddm-form-report-header">
		<div class="container-fluid container-fluid-max-xl">
			<div class="align-items-center autofit-row">
				<span class="ddm-form-report-header-title text-truncate">
					<liferay-ui:message arguments="<%= totalItems %>" key="x-entries" />
				</span>
			</div>

			<div class="align-items-center autofit-row">
				<span class="ddm-form-report-header-subtitle text-truncate">
					<c:choose>
						<c:when test="<%= totalItems > 0 %>">
							<liferay-ui:message arguments="ago few seconds" key="last-entry-submitted-x" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message key="there-are-no-entries" />
						</c:otherwise>
					</c:choose>
				</span>
			</div>
		</div>
	</div>

	<nav class="mb-4 navbar navbar-collapse-absolute navbar-expand-md navbar-underline navigation-bar navigation-bar-light">
		<div class="container-fluid container-fluid-max-xl">
			<ul class="navbar-nav" id="<portlet:namespace />navTab" role="tablist">
				<li class="nav-item">
					<a aria-controls="<portlet:namespace />navEntries" class="active nav-link" data-toggle="liferay-tab" href="#<portlet:namespace />navEntries" id="<portlet:namespace />navEntriesTab" role="tab">
						<liferay-ui:message key="entries" />
					</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="tab-content" id="<portlet:namespace />navTabContents">
		<div aria-labelledby="<portlet:namespace />navEntriesTab" class="active fade show tab-pane" id="<portlet:namespace />navEntries" role="tabpanel">
			<liferay-util:include page="/admin/form_instance_records_search_container.jsp" servletContext="<%= application %>" />
		</div>
	</div>
</div>