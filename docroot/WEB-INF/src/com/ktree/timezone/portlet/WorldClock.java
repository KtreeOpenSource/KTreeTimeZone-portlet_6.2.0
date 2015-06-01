package com.ktree.timezone.portlet;

/*
 * Author: Vikash@KTree.com
 * Date  : 26th May 2015
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.portlet.*;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.*;
import com.liferay.portal.kernel.json.*;
import com.liferay.portal.kernel.servlet.*;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.ktree.timezone.model.dao.model.impl.WorldClockImpl;
import com.ktree.timezone.model.dao.service.*;


public class WorldClock extends MVCPortlet {
	
	@ProcessAction(name="worldclokreq")  
    public void worldclokreq(ActionRequest actionRequest, ActionResponse actionResponse)
    		    throws IOException, PortletException{  
    	
        String selected_timezone = ParamUtil.getString(actionRequest, "country");
           
        SimpleDateFormat sd = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone(selected_timezone));
        String currdate = sd.format(date);
        
        PortletPreferences prefs=actionRequest.getPreferences();
        prefs.setValue("timezoneparam",currdate); 
        prefs.setValue("time_zone",selected_timezone); 
        prefs.store();
        
        if(!(selected_timezone.contains("Asia/Calcutta")||selected_timezone.contains("US/Central")||selected_timezone.contains("Asia/Dubai"))){ 
	        ThemeDisplay themeDisplayData  =(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
	        long userid = themeDisplayData.getPermissionChecker().getUser().getUserId();        
	        boolean isAvailable = isTimeZoneAdded(userid,selected_timezone);
		    if(!isAvailable){    
	        	com.ktree.timezone.model.dao.model.WorldClock currentTimeZoneObject = new WorldClockImpl();        
		        currentTimeZoneObject.setUserid(userid);
		        currentTimeZoneObject.setTimezone(date);
		        if(selected_timezone.length()>0)
		        	currentTimeZoneObject.setPlace(selected_timezone);
		        
		        try {
		        	WorldClockLocalServiceUtil.addWorldClock(currentTimeZoneObject); 
		        	SessionMessages.add(actionRequest, "request_processed", "");
		
				} catch (SystemException e) {
					e.printStackTrace();
				}
		    }
	    }
               
    }  
	
	public boolean isTimeZoneAdded(long userid,String selected_timezone){
		boolean isAvailbale = false;
		try{
			DynamicQuery query = DynamicQueryFactoryUtil.forClass(com.ktree.timezone.model.dao.model.WorldClock.class)
					.add(PropertyFactoryUtil.forName("place").eq(selected_timezone))
					.add(PropertyFactoryUtil.forName("userid").eq(userid));  
	
			List results = WorldClockLocalServiceUtil.dynamicQuery(query, 0, WorldClockLocalServiceUtil.getWorldClocksCount());
			if(results.size()>0)
				isAvailbale = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return isAvailbale;
	}
    
    @ProcessAction(name="deleTimeZone")
    public void deleTimeZone(ActionRequest actionRequest,ActionResponse actionResponse) throws PortletException{
    	String timeZoneToDelete = ParamUtil.getString(actionRequest,"place");
    	if(timeZoneToDelete.length()>0){
	    	String sId = timeZoneToDelete.substring(timeZoneToDelete.indexOf("@"),timeZoneToDelete.length());
	    	sId = sId.replaceAll("@@","");
	    	int id = Integer.parseInt(sId);
	    	try {
				WorldClockLocalServiceUtil.deleteWorldClock(id);
			} catch (PortalException e) {
				e.printStackTrace();
			} catch (SystemException e) {
				e.printStackTrace();
			}
    	}    	
    }
        
    @ProcessAction(name="getAllCountryTimeZone")
    public Map getAllCountryTimeZone(long userId,boolean isGuest){
    	SimpleDateFormat sd1 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd2 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd3 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a"); 
        Date date = new Date();
        sd1.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        sd2.setTimeZone(TimeZone.getTimeZone("US/Central"));
        sd3.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));
        
        String india_time=sd1.format(date);
        String us_time=sd2.format(date);
        String dubai_time=sd3.format(date);
        
    	Map<String,String> mapAllTimeZone = new LinkedHashMap<String,String>();
    	mapAllTimeZone.put("India", india_time); 
    	mapAllTimeZone.put("US Central", us_time);
    	mapAllTimeZone.put("Dubai", dubai_time);

    	List<String> listOfTimesZOnes = new ArrayList();
    	if(!isGuest){
	    	try{
	        	List<com.ktree.timezone.model.dao.model.WorldClock> listOfTimesZones = WorldClockLocalServiceUtil.getWorldClocks(0, WorldClockLocalServiceUtil.getWorldClocksCount());
	        	for(int i=0;i<listOfTimesZones.size();i++){
		        	com.ktree.timezone.model.dao.model.WorldClock worldClock = listOfTimesZones.get(i);
		        	long userid = worldClock.getUserid();
		        	if(userId==userid){
		        		long id = worldClock.getId();
		        		
						String place = worldClock.getPlace()+"@@"+String.valueOf(id);
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
						simpleDateFormat.setTimeZone(TimeZone.getTimeZone(worldClock.getPlace()));
						date = new Date();
						String sTimeZone = simpleDateFormat.format(date);	
						if(!listOfTimesZOnes.contains(worldClock.getPlace())){
							if(worldClock.getPlace().length()>0){
								if(worldClock.getPlace().length()>0){
									mapAllTimeZone.put(place, sTimeZone);
									listOfTimesZOnes.add(worldClock.getPlace());
								}
							}
						}
		        	}
		        }
	        	
	        }catch(Exception e){
	        	System.out.println("[WARNING] com.ktree.timezone.portlet.WorldClock "+e);
	        }
    	}
    	
    	return mapAllTimeZone;
    }
    
    @ProcessAction(name="getTimeZones")
    public void getTimeZones(ActionRequest actionRequest,ActionResponse actionResponse)
    		throws PortletException{
    	boolean isGuest = Boolean.getBoolean(ParamUtil.getString(actionRequest,"isGuest"));
    	long userid = Long.valueOf(ParamUtil.getString(actionRequest,"userid"));
    	Map mapTimeZone = getAllCountryTimeZone(userid,isGuest);
    	
    	try {
			JSONObject jsonUser = null;
			JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();
			
			Set keys_ = mapTimeZone.keySet();
			for (Iterator i = keys_.iterator(); i.hasNext();) {
				jsonUser = JSONFactoryUtil.createJSONObject();
				String key = (String) i.next();
				String time = (String) mapTimeZone.get(key);
				jsonUser.put("area", key);
				jsonUser.put("timeZone", time);
				usersJsonArray.put(jsonUser);
			}	
						
			writeJSON(actionRequest, actionResponse, usersJsonArray);			
			
		} catch (Exception e) {
			System.out.println("Warning "+e);
		}    	
    }
    
    @ProcessAction(name="getTimeZonesBeforeFiveMinute")
    public void getTimeZonesBeforeFiveMinute(ActionRequest actionRequest,ActionResponse actionResponse)
    		throws PortletException{
    	boolean isGuest = Boolean.getBoolean(ParamUtil.getString(actionRequest,"isGuest"));
    	long userid = Long.valueOf(ParamUtil.getString(actionRequest,"userid"));
    	Map mapTimeZone = getAllCountryTimeZoneBeforeFiveMinute(userid,isGuest);
    	
    	try {
			JSONObject jsonUser = null;
			JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();
			
			Set keys_ = mapTimeZone.keySet();
			for (Iterator i = keys_.iterator(); i.hasNext();) {
				jsonUser = JSONFactoryUtil.createJSONObject();
				String key = (String) i.next();
				String time = (String) mapTimeZone.get(key);
				jsonUser.put("area", key);
				jsonUser.put("timeZone", time);
				usersJsonArray.put(jsonUser);
			}	
						
			writeJSON(actionRequest, actionResponse, usersJsonArray);			
			
		} catch (Exception e) {
			System.out.println("Warning "+e);
		}    	
    }
    
    @ProcessAction(name="getTimeZonesAfterFiveMinute")
    public void getTimeZonesAfterFiveMinute(ActionRequest actionRequest,ActionResponse actionResponse)
    		throws PortletException{
    	boolean isGuest = Boolean.getBoolean(ParamUtil.getString(actionRequest,"isGuest"));
    	long userid = Long.valueOf(ParamUtil.getString(actionRequest,"userid"));
    	Map mapTimeZone = getAllCountryTimeZoneAfterFiveMinute(userid,isGuest);
    	
    	try {
			JSONObject jsonUser = null;
			JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();
			
			Set keys_ = mapTimeZone.keySet();
			for (Iterator i = keys_.iterator(); i.hasNext();) {
				jsonUser = JSONFactoryUtil.createJSONObject();
				String key = (String) i.next();
				String time = (String) mapTimeZone.get(key);
				jsonUser.put("area", key);
				jsonUser.put("timeZone", time);
				usersJsonArray.put(jsonUser);
			}	
						
			writeJSON(actionRequest, actionResponse, usersJsonArray);			
			
		} catch (Exception e) {
			System.out.println("Warning "+e);
		}    	
    }
    
    public Map getAllCountryTimeZoneAfterFiveMinute(long userId,boolean isGuest){
    	SimpleDateFormat sd1 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd2 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd3 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a"); 
        Date date = new Date();
        sd1.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        sd2.setTimeZone(TimeZone.getTimeZone("US/Central"));
        sd3.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));
        
        String india_time=sd1.format(new Date(date.getTime()+(5*60*1000)));
        String us_time=sd2.format(new Date(date.getTime()+(5*60*1000)));
        String dubai_time=sd3.format(new Date(date.getTime()+(5*60*1000)));
        
    	Map<String,String> mapAllTimeZone = new LinkedHashMap<String,String>();
    	mapAllTimeZone.put("India", india_time);
    	mapAllTimeZone.put("US Central", us_time);
    	mapAllTimeZone.put("Dubai", dubai_time);

    	List<String> listOfTimesZOnes = new ArrayList();
    	if(!isGuest){
	    	try{
	        	List<com.ktree.timezone.model.dao.model.WorldClock> listOfTimesZones = WorldClockLocalServiceUtil.getWorldClocks(0, WorldClockLocalServiceUtil.getWorldClocksCount());
	        	for(int i=0;i<listOfTimesZones.size();i++){
		        	com.ktree.timezone.model.dao.model.WorldClock worldClock = listOfTimesZones.get(i);
		        	long userid = worldClock.getUserid();
		        	if(userId==userid){
		        		long id = worldClock.getId();
		        		
						String place = worldClock.getPlace()+"@@"+String.valueOf(id);
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
						simpleDateFormat.setTimeZone(TimeZone.getTimeZone(worldClock.getPlace()));
						date = new Date();
						String sTimeZone = simpleDateFormat.format(new Date(date.getTime()+(5*60*1000)));	
						if(!listOfTimesZOnes.contains(worldClock.getPlace())){
							if(worldClock.getPlace().length()>0){
								if(worldClock.getPlace().length()>0){
									mapAllTimeZone.put(place, sTimeZone);
									listOfTimesZOnes.add(worldClock.getPlace());
								}
							}
						}
		        	}
		        }	        	
	        }catch(Exception e){
	        	System.out.println("[WARNING] com.ktree.timezone.portlet.WorldClock "+e);
	        }
    	}
    	
    	return mapAllTimeZone;
    }
    
    public Map getAllCountryTimeZoneBeforeFiveMinute(long userId,boolean isGuest){
    	SimpleDateFormat sd1 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd2 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd3 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a"); 
        Date date = new Date();
        sd1.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        sd2.setTimeZone(TimeZone.getTimeZone("US/Central"));
        sd3.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));
        
        String india_time=sd1.format(new Date(date.getTime()-(5*60*1000)));
        String us_time=sd2.format(new Date(date.getTime()-(5*60*1000)));
        String dubai_time=sd3.format(new Date(date.getTime()-(5*60*1000)));
        
    	Map<String,String> mapAllTimeZone = new LinkedHashMap<String,String>();
    	mapAllTimeZone.put("India", india_time);
    	mapAllTimeZone.put("US Central", us_time);
    	mapAllTimeZone.put("Dubai", dubai_time);

    	List<String> listOfTimesZOnes = new ArrayList();
    	if(!isGuest){
	    	try{
	        	List<com.ktree.timezone.model.dao.model.WorldClock> listOfTimesZones = WorldClockLocalServiceUtil.getWorldClocks(0, WorldClockLocalServiceUtil.getWorldClocksCount());
	        	for(int i=0;i<listOfTimesZones.size();i++){
		        	com.ktree.timezone.model.dao.model.WorldClock worldClock = listOfTimesZones.get(i);
		        	long userid = worldClock.getUserid();
		        	if(userId==userid){
		        		long id = worldClock.getId();
		        		
						String place = worldClock.getPlace()+"@@"+String.valueOf(id);
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
						simpleDateFormat.setTimeZone(TimeZone.getTimeZone(worldClock.getPlace()));
						date = new Date();
						String sTimeZone = simpleDateFormat.format(new Date(date.getTime()-(5*60*1000)));	
						if(!listOfTimesZOnes.contains(worldClock.getPlace())){
							if(worldClock.getPlace().length()>0){
								if(worldClock.getPlace().length()>0){
									mapAllTimeZone.put(place, sTimeZone);
									listOfTimesZOnes.add(worldClock.getPlace());
								}
							}
						}
		        	}
		        }	        	
	        }catch(Exception e){
	        	System.out.println("[WARNING] com.ktree.timezone.portlet.WorldClock "+e);
	        }
    	}
    	
    	return mapAllTimeZone;
    }
    
    @ProcessAction(name="getTimeZonesForGuest")
    public void getTimeZonesForGuest(ActionRequest actionRequest,ActionResponse actionResponse) throws PortletException{
    	SimpleDateFormat sd1 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd2 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        SimpleDateFormat sd3 = new SimpleDateFormat("EEEE|MMMM d, yyyy | hh:mm:a");
        Date date = new Date();
        sd1.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        sd2.setTimeZone(TimeZone.getTimeZone("US/Central"));
        sd3.setTimeZone(TimeZone.getTimeZone("Asia/Dubai"));
        String india_time=sd1.format(date);
        String us_time=sd2.format(date);
        String dubai_time=sd3.format(date);
        
    	Map mapTimeZone = new LinkedHashMap();
    	mapTimeZone.put("India", india_time);
    	mapTimeZone.put("US Central", us_time);
    	mapTimeZone.put("Dubai", dubai_time);
    	
    	try {
			JSONObject jsonUser = null;
			JSONArray usersJsonArray = JSONFactoryUtil.createJSONArray();
			
			Set keys_ = mapTimeZone.keySet();
			for (Iterator i = keys_.iterator(); i.hasNext();) {
				jsonUser = JSONFactoryUtil.createJSONObject();
				String key = (String) i.next();
				String time = (String) mapTimeZone.get(key);
				jsonUser.put("area", key);
				jsonUser.put("timeZone", time);
				usersJsonArray.put(jsonUser);
			}	
						
			writeJSON(actionRequest, actionResponse, usersJsonArray);			
			
		} catch (Exception e) {
			System.out.println("Warning "+e);
		}
    }

    public String getPrevTime(String timZone){
    	if(timZone.equalsIgnoreCase("India")){
			timZone = "Asia/Calcutta";
		}else if(timZone.equalsIgnoreCase("US Central")){
			timZone = "US/Central";
		}else if(timZone.equalsIgnoreCase("Dubai")){
			timZone = "Asia/Dubai";
		}
    	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:a");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone(timZone));
        String prevTime = sdf.format(new Date(date.getTime()-(5*60*1000)));
        return prevTime;
    }
	
	public String getNextTime(String timZone){
		if(timZone.equalsIgnoreCase("India")){
			timZone = "Asia/Calcutta";
		}else if(timZone.equalsIgnoreCase("US Central")){
			timZone = "US/Central";
		}else if(timZone.equalsIgnoreCase("Dubai")){
			timZone = "Asia/Dubai";
		}
		
    	SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:a");
        Date date = new Date();
        sdf.setTimeZone(TimeZone.getTimeZone(timZone));
        String prevTime = sdf.format(new Date(date.getTime()+(5*60*1000)));
        return prevTime;
    }	
}
