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

import React, {useContext} from 'react';
import {EditAppContext} from './EditAppContext.es';
import ToggleSwitch from '../../components/toggle-switch/ToggleSwitch.es';

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
		state: {
			app: {appDeployments}
		},
		dispatch
	} = useContext(EditAppContext);

	const productMenu = appDeployments.find(
		appDeployment => appDeployment.type === 'productMenu'
	);

	const onScopeChange = event => {
		const scope = event.target.value;

		const newDeployment = {
			...productMenu,
			settings: {
				...productMenu.settings,
				scope: scope.split(',')
			}
		};

		dispatch({
			appDeployment: newDeployment,
			type: 'UPDATE_DEPLOYMENT'
		});
	};

	const scope = productMenu ? productMenu.settings.scope : [];

	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-3">
				<div className="autofit-col-expand">
					<section className="autofit-section">
						<h3>{Liferay.Language.get('product-menu')}</h3>
						<p className="list-group-subtext">
							<small>
								{Liferay.Language.get(
									'deploy-to-the-control-panel-or-a-site-menu'
								)}
							</small>
						</p>
					</section>
				</div>

				<div className="autofit-col right">
					<ToggleSwitch
						checked={!!productMenu}
						onChange={checked => {
							if (checked) {
								dispatch({
									deploymentType: 'productMenu',
									type: 'ADD_DEPLOYMENT'
								});
							} else {
								dispatch({
									deploymentType: 'productMenu',
									type: 'REMOVE_DEPLOYMENT'
								});
							}
						}}
					/>
				</div>
			</div>

			{productMenu && (
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
								<select
									className="form-control"
									disabled={true}
									id="site"
									value={1}
								>
									<option value={1}>
										{Liferay.Language.get('all-sites')}
									</option>
								</select>
							</div>
						</div>
					)}
				</div>
			)}
		</>
	);
};
