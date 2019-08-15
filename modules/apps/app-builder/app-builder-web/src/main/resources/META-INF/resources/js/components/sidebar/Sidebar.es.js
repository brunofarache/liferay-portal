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

import classNames from 'classnames';
import React from 'react';
import Body from './Body.es';
import Footer from './Footer.es';
import Header from './Header.es';

const Sidebar = ({children, isOpen}) => {
	return (
		<div className={classNames('sidebar-container', {'closed': !isOpen})}>
			<div className="sidebar sidebar-light">{children}</div>
		</div>
	);
};

Sidebar.Body = Body;
Sidebar.Footer = Footer;
Sidebar.Header = Header;

export default Sidebar;
