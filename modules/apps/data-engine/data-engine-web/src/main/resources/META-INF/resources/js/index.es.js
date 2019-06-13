import React from 'react';
import ReactDOM from 'react-dom';
import ClayTable from '@clayui/table';

class AppComponent extends React.Component {
	render() {
		return (
			<ClayTable>
				<ClayTable.Head>
					<ClayTable.Row>
						<ClayTable.Cell expanded headingCell headingTitle>
						{'Teams'}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell headingTitle>
						{'Region'}
						</ClayTable.Cell>
						<ClayTable.Cell headingCell headingTitle>
						{'Country'}
						</ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Head>
				<ClayTable.Body>
					<ClayTable.Row>
						<ClayTable.Cell headingTitle>{'White and Red'}</ClayTable.Cell>
						<ClayTable.Cell>{'South America'}</ClayTable.Cell>
						<ClayTable.Cell>{'Brazil'}</ClayTable.Cell>
					</ClayTable.Row>
					<ClayTable.Row>
						<ClayTable.Cell headingTitle>{'White and Purple'}</ClayTable.Cell>
						<ClayTable.Cell>{'Europe'}</ClayTable.Cell>
						<ClayTable.Cell>{'Spain'}</ClayTable.Cell>
					</ClayTable.Row>
				</ClayTable.Body>
			</ClayTable>
		);
	}
}

export default function(namespace, portletId) {
	const container = document.getElementById(`${namespace}root`);

	ReactDOM.render(<AppComponent />, container);
}