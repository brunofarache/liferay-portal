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
import {AppDeploymentContext} from './AppDeploymentContext.es';
import ToggleSwitch from '../../components/toggle-switch/ToggleSwitch.es';

const CONTROL_PANEL_SCOPES = [
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
			app: {
				name: {en_US: appName},
				appDeployments
			}
		},
		dispatch
	} = useContext(AppDeploymentContext);

	const productMenu = appDeployments.find(
		deployment => deployment.type === 'productMenu'
	);

	const onScopeChange = event => {
		const newScope = event.target.value;

		const newSettings = {
			...appDeployments.find(
				deployment => deployment.type === 'productMenu'
			).settings,
			scope: newScope.split(',')
		};

		dispatch({
			deploymentType: 'productMenu',
			settings: newSettings,
			type: 'UPDATE_DEPLOYMENT_SETTINGS'
		});
	};

	const scope = productMenu ? productMenu.settings.scope : [];

	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<h2>{`${Liferay.Language.get('deploy-as')}...`}</h2>
				</div>
			</div>

			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<section className="autofit-section">
						<p className="list-group-title">
							<h3>{Liferay.Language.get('product-menu')}</h3>
						</p>
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
				<>
					<div className="autofit-row pl-4 pr-4">
						<div className="autofit-col-expand">
							<div className="form-group">
								<label htmlFor="menuLabel">
									{Liferay.Language.get('menu-label')}
								</label>
								<input
									className="form-control"
									disabled={true}
									id="menuLabel"
									placeholder={Liferay.Language.get(
										'untitled-app'
									)}
									type="text"
									value={appName}
								/>
							</div>
						</div>
					</div>

					<div className="autofit-row pl-4 pr-4">
						<div className="autofit-col-expand">
							<div className="form-group">
								<label htmlFor="selectScope">
									{Liferay.Language.get('place-it-in-the')}
								</label>
								<select
									className="form-control"
									id="selectScope"
									onChange={onScopeChange}
									value={scope}
								>
									{CONTROL_PANEL_SCOPES.map(
										(scope, index) => {
											return (
												<option
													key={index}
													value={scope.value}
												>
													{scope.label}
												</option>
											);
										}
									)}
								</select>
							</div>
						</div>

						{scope.includes('site_administration.content') && (
							<div className="col-md-6">
								<div className="form-group">
									<label htmlFor="selectSite">
										{Liferay.Language.get('site')}
									</label>
									<select
										className="form-control"
										disabled={true}
										id="selectSite"
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
				</>
			)}

			<div className="autofit-row pl-2 pr-2">
				<div className="col-md-12">
					<h4 className="card-divider"></h4>
				</div>
			</div>
		</>
	);
};
