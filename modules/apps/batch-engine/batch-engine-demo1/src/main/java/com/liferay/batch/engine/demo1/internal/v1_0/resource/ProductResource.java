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

package com.liferay.batch.engine.demo1.internal.v1_0.resource;

import com.liferay.batch.engine.demo1.internal.v1_0.model.Product;

import org.osgi.service.component.annotations.Component;

/**
 * @author Ivica Cardic
 */
@Component(service = ProductResource.class)
public class ProductResource {

	public void create(Product product) {
		System.out.println("Create product: " + product);
	}

	public void delete(Product product) {
		System.out.println("Delete product: " + product);
	}

	public void update(Product product) {
		System.out.println("Update product: " + product);
	}

}