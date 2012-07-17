/*
 * Copyright 2011 cruxframework.org.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.cruxframework.crux.core.rebind.screen;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.cruxframework.crux.core.client.utils.StringUtils;
import org.cruxframework.crux.core.rebind.screen.widget.ViewFactoryCreator;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gwt.dev.util.collect.HashSet;

/**
 * Represents a Crux View at the application's server side. Used for GWT Generators.
 * 
 * @author Thiago Bustamante
 */
public class View 
{
	protected String id;
	protected String title;
	protected String fragment;
	protected Map<String, Widget> widgets = new HashMap<String, Widget>();
	protected Set<String> widgetTypes = new HashSet<String>();
	protected Map<String, Event> events = new HashMap<String, Event>();
	protected List<String> controllers = new ArrayList<String>();
	protected List<String> serializers = new ArrayList<String>();
	protected List<String> formatters = new ArrayList<String>();
	protected List<String> dataSources = new ArrayList<String>();
	protected boolean toucheEventAdaptersEnabled = false;
	protected List<String> views = new ArrayList<String>();
	private ViewFactoryCreator factory = null;

	private final JSONArray elements;
	private final JSONObject lazyDependencies;
	private final boolean rootView;
	private final String html;
	
	public View(String id, JSONArray elements, JSONObject lazyDependencies, String html, boolean rootView) 
	{
		this.id = id;
		this.elements = elements;
		this.lazyDependencies = lazyDependencies;
		this.html = html;
		this.rootView = rootView;
	}

	/**
	 * Return DeclarativeFactory associated with the given id
	 * @param widgetId
	 * @return
	 */
	public Widget getWidget(String widgetId)
	{
		if (widgetId == null) return null;
		return widgets.get(widgetId);
	}

	/**
	 * Return a Set containing all types of widgets found on this view
	 * @return
	 */
	public Set<String> getWidgetTypesIncluded()
	{
		return widgetTypes;
	}
	
	/**
	 * Iterate over widgets
	 * @return
	 */
	public Iterator<Widget> iterateWidgets() 
	{
		return widgets.values().iterator();
	}

	/**
	 * Add a new widget to view
	 * @param widget
	 */
	protected void addWidget(Widget widget)
	{
		if (widget != null)
		{
			widgets.put(widget.getId(), widget);
			if (!widgetTypes.contains(widget.getType()))
			{
				widgetTypes.add(widget.getType());
			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean isTouchEventAdaptersEnabled()
	{
		return this.toucheEventAdaptersEnabled;
	}
	
	/**
	 * 
	 * @param toucheEventAdaptersEnabled
	 */
	public void setToucheEventAdaptersEnabled(boolean toucheEventAdaptersEnabled)
	{
		this.toucheEventAdaptersEnabled = toucheEventAdaptersEnabled;
	}
	
	/**
	 * Return view identifier
	 * @return
	 */
	public String getId() 
	{
		return id;
	}
	
	/**
	 * Add a new event to view
	 * @param event
	 */
	protected void addEvent(Event event)
	{
		if (event != null)
		{
			events.put(event.getId(), event);
		}
	}
	
	/**
	 * Return a event associated with the given id
	 * @param evtId
	 * @return
	 */
	public Event getEvent(String evtId)
	{
		return events.get(evtId);
	}
	
	/**
	 * Iterate over view events
	 * @return
	 */
	public Iterator<Event> iterateEvents()
	{
		return events.values().iterator();
	}

	/**
	 * Import a controller into view
	 * @param event
	 */
	protected void addController(String controller)
	{
		if (!StringUtils.isEmpty(controller))
		{
			controllers.add(controller);
		}
	}
	
	/**
	 * Iterate over view controllers
	 * @return
	 */
	public Iterator<String> iterateControllers()
	{
		return controllers.iterator();
	}

	/**
	 * 
	 * @param controller
	 * @return
	 */
	public boolean useController(String controller)
	{
		return controllers.contains(controller);
	}
	
	/**
	 * 
	 * @param datasource
	 * @return
	 */
	public boolean useDataSource(String datasource)
	{
		return dataSources.contains(datasource);
	}
	
	/**
	 * 
	 * @param formatter
	 * @return
	 */
	public boolean useFormatter(String formatter)
	{
		return formatters.contains(formatter);
	}
	
	/**
	 * Import a serializer for a CruxSerializable into view
	 * @param event
	 */
	protected void addSerializer(String serializer)
	{
		if (!StringUtils.isEmpty(serializer))
		{
			serializers.add(serializer);
		}
	}
	
	/**
	 * Iterate over view serializers
	 * @return
	 */
	public Iterator<String> iterateSerializers()
	{
		return serializers.iterator();
	}
	
	/**
	 * Import a formatter into view
	 * @param event
	 */
	protected void addFormatter(String formatter)
	{
		if (!StringUtils.isEmpty(formatter))
		{
			formatters.add(formatter);
		}
	}
	
	/**
	 * Iterate over view formatters
	 * @return
	 */
	public Iterator<String> iterateFormatters()
	{
		return formatters.iterator();
	}	

	/**
	 * Import a dataSource into view
	 * @param event
	 */
	protected void addDataSource(String dataSource)
	{
		if (!StringUtils.isEmpty(dataSource))
		{
			dataSources.add(dataSource);
		}
	}
	
	/**
	 * Iterate over view dataSources
	 * @return
	 */
	public Iterator<String> iterateDataSources()
	{
		return dataSources.iterator();
	}

	/**
	 * Return the elements metadata
	 * @return
	 */
	public JSONArray getElements()
    {
    	return elements;
    }
	
	/**
	 * Return the lazy dependencies metadata
	 * @return
	 */
	public JSONObject getLazyDependencies()
	{
		return lazyDependencies;
	}

	/**
	 * 
	 * @return
	 */
	public ViewFactoryCreator getFactory() 
	{
		return factory;
	}

	/**
	 * 
	 * @param factory
	 */
	public void setFactory(ViewFactoryCreator factory) 
	{
		this.factory = factory;
	}

	/**
	 * Fragment name, used for code spliting
	 * @return
	 */
	public String getFragment()
    {
	    return this.fragment;
    }

	/**
	 * Fragment name, used for code spliting
	 * @param fragment
	 */
	protected void setFragment(String fragment)
    {
    	this.fragment = fragment;
    }

	/**
	 * Return the view title
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the view title
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Import a view into screen
	 * @param event
	 */
	protected void addView(String view)
	{
		if (!StringUtils.isEmpty(view))
		{
			views.add(view); 
		}
	}
	
	/**
	 * Iterate over screen views
	 * @return
	 */
	public Iterator<String> iterateViews()
	{
		return views.iterator();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isRootView()
	{
		return rootView;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getHtml()
	{
		return html;
	}
}
