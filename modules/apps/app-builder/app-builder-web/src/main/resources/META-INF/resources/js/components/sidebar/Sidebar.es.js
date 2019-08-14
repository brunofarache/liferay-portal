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

import ClayIcon from '@clayui/icon';
import React from 'react';
import Button from '../button/Button.es';

export default () => {
	return (
		<div className="sidebar-container">
			<div className="sidebar sidebar-light">
				<div className="sidebar-header">
					<div className="autofit-row sidebar-section">
						<div className="autofit-col autofit-col-expand">
							<div className="input-group">
								<div className="input-group-item">
									<input
										aria-label={Liferay.Language.get(
											'search'
										)}
										className="form-control input-group-inset input-group-inset-after"
										placeholder={Liferay.Language.get(
											'search'
										)}
										type="text"
									/>

									<div className="input-group-inset-item input-group-inset-item-after">
										<button
											className="btn btn-unstyled"
											type="button"
										>
											<ClayIcon symbol="search" />
										</button>
									</div>
								</div>
								<div className="input-group-item input-group-item-shrink">
									<Button
										displayType="secondary"
										symbol="angle-right"
									/>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div className="sidebar-body">
					<nav className="component-navigation-bar navbar navigation-bar navbar-collapse-absolute navbar-expand-md navbar-underline">
						<a
							aria-controls="sidebarLightCollapse00"
							aria-expanded="false"
							aria-label="Toggle Navigation"
							className="collapsed navbar-toggler navbar-toggler-link"
							data-toggle="collapse"
							href="#sidebarLightCollapse00"
							role="button"
						>
							<span className="navbar-text-truncate">
								Details
							</span>
							<svg
								className="lexicon-icon lexicon-icon-caret-bottom"
								focusable="false"
								role="presentation"
							>
								<use href="/images/icons/icons.svg#caret-bottom"></use>
							</svg>
						</a>
						<div
							className="collapse navbar-collapse"
							id="sidebarLightCollapse00"
						>
							<ul className="nav navbar-nav" role="tablist">
								<li className="nav-item">
									<a
										aria-controls="sidebarLightDetails"
										aria-selected="true"
										className="nav-link active"
										data-toggle="tab"
										href="#sidebarLightDetails"
										id="sidebarLightDetailsTab"
										role="tab"
									>
										<span className="navbar-text-truncate">
											Details
										</span>
									</a>
								</li>
								<li className="nav-item">
									<a
										aria-controls="sidebarLightVersions"
										aria-selected="false"
										className="nav-link"
										data-toggle="tab"
										href="#sidebarLightVersions"
										id="sidebarLightVersionsTab"
										role="tab"
									>
										<span className="navbar-text-truncate">
											Versions
										</span>
									</a>
								</li>
							</ul>
						</div>
					</nav>
					<div className="tab-content">
						<div
							aria-labelledby="sidebarLightDetailsTab"
							className="fade tab-pane active show"
							id="sidebarLightDetails"
							role="tabpanel"
						>
							<div
								className="aspect-ratio aspect-ratio-16-to-9 sidebar-panel"
								style={{marginTop: '1.5rem'}}
							>
								<img
									alt="thumbnail"
									className="aspect-ratio-item aspect-ratio-item-center-middle aspect-ratio-item-fluid"
									src="/images/DeathtoStock_Desk4.jpg"
								/>
								<div className="sticker sticker-bottom-left sticker-danger">
									JPG
								</div>
							</div>
							<dl className="sidebar-dl sidebar-section">
								<dt className="sidebar-dt">Url</dt>
								<dd className="sidebar-dd">
									<a href="#1">
										http://localhost:8080/documents/20140/
									</a>
								</dd>
								<dt className="sidebar-dt">Created</dt>
								<dd className="sidebar-dd">
									<a href="#1">Helen Smith</a>
								</dd>
								<dt className="sidebar-dt">Description</dt>
								<dd className="sidebar-dd">
									A picture of a person using a ruler and
									exacto knife to cut construction paper.
								</dd>
								<dt className="sidebar-dt">Size</dt>
								<dd className="sidebar-dd">745KB</dd>
								<dt className="sidebar-dt">Tags</dt>
								<dd className="sidebar-dd">
									<span className="label label-lg label-secondary">
										<span className="label-item label-item-expand">
											Tag One
										</span>
									</span>
									<span className="label label-lg label-secondary">
										<span className="label-item label-item-expand">
											Tag Two
										</span>
									</span>
								</dd>
								<dt className="sidebar-dt">Related Assets</dt>
								<dd className="sidebar-dd">
									<ul className="list-group sidebar-list-group">
										<li className="list-group-item list-group-item-flex">
											<div className="autofit-col">
												<div className="sticker sticker-secondary">
													<span className="inline-item">
														<svg
															className="lexicon-icon lexicon-icon-blogs"
															focusable="false"
															role="presentation"
														>
															<use href="/images/icons/icons.svg#blogs"></use>
														</svg>
													</span>
												</div>
											</div>
											<div className="autofit-col autofit-col-expand">
												<section className="autofit-section">
													<div className="list-group-title text-truncate-inline">
														<a
															className="text-truncate"
															href="#1"
														>
															ReallySuperInsanelyJustIncrediblyLongAndTotallyNotPossibleWordButWeAreReallyTryingToCoverAllOurBasesHereJustInCaseSomeoneIsNutsAsPerUsual
														</a>
													</div>
												</section>
											</div>
										</li>
									</ul>
								</dd>
							</dl>
							<div
								aria-orientation="vertical"
								className="panel-group panel-group-flush panel-group-sm"
								role="tablist"
							>
								<div className="panel panel-unstyled">
									<a
										aria-controls="collapseOne"
										aria-expanded="false"
										className="collapse-icon panel-header panel-header-link collapsed"
										data-toggle="collapse"
										href="#collapseOne"
										id="headingOne"
										role="tab"
									>
										<span className="panel-title">
											Collapsible Group Item #1
										</span>
										<span className="collapse-icon-closed">
											<svg
												className="lexicon-icon lexicon-icon-angle-left"
												focusable="false"
												role="presentation"
											>
												<use href="/images/icons/icons.svg#angle-left"></use>
											</svg>
										</span>
										<span className="collapse-icon-open">
											<svg
												className="lexicon-icon lexicon-icon-angle-down"
												focusable="false"
												role="presentation"
											>
												<use href="/images/icons/icons.svg#angle-down"></use>
											</svg>
										</span>
									</a>
									<div
										aria-labelledby="headingOne"
										className="panel-collapse collapse"
										id="collapseOne"
										role="tabpanel"
									>
										<div className="panel-body">
											In organic dripper so, body, robust
											medium pumpkin spice cup, in aged
											dripper latte extra cup white.
										</div>
									</div>
								</div>
							</div>
						</div>
						<div
							aria-labelledby="sidebarLightVersionsTab"
							className="fade tab-pane"
							id="sidebarLightVersions"
							role="tabpanel"
						>
							<ul className="list-group sidebar-list-group">
								<li className="list-group-item list-group-item-flex">
									<div className="autofit-col autofit-col-expand">
										<div className="list-group-title">
											Version 1.2
										</div>
										<div className="list-group-subtitle">
											By Helen, on 8/31/17 9:15am
										</div>
										<div className="list-group-subtext">
											No Change Log
										</div>
									</div>
									<div className="autofit-col">
										<div className="dropdown dropdown-action">
											<a
												aria-expanded="false"
												aria-haspopup="true"
												className="component-action dropdown-toggle"
												data-toggle="dropdown"
												href="#1"
												id="sidebarLightListDropdownId01"
												role="button"
											>
												<svg
													className="lexicon-icon lexicon-icon-ellipsis-v"
													focusable="false"
													role="presentation"
												>
													<use href="/images/icons/icons.svg#ellipsis-v"></use>
												</svg>
											</a>
											<ul
												aria-labelledby="sidebarLightListDropdownId01"
												className="dropdown-menu"
												style={{
													left: '0px',
													position: 'absolute',
													top: '0px',
													transform:
														'translate3d(0px, 811px, 0px)',
													willChange: 'transform'
												}}
												x-out-of-boundaries=""
												x-placement="bottom-start"
											>
												<li>
													<a
														className="dropdown-item"
														href="#1"
														role="button"
													>
														Download
													</a>
												</li>
												<li>
													<a
														className="dropdown-item"
														href="#1"
													>
														Edit
													</a>
												</li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	);
};
