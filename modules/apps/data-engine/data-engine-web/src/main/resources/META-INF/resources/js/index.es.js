import axios from 'axios';
import ClayPagination from '@clayui/pagination';
import ClayTable from '@clayui/table';
import React from 'react';
import ReactDOM from 'react-dom';
import moment from 'moment';

class Pagination extends React.Component {
	state = {
		page: 1
	}

	handleOnPageChange = (page) => {
		this.setState({page});
		this.props.onPageChange(page);
	}

	render() {
		const { totalPages } = this.props;
		const { page } = this.state;

		return (
			<ClayPagination
				activePage={page}
				ellipsisBuffer={0}
				onPageChange={this.handleOnPageChange}
				spritemap={`${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`}
				totalPages={totalPages}
			/>
		);
	}
}

class Table extends React.Component {
	render() {
		const { items } = this.props;

		return (
			<ClayTable>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTable.Cell expanded headingCell headingTitle>
							{'Name'}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell headingTitle>
							{'Created Date'}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell headingTitle>
							{'Modified Date'}
						</ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>
					<ClayTable.Row divider>
						<ClayTable.Cell colSpan={3}>{'Custom Objects'}</ClayTable.Cell>
					</ClayTable.Row>
					{items.map((item) => (
						<ClayTable.Row>
							<ClayTable.Cell headingTitle>{item.name.en_US}</ClayTable.Cell>
							<ClayTable.Cell>{moment(item.dateCreated).fromNow()}</ClayTable.Cell>
							<ClayTable.Cell>{moment(item.dateModified).fromNow()}</ClayTable.Cell>
						</ClayTable.Row>
					))}
				</ClayTable.Body>
			</ClayTable>
		);
	}
}

class SearchContainer extends React.Component {
	state = {
		items: []
	}

	query = (page) => {
		axios.defaults.baseURL = '/o/data-engine/v1.0';

		axios.get(
			`/sites/${Liferay.ThemeDisplay.getScopeGroupIdOrLiveGroupId()}/data-definitions`,
			{
				params: {
					['p_auth']: Liferay.authToken,
					page: page,
					pageSize: 2
				}
			})
			.then((response) => response.data.items)
			.then((items) => this.setState({items}))
			.catch((error) => console.log(error));
	}

	componentDidMount() {
		this.query(1);
	}

	render() {
		const { items } = this.state;

		return (
			<div>
				<Table items={items} />
				<Pagination onPageChange={this.query} totalPages={3} />
			</div>
		);
	}
}

export default function(namespace) {
	const container = document.getElementById(`${namespace}root`);

	ReactDOM.render(<SearchContainer />, container);
}