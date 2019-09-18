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

import React, {useState, useContext} from 'react';
import ClayAlert from '@clayui/alert';
import DeploymentOption from './DeploymentOption.es';
import {AppDeploymentContext} from './AppDeploymentContext.es';

export default () => {
	const {
		app: {
			name: {en_US: appName},
			settings
		},
		setApp
	} = useContext(AppDeploymentContext);

	const [state, setState] = useState({
		productMenu: settings.deploymentTypes.includes('productMenu'),
		standalone: settings.deploymentTypes.includes('standalone'),
		widget: settings.deploymentTypes.includes('widget')
	});

	const [showWidgetAlert, setShowWidgetAlert] = useState(true);

	const onSwitchToggle = type => {
		const {deploymentTypes: types} = settings;

		setApp(prevApp => ({
			...prevApp,
			settings: {
				deploymentTypes: types.includes(type)
					? types.filter(deploymentType => deploymentType !== type)
					: types.concat(type)
			}
		}));

		setState(prevState => ({
			...prevState,
			[type]: !prevState[type]
		}));
	};

	const {productMenu: isProductMenu, widget: isWidget} = state;

	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-4">
				<div className="autofit-col-expand">
					<h2>{`${Liferay.Language.get('deploy-as')}...`}</h2>
				</div>
			</div>

			<div className="autofit-row pl-4 pr-4 scrollable-container">
				<div className="autofit-col-expand">
					<DeploymentOption
						isActive={isProductMenu}
						onToggle={onSwitchToggle}
						optionDescription={Liferay.Language.get(
							'deploy-to-the-control-panel-or-a-site-menu'
						)}
						optionKey={'productMenu'}
						optionTitle={Liferay.Language.get('product-menu')}
					>
						<>
							<div className="autofit-row">
								<div className="autofit-col-expand">
									<div className="form-group">
										<label htmlFor="productMenuName">
											{Liferay.Language.get('menu-label')}
										</label>
										<input
											className="form-control"
											disabled={true}
											id="productMenuName"
											placeholder={Liferay.Language.get(
												'untitled-app'
											)}
											type="text"
											value={appName}
										/>
									</div>
								</div>
							</div>

							<div className="autofit-row">
								<div className="autofit-col-expand">
									<div className="form-group">
										<label htmlFor="selectPlacement">
											{Liferay.Language.get(
												'place-it-in-the'
											)}
										</label>
										<select
											className="form-control"
											disabled={true}
											id="selectPlacement"
											value={1}
										>
											<option value={1}>
												{Liferay.Language.get(
													'control-panel'
												)}
											</option>
											<option value={2}>
												{Liferay.Language.get(
													'site-menu'
												)}
											</option>
											<option value={3}>
												{Liferay.Language.get(
													'control-panel-and-site-menu'
												)}
											</option>
										</select>
									</div>
								</div>
								<div className="col-md-6">
									<div className="form-group">
										<label htmlFor="selectSites">
											{Liferay.Language.get('site-menu')}
										</label>
										<select
											className="form-control"
											disabled={true}
											id="selectSites"
											value={1}
										>
											<option value={1}>
												{Liferay.Language.get(
													'all-sites'
												)}
											</option>
										</select>
									</div>
								</div>
							</div>
						</>
					</DeploymentOption>

					<DeploymentOption
						isActive={isWidget}
						onToggle={onSwitchToggle}
						optionDescription={Liferay.Language.get(
							'deploy-it-as-a-display-widget'
						)}
						optionKey={'widget'}
						optionTitle={Liferay.Language.get('widget')}
					>
						{showWidgetAlert && (
							<div className="autofit-row pl-2 pr-2">
								<div className="col-md-12">
									<ClayAlert
										displayType="info"
										onClose={() =>
											setShowWidgetAlert(false)
										}
										title={Liferay.Language.get('info')}
									>
										{Liferay.Language.get(
											'the-widget-will-be-available-under-add-widgets-app-builder'
										)}
									</ClayAlert>
								</div>
							</div>
						)}
					</DeploymentOption>
				</div>
			</div>
		</>
	);
};
