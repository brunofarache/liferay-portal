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

import React, {useState, useEffect} from 'react';
import {ClayRadio} from '@clayui/form';
import ClayTable from '@clayui/table';
import {getItem} from '../../utils/client.es';
import moment = require('moment');

export default ({dataDefinitionId}) => {
	const [formViews, setFormViews] = useState([]);

	useEffect(() => {
		const getFormViews = getItem(
			`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-layouts`
		);

		getFormViews.then(response => {
			setFormViews(response.items);
		});
	}, [dataDefinitionId]);

	return (
		<ClayTable bordered={false} hover>
			<ClayTable.Head>
				<ClayTable.Row>
					<ClayTable.Cell expanded headingCell>
						{'Name'}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{'Created Date'}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell>
						{'Modified Date'}
					</ClayTable.Cell>
					<ClayTable.Cell headingCell></ClayTable.Cell>
				</ClayTable.Row>
			</ClayTable.Head>

			<ClayTable.Body>
				{formViews.map((formView, index) => (
					<ClayTable.Row key={index}>
						<ClayTable.Cell>{formView.name.en_US}</ClayTable.Cell>
						<ClayTable.Cell>{moment(formView.dateCreated).fromNow()}</ClayTable.Cell>
						<ClayTable.Cell>{moment(formView.dateModified).fromNow()}</ClayTable.Cell>
						<ClayTable.Cell>
							<ClayRadio value="row 2"></ClayRadio>
						</ClayTable.Cell>
					</ClayTable.Row>
				))}
			</ClayTable.Body>
		</ClayTable>
	);
};
