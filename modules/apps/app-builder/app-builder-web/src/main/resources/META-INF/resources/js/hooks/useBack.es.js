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

import React, {useEffect} from 'react';
import ReactDOM from 'react-dom';
import {render} from 'frontend-js-react-web';
import Button from '../components/button/Button.es';

export default onClick => {
	useEffect(() => {
		const parent = document.querySelector(
			'li.control-menu-nav-category.sites-control-group > ul'
		);

		const li = document.createElement('li');
		li.classList.add('control-menu-nav-item');
		parent.appendChild(li);

		render(
			<Button
				displayType="unstyled"
				onClick={onClick}
				symbol="angle-left"
			/>,
			li
		);

		return () => {
			ReactDOM.unmountComponentAtNode(li);
		};
	}, [onClick]);
};
