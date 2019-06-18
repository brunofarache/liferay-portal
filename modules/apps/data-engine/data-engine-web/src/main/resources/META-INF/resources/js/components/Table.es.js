import ClayDropDown, { Align } from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayTable from '@clayui/table';
import moment from 'moment';
import React, { Component } from 'react';

const spritemap = `${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`;

class DropDownWithState extends Component {
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

export default class Table extends Component {
	render() {
		const { items, onDeleteItem } = this.props;

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
										<ClayDropDown.Item onClick={() => onDeleteItem(item.id)}>
											{'Delete'}
										</ClayDropDown.Item>
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