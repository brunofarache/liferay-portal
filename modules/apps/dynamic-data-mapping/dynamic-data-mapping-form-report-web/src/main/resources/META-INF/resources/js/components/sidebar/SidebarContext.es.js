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

import React, {createContext, useState} from 'react';

const SidebarContext = createContext({});

const SidebarContextProvider = ({children}) => {
	const [sidebarState, setSidebarState] = useState({
		field: null,
		isOpen: false,
		totalEntries: 0,
	});

	const toggleSidebar = (field, totalEntries) => {
		setSidebarState(() => ({
			field,
			isOpen: field !== null,
			totalEntries,
		}));
	};

	const {field, isOpen, totalEntries} = sidebarState;

	return (
		<SidebarContext.Provider
			value={{field, isOpen, toggleSidebar, totalEntries}}
		>
			{children}
		</SidebarContext.Provider>
	);
};

export {SidebarContext, SidebarContextProvider};
