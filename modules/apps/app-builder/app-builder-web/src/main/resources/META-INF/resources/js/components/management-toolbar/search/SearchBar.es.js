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

import ClayManagementToolbar from '@clayui/management-toolbar';
import React, {useContext, useState} from 'react';

import SearchBarActions from './SearchBarActions.es';
import SearchContext from './SearchContext.es';
import SearchInput from './SearchInput.es';
import SearchSort from './SearchSort.es';

export default ({addButton, columns, totalCount}) => {
	const [{keywords}, dispatch] = useContext(SearchContext);
	const [showMobile, setShowMobile] = useState(false);
	const disabled = keywords === '' && totalCount === 0;

	return (
		<ClayManagementToolbar>
			<SearchSort columns={columns} disabled={disabled} />
			<SearchInput
				disabled={disabled}
				onSubmit={searchText =>
					dispatch({keywords: searchText, type: 'SEARCH'})
				}
				searchText={keywords}
				setShowMobile={setShowMobile}
				showMobile={showMobile}
			/>
			<SearchBarActions
				addButton={addButton}
				setShowMobile={setShowMobile}
			/>
		</ClayManagementToolbar>
	);
};
