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
package br.com.sysmap.crux.widgets.client.stackmenu;

import br.com.sysmap.crux.core.client.declarative.TagChild;
import br.com.sysmap.crux.core.client.declarative.TagChildAttributes;
import br.com.sysmap.crux.core.client.declarative.TagChildren;
import br.com.sysmap.crux.core.client.screen.InterfaceConfigException;
import br.com.sysmap.crux.core.client.screen.children.WidgetChildProcessor;
import br.com.sysmap.crux.core.client.screen.parser.CruxMetaDataElement;
import br.com.sysmap.crux.core.rebind.widget.WidgetCreator;
import br.com.sysmap.crux.core.rebind.widget.WidgetCreatorContext;

/**
 * Factory for Stack Menu
 * @author Gesse S. F. Dafe
 */
@br.com.sysmap.crux.core.client.declarative.DeclarativeFactory(id="stackMenu", library="widgets")
public class StackMenuFactory extends WidgetCreator<StackMenu, WidgetCreatorContext>
{
	@Override
	public StackMenu instantiateWidget(CruxMetaDataElement element, String widgetId) throws InterfaceConfigException
	{
		return new StackMenu();
	}
	
	@Override
	@TagChildren({
		@TagChild(StackMenuItemProcessor.class)
	})
	public void processChildren(WidgetCreatorContext context) throws InterfaceConfigException {}

	@TagChildAttributes(tagName="item", minOccurs="0", maxOccurs="unbounded", type=StackMenuItemFactory.class)
	public static class StackMenuItemProcessor extends WidgetChildProcessor<StackMenu, WidgetCreatorContext>
	{
		@Override
		public void processChildren(WidgetCreatorContext context) throws InterfaceConfigException 
		{
			StackMenuItem childWidget = (StackMenuItem)createChildWidget(context.getChildElement());
			StackMenu rootWidget = context.getWidget();
			rootWidget.add(childWidget);
		}
	}
}