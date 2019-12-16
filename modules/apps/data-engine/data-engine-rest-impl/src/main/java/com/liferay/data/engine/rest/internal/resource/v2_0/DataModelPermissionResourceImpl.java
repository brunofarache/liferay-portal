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

package com.liferay.data.engine.rest.internal.resource.v2_0;

import com.liferay.data.engine.rest.dto.v2_0.DataModelPermission;
import com.liferay.data.engine.rest.internal.constants.DataDefinitionConstants;
import com.liferay.data.engine.rest.internal.constants.DataEngineConstants;
import com.liferay.data.engine.rest.internal.constants.DataLayoutConstants;
import com.liferay.data.engine.rest.internal.constants.DataRecordCollectionConstants;
import com.liferay.data.engine.rest.internal.resource.util.DataEnginePermissionUtil;
import com.liferay.data.engine.rest.resource.v2_0.DataModelPermissionResource;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalService;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMStructureLayout;
import com.liferay.dynamic.data.mapping.service.DDMStructureLayoutLocalService;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ResourceAction;
import com.liferay.portal.kernel.model.ResourceConstants;
import com.liferay.portal.kernel.model.ResourcePermission;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ResourceActionLocalService;
import com.liferay.portal.kernel.service.ResourcePermissionLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.permission.ModelPermissions;
import com.liferay.portal.kernel.service.permission.ModelPermissionsFactory;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Jeyvison Nascimento
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v2_0/data-model-permission.properties",
	scope = ServiceScope.PROTOTYPE, service = DataModelPermissionResource.class
)
public class DataModelPermissionResourceImpl
	extends BaseDataModelPermissionResourceImpl {

	@Override
	public Page<DataModelPermission> getDataDefinitionDataModelPermissionsPage(
			Long dataDefinitionId, String roleNames)
		throws Exception {

		DDMStructure ddmStructure = _ddmStructureLocalService.getDDMStructure(
			dataDefinitionId);

		DataEnginePermissionUtil.checkPermission(
			ActionKeys.PERMISSIONS, _groupLocalService,
			ddmStructure.getGroupId());

		return _getDataModelPermissionPage(
			ddmStructure.getCompanyId(), dataDefinitionId,
			DataRecordCollectionConstants.RESOURCE_NAME, roleNames);
	}

	@Override
	public Page<DataModelPermission>
			getDataRecordCollectionDataModelPermissionsPage(
				Long dataRecordCollectionId, String roleNames)
		throws Exception {

		DDLRecordSet ddlRecordSet = _ddlRecordSetLocalService.getRecordSet(
			dataRecordCollectionId);

		DataEnginePermissionUtil.checkPermission(
			ActionKeys.PERMISSIONS, _groupLocalService,
			ddlRecordSet.getGroupId());

		return _getDataModelPermissionPage(
			ddlRecordSet.getCompanyId(), dataRecordCollectionId,
			DataDefinitionConstants.RESOURCE_NAME, roleNames);
	}

	@Override
	public void postSiteDataModelPermission(
			Long siteId, DataModelPermission[] dataModelPermissions)
		throws Exception {

		DataEnginePermissionUtil.checkPermission(
			ActionKeys.PERMISSIONS, _groupLocalService, siteId);

		for (DataModelPermission dataModelPermission : dataModelPermissions) {
			DataEnginePermissionUtil.persistPermission(
				ListUtil.fromArray(dataModelPermission.getActionIds()),
				contextCompany, DataEngineConstants.OPERATION_SAVE_PERMISSION,
				_resourcePermissionLocalService, _roleLocalService,
				new String[] {dataModelPermission.getRoleName()});
		}
	}

	@Override
	public void putDataDefinitionDataModelPermission(
			Long dataDefinitionId, DataModelPermission[] dataModelPermissions)
		throws Exception {

		DDMStructure ddmStructure = _ddmStructureLocalService.getDDMStructure(
			dataDefinitionId);

		DataEnginePermissionUtil.checkPermission(
			ActionKeys.PERMISSIONS, _groupLocalService,
			ddmStructure.getGroupId());

		_resourcePermissionLocalService.updateResourcePermissions(
			ddmStructure.getCompanyId(), ddmStructure.getGroupId(),
			DataRecordCollectionConstants.RESOURCE_NAME,
			String.valueOf(dataDefinitionId),
			_getModelPermissions(
				dataModelPermissions, DataDefinitionConstants.RESOURCE_NAME));
	}

	@Override
	public void putDataLayoutDataModelPermission(
			Long dataLayoutId, DataModelPermission[] dataModelPermissions)
		throws Exception {

		DDMStructureLayout ddmStructureLayout =
			_ddmStructureLayoutLocalService.getStructureLayout(dataLayoutId);

		DataEnginePermissionUtil.checkPermission(
			ActionKeys.PERMISSIONS, _groupLocalService,
			ddmStructureLayout.getGroupId());

		_resourcePermissionLocalService.updateResourcePermissions(
			ddmStructureLayout.getCompanyId(), ddmStructureLayout.getGroupId(),
			DataRecordCollectionConstants.RESOURCE_NAME,
			String.valueOf(dataLayoutId),
			_getModelPermissions(
				dataModelPermissions, DataLayoutConstants.RESOURCE_NAME));
	}

	@Override
	public void putDataRecordCollectionDataModelPermission(
			Long dataRecordCollectionId,
			DataModelPermission[] dataModelPermissions)
		throws Exception {

		DDLRecordSet ddlRecordSet = _ddlRecordSetLocalService.getRecordSet(
			dataRecordCollectionId);

		DataEnginePermissionUtil.checkPermission(
			ActionKeys.PERMISSIONS, _groupLocalService,
			ddlRecordSet.getGroupId());

		_resourcePermissionLocalService.updateResourcePermissions(
			ddlRecordSet.getCompanyId(), ddlRecordSet.getGroupId(),
			DataRecordCollectionConstants.RESOURCE_NAME,
			String.valueOf(dataRecordCollectionId),
			_getModelPermissions(
				dataModelPermissions,
				DataRecordCollectionConstants.RESOURCE_NAME));
	}

	private Page<DataModelPermission> _getDataModelPermissionPage(
			Long companyId, Long id, String resourceName, String roleNames)
		throws PortalException {

		List<ResourceAction> resourceActions =
			_resourceActionLocalService.getResourceActions(resourceName);

		return Page.of(
			transform(
				DataEnginePermissionUtil.getRoles(
					contextCompany, _roleLocalService,
					StringUtil.split(roleNames)),
				role -> _toDataModelPermission(
					companyId, id, resourceActions, role)));
	}

	private ModelPermissions _getModelPermissions(
		DataModelPermission[] dataModelPermissions, String resourceName) {

		ModelPermissions modelPermissions =
			ModelPermissionsFactory.createWithDefaultPermissions(resourceName);

		for (DataModelPermission dataModelPermission : dataModelPermissions) {
			modelPermissions.addRolePermissions(
				dataModelPermission.getRoleName(),
				dataModelPermission.getActionIds());
		}

		return modelPermissions;
	}

	private DataModelPermission _toDataModelPermission(
			Long companyId, Long id, List<ResourceAction> resourceActions,
			Role role)
		throws Exception {

		ResourcePermission resourcePermission =
			_resourcePermissionLocalService.fetchResourcePermission(
				companyId, DataRecordCollectionConstants.RESOURCE_NAME,
				ResourceConstants.SCOPE_INDIVIDUAL, String.valueOf(id),
				role.getRoleId());

		if (resourcePermission == null) {
			return null;
		}

		Set<String> actionsIdsSet = new HashSet<>();

		long actionIds = resourcePermission.getActionIds();

		for (ResourceAction resourceAction : resourceActions) {
			long bitwiseValue = resourceAction.getBitwiseValue();

			if ((actionIds & bitwiseValue) == bitwiseValue) {
				actionsIdsSet.add(resourceAction.getActionId());
			}
		}

		return new DataModelPermission() {
			{
				actionIds = actionsIdsSet.toArray(new String[0]);
				roleName = role.getName();
			}
		};
	}

	@Reference
	private DDLRecordSetLocalService _ddlRecordSetLocalService;

	@Reference
	private DDMStructureLayoutLocalService _ddmStructureLayoutLocalService;

	@Reference
	private DDMStructureLocalService _ddmStructureLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private ResourceActionLocalService _resourceActionLocalService;

	@Reference
	private ResourcePermissionLocalService _resourcePermissionLocalService;

	@Reference
	private RoleLocalService _roleLocalService;

}