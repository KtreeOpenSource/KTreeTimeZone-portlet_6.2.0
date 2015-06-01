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
import com.ktree.timezone.model.dao.model.WorldClockModel;
import com.ktree.timezone.model.dao.model.WorldClockSoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the WorldClock service. Represents a row in the &quot;table_WorldClock&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.ktree.timezone.model.dao.model.WorldClockModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link WorldClockImpl}.
 * </p>
 *
 * @author vikash@KTree
 * @see WorldClockImpl
 * @see com.ktree.timezone.model.dao.model.WorldClock
 * @see com.ktree.timezone.model.dao.model.WorldClockModel
 * @generated
 */
@JSON(strict = true)
public class WorldClockModelImpl extends BaseModelImpl<WorldClock>
	implements WorldClockModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a world clock model instance should use the {@link com.ktree.timezone.model.dao.model.WorldClock} interface instead.
	 */
	public static final String TABLE_NAME = "table_WorldClock";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id_", Types.INTEGER },
			{ "place", Types.VARCHAR },
			{ "timezone", Types.TIMESTAMP },
			{ "userid", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table table_WorldClock (id_ INTEGER not null primary key,place VARCHAR(75) null,timezone DATE null,userid LONG)";
	public static final String TABLE_SQL_DROP = "drop table table_WorldClock";
	public static final String ORDER_BY_JPQL = " ORDER BY worldClock.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY table_WorldClock.id_ ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.com.ktree.timezone.model.dao.model.WorldClock"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.com.ktree.timezone.model.dao.model.WorldClock"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static WorldClock toModel(WorldClockSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		WorldClock model = new WorldClockImpl();

		model.setId(soapModel.getId());
		model.setPlace(soapModel.getPlace());
		model.setTimezone(soapModel.getTimezone());
		model.setUserid(soapModel.getUserid());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<WorldClock> toModels(WorldClockSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<WorldClock> models = new ArrayList<WorldClock>(soapModels.length);

		for (WorldClockSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.com.ktree.timezone.model.dao.model.WorldClock"));

	public WorldClockModelImpl() {
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
	public Class<?> getModelClass() {
		return WorldClock.class;
	}

	@Override
	public String getModelClassName() {
		return WorldClock.class.getName();
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

	@JSON
	@Override
	public int getId() {
		return _id;
	}

	@Override
	public void setId(int id) {
		_id = id;
	}

	@JSON
	@Override
	public String getPlace() {
		if (_place == null) {
			return StringPool.BLANK;
		}
		else {
			return _place;
		}
	}

	@Override
	public void setPlace(String place) {
		_place = place;
	}

	@JSON
	@Override
	public Date getTimezone() {
		return _timezone;
	}

	@Override
	public void setTimezone(Date timezone) {
		_timezone = timezone;
	}

	@JSON
	@Override
	public long getUserid() {
		return _userid;
	}

	@Override
	public void setUserid(long userid) {
		_userid = userid;
	}

	@Override
	public WorldClock toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (WorldClock)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		WorldClockImpl worldClockImpl = new WorldClockImpl();

		worldClockImpl.setId(getId());
		worldClockImpl.setPlace(getPlace());
		worldClockImpl.setTimezone(getTimezone());
		worldClockImpl.setUserid(getUserid());

		worldClockImpl.resetOriginalValues();

		return worldClockImpl;
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

		if (!(obj instanceof WorldClock)) {
			return false;
		}

		WorldClock worldClock = (WorldClock)obj;

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
	public void resetOriginalValues() {
	}

	@Override
	public CacheModel<WorldClock> toCacheModel() {
		WorldClockCacheModel worldClockCacheModel = new WorldClockCacheModel();

		worldClockCacheModel.id = getId();

		worldClockCacheModel.place = getPlace();

		String place = worldClockCacheModel.place;

		if ((place != null) && (place.length() == 0)) {
			worldClockCacheModel.place = null;
		}

		Date timezone = getTimezone();

		if (timezone != null) {
			worldClockCacheModel.timezone = timezone.getTime();
		}
		else {
			worldClockCacheModel.timezone = Long.MIN_VALUE;
		}

		worldClockCacheModel.userid = getUserid();

		return worldClockCacheModel;
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

	private static ClassLoader _classLoader = WorldClock.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			WorldClock.class
		};
	private int _id;
	private String _place;
	private Date _timezone;
	private long _userid;
	private WorldClock _escapedModel;
}