import ClayIcon from '@clayui/icon';
import React, { useState } from 'react';

const spritemap = `${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`;

export default function SearchInput(props) {
	const [keywords, setKeywords] = useState('');

	const onChange = (event) => {
		const { value } = event.target;
		setKeywords(value);
		props.onSearch(1, value);
	}

	return (
		<div class="input-group">
			<div class="input-group-item">
				<input
					value={keywords}
					onChange={onChange} 
					aria-label="Search for"
					class="form-control input-group-inset input-group-inset-after"
					placeholder="Search..."
					type="text"/>

				<div class="input-group-inset-item input-group-inset-item-after">
					<button class="btn btn-unstyled d-none d-md-inline-block" type="button">
						<ClayIcon spritemap={spritemap} symbol="search" />
					</button>
				</div>
			</div>
		</div>
	);
}