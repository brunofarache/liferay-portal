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

import React, {useContext, useEffect} from 'react';
import {Route, Switch} from 'react-router-dom';
import {AppContext, AppProvider} from './AppContext.es';
import DeployApp from './DeployApp.es';
import SelectFormView from './SelectFormView.es';
import SelectTableView from './SelectTableView.es';
import ControlMenu from '../../components/control-menu/ControlMenu.es';
import {getItem} from '../../utils/client.es';

export default ({
	match: {
		params: {appId},
		path
	}
}) => {
	const {setApp} = useContext(AppContext);

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

	return (
		<>
			<ControlMenu backURL="../" title={title} />

			<AppProvider>
				<Switch>
					<Route
						component={SelectFormView}
						path={[`${path}/form-view`]}
					></Route>
					<Route
						component={SelectTableView}
						path={[`${path}/table-view`]}
					></Route>
					<Route
						component={DeployApp}
						path={[`${path}/deployment`]}
					></Route>
				</Switch>
			</AppProvider>
		</>
	);
};
