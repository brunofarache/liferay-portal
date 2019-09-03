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

import {render} from 'frontend-js-react-web';
import React from 'react';
import {DragDropContext as dragDropContext} from 'react-dnd';
import HTML5Backend from 'react-dnd-html5-backend';
import {AppContextProvider} from '../../AppContext.es';
import EditFormView from './EditFormView.es';

function renderComponent({basePortletURL, ...props}) {
	return (
		<AppContextProvider basePortletURL={basePortletURL}>
			<EditFormView {...props} />
		</AppContextProvider>
	);
}

export default function(containerId, data) {
	render(
		dragDropContext(HTML5Backend)(renderComponent),
		data,
		document.getElementById(containerId)
	);
}
