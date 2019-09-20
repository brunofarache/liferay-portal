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

import React, {useContext, useState} from 'react';
import ClayAlert from '@clayui/alert';
import {EditAppContext} from './EditAppContext.es';
import ToggleSwitch from '../../components/toggle-switch/ToggleSwitch.es';

export default () => {
	const [isAlertClosed, setAlertClosed] = useState(false);

	const {
		state: {
			app: {appDeployments}
		},
		dispatch
	} = useContext(EditAppContext);

	const isWidget = appDeployments.some(
		appDeployment => appDeployment.type === 'widget'
	);

	return (
		<>
			<div className="autofit-row pl-4 pr-4 mb-3">
				<div className="autofit-col-expand">
					<section className="autofit-section">
						<h3>{Liferay.Language.get('widget')}</h3>
						<p className="list-group-subtext">
							<small>
								{Liferay.Language.get(
									'deploy-a-display-widget'
								)}
							</small>
						</p>
					</section>
				</div>

				<div className="autofit-col right">
					<ToggleSwitch
						checked={isWidget}
						onChange={checked => {
							if (checked) {
								setAlertClosed(false);
								dispatch({
									deploymentType: 'widget',
									type: 'ADD_DEPLOYMENT'
								});
							} else {
								dispatch({
									deploymentType: 'widget',
									type: 'REMOVE_DEPLOYMENT'
								});
							}
						}}
					/>
				</div>
			</div>

			{isWidget && !isAlertClosed && (
				<div className="autofit-row pl-4 pr-4">
					<div className="autofit-col-expand">
						<ClayAlert
							displayType="info"
							onClose={() => setAlertClosed(!isAlertClosed)}
							title={`${Liferay.Language.get('info')}:`}
						>
							{`${Liferay.Language.get(
								'the-widget-will-be-available-under'
							)} "${Liferay.Language.get(
								'add-widgets-app-builder'
							)}"`}
						</ClayAlert>
					</div>
				</div>
			)}
		</>
	);
};
