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
		case 'UPDATE_LIST_VIEW_ID': {
			return {
				...state,
				app: {
					...state.app,
					dataListViewId: action.itemId
				}
			};
		}
		case 'UPDATE_DATA_LAYOUT_ID': {
			return {
				...state,
				app: {
					...state.app,
					dataLayoutId: action.itemId
				}
			};
		}
		case 'UPDATE_NAME': {
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
		case 'UPDATE_SETTINGS': {
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

const AppDeploymentContext = createContext();

const AppDeploymentProvider = ({children}) => {
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

	return (
		<AppDeploymentContext.Provider value={{dispatch, state}}>
			{children}
		</AppDeploymentContext.Provider>
	);
};

export {AppDeploymentProvider, AppDeploymentContext};
