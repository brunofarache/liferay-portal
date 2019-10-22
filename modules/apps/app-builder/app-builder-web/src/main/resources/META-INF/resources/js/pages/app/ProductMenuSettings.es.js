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

import ClayDropDown, {Align} from '@clayui/drop-down';
import {ClayCheckbox} from '@clayui/form';
import classNames from 'classnames';
import React, {useContext, useEffect, useState} from 'react';

import {getItem} from '../../utils/client.es';
import EditAppContext, {
	PRODUCT_MENU,
	UPDATE_SETTINGS_SCOPE,
	UPDATE_SETTINGS_SITE_IDS
} from './EditAppContext.es';

const {Item, ItemList} = ClayDropDown;

const SCOPES = [
	{
		label: Liferay.Language.get('control-panel'),
		value: ['control_panel']
	},
	{
		label: Liferay.Language.get('site-menu'),
		value: ['site_administration.content']
	},
	{
		label: Liferay.Language.get('control-panel-and-site-menu'),
		value: ['control_panel', 'site_administration.content']
	}
];

export default () => {
	const {
		dispatch,
		state: {
			app: {appDeployments}
		}
	} = useContext(EditAppContext);

	const [sites, setSites] = useState([]);
	const [active, setActive] = useState(false);

	useEffect(() => {
		getItem('/o/headless-admin-user/v1.0/my-user-account/sites').then(
			({items: sites = []}) => setSites(sites)
		);
	}, []);

	const onScopeChange = event => {
		const scope = event.target.value;

		dispatch({
			scope: scope.split(','),
			type: UPDATE_SETTINGS_SCOPE
		});
	};

	const onSiteIdsChange = event => {
		const siteId = event.target.value;

		dispatch({
			siteIds: [parseInt(siteId, 10)],
			type: UPDATE_SETTINGS_SITE_IDS
		});
	};

	const {
		settings: {scope, siteIds = [0]}
	} = appDeployments.find(
		appDeployment => appDeployment.type === PRODUCT_MENU
	);

	return (
		<div className="autofit-row pl-4 pr-4">
			<div className="autofit-col-expand">
				<div className="form-group">
					<label htmlFor="scope">
						{Liferay.Language.get('place-it-in-the')}
					</label>
					<select
						className="form-control"
						id="scope"
						onChange={onScopeChange}
						value={scope}
					>
						{SCOPES.map(({label, value}, index) => (
							<option key={index} value={value}>
								{label}
							</option>
						))}
					</select>
				</div>
			</div>

			{scope.includes('site_administration.content') && (
				<div className="col-md-6">
					<div className="form-group">
						<label htmlFor="site">
							{Liferay.Language.get('site')}
						</label>

						<ClayDropDown
							active={active}
							alignmentPosition={Align.BottomLeft}
							onActiveChange={newVal => setActive(newVal)}
							trigger={
								<button
									aria-label={Liferay.Language.get(
										'select-site'
									)}
									className={classNames(
										'form-control',
										'form-control-select',
										'select-site'
									)}
									id="site"
								>
									{Liferay.Language.get('all-sites')}
								</button>
							}
						>
							<ItemList>
								{sites.map(({id, name}) => (
									<Item key={id}>
										<ClayCheckbox
											checked
											label={name}
											onChange={() => {}}
										/>
									</Item>
								))}
							</ItemList>
						</ClayDropDown>
					</div>
				</div>
			)}
		</div>
	);
};
