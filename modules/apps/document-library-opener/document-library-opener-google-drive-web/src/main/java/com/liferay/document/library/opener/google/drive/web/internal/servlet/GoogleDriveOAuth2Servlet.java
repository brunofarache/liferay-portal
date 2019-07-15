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

package com.liferay.document.library.opener.google.drive.web.internal.servlet;

import com.liferay.document.library.opener.google.drive.DLOpenerGoogleDriveManager;
import com.liferay.document.library.opener.google.drive.web.internal.constants.DLOpenerGoogleDriveWebConstants;
import com.liferay.document.library.opener.google.drive.web.internal.util.OAuth2Helper;
import com.liferay.document.library.opener.google.drive.web.internal.util.State;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(
	immediate = true,
	property = {
		"osgi.http.whiteboard.servlet.name=com.liferay.document.library.opener.google.drive.web.internal.servlet.GoogleDriveOAuth2Servlet",
		"osgi.http.whiteboard.servlet.pattern=" + DLOpenerGoogleDriveWebConstants.GOOGLE_DRIVE_SERVLET_PATH,
		"servlet.init.httpMethods=GET,POST"
	},
	service = Servlet.class
)
public class GoogleDriveOAuth2Servlet extends HttpServlet {

	@Override
	protected void doGet(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		State state = State.get(
			_portal.getOriginalServletRequest(httpServletRequest));

		if (state == null) {
			throw new IllegalStateException(
				"Authorization state not initialized");
		}
		else if (!state.isValid(httpServletRequest)) {
			state.goToFailurePage(httpServletRequest, httpServletResponse);
		}
		else {
			_requestAuthorizationToken(
				httpServletRequest, httpServletResponse, state);
		}
	}

	@Override
	protected void doPost(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		doGet(httpServletRequest, httpServletResponse);
	}

	private void _requestAuthorizationToken(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, State state)
		throws IOException {

		String code = ParamUtil.getString(httpServletRequest, "code");

		if (Validator.isNull(code)) {
			state.goToFailurePage(httpServletRequest, httpServletResponse);
		}
		else {
			try {
				_dlOpenerGoogleDriveManager.requestAuthorizationToken(
					_portal.getCompanyId(httpServletRequest), state.getUserId(),
					code, _oAuth2Helper.getRedirectURI(httpServletRequest));
			}
			catch (PortalException pe) {
				throw new IOException(pe);
			}

			state.goToSuccessPage(httpServletRequest, httpServletResponse);
		}
	}

	private static final long serialVersionUID = 7759897747401129852L;

	@Reference
	private DLOpenerGoogleDriveManager _dlOpenerGoogleDriveManager;

	@Reference
	private OAuth2Helper _oAuth2Helper;

	@Reference
	private Portal _portal;

}