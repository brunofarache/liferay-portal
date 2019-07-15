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

var fs = require('fs').promises;

const portalLanguage = {
	'showing-x-to-x-of-x-entries': 'Showing {0} to {1} of {2} entries.'
};

export const mockLanguage = async () => {
	const data = await fs.readFile(
		'./src/main/resources/content/Language.properties'
	);

	const file = data.toString();
	const portletLanguage = {};

	file.split(/\r?\n/).forEach(line => {
		const [key, value] = line.split('=');
		portletLanguage[key] = value;
	});

	const language = {
		...portalLanguage,
		...portletLanguage
	};

	window.Liferay.Language = {
		get: key => (language[key] ? language[key] : key)
	};

	return language;
};
