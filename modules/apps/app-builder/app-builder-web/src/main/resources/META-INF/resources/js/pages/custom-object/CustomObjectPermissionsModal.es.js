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

import ClayModal, {useModal} from '@clayui/modal';
import React from 'react';

import ListView from '../../components/list-view/ListView.es';
import {useRequest} from '../../hooks/index.es';

const COLUMNS = [
	{
		key: 'name',
		sortable: true,
		value: Liferay.Language.get('role')
	}
];

const EMPTY_STATE = {
	button: () => <></>,
	description: Liferay.Language.get(
		'select-the-form-and-table-view-you-want-and-deploy-your-app-as-a-widget-standalone-or-place-it-in-the-product-menu'
	),
	title: Liferay.Language.get('there-are-no-apps-yet')
};

export default ({onClose, visible}) => {
	const {observer} = useModal({
		onClose
	});

	if (!visible) {
		return <></>;
	}

	return (
		<ClayModal observer={observer}>
			<ClayModal.Header>{Liferay.Language.get('permissions')}</ClayModal.Header>
			<ClayModal.Body>
				<ListView
					actions={[]}
					addButton={() => <></>}
					columns={COLUMNS}
					emptyState={EMPTY_STATE}
					endpoint={'/o/headless-admin-user/v1.0/roles'}
				>
					{item => item}
				</ListView>
			</ClayModal.Body>
		</ClayModal>
	);
};
