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

import React, {Fragment, useState} from 'react';
import {
	ManagementToolbar,
	SearchBar
} from '../../components/management-toolbar/index.es';
import {FORM_VIEWS} from '../../components/search-container/constants.es';
import SearchContainer from '../../components/search-container/SearchContainer.es';

export default ({
	match: {
		params: {dataDefinitionId}
	}
}) => {
	const [state, setState] = useState({
		keywords: '',
		sort: '',
		totalCount: 0
	});

	const onSearch = keywords => {
		setState({...state, keywords});
	};

	const onSort = sort => {
		setState({...state, sort});
	};

	const {keywords, sort, totalCount} = state;

	return (
		<Fragment>
			<ManagementToolbar>
				<SearchBar
					columns={FORM_VIEWS.COLUMNS}
					keywords={keywords}
					onSearch={onSearch}
					onSort={onSort}
					totalCount={totalCount}
				/>
			</ManagementToolbar>

			<SearchContainer
				actions={FORM_VIEWS.ACTIONS}
				columns={FORM_VIEWS.COLUMNS}
				emptyState={FORM_VIEWS.EMPTY_STATE}
				endpoint={FORM_VIEWS.ENDPOINT(dataDefinitionId)}
				formatter={FORM_VIEWS.FORMATTER}
				keywords={keywords}
				sort={sort}
			/>
		</Fragment>
	);
};
