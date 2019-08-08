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

import ClayButton from '@clayui/button';
import React, {useState} from 'react';
import {addItem} from '../../utils/client.es';

export default ({
	history,
	match: {
		params: {dataDefinitionId}
	}
}) => {
	const [name, setName] = useState('');

	const addTableView = () => {
		addItem(
			`/o/data-engine/v1.0/data-definitions/${dataDefinitionId}/data-list-views`,
			{
				name: {
					value: name
				}
			}
		).then(() => history.goBack());
	};

	const onChange = event => {
		setName(event.target.value);
	};

	return (
		<nav className="component-tbar subnav-tbar-light tbar tbar-article">
			<div className="container-fluid container-fluid-max-xl">
				<ul className="tbar-nav">
					<li className="tbar-item tbar-item-expand">
						<div className="input-group">
							<div className="input-group-item">
								<input
									aria-label={Liferay.Language.get(
										'untitled-table-view'
									)}
									className="form-control form-control-inline"
									onChange={onChange}
									placeholder={Liferay.Language.get(
										'untitled-table-view'
									)}
									type="text"
									value={name}
								/>
							</div>
						</div>
					</li>
					<li className="tbar-item">
						<div className="tbar-section">
							<ClayButton
								className="mr-3"
								displayType="secondary"
								onClick={() => history.goBack()}
								small
							>
								{Liferay.Language.get('cancel')}
							</ClayButton>
							<ClayButton
								className="mr-3"
								onClick={addTableView}
								small
							>
								{Liferay.Language.get('save')}
							</ClayButton>
						</div>
					</li>
				</ul>
			</div>
		</nav>
	);
};
