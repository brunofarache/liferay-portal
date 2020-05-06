/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayButton from '@clayui/button';
import ClayIcon from '@clayui/icon';
import React, {useState} from 'react';

import COLOR_MAP from '../../utils/colors';

import '../../css/styles.scss';

const CustomLegend = (props) => {
	const {
		activeIndex,
		callbackMouseOutOfLegend,
		callbackMouseOverLegend,
		items,
	} = props;
	const [showAll, setShowAll] = useState(false);
	const [showLabel, setShowLabel] = useState(
		Liferay.Language.get('show-all')
	);
	const [showLimit, setShowLimit] = useState(10);

	const showMoreItems = () => {
		setShowAll(!showAll);

		if (!showAll && items.length >= showLimit) {
			setShowLabel(Liferay.Language.get('show-less'));
			setShowLimit(items.length);
		}
		else if (items.length >= showLimit) {
			setShowLimit(10);
			setShowLabel(Liferay.Language.get('show-all'));
		}
	};

	return (
		<div className="legend-container well well-lg">
			<div className="legend-list">
				<ul style={{listStyleType: 'none'}}>
					{items.slice(0, showLimit).map((entry, index) => (
						<li
							className={
								activeIndex != null && activeIndex != index
									? 'dim'
									: ''
							}
							data-item={index}
							key={`item-${index}`}
						>
							<svg height="20" width="20">
								<circle
									cx="10"
									cy="10"
									fill={COLOR_MAP[index]}
									onMouseOut={() =>
										callbackMouseOutOfLegend()
									}
									onMouseOver={(e) =>
										callbackMouseOverLegend(
											e.currentTarget.closest('li')
												.dataset.item
										)
									}
									r="9"
									strokeWidth="3"
								/>
							</svg>
							<text
								onMouseOut={() => callbackMouseOutOfLegend()}
								onMouseOver={(e) =>
									callbackMouseOverLegend(
										e.currentTarget.closest('li').dataset
											.item
									)
								}
								x="0em"
								y="1em"
							>
								<tspan dy="2em" x="2.5em">
									{entry.name}
								</tspan>
							</text>
						</li>
					))}
					<div
						className={
							items.length < showLimit
								? 'hide'
								: 'show-more-action'
						}
					>
						<svg height="20" width="20">
							<g>
								<circle
									cx="10"
									cy="10"
									fill={'#F0F5FF'}
									r="9"
									strokeWidth="3"
								></circle>
								<ClayIcon
									symbol={
										!showAll
											? 'caret-bottom-l'
											: 'caret-top-l'
									}
								/>
							</g>
						</svg>
						<ClayButton
							className="show-more-button"
							displayType="unstyled"
							onClick={showMoreItems}
						>
							{showLabel}
						</ClayButton>
					</div>
				</ul>
			</div>
		</div>
	);
};

export default CustomLegend;
