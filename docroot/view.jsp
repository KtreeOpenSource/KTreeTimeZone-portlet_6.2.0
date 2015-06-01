<!-- 
 Author: Vikash@KTree.com
 Date  : 26th May 2015
 -->
<%@page import="com.liferay.portal.service.ServiceContext"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@page import="javax.portlet.PortletPreferences"%>
<%@page import="javax.portlet.ActionResponse"%>
<%@page import="java.util.HashSet"%>
<%@page import="javax.portlet.WindowState"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page import="java.util.TimeZone"%>
<%@ page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil"%>
<%@ page import="java.util.Arrays"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@page import="com.liferay.portal.kernel.util.Validator"%>
<%@page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.ktree.timezone.portlet.WorldClock" %>
<%@page import="com.liferay.portal.service.ServiceContext"%>
<%@page import="com.liferay.portal.service.ServiceContextFactory"%>
<%@page import="java.util.StringTokenizer"%>

<portlet:defineObjects />
<portlet:actionURL name="worldclokreq" var="worldclockurl"/>

<div>
	<%
	ThemeDisplay themeDisplayData = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	com.liferay.portal.model.User user = themeDisplayData.getPermissionChecker().getUser();
	long userid = user.getUserId();
	boolean isGuest = user.getRoles().toString().contains("Guest");
	Map mapTimeZone = new WorldClock().getAllCountryTimeZone(userid,isGuest);
	boolean isOmniUser = themeDisplayData.getPermissionChecker().isOmniadmin();
	%>
</div>

