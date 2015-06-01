/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.ktree.timezone.model.dao.service.persistence;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Serializable;

/**
 * @author vikash@KTree
 */
public class WorldClockPK implements Comparable<WorldClockPK>, Serializable {
	public int id;
	public String place;

	public WorldClockPK() {
	}

	public WorldClockPK(int id, String place) {
		this.id = id;
		this.place = place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Override
	public int compareTo(WorldClockPK pk) {
		if (pk == null) {
			return -1;
		}

		int value = 0;

		if (id < pk.id) {
			value = -1;
		}
		else if (id > pk.id) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = place.compareTo(pk.place);

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorldClockPK)) {
			return false;
		}

		WorldClockPK pk = (WorldClockPK)obj;

		if ((id == pk.id) && (place.equals(pk.place))) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (String.valueOf(id) + String.valueOf(place)).hashCode();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(10);

		sb.append(StringPool.OPEN_CURLY_BRACE);

		sb.append("id");
		sb.append(StringPool.EQUAL);
		sb.append(id);

		sb.append(StringPool.COMMA);
		sb.append(StringPool.SPACE);
		sb.append("place");
		sb.append(StringPool.EQUAL);
		sb.append(place);

		sb.append(StringPool.CLOSE_CURLY_BRACE);

		return sb.toString();
	}
}