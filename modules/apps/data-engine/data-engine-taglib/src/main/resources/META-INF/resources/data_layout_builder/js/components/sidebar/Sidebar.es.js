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

import ClayButton, {ClayButtonWithIcon} from '@clayui/button';
import classNames from 'classnames';
import React, {useEffect, useState} from 'react';

import Button from '../button/Button.es';

const Sidebar = React.forwardRef(
	(
		{
			children,
			className,
			closeable = true,
			closed = false,
			onToggle = () => {},
			selectedTab,
			setSelectedTab = () => {},
			tabs = [],
		},
		ref
	) => {
		const [isClosed, setClosed] = useState(closed);

		const handleToggle = () => {
			const closed = !isClosed;
			setClosed(closed);
			onToggle(closed);
		};

		useEffect(() => {
			setClosed(closed);
		}, [closed]);

		return (
			<div className={className} ref={ref}>
				<div
					className={classNames(
						'data-layout-builder-sidebar',
						'main',
						{
							closed: isClosed,
						}
					)}
				>
					<div className="sidebar sidebar-light">
						<div className="autofit-col ml-2">
							<SideBarButtonGroup
								onTabClick={setSelectedTab}
								selectedTab={selectedTab}
								tabs={tabs}
							>
								<div className="autofit-col ml-2">
									{closeable && (
										<ClayButtonWithIcon
											displayType="secondary"
											onClick={onToggle}
											small
											symbol="angle-right"
										/>
									)}
								</div>
							</SideBarButtonGroup>
						</div>
						{children}
					</div>
				</div>
				{closeable && (
					<div
						className={classNames(
							'data-layout-builder-sidebar',
							'mini',
							{
								closed: !isClosed,
							}
						)}
					>
						<Button
							displayType="secondary"
							onClick={handleToggle}
							symbol="angle-left"
						/>
					</div>
				)}
			</div>
		);
	}
);

const SidebarBody = ({children, className}) => {
	return (
		<div className={classNames(className, 'sidebar-body')}>{children}</div>
	);
};

const SidebarFooter = ({children}) => {
	return <div className="sidebar-footer">{children}</div>;
};

const SidebarHeader = ({children, className}) => {
	return (
		<div className={classNames(className, 'sidebar-header')}>
			{children}
		</div>
	);
};

const SideBarButtonGroup = ({children, onTabClick, selectedTab, tabs}) => (
	<SidebarHeader>
		<div className="autofit-row sidebar-section">
			<div className="autofit-col autofit-col-expand">
				<ClayButton.Group>
					{tabs.map(({label}, index) => (
						<ClayButton
							className={classNames({
								active: selectedTab === index,
							})}
							displayType="secondary"
							key={index}
							onClick={event => {
								event.preventDefault();
								onTabClick(index);
							}}
							small
						>
							{label}
						</ClayButton>
					))}
				</ClayButton.Group>
			</div>
			{children}
		</div>
	</SidebarHeader>
);

const SidebarTabs = ({initialSelectedTab = 0, tabs}) => {
	const [selectedTab, setSelectedTab] = useState(initialSelectedTab);

	return (
		<>
			<SidebarTab
				onTabClick={setSelectedTab}
				selectedTab={selectedTab}
				tabs={tabs}
			/>

			<SidebarTabContent>{tabs[selectedTab].render()}</SidebarTabContent>
		</>
	);
};

const SidebarTab = ({onTabClick, selectedTab, tabs}) => {
	return (
		<nav className="component-tbar tbar">
			<div className="container-fluid">
				<ul className="nav nav-underline" role="tablist">
					{tabs.map(({label}, index) => (
						<li className="nav-item" key={index}>
							<button
								className={classNames(
									'btn btn-unstyled nav-link',
									{
										active: selectedTab === index,
									}
								)}
								data-senna-off
								onClick={event => {
									event.preventDefault();
									onTabClick(index);
								}}
								role="tab"
							>
								{label}
							</button>
						</li>
					))}
				</ul>
			</div>
		</nav>
	);
};

const SidebarTabContent = ({children}) => {
	return (
		<div className="tab-content">
			<div className="active fade mt-3 show tab-pane" role="tabpanel">
				{children}
			</div>
		</div>
	);
};

Sidebar.Body = SidebarBody;
Sidebar.Footer = SidebarFooter;
Sidebar.Header = SidebarHeader;
Sidebar.Tab = SidebarTab;
Sidebar.Tabs = SidebarTabs;
Sidebar.TabContent = SidebarTabContent;

export default Sidebar;

export {
	SidebarBody,
	SidebarFooter,
	SidebarHeader,
	SidebarTab,
	SidebarTabs,
	SidebarTabContent,
};
