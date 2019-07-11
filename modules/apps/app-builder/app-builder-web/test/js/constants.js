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

export const ACTIONS = [
	{
		name: 'Delete',
		callback: () => {}
	}
];

export const COLUMNS = [
	{
		name: 'Name'
	},
	{
		dateCreated: 'Created Date'
	},
	{
		dateModified: 'Modified Date'
	}
];

export const EMPTY_STATE = {
	title: 'title',
	description: 'description'
};

export const ENDPOINT = '/endpoint';

export const FORMATTER = items => items;

export const ITEMS = {
	ONE: [
		{
			id: 1,
			name: 'Name',
			dateCreated: '01/01/2019',
			dateModified: '01/02/2019'
		}
	]
};

export const RESPONSES = {
	ONE: {
		items: ITEMS.ONE,
		lastPage: 1,
		page: 1,
		pageSize: 1,
		totalCount: 1
	}
};
