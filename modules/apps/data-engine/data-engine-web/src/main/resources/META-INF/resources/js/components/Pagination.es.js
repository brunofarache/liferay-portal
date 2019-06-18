import ClayPagination from '@clayui/pagination';
import React, { Component } from 'react';

const spritemap = `${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`;

export default class Pagination extends Component {
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