<body>
	<head>
		<meta charset="utf-8">
		<title>World Clock</title>
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<script>
			<%if(!isGuest){%>
				setInterval(updateDiv,60000);
			<%}else{%>
				setInterval(updateDivForGuest,60000);
			<%}%>
			var allRows ="";
			function updateDiv(){
				var url = '<portlet:actionURL><portlet:param name="javax.portlet.action" value="getTimeZones" /><portlet:param name="userid" value="<%=String.valueOf(userid)%>" /><portlet:param name="isGuest" value="<%=String.valueOf(isGuest)%>" /></portlet:actionURL>';  
			    AUI().use('aui-base','aui-io-request', function(A){
				    A.io.request(url,{dataType: 'json',method: 'GET',
						data: {param:"timezone"}, 				    
					    on: {  
					      failure: function() { },  
					      success: function(data) {
					    	  var data = this.get('responseData');				    	 
					    	  A.Array.each(data, function(obj, idx){
					    		  var location = obj.area;
					    		  var timeValue =obj.timeZone;
					    		  var values = timeValue.split("|");
					    		  
					    		  var day = values[0];
					    		  var date = values[1];
					    		  var time = values[2];
					    		  
					    		  var timeFormat = time.split(":");
					    		  var hr = timeFormat[0];
					    		  var min = timeFormat[1];
					    		  var note = timeFormat[2];
					    		 
					    		  var tableRow = "";
					    		  if(location.indexOf("@")>0){ 
					    		  	tableRow ='<tr><td id="place">'+location.split('@')[0]+'</td><td id="timeZone"><div class="time-container"><span class="xhr">'+hr+'</span><span class="delimeter">:</span><span class="xmin">'+min+'</span><span class="xnote">'+note+'</span><span class="xday">'+day+'</span></div></td><td id="action"><a href="<portlet:actionURL ><portlet:param name='javax.portlet.action' value='deleTimeZone'/><portlet:param name='place' value='${location}'/></portlet:actionURL>"><img src="<%=request.getContextPath()%>/css/images/delete.png"/></a</td></tr>';
					    		  }else{
					    			tableRow='<tr><td id="place">'+obj.area+'</td><td id="timeZone"><div class="time-container"><span class="xhr">'+hr+'</span><span class="delimeter">:</span><span class="xmin">'+min+'</span><span class="xnote">'+note+'</span><span class="xday">'+day+'</span></div></td><td><span id="auto_default" style="padding-right: 16px !important;float: right !important;color:#000;">Default</span></td></tr>';	  
					    		  }
					    		  allRows = allRows.trim()+tableRow.trim();
						    	  A.one('#timezone_table').setHTML(allRows.trim());
					    	  });
					    	  allRows ="";
					      }
					    }  
				    });
			    });
			}
		
			function updateDivForGuest(){
				var url = '<portlet:actionURL><portlet:param name="javax.portlet.action" value="getTimeZonesForGuest"/></portlet:actionURL>';  
			    AUI().use('aui-base','aui-io-request', function(A){
				    A.io.request(url,{dataType: 'json',method: 'GET',
						data: {param:"timezone"}, 				    
					    on: {  
					      failure: function() { },  
					      success: function(data) {
					    	  var data = this.get('responseData');				    	 
					    	  A.Array.each(data, function(obj, idx){
					    		  var location = obj.area;
					    		  var timeValue =obj.timeZone;
					    		  var values = timeValue.split("|");
					    		  
					    		  var day = values[0];
					    		  var date = values[1];
					    		  var time = values[2];
					    		  
					    		  var timeFormat = time.split(":");
					    		  var hr = timeFormat[0];
					    		  var min = timeFormat[1];
					    		  var note = timeFormat[2];
					    		  
					    		  if(location.indexOf("@")>0){ 
					    		  	var tableRow='<tr><td id="place">'+location.split('@')[0]+'</td><td id="timeZone"><div class="time-container"><span class="xhr">'+hr+'</span><span class="delimeter">:</span><span class="xmin">'+min+'</span><span class="xnote">'+note+'</span><span class="xday">'+day+'</span></div></td><td id="action"><a href="<portlet:actionURL ><portlet:param name='javax.portlet.action' value='deleTimeZone'/><portlet:param name='place' value='${location}'/></portlet:actionURL>"><img src="<%=request.getContextPath()%>/css/images/delete.png"/></a</td></tr>';
					    		  }else{
					    			var tableRow='<tr><td id="place">'+obj.area+'</td><td id="timeZone"><div class="time-container"><span class="xhr">'+hr+'</span><span class="delimeter">:</span><span class="xmin">'+min+'</span><span class="xnote">'+note+'</span><span class="xday">'+day+'</span></div></td><td><span id="auto_default" style="padding-right: 16px !important;float: right !important;color:#000;">Default</span></td></tr>';	  
					    		  }
					    		  allRows = allRows.trim()+tableRow.trim();
						    	  A.one('#timezone_table').setHTML(allRows.trim());
					    	  });
					    	  allRows ="";
					      }
					    }  
				    });
			    });
			}
			var lastElement;
			var lastChildrenDay;
			var lastChildrenDate;
			var lastOpened;
			$(document).ready(function(){
				$(".time-zone-area").on("click",function(){
					if(lastElement!=undefined){
						lastOpened.css("pointer-events","");
						lastOpened.css("cursor","pointer");
						lastElement.animate({height: "toggle"},800, function(){});
					}
					if(lastChildrenDay!=undefined)
						lastChildrenDay.fadeIn(2000);//animate({height: "toggle"},800, function(){});
					if(lastChildrenDate!=undefined)
						lastChildrenDate.fadeIn(2000);//animate({height: "toggle"},800, function(){});
					
					$(this).css("pointer-events","none");
					$(this).css("cursor","default");
					
					$(this).find(".day").fadeOut(1000);//.css("display","none");
					$(this).find(".dt").fadeOut(1000);//.css("display","none");
					
					$(this).next(".time-zone-area-time").animate({height: "toggle"},800, function(){});
					lastElement = $(this).next(".time-zone-area-time"); 
					lastChildrenDay=$(this).find(".day");
					lastChildrenDate=$(this).find(".dt");
					lastOpened = $(this);
				});
				
				$(".configurator").on("click",function(){
					$(".config").animate({height: "toggle"},1500, function(){});
					$(".main").animate({height: "toggle"},1500, function(){});
				});
				$(".back").on("click",function(){
					$(".config").animate({height: "toggle"},1500, function(){});
					$(".main").animate({height: "toggle"},1500, function(){});
				});
				
				$("#go_button").text("");
				
				setInterval(function() {
					var url = '<portlet:actionURL><portlet:param name="javax.portlet.action" value="getTimeZones" /><portlet:param name="userid" value="<%=String.valueOf(userid)%>" /><portlet:param name="isGuest" value="<%=String.valueOf(isGuest)%>" /></portlet:actionURL>';  
				    AUI().use('aui-base','aui-io-request', function(A){
					    A.io.request(url,{dataType: 'json',method: 'GET',
							data: {param:"timezone"}, 				    
						    on: {  
						      failure: function() { },  
						      success: function(data) {
						    	  var data = this.get('responseData');				    	 
						    	  A.Array.each(data, function(obj, idx){
						    		  var location = obj.area;
						    		  var timeValue =obj.timeZone;
						    		  var values = timeValue.split("|");
						    		  
						    		  if(location.contains(" ")){
						    			  location = location.replace(/ /g, '_');
						    		  }
						    		  
						    		  if (location.contains("/")){
						    			  location = location.replace(/\//g, '_');
						    		  }
						    		  
						    		  if (location.contains("@")){
						    			  location = location.substr(0,location.indexOf("@"))
						    		  }
						    		  
						    		  var day = values[0];
						    		  var date = values[1];
						    		  var time = values[2];
						    		  
						    		  var timeFormat = time.split(":");
						    		  var hr = timeFormat[0];
						    		  var min = timeFormat[1];
						    		  var note = timeFormat[2];
						    		  
						    		  $("#"+location).find(".min").text(min);
						    		  $("#"+location).find(".hr").text(hr);
						    		  $("#"+location).find(".notation_current").text(note);		
						    		  
						    		  $("#"+location+"_header").text(hr+":"+min+" "+note);
						    		  $("#"+location+"_day_header").text(day);	
						    		  $("#"+location+"_date_bar").text(date);
						    		  $("#"+location+"_day_bar").text(day);	
						    	  });
						      }
						    }  
					    });
				    });
				},60*1000);
				
				setInterval(function() {
					var url = '<portlet:actionURL><portlet:param name="javax.portlet.action" value="getTimeZonesAfterFiveMinute" /><portlet:param name="userid" value="<%=String.valueOf(userid)%>" /><portlet:param name="isGuest" value="<%=String.valueOf(isGuest)%>" /></portlet:actionURL>';  
				    AUI().use('aui-base','aui-io-request', function(A){
					    A.io.request(url,{dataType: 'json',method: 'GET',
							data: {param:"timezone"}, 				    
						    on: {  
						      failure: function() { },  
						      success: function(data) {
						    	  var data = this.get('responseData');				    	 
						    	  A.Array.each(data, function(obj, idx){
						    		  var location = obj.area;
						    		  var timeValue =obj.timeZone;
						    		  var values = timeValue.split("|");
						    		  
						    		  if(location.contains(" ")){
						    			  location = location.replace(/ /g, '_');
						    		  }
						    		  
						    		  if (location.contains("/")){
						    			  location = location.replace(/\//g, '_');
						    		  }
						    		  
						    		  if (location.contains("@")){
						    			  location = location.substr(0,location.indexOf("@"))
						    		  }
						    		  var day = values[0];
						    		  var date = values[1];
						    		  var time = values[2];
						    		  
						    		  var timeFormat = time.split(":");
						    		  var hr = timeFormat[0];
						    		  var min = timeFormat[1];
						    		  var note = timeFormat[2];
						    		  
						    		  $("#"+location).find(".nextMin").text(min);
						    		  $("#"+location).find(".nextHr").text(hr); 
						    		  $("#"+location).find(".next-notation").text(note);					    		  
						    		  
						    	  });
						      }
						    }  
					    });
				   });
				},60*1000);
			
				setInterval(function() {
					var url = '<portlet:actionURL><portlet:param name="javax.portlet.action" value="getTimeZonesBeforeFiveMinute" /><portlet:param name="userid" value="<%=String.valueOf(userid)%>" /><portlet:param name="isGuest" value="<%=String.valueOf(isGuest)%>" /></portlet:actionURL>';  
			    	AUI().use('aui-base','aui-io-request', function(A){
				    A.io.request(url,{dataType: 'json',method: 'GET',
						data: {param:"timezone"}, 				    
					    on: {  
					      failure: function() { },  
					      success: function(data) {
					    	  var data = this.get('responseData');				    	 
					    	  A.Array.each(data, function(obj, idx){
					    		  var location = obj.area;
					    		  var timeValue =obj.timeZone;
					    		  var values = timeValue.split("|");
					    		  
					    		  if(location.contains(" ")){
					    			  location = location.replace(/ /g, '_');
					    		  }
					    		  
					    		  if (location.contains("/")){
					    			  location = location.replace(/\//g, '_');
					    		  }
					    		  
					    		  if (location.contains("@")){
					    			  location = location.substr(0,location.indexOf("@"))
					    		  }
					    		  
					    		  var day = values[0];
					    		  var date = values[1];
					    		  var time = values[2];
					    		  
					    		  var timeFormat = time.split(":");
					    		  var hr = timeFormat[0];
					    		  var min = timeFormat[1];
					    		  var note = timeFormat[2];
					    		  
					    		  $("#"+location).find(".prevmin").text(min);
					    		  $("#"+location).find(".prevHr").text(hr); 
					    		  $("#"+location).find(".pre-notation").text(note);					    		  
					    		  
					    	  });
					      }
					    }  
				    });
			    });
			},60*1000);
		
			YUI().use('aui-tabview', function(Y) {
				new Y.TabView({
					srcNode : '#myTab'
				}).render();
			});
			});
		</script>
	</head>
		
	<div class="config" style="display:none">
		<div class="time-zone-header" style="cursor:pointer">
			<span class="back icon-desktop" title="Back to main window"></span>	
			<span class="time-zone-title">Clock Settings</span>
		</div>
		<aui:form id="fm" method="post" action="<%=worldclockurl%>">
			<div id="myTab">
				<ul class="nav-tabs" style="display:none">
					<li style="background-color:#DDD;display:none"><a href="tab-1">Time Zone</a></li>
					<%if(isOmniUser){ %>
					<li style="background-color:#DDD;display:none"><a href="tab-2">Admin</a></li>
					<%} %>
				</ul>
				
				<div class="time_portlet">
					<div id="tab-1" class="tab-pane">
						<% if(!isGuest){ %>
						
						<table class="selection" style="width:100%">
							<tr>
								<td width="80%"><span class="add-messgae">Select Time Zone Area:</span>
										<aui:select style="width: 100%;" label="" name="country" id="country_val">
											<%
											String timeZoneArray[] = TimeZone.getAvailableIDs();
											Arrays.sort(timeZoneArray);
											for (int i = 0; i < timeZoneArray.length; i++) {
											%>
											<aui:option class="timezone-data-option" name="timezone_data" value="<%=timeZoneArray[i]%>"><%=timeZoneArray[i]%></aui:option>
											<%
											}
											%>
										</aui:select>
								</td>
								<td width="20%"><aui:button type="submit" value="" label="" id="go_button"/></td>
							</tr>
						</table>
						<%} %>
						<table id="timezone_table" border="0" style="width:100%">
							<%
								Set keys_ = mapTimeZone.keySet();
								for (Iterator i = keys_.iterator(); i.hasNext();) {
									String key = (String) i.next();
									String place = key;
									String timeZoneArea = place;
									if(timeZoneArea.contains("@")){
										timeZoneArea = timeZoneArea.substring(0,timeZoneArea.indexOf("@"));								
									}	
									String _timeZone = (String) mapTimeZone.get(key);
									
									StringTokenizer _tokens = new StringTokenizer(_timeZone,"|");
									String _day = ""; 
									String _date = "";
									String _time = "";
									while(_tokens.hasMoreElements()){
										_day =  _tokens.nextToken();
										_date = _tokens.nextToken();
										_time = _tokens.nextToken();
									}
									
									StringTokenizer _timeTokens = new StringTokenizer(_time,":");
									String _hr = "";
									String _min = "";
									String _notation = "";
									while(_timeTokens.hasMoreElements()){
										_hr = _timeTokens.nextToken().trim();
										_min = _timeTokens.nextToken().trim();
										_notation = _timeTokens.nextToken().trim();
									}
									
									%>
									<tr class="timezone_row">
										<%if(place.length()>0) {%>
										<td id="place"><%=timeZoneArea%></td>
										<td id="timeZone">
											<div class="time-container">
												<span class="xhr"><%=_hr %></span><span class="delimeter">:</span><span class="xmin"><%=_min %></span>
												<span class="xnote"><%=_notation %></span>
												<span class="xday"><%=_day %></span>
											</div>																						
										</td>
										
										<td id="action">
											<%if(place.contains("@@")){ %>
											<a title="Delete" href="<portlet:actionURL>
														<portlet:param name="javax.portlet.action" value="deleTimeZone" />
														<portlet:param name="place" value="<%=place%>" />
													 </portlet:actionURL>"><img src="<%=request.getContextPath()%>/css/images/delete.png"/></a>
											
											<%}else{ %>
												<span style="color:green;">Default</span>
											<%} %>
										</td>
										<%} %>
									</tr>
									<%
								}
							%>
						</table>				
					</div>
					<%if(isOmniUser){%>
					<div id="tab-2">
						<p>@Vikash</p>
					</div>
					<%} %>
				</div>
			</div>
		</aui:form>
	</div>
	
	<div class="main">
		<div class="time-zone-header">	
			<aui:fieldset label="">
				<aui:layout>
					<aui:column columnWidth="85">										
						<span class="time-zone-title-main">World Clock</span>
					</aui:column>
					<aui:column columnWidth="15">
		            	<span title="Setting" class="configurator icon-cog"></span>	
				    </aui:column>										    			
				</aui:layout>
			</aui:fieldset>
		</div>
		
		<div class="time-zone-content">
			<%
			Set keys_ = mapTimeZone.keySet();
			for (Iterator i = keys_.iterator(); i.hasNext();) {
				String key = (String) i.next();
				String place = key;
				String timeZoneArea = place;
				
				String date_time = (String) mapTimeZone.get(key);
				StringTokenizer tokens = new StringTokenizer(date_time,"|");
				String day = ""; 
				String date = "";
				String time = "";
				while(tokens.hasMoreElements()){
					day =  tokens.nextToken();
					date = tokens.nextToken();
					time = tokens.nextToken();
				}
				
				StringTokenizer timeTokens = new StringTokenizer(time,":");
				String hr = "";
				String min = "";
				String notation = "";
				while(timeTokens.hasMoreElements()){
					hr = timeTokens.nextToken().trim();
					min = timeTokens.nextToken().trim();
					notation = timeTokens.nextToken().trim();
				}
				
				com.ktree.timezone.portlet.WorldClock worldClock = new com.ktree.timezone.portlet.WorldClock();
				if(timeZoneArea.contains("@")){
					timeZoneArea = timeZoneArea.substring(0,timeZoneArea.indexOf("@"));	
					String prevTime = worldClock.getPrevTime(timeZoneArea);
					String prevHr = "";
					String prevMin = "";
					String prevNotation = ""; 
					StringTokenizer prevTimeTokens = new StringTokenizer(prevTime,":");
					while(prevTimeTokens.hasMoreElements()){
						prevHr = prevTimeTokens.nextToken().trim();
						prevMin = prevTimeTokens.nextToken().trim();
						prevNotation = prevTimeTokens.nextToken().trim();
					}
					
					String nextTime = worldClock.getNextTime(timeZoneArea);
					String nextHr = "";
					String nextMin = "";
					String nextNotation = "";
					StringTokenizer nextTimeTokens = new StringTokenizer(nextTime,":");
					while(nextTimeTokens.hasMoreElements()){
						nextHr = nextTimeTokens.nextToken().trim();
						nextMin = nextTimeTokens.nextToken().trim();
						nextNotation = nextTimeTokens.nextToken().trim();
					}
				
					String region = timeZoneArea;
					if(timeZoneArea.contains(" ")){
						region = timeZoneArea.replaceAll(" ","_");
					}else if(timeZoneArea.contains("/")){
						region = timeZoneArea.replaceAll("/","_");
					}
					
					String regionName = timeZoneArea; 
					if(timeZoneArea.length()>10){
						timeZoneArea = timeZoneArea.substring(0,9)+"...";
					}
					%>
					<div class="time-zone-area" style="cursor:pointer">
						<table>
							<tr>
								<td width="35%"><span class="place" title="<%=regionName %>"><%=timeZoneArea%></span></td>
								<td width="35%"><span id="<%=region%>_day_header" class="day"><%=day %></span></td>
								<td width="20%"><span id="<%=region%>_header" class="dt"><%=hr+":"+min+" "+notation %></span></td>								
							</tr>
						</table>
					</div>
					<div class="time-zone-area-time" id="<%=region%>" style="display:none">
						<div class="date-container">
							<table>
								<tr>
									<td width="19%"><span class="date-title">Date:</span></td>
									<td width="58%"><span id="<%=region%>_date_bar" class="date"><%=date%></span></td>
									<td width="19%"><span id="<%=region%>_day_bar" class="d"><%=day%></span></td>
								</tr>
							</table>
						</div>
						<table class="time-table"> 
							<tr class="prev">
								<td><span class="hrs">hrs</span><span class="prevHr"><%=prevHr%></span><span class="pre-notation"><%=prevNotation%></span></td>
								<td><span class="mins">min</span><span class="prevmin"><%=prevMin%></span></td>
							</tr>
							<tr class="current">
								<td><span class="hrs">hrs</span><span class="hr"><%=hr%></span><span class="notation_current"><%=notation%></span></td>
								<td><span class="mins">min</span><span class="min"><%=min%></span></td>
							</tr>
							<tr class="next">
								<td><span class="hrs">hrs</span><span class="nextHr"><%=nextHr%></span><span class="next-notation"><%=nextNotation%></span></td>
								<td><span class="mins">min</span><span class="nextMin"><%=nextMin%></span></td>
							</tr>
						</table>
					</div>
					<%
				}else{
					worldClock = new com.ktree.timezone.portlet.WorldClock();
					if(timeZoneArea.contains("@")){
						timeZoneArea = timeZoneArea.substring(0,timeZoneArea.indexOf("@"));	
					}
					String prevTime = worldClock.getPrevTime(timeZoneArea);
					String prevHr = "";
					String prevMin = "";
					String prevNotation = ""; 
					StringTokenizer prevTimeTokens = new StringTokenizer(prevTime,":");
					while(prevTimeTokens.hasMoreElements()){
						prevHr = prevTimeTokens.nextToken().trim();
						prevMin = prevTimeTokens.nextToken().trim();
						prevNotation = prevTimeTokens.nextToken().trim();
					}
					
					String nextTime = worldClock.getNextTime(timeZoneArea);
					String nextHr = "";
					String nextMin = "";
					String nextNotation = "";
					StringTokenizer nextTimeTokens = new StringTokenizer(nextTime,":");
					while(nextTimeTokens.hasMoreElements()){
						nextHr = nextTimeTokens.nextToken().trim();
						nextMin = nextTimeTokens.nextToken().trim();
						nextNotation = nextTimeTokens.nextToken().trim();
					}
					
					String region = timeZoneArea;
					if(timeZoneArea.contains(" ")){
						region = timeZoneArea.replaceAll(" ","_");
					}else if(timeZoneArea.contains("/")){
						region = timeZoneArea.replaceAll("/","_");
					}
					
					String regionName = timeZoneArea; 
					if(timeZoneArea.length()>16){
						timeZoneArea = timeZoneArea.substring(0,15)+"...";
					}
					%>
					<div class="time-zone-area" style="cursor:pointer"> 
						<table>
							<tr>
								<td width="35%"><span class="place" title="<%=regionName %>"><%=timeZoneArea %></span></td>
								<td width="35%"><span id="<%=region%>_day_header" class="day"><%=day %></span></td>
								<td width="20%"><span id="<%=region%>_header" class="dt"><%=hr+":"+min+" "+notation %></span></td>
							</tr>
						</table>
					</div>
					<div class="time-zone-area-time" id="<%=region%>" style="display:none">
						<div class="date-container">
							<table>
								<tr>
									<td width="19%"><span class="date-title">Date:</span></td>
									<td width="58%"><span id="<%=region%>_date_bar" class="date"><%=date%></span></td>
									<td width="19%"><span id="<%=region%>_day_bar" class="d"><%=day%></span></span></td>
								</tr>
							</table>							
						</div>
						<table class="time-table">
							<tr class="prev">
								<td><span class="hrs">hrs</span><span class="prevHr"><%=prevHr%></span><span class="pre-notation"><%=notation%></span></td>
								<td><span class="mins">min</span><span class="prevmin"><%=prevMin%></span></td>
							</tr>
							<tr class="current">
								<td><span class="hrs">hrs</span><span class="hr"><%=hr%></span><span class="notation_current"><%=notation%></span></td>
								<td><span class="mins">min</span><span class="min"><%=min%></span></td>
							</tr>
							<tr class="next">
								<td><span class="hrs">hrs</span><span class="nextHr"><%=nextHr%></span><span class="next-notation"><%=notation%></span></td>
								<td><span class="mins">min</span><span class="nextMin"><%=nextMin%></span></td>
							</tr>
						</table>
					</div>
					<%
				}
			}
			%>
			
		</div>
	</div>
</body>