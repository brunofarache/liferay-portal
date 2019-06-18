import React from 'react';
import ReactDOM from 'react-dom';

import SearchContainer from './components/SearchContainer.es';

export default function(namespace) {
	const container = document.getElementById(`${namespace}root`);
	const baseURL = '/o/data-engine/v1.0';
	const endpoint = `${baseURL}/sites/${Liferay.ThemeDisplay.getScopeGroupIdOrLiveGroupId()}/data-definitions`;

	ReactDOM.render(<SearchContainer endpoint={endpoint} pageSize={3} />, container);
}