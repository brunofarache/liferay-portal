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

import moment from 'moment';
import React from 'react';
import ListView from '../../components/list-view/ListView.es';
import {confirmDelete} from '../../utils/client.es';

const FORM_VIEWS = {
	ACTIONS: [
		{
			callback: confirmDelete('/o/data-engine/v1.0/data-layouts/'),
			name: Liferay.Language.get('delete')
		}
	],
	COLUMNS: [
		{
			key: 'name',
			value: Liferay.Language.get('name')
		},
		{
			key: 'dateCreated',
			value: Liferay.Language.get('create-date')
		},
		{
			asc: false,
			key: 'dateModified',
			value: Liferay.Language.get('modified-date')
		}
	],
	EMPTY_STATE: {
		empty: {
			description: Liferay.Language.get(
				'create-one-or-more-forms-to-display-the-data-held-in-your-data-object'
			),
			title: Liferay.Language.get('there-are-no-form-views-yet')
		}
	},
	FORMATTER: items =>
		items.map(item => ({
			dateCreated: moment(item.dateCreated).fromNow(),
			dateModified: moment(item.dateModified).fromNow(),
			id: item.id,
			name: item.name.en_US
		}))
};

export default ({
	match: {
		params: {dataDefinitionId}
	}
}) => {
	return (
		<ListView
			actions={FORM_VIEWS.ACTIONS}
			columns={FORM_VIEWS.COLUMNS}
			emptyState={FORM_VIEWS.EMPTY_STATE}
			endpoint={`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-layouts`}
			formatter={FORM_VIEWS.FORMATTER}
			sortColumns={FORM_VIEWS.COLUMNS}
		/>
	);
};
