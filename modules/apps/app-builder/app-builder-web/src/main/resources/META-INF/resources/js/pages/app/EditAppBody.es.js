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

import React, {useState} from 'react';
import {useResource} from '@clayui/data-provider';
import ListItems from './ListItems.es';
import Button from '../../components/button/Button.es';
import {getURL} from '../../utils/client.es';

export default ({emptyState, endpoint, title, ...restProps}) => {
	const [isLoading, setLoading] = useState(true);

	const {resource} = useResource({
		fetchDelay: 0,
		fetchOptions: {
			credentials: 'same-origin',
			method: 'GET'
		},
		link: getURL(endpoint),
		onNetworkStatusChange: status => {
			setLoading(status < 4);
		}
	});

	let items = [];

	if (resource) {
		({items = []} = resource);
	}

	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<h2>{title}</h2>
				</div>
			</div>

			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<div className="input-group">
						<div className="input-group-item">
							<input
								aria-label={Liferay.Language.get('search')}
								className="form-control input-group-inset input-group-inset-after"
								placeholder={`${Liferay.Language.get(
									'search'
								)}...`}
								type="text"
							/>
							<div className="input-group-inset-item input-group-inset-item-after">
								<Button
									disabled={true}
									displayType="unstyled"
									symbol="search"
								/>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div className="autofit-row pl-4 pr-4 scrollable-container">
				<div className="autofit-col-expand">
					<ListItems
						emptyState={emptyState}
						isEmpty={items.length === 0}
						isLoading={isLoading}
						items={items}
						{...restProps}
					/>
				</div>
			</div>
		</>
	);
};
