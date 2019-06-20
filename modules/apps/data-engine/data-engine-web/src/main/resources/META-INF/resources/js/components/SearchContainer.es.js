import axios from 'axios';
import React, { useEffect, useState } from 'react';
import Pagination from './Pagination.es';
import SearchInput from './SearchInput.es';
import Table from './Table.es';

export default function SearchContainer(props) {
	useEffect(
		() => {
			query(1);
		},
		[]
	);

	const [state, setState] = useState({
		currentPage: 1,
		items: [],
		totalPages: 1
	});

	const onDeleteItem = (id) => {
		const baseURL = '/o/data-engine/v1.0';
		const endpoint = `${baseURL}/data-definitions/${id}`;

		axios.delete(
			endpoint,
			{
				params: {
					['p_auth']: Liferay.authToken
				}
			})
			.then(() => query(state.currentPage))
			.catch((error) => console.log(error));
    }
    
	const query = (page, keywords = '') => {
        const { endpoint, pageSize } = props;

		axios.get(
			endpoint,
			{
				params: {
					['p_auth']: Liferay.authToken,
                    page,
                    keywords,
					pageSize
				}
			})
			.then((response) => [response.data.items, response.data.lastPage])
			.then(([items, totalPages]) => setState({currentPage: page, items, totalPages}))
			.catch((error) => console.log(error));
	}

	return (
		<div>
			<SearchInput onSearch={query} />
			<br />
			<Table items={state.items} onDeleteItem={onDeleteItem} />
			<Pagination onPageChange={query} totalPages={state.totalPages} />
		</div>
	);
}