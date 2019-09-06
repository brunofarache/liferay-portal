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

package com.liferay.batch.engine.internal.reader;

import com.liferay.batch.engine.BatchEngineTaskContentType;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Shuyang Zhou
 */
public class BatchEngineTaskItemReaderFactory {

	public static <T> BatchEngineTaskItemReader<T> create(
			Class<T> itemClass,
			BatchEngineTaskContentType batchEngineTaskContentType,
			InputStream inputStream)
		throws IOException {

		if (batchEngineTaskContentType == BatchEngineTaskContentType.CSV) {
			return new FlatFileBatchEngineTaskItemReader<>(
				itemClass, inputStream);
		}

		if (batchEngineTaskContentType == BatchEngineTaskContentType.JSON) {
			return new JSONBatchEngineTaskItemReader<>(
				itemClass, inputStream);
		}

		if ((batchEngineTaskContentType == BatchEngineTaskContentType.XLS) ||
			(batchEngineTaskContentType == BatchEngineTaskContentType.XLSX)) {

			return new XLSBatchEngineTaskItemReader<>(itemClass, inputStream);
		}

		throw new IllegalArgumentException(
			"Unknown batch engine task content type " +
				batchEngineTaskContentType);
	}

}