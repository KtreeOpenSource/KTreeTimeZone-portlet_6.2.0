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

package com.ktree.timezone.model.dao.model;

import com.ktree.timezone.model.dao.service.ClpSerializer;
import com.ktree.timezone.model.dao.service.WorldClockLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vikash@KTree
 */
public class WorldClockClp extends BaseModelImpl<WorldClock>
	implements WorldClock {
	public WorldClockClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return WorldClock.class;
	}

	@Override
	public String getModelClassName() {
		return WorldClock.class.getName();
	}

	@Override
	public int getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(int primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Integer)primaryKeyObj).intValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("place", getPlace());
		attributes.put("timezone", getTimezone());
		attributes.put("userid", getUserid());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Integer id = (Integer)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		String place = (String)attributes.get("place");

		if (place != null) {
			setPlace(place);
		}

		Date timezone = (Date)attributes.get("timezone");

		if (timezone != null) {
			setTimezone(timezone);
		}

		Long userid = (Long)attributes.get("userid");

		if (userid != null) {
			setUserid(userid);
		}
	}

	@Override
	public int getId() {
		return _id;
	}

	@Override
	public void setId(int id) {
		_id = id;

		if (_worldClockRemoteModel != null) {
			try {
				Class<?> clazz = _worldClockRemoteModel.getClass();

				Method method = clazz.getMethod("setId", int.class);

				method.invoke(_worldClockRemoteModel, id);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPlace() {
		return _place;
	}

	@Override
	public void setPlace(String place) {
		_place = place;

		if (_worldClockRemoteModel != null) {
			try {
				Class<?> clazz = _worldClockRemoteModel.getClass();

				Method method = clazz.getMethod("setPlace", String.class);

				method.invoke(_worldClockRemoteModel, place);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getTimezone() {
		return _timezone;
	}

	@Override
	public void setTimezone(Date timezone) {
		_timezone = timezone;

		if (_worldClockRemoteModel != null) {
			try {
				Class<?> clazz = _worldClockRemoteModel.getClass();

				Method method = clazz.getMethod("setTimezone", Date.class);

				method.invoke(_worldClockRemoteModel, timezone);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserid() {
		return _userid;
	}

	@Override
	public void setUserid(long userid) {
		_userid = userid;

		if (_worldClockRemoteModel != null) {
			try {
				Class<?> clazz = _worldClockRemoteModel.getClass();

				Method method = clazz.getMethod("setUserid", long.class);

				method.invoke(_worldClockRemoteModel, userid);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getWorldClockRemoteModel() {
		return _worldClockRemoteModel;
	}

	public void setWorldClockRemoteModel(BaseModel<?> worldClockRemoteModel) {
		_worldClockRemoteModel = worldClockRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _worldClockRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_worldClockRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() throws SystemException {
		if (this.isNew()) {
			WorldClockLocalServiceUtil.addWorldClock(this);
		}
		else {
			WorldClockLocalServiceUtil.updateWorldClock(this);
		}
	}

	@Override
	public WorldClock toEscapedModel() {
		return (WorldClock)ProxyUtil.newProxyInstance(WorldClock.class.getClassLoader(),
			new Class[] { WorldClock.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		WorldClockClp clone = new WorldClockClp();

		clone.setId(getId());
		clone.setPlace(getPlace());
		clone.setTimezone(getTimezone());
		clone.setUserid(getUserid());

		return clone;
	}

	@Override
	public int compareTo(WorldClock worldClock) {
		int primaryKey = worldClock.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WorldClockClp)) {
			return false;
		}

		WorldClockClp worldClock = (WorldClockClp)obj;

		int primaryKey = worldClock.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", place=");
		sb.append(getPlace());
		sb.append(", timezone=");
		sb.append(getTimezone());
		sb.append(", userid=");
		sb.append(getUserid());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.ktree.timezone.model.dao.model.WorldClock");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>place</column-name><column-value><![CDATA[");
		sb.append(getPlace());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>timezone</column-name><column-value><![CDATA[");
		sb.append(getTimezone());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userid</column-name><column-value><![CDATA[");
		sb.append(getUserid());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private int _id;
	private String _place;
	private Date _timezone;
	private long _userid;
	private BaseModel<?> _worldClockRemoteModel;
}