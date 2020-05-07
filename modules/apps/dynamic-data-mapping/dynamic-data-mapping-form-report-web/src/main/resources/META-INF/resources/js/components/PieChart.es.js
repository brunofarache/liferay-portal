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

import React, {useState} from 'react';
import {
	Cell,
	Pie,
	PieChart,
	ResponsiveContainer,
	Sector,
	Tooltip,
} from 'recharts';

import colors from '../../utils/colors.es';
import Legend from './Legend.es';
import TooltipContent from './TooltipContent.es';

const RADIAN = Math.PI / 180;

export default ({data, totalEntries}) => {
	const [activeIndex, setActiveIndex] = useState(null);
	const [animationActive, setAnimationActive] = useState(true);
	const radius = 130;
	const innerRadius = 80;

	const callbackMouseOutOfLegend = () => {
		setActiveIndex(null);
	};

	const callBackMouseOverLegend = (dataFromChild) => {
		setActiveIndex(parseInt(dataFromChild, 10));
	};

	const onPieEnter = (data, index) => {
		setActiveIndex(index);
	};

	const onSectorOut = () => {
		setActiveIndex(null);
	};

	const renderActiveShape = (props) => {
		const {cx, cy, endAngle, innerRadius, outerRadius, startAngle} = props;

		setAnimationActive(false);

		return (
			<g>
				<Sector
					cx={cx}
					cy={cy}
					endAngle={endAngle}
					fill={colors(activeIndex)}
					innerRadius={innerRadius}
					onMouseOut={onSectorOut}
					outerRadius={outerRadius + 5}
					startAngle={startAngle}
				/>
			</g>
		);
	};

	const renderCustomizedLabel = ({
		cx,
		cy,
		innerRadius,
		midAngle,
		outerRadius,
		percent,
	}) => {
		const radius = innerRadius + (outerRadius - innerRadius) * 0.5;
		const x = cx + radius * Math.cos(-midAngle * RADIAN);
		const y = cy + radius * Math.sin(-midAngle * RADIAN);

		return (
			<text
				dominantBaseline="central"
				fill="white"
				textAnchor="middle"
				x={x}
				y={y}
			>
				{`${(percent * 100).toFixed(0)}%`}
			</text>
		);
	};

	return (
		<>
			<ResponsiveContainer height="100%" width="50%">
				<PieChart>
					<Pie
						activeIndex={activeIndex}
						activeShape={renderActiveShape}
						cx="50%"
						cy="50%"
						data={data}
						dataKey="count"
						innerRadius={innerRadius}
						isAnimationActive={animationActive}
						label={renderCustomizedLabel}
						labelLine={false}
						nameKey="label"
						onMouseEnter={onPieEnter}
						outerRadius={radius}
						paddingAngle={0}
					>
						{data.map((_, index) => (
							<Cell fill={colors(index)} key={index} />
						))}
					</Pie>

					<Tooltip
						content={
							<TooltipContent
								activeIndex={activeIndex}
								totalEntries={totalEntries}
							/>
						}
					/>
				</PieChart>
			</ResponsiveContainer>

			<Legend
				activeIndex={activeIndex}
				callbackMouseOutOfLegend={callbackMouseOutOfLegend}
				callbackMouseOverLegend={callBackMouseOverLegend}
				items={data}
			/>
		</>
	);
};
