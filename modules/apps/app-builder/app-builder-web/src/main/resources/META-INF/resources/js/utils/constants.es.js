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

import {deleteItem} from './client.es';
import {Link} from 'react-router-dom';
import moment from 'moment';
import React from 'react';

export const APPS = {
	ACTIONS: [
		{
			name: Liferay.Language.get('delete'),
			callback: row => deleteItem(`/o/app-builder/v1.0/apps/${row.id}`)
		}
	],
	COLUMNS: [
		{
			name: Liferay.Language.get('name')
		},
		{
			type: Liferay.Language.get('deployed-as')
		},
		{
			dateCreated: Liferay.Language.get('create-date')
		},
		{
			dateModified: Liferay.Language.get('modified-date')
		},
		{
			status: Liferay.Language.get('status')
		}
	],
	EMPTY_STATE: {
		title: Liferay.Language.get('no-deployments-yet'),
		description: Liferay.Language.get(
			'select-the-form-and-table-view-you-want-and-deploy-your-app-as-a-widget-standalone-or-place-it-in-the-product-menu'
		)
	},
	ENDPOINT: dataDefinitionId =>
		`/o/app-builder/v1.0/data-definitions/${dataDefinitionId}/apps`,
	FORMATTER: items =>
		items.map(item => ({
			dateCreated: moment(item.dateCreated).fromNow(),
			dateModified: moment(item.dateModified).fromNow(),
			id: item.id,
			name: item.name.en_US,
			status: item.settings.status,
			type: item.settings.type
		}))
};

export const CUSTOM_OBJECTS = {
	ACTIONS: [
		{
			name: Liferay.Language.get('delete'),
			callback: row =>
				deleteItem(`/o/data-engine/v1.0/data-definitions/${row.id}`)
		}
	],
	COLUMNS: [
		{
			name: Liferay.Language.get('name')
		},
		{
			dateCreated: Liferay.Language.get('create-date')
		},
		{
			dateModified: Liferay.Language.get('modified-date')
		}
	],
	EMPTY_STATE: {
		title: Liferay.Language.get('no-custom-objects-yet'),
		description: Liferay.Language.get(
			'custom-objects-define-the-types-of-data-your-business-application-needs'
		)
	},
	ENDPOINT: `/o/data-engine/v1.0/sites/${Liferay.ThemeDisplay.getCompanyGroupId()}/data-definitions`,
	FORMATTER: items =>
		items.map(item => ({
			dateCreated: moment(item.dateCreated).fromNow(),
			dateModified: moment(item.dateModified).fromNow(),
			id: item.id,
			name: (
				<Link to={`/custom-object/${item.id}/form-views`}>
					{item.name.en_US}
				</Link>
			)
		}))
};

export const TABLE_VIEWS = {
	ACTIONS: [
		{
			name: Liferay.Language.get('delete'),
			callback: row =>
				deleteItem(`/o/data-engine/v1.0/data-layouts-views/${row.id}`)
		}
	],
	COLUMNS: [
		{
			name: Liferay.Language.get('name')
		},
		{
			dateCreated: Liferay.Language.get('create-date')
		},
		{
			dateModified: Liferay.Language.get('modified-date')
		}
	],
	EMPTY_STATE: {
		title: Liferay.Language.get('no-table-views-yet'),
		description: Liferay.Language.get(
			'create-one-or-more-tables-to-display-the-data-held-in-your-data-object'
		)
	},
	ENDPOINT: dataDefinitionId =>
		`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-layouts-views`,
	FORMATTER: items =>
		items.map(item => ({
			dateCreated: moment(item.dateCreated).fromNow(),
			dateModified: moment(item.dateModified).fromNow(),
			id: item.id,
			name: item.name.en_US
		}))
};

export const FORM_VIEWS = {
	ACTIONS: [
		{
			name: Liferay.Language.get('delete'),
			callback: row =>
				deleteItem(`/o/data-engine/v1.0/data-layouts/${row.id}`)
		}
	],
	COLUMNS: [
		{
			name: Liferay.Language.get('name')
		},
		{
			dateCreated: Liferay.Language.get('create-date')
		},
		{
			dateModified: Liferay.Language.get('modified-date')
		}
	],
	EMPTY_STATE: {
		title: Liferay.Language.get('no-form-views-yet'),
		description: Liferay.Language.get(
			'create-one-or-more-forms-to-display-the-data-held-in-your-data-object'
		)
	},
	ENDPOINT: dataDefinitionId =>
		`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-layouts`,
	FORMATTER: items =>
		items.map(item => ({
			dateCreated: moment(item.dateCreated).fromNow(),
			dateModified: moment(item.dateModified).fromNow(),
			id: item.id,
			name: item.name.en_US
		}))
};
