import ClayIcon from '@clayui/icon';
import React, { Component } from 'react';

const spritemap = `${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`;

export default class SearchInput extends Component {
	state = {
		value: ''
	}

	onChange = (event) => {
		const { onSearch } = this.props;
		this.setState({value: event.target.value}, () => onSearch(1, this.state.value));
	}

	render() {
		return (
			<div class="input-group">
				<div class="input-group-item">
					<input value={this.state.value} onChange={this.onChange} aria-label="Search for" class="form-control input-group-inset input-group-inset-after" placeholder="Search..." type="text"/>
					<div class="input-group-inset-item input-group-inset-item-after">
						<button class="btn btn-unstyled d-none d-md-inline-block" type="button">
							<ClayIcon spritemap={spritemap} symbol="search" />
						</button>
					</div>
				</div>
			</div>
		);
	}
}