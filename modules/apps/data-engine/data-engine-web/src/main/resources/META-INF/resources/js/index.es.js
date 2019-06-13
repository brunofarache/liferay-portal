import axios from 'axios';
import ClayPagination from '@clayui/pagination';
import ClayTable from '@clayui/table';
import React from 'react';
import ReactDOM from 'react-dom';
import moment from 'moment';

class CustomObjectsTable extends React.Component {
	state = {
		customObjects: []
	}

	componentDidMount() {
		axios.defaults.baseURL = '/o/data-engine/v1.0';

		axios.get(
			`/sites/${Liferay.ThemeDisplay.getScopeGroupIdOrLiveGroupId()}/data-definitions`,
			{
				params: {
					['p_auth']: Liferay.authToken
				}
			})
			.then((response) => response.data.items)
			.then((customObjects) => this.setState({customObjects}))
			.catch((error) => console.log(error));
	}

	render() {
		const { customObjects } = this.state;

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
					{customObjects.map((customObject) => (
						<ClayTable.Row>
							<ClayTable.Cell headingTitle>{customObject.name.en_US}</ClayTable.Cell>
							<ClayTable.Cell>{moment(customObject.dateCreated).fromNow()}</ClayTable.Cell>
							<ClayTable.Cell>{moment(customObject.dateModified).fromNow()}</ClayTable.Cell>
						</ClayTable.Row>
					))}
				</ClayTable.Body>
			</ClayTable>
		);
	}
}

class Pagination extends React.Component {
	render() {
		return (
			<ClayPagination
				activePage={6}
				ellipsisBuffer={2}
				totalPages={11}
				spritemap={`${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`}
			/>
		);
	}
}

export default function(namespace) {
	const container = document.getElementById(`${namespace}root`);

	ReactDOM.render(<Pagination />, container);
}