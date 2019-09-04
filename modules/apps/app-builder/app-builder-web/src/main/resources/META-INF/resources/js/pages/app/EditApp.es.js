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

import React, {useEffect, useContext} from 'react';
import {Route, Switch} from 'react-router-dom';
import ControlMenu from '../../components/control-menu/ControlMenu.es';
import {getItem, addItem, updateItem} from '../../utils/client.es';
import {UpperToolbarInput} from '../../components/upper-toolbar/UpperToolbar.es';
import FormViewSelection from './FormViewSelection.es';
import TestMultiStep from './TestMultiStep.es';
import {AppContext} from './AppContext.es';

export default ({
	history,
	match: {
		params: {dataDefinitionId, appId},
		path
	}
}) => {
	const {app, setApp} = useContext(AppContext);

	let title = Liferay.Language.get('new-app');

	if (appId) {
		title = Liferay.Language.get('edit-app');
	}

	useEffect(() => {
		if (appId) {
			const getApp = getItem(`/apps/${appId}`);
			getApp.then(app => {
				setApp(app);
			});
		}
	}, [appId, setApp]);

	const handleSubmit = () => {
		if (app.name.en_US === '') {
			return;
		}

		if (appId) {
			updateItem(`/o/app-builder/v1.0/apps/${appId}`, app).then(() =>
				history.goBack()
			);
		} else {
			addItem(
				`/o/app-builder/v1.0/data-definitions/${dataDefinitionId}/apps`,
				app
			).then(() => history.goBack());
		}
	};

	const handleAppNameChange = event => {
		const name = event.target.value;

		setApp({
			...app,
			name: {
				en_US: name
			}
		});
	};

	return (
		<>
			<ControlMenu backURL="../" title={title} />

			<div className="container-fluid container-fluid-max-lg mt-4">
				<div className="card card-root">
					<div className="card-header align-items-center d-flex justify-content-between bg-transparent">
						<UpperToolbarInput
							onInput={handleAppNameChange}
							placeholder={Liferay.Language.get('untitled-app')}
							value={app.name.en_US}
						/>
					</div>

					<h4 className="card-divider mb-4"></h4>

					<TestMultiStep />

					<Switch>
						<Route
							component={FormViewSelection}
							path={[`${path}/first-step`]}
						/>
					</Switch>

					<h4 className="card-divider"></h4>

					<div className="card-footer bg-transparent">
						<div className="autofit-row">
							<div className="col-md-4">
								<button
									className="btn btn-secondary"
									onClick={() => history.goBack()}
								>
									{Liferay.Language.get('cancel')}
								</button>
							</div>
							<div className="col-md-4 offset-md-4 text-right">
								<button
									className="btn btn-primary"
									onClick={handleSubmit}
								>
									{Liferay.Language.get('next')}
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</>
	);
};
