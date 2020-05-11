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

import {usePrevious} from 'frontend-js-react-web';
import {useEffect, useState} from 'react';

import {request} from '../utils/client.es';
import {isEqualObjects} from '../utils/utils.es';

export default ({endpoint, method, params}) => {
	const initialState = {
		error: null,
		isLoading: true,
		response: {},
	};
	const [state, setState] = useState(initialState);

	const doFetch = (options) => {
		setState(initialState);

		request(options)
			.then((response) => {
				setState({
					error: null,
					isLoading: false,
					response,
				});
			})
			.catch((error) => {
				setState({
					error,
					isLoading: false,
					response: {},
				});
			});
	};

	const refetch = () => doFetch({endpoint, method, params});

	const previousParams = usePrevious(params);

	useEffect(() => {
		if (!isEqualObjects(params, previousParams)) {
			refetch();
		}
		// eslint-disable-next-line react-hooks/exhaustive-deps
	}, [params]);

	return {refetch, ...state};
};
