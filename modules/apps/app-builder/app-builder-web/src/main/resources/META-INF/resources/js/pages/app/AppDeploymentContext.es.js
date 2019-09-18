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

import React, {createContext, useReducer} from 'react';

function reducer(state, action) {
	switch (action.type) {
		case 'CHANGE_LIST_VIEW': {
			return {
				...state,
				app: {
					...state.app,
					dataListViewId: action.itemId
				}
			};
		}
		case 'CHANGE_DATA_LAYOUT': {
			return {
				...state,
				app: {
					...state.app,
					dataLayoutId: action.itemId
				}
			};
		}
		case 'CHANGE_APP_NAME': {
			return {
				...state,
				app: {
					...state.app,
					name: {
						en_US: action.appName
					}
				}
			};
		}
		case 'CHANGE_APP_SETTINGS': {
			return {
				...state,
				app: {
					...state.app,
					settings: action.settings
				}
			};
		}
		default: {
			return state;
		}
	}
}

const useApp = () => {
	const [state, dispatch] = useReducer(reducer, {
		app: {
			dataLayoutId: null,
			dataListViewId: null,
			name: {
				en_US: ''
			},
			settings: {
				deploymentTypes: []
			},
			status: 'undeployed'
		}
	});

	return {dispatch, state};
};

const AppDeploymentContext = createContext();

const AppDeploymentProvider = ({children}) => {
	return (
		<AppDeploymentContext.Provider value={useApp()}>
			{children}
		</AppDeploymentContext.Provider>
	);
};

export {AppDeploymentProvider, AppDeploymentContext};
