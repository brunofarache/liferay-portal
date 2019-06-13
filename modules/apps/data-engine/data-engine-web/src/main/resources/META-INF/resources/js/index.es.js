import axios from 'axios';
import React from 'react';
import ReactDOM from 'react-dom';
import ClayTable from '@clayui/table';

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
			.catch((error) => {
				console.log(error);
			});
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
							<ClayTable.Cell>{customObject.dateCreated}</ClayTable.Cell>
							<ClayTable.Cell>{customObject.dateModified}</ClayTable.Cell>
						</ClayTable.Row>
					))}
				</ClayTable.Body>
			</ClayTable>
		);
	}
}

export default function(namespace) {
	const container = document.getElementById(`${namespace}root`);

	ReactDOM.render(<CustomObjectsTable />, container);
}