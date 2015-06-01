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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ktree.timezone.model.dao.service.http.WorldClockServiceSoap}.
 *
 * @author vikash@KTree
 * @see com.ktree.timezone.model.dao.service.http.WorldClockServiceSoap
 * @generated
 */
public class WorldClockSoap implements Serializable {
	public static WorldClockSoap toSoapModel(WorldClock model) {
		WorldClockSoap soapModel = new WorldClockSoap();

		soapModel.setId(model.getId());
		soapModel.setPlace(model.getPlace());
		soapModel.setTimezone(model.getTimezone());
		soapModel.setUserid(model.getUserid());

		return soapModel;
	}

	public static WorldClockSoap[] toSoapModels(WorldClock[] models) {
		WorldClockSoap[] soapModels = new WorldClockSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static WorldClockSoap[][] toSoapModels(WorldClock[][] models) {
		WorldClockSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new WorldClockSoap[models.length][models[0].length];
		}
		else {
			soapModels = new WorldClockSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static WorldClockSoap[] toSoapModels(List<WorldClock> models) {
		List<WorldClockSoap> soapModels = new ArrayList<WorldClockSoap>(models.size());

		for (WorldClock model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new WorldClockSoap[soapModels.size()]);
	}

	public WorldClockSoap() {
	}

	public int getPrimaryKey() {
		return _id;
	}

	public void setPrimaryKey(int pk) {
		setId(pk);
	}

	public int getId() {
		return _id;
	}

	public void setId(int id) {
		_id = id;
	}

	public String getPlace() {
		return _place;
	}

	public void setPlace(String place) {
		_place = place;
	}

	public Date getTimezone() {
		return _timezone;
	}

	public void setTimezone(Date timezone) {
		_timezone = timezone;
	}

	public long getUserid() {
		return _userid;
	}

	public void setUserid(long userid) {
		_userid = userid;
	}

	private int _id;
	private String _place;
	private Date _timezone;
	private long _userid;
}