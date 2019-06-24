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

<%@ include file="/layout/view/init.jsp" %>

<%
String ppid = ParamUtil.getString(request, "p_p_id");
long previewAssetEntryId = ParamUtil.getLong(request, "previewAssetEntryId");
int previewAssetEntryType = ParamUtil.getInteger(request, "previewAssetEntryType");
%>

<liferay-ui:success key="layoutPublished" message="the-page-was-published-succesfully" />

<c:choose>
	<c:when test="<%= (themeDisplay.isStatePopUp() || themeDisplay.isWidget() || layoutTypePortlet.hasStateMax()) && Validator.isNotNull(ppid) %>">

		<%
		String templateId = null;
		String templateContent = null;
		String langType = null;

		if (themeDisplay.isStatePopUp() || themeDisplay.isWidget()) {
			templateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "pop_up";
			templateContent = LayoutTemplateLocalServiceUtil.getContent("pop_up", true, theme.getThemeId());
			langType = LayoutTemplateLocalServiceUtil.getLangType("pop_up", true, theme.getThemeId());
		}
		else {
			ppid = StringUtil.split(layoutTypePortlet.getStateMax())[0];

			templateId = theme.getThemeId() + LayoutTemplateConstants.STANDARD_SEPARATOR + "max";
			templateContent = LayoutTemplateLocalServiceUtil.getContent("max", true, theme.getThemeId());
			langType = LayoutTemplateLocalServiceUtil.getLangType("max", true, theme.getThemeId());
		}

		if (Validator.isNotNull(templateContent)) {
			RuntimePageUtil.processTemplate(request, response, ppid, new StringTemplateResource(templateId, templateContent), langType);
		}
		%>

	</c:when>
	<c:otherwise>

		<%
		LayoutPageTemplateStructure layoutPageTemplateStructure = LayoutPageTemplateStructureLocalServiceUtil.fetchLayoutPageTemplateStructure(layout.getGroupId(), PortalUtil.getClassNameId(Layout.class.getName()), layout.getPlid(), true);

		long[] segmentsExperienceIds = GetterUtil.getLongValues(request.getAttribute(SegmentsWebKeys.SEGMENTS_EXPERIENCE_IDS), new long[] {SegmentsConstants.SEGMENTS_EXPERIENCE_ID_DEFAULT});

		String data = layoutPageTemplateStructure.getData(segmentsExperienceIds);
		%>

		<c:if test="<%= Validator.isNotNull(data) %>">

			<%
			JSONObject dataJSONObject = JSONFactoryUtil.createJSONObject(data);

			JSONArray structureJSONArray = dataJSONObject.getJSONArray("structure");
			%>

			<liferay-layout:render-fragment-layout
				mode="<%= FragmentEntryLinkConstants.VIEW %>"
				previewClassPK="<%= previewAssetEntryId %>"
				previewType="<%= previewAssetEntryType %>"
				segmentsExperienceIds="<%= segmentsExperienceIds %>"
				structureJSONArray="<%= structureJSONArray %>"
			/>
		</c:if>
	</c:otherwise>
</c:choose>

<liferay-ui:layout-common />