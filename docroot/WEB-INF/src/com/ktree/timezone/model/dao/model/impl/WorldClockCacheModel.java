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

package com.ktree.timezone.model.dao.model.impl;

import com.ktree.timezone.model.dao.model.WorldClock;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WorldClock in entity cache.
 *
 * @author vikash@KTree
 * @see WorldClock
 * @generated
 */
public class WorldClockCacheModel implements CacheModel<WorldClock>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{id=");
		sb.append(id);
		sb.append(", place=");
		sb.append(place);
		sb.append(", timezone=");
		sb.append(timezone);
		sb.append(", userid=");
		sb.append(userid);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WorldClock toEntityModel() {
		WorldClockImpl worldClockImpl = new WorldClockImpl();

		worldClockImpl.setId(id);

		if (place == null) {
			worldClockImpl.setPlace(StringPool.BLANK);
		}
		else {
			worldClockImpl.setPlace(place);
		}

		if (timezone == Long.MIN_VALUE) {
			worldClockImpl.setTimezone(null);
		}
		else {
			worldClockImpl.setTimezone(new Date(timezone));
		}

		worldClockImpl.setUserid(userid);

		worldClockImpl.resetOriginalValues();

		return worldClockImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		id = objectInput.readInt();
		place = objectInput.readUTF();
		timezone = objectInput.readLong();
		userid = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeInt(id);

		if (place == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(place);
		}

		objectOutput.writeLong(timezone);
		objectOutput.writeLong(userid);
	}

	public int id;
	public String place;
	public long timezone;
	public long userid;
}