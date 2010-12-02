/*
 * Copyright 2009 Sysmap Solutions Software e Consultoria Ltda.
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
package br.com.sysmap.crux.widgets.client.dynatabs;

import br.com.sysmap.crux.core.client.Crux;
import br.com.sysmap.crux.core.client.declarative.DeclarativeFactory;
import br.com.sysmap.crux.core.client.declarative.TagAttributeDeclaration;
import br.com.sysmap.crux.core.client.declarative.TagAttributesDeclaration;
import br.com.sysmap.crux.core.client.declarative.TagChild;
import br.com.sysmap.crux.core.client.declarative.TagChildAttributes;
import br.com.sysmap.crux.core.client.declarative.TagChildren;
import br.com.sysmap.crux.core.client.declarative.TagEventDeclaration;
import br.com.sysmap.crux.core.client.declarative.TagEventsDeclaration;
import br.com.sysmap.crux.core.client.screen.InterfaceConfigException;
import br.com.sysmap.crux.core.client.screen.ScreenFactory;
import br.com.sysmap.crux.core.client.screen.WidgetFactory;
import br.com.sysmap.crux.core.client.screen.WidgetFactoryContext;
import br.com.sysmap.crux.core.client.screen.children.WidgetChildProcessor;
import br.com.sysmap.crux.core.client.screen.parser.CruxMetaDataElement;
import br.com.sysmap.crux.widgets.client.event.focusblur.BeforeBlurEvtBind;
import br.com.sysmap.crux.widgets.client.event.focusblur.BeforeFocusEvtBind;
import br.com.sysmap.crux.widgets.client.event.openclose.BeforeCloseEvtBind;

/**
 * Factory for Decorated Button widget
 * @author Gesse S. F. Dafe
 */
@DeclarativeFactory(id="dynaTabs", library="widgets")
public class DynaTabsFactory extends WidgetFactory<DynaTabs, WidgetFactoryContext>
{
	@Override
	public DynaTabs instantiateWidget(CruxMetaDataElement element, String widgetId) throws InterfaceConfigException
	{
		return new DynaTabs();
	}

	@Override
	@TagChildren({
		@TagChild(DynaTabProcessor.class)
	})
	public void processChildren(WidgetFactoryContext context) throws InterfaceConfigException {}
	
	@TagChildAttributes(tagName="tab", minOccurs="0", maxOccurs="unbounded")
	public static class DynaTabProcessor extends WidgetChildProcessor<DynaTabs, WidgetFactoryContext>
	{
		protected BeforeFocusEvtBind beforeFocusEvtBind = new BeforeFocusEvtBind();
		protected BeforeBlurEvtBind beforeBlurEvtBind = new BeforeBlurEvtBind();
		protected BeforeCloseEvtBind beforeCloseEvtBind = new BeforeCloseEvtBind();

		@Override
		@TagAttributesDeclaration({
			@TagAttributeDeclaration(value="id", required=true),
			@TagAttributeDeclaration(value="url", required=true),
			@TagAttributeDeclaration("label"),
			@TagAttributeDeclaration(value="closeable", type=Boolean.class)
		})
		@TagEventsDeclaration({
			@TagEventDeclaration("onBeforeFocus"),
			@TagEventDeclaration("onBeforeBlur"),
			@TagEventDeclaration("onBeforeClose")
		})
		public void processChildren(WidgetFactoryContext context) throws InterfaceConfigException
		{
			CruxMetaDataElement childElement = context.getChildElement();
			assert(childElement.containsKey("id")):Crux.getMessages().screenFactoryWidgetIdRequired();
			String id = childElement.getProperty("id");
			String label = context.readChildProperty("label");
			label = (label != null && label.length() > 0) ? ScreenFactory.getInstance().getDeclaredMessage(label) : "";
			String url = context.readChildProperty("url");
						
			boolean closeable = true;
			String strCloseable = context.readChildProperty("closeable");
			if(strCloseable != null && strCloseable.trim().length() > 0)
			{
				closeable = Boolean.parseBoolean(strCloseable);
			}
			
			DynaTabs rootWidget = context.getWidget();
			Tab tab = rootWidget.openTab(id, label, url, closeable, false);
			
			beforeFocusEvtBind.bindEvent(childElement, tab);			
			beforeBlurEvtBind.bindEvent(childElement, tab);			
			beforeCloseEvtBind.bindEvent(childElement, tab);			
		}
	}
}