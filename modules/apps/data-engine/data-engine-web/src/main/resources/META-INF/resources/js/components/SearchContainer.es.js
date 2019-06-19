import axios from 'axios';
import React, { Component } from 'react';
import Pagination from './Pagination.es';
import SearchInput from './SearchInput.es';
import Table from './Table.es';

export default class SearchContainer extends Component {
	state = {
        currentPage: 1,
        keywords: '',
		items: [],
		totalPages: 1
	}

	onDeleteItem = (id) => {
		const baseURL = '/o/data-engine/v1.0';
		const endpoint = `${baseURL}/data-definitions/${id}`;

		axios.delete(
			endpoint,
			{
				params: {
					['p_auth']: Liferay.authToken
				}
			})
			.then(() => this.query(this.state.currentPage))
			.catch((error) => console.log(error));
    }
    
	query = (page, keywords = '') => {
        const { endpoint, pageSize } = this.props;

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
			.then(([items, totalPages]) => this.setState({currentPage: page, items, totalPages}))
			.catch((error) => console.log(error));
	}

	componentDidMount() {
		this.query(1);
	}

	render() {
		const { items, totalPages } = this.state;

		return (
			<div>
                <SearchInput onSearch={this.query} />
                <br />
				<Table items={items} onDeleteItem={this.onDeleteItem} />
				<Pagination onPageChange={this.query} totalPages={totalPages} />
			</div>
		);
	}
}