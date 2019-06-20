import ClayPagination from '@clayui/pagination';
import React, { useState } from 'react';

const spritemap = `${Liferay.ThemeDisplay.getPathThemeImages()}/lexicon/icons.svg`;

export default function Pagination(props) {
	const [page, setPage] = useState(1);

	const handleOnPageChange = (page) => {
		setPage(page);
		props.onPageChange(page);
	}

	return (
		<ClayPagination
			activePage={page}
			ellipsisBuffer={0}
			onPageChange={handleOnPageChange}
			spritemap={spritemap}
			totalPages={props.totalPages}
		/>
	);
}