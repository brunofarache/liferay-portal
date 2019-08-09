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

package com.liferay.app.builder.model;

import java.util.Objects;

/**
 * @author Gabriel Albuquerque
 */
public enum AppBuilderAppStatus {

	DEPLOYED("deployed", 0), UNDEPLOYED("undeployed", 1);

	public static String parse(int value) {
		if (Objects.equals(DEPLOYED.getValue(), value)) {
			return DEPLOYED.getName();
		}
		else if (Objects.equals(UNDEPLOYED.getValue(), value)) {
			return UNDEPLOYED.getName();
		}

		throw new IllegalArgumentException("Invalid value " + value);
	}

	public static int parse(String name) {
		if (Objects.equals(DEPLOYED.getName(), name)) {
			return DEPLOYED.getValue();
		}
		else if (Objects.equals(UNDEPLOYED.getName(), name)) {
			return UNDEPLOYED.getValue();
		}

		throw new IllegalArgumentException("Invalid name " + name);
	}

	public String getName() {
		return _name;
	}

	public int getValue() {
		return _value;
	}

	private AppBuilderAppStatus(String name, int value) {
		_name = name;
		_value = value;
	}

	private final String _name;
	private final int _value;

}