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

// workaround for: https://github.com/facebook/react/pull/14853
export const disableActWarnings = () => {
	// eslint-disable-next-line no-console
	const originalError = console.error;
	// eslint-disable-next-line no-console
	const originalWarn = console.warn;

	// eslint-disable-next-line no-console
	console.error = (...args) => {
		if (
			/Warning.*not wrapped in act/.test(args[0]) ||
			/DataProvider: Error making/.test(args[0])
		) {
			return;
		}

		originalError.call(console, ...args);
	};

	// eslint-disable-next-line no-console
	console.warn = (...args) => {
		if (/DataProvider: Trying/.test(args[0])) {
			return;
		}

		originalWarn.call(console, ...args);
	};

	return [originalError, originalWarn];
};

export const restoreConsole = (originalError, originalWarn) => {
	// eslint-disable-next-line no-console
	console.error = originalError;
	// eslint-disable-next-line no-console
	console.warn = originalWarn;
};
