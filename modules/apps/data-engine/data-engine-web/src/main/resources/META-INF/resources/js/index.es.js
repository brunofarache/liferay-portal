import axios from 'axios';
import ClayDropDown, { Align } from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayPagination from '@clayui/pagination';
import ClayTable from '@clayui/table';
import React from 'react';
import ReactDOM from 'react-dom';
import moment from 'moment';

const spritemap = `${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`;

class DropDownItem extends React.Component {
	handleOnDelete = () => {
		const { id } = this.props;
		const baseURL = '/o/data-engine/v1.0';
		const endpoint = `${baseURL}/data-definitions/${id}`;

		axios.delete(
			endpoint,
			{
				params: {
					['p_auth']: Liferay.authToken
				}
			})
			.then((response) => console.log(response))
			.catch((error) => console.log(error));
	}

	render() {
		const { label } = this.props;

		return (
			<ClayDropDown.Item onClick={this.handleOnDelete}>
				{label}
			</ClayDropDown.Item>
		);
	}
}
class DropDownWithState extends React.Component {
	state = {
		active: false
	}

	render() {
		return (
			<ClayDropDown
				active={this.state.active}
				alignmentPosition={Align.RightCenter}
				onActiveChange={newVal => this.setState({active: newVal})}
				trigger={<ClayIcon spritemap={spritemap} symbol="ellipsis-v" />}
			>
				{this.props.children}
			</ClayDropDown>
		);
	}
}

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
				spritemap={spritemap}
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
						<ClayTable.Cell headingCell headingTitle>
							{''}
						</ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>
					<ClayTable.Row divider>
						<ClayTable.Cell colSpan={4}>{'Custom Objects'}</ClayTable.Cell>
					</ClayTable.Row>
					{items.map((item, i) => (
						<ClayTable.Row>
							<ClayTable.Cell headingTitle>{item.name.en_US}</ClayTable.Cell>
							<ClayTable.Cell>{moment(item.dateCreated).fromNow()}</ClayTable.Cell>
							<ClayTable.Cell>{moment(item.dateModified).fromNow()}</ClayTable.Cell>
							<ClayTable.Cell>
								<DropDownWithState>
									<ClayDropDown.ItemList>
										<DropDownItem label={'Delete'} id={item.id} />
									</ClayDropDown.ItemList>
								</DropDownWithState>
							</ClayTable.Cell>
						</ClayTable.Row>
					))}
				</ClayTable.Body>
			</ClayTable>
		);
	}
}

class SearchContainer extends React.Component {
	state = {
		items: [],
		totalPages: 1
	}

	query = (page) => {
		const { endpoint, pageSize } = this.props;

		axios.get(
			endpoint,
			{
				params: {
					['p_auth']: Liferay.authToken,
					page,
					pageSize
				}
			})
			.then((response) => [response.data.items, response.data.lastPage])
			.then(([items, totalPages]) => this.setState({items, totalPages}))
			.catch((error) => console.log(error));
	}

	componentDidMount() {
		this.query(1);
	}

	render() {
		const { items, totalPages } = this.state;

		return (
			<div>
				<Table items={items} />
				<Pagination onPageChange={this.query} totalPages={totalPages} />
			</div>
		);
	}
}

export default function(namespace) {
	const container = document.getElementById(`${namespace}root`);
	const baseURL = '/o/data-engine/v1.0';
	const endpoint = `${baseURL}/sites/${Liferay.ThemeDisplay.getScopeGroupIdOrLiveGroupId()}/data-definitions`;

	ReactDOM.render(<SearchContainer endpoint={endpoint} pageSize={3} />, container);
}