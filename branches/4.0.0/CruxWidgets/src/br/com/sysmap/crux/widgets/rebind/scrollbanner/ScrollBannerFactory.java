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
package br.com.sysmap.crux.widgets.rebind.scrollbanner;

import org.json.JSONObject;

import br.com.sysmap.crux.core.client.utils.EscapeUtils;
import br.com.sysmap.crux.core.rebind.CruxGeneratorException;
import br.com.sysmap.crux.core.rebind.screen.widget.WidgetCreator;
import br.com.sysmap.crux.core.rebind.screen.widget.WidgetCreatorContext;
import br.com.sysmap.crux.core.rebind.screen.widget.ViewFactoryCreator.SourcePrinter;
import br.com.sysmap.crux.core.rebind.screen.widget.creator.children.WidgetChildProcessor;
import br.com.sysmap.crux.core.rebind.screen.widget.declarative.DeclarativeFactory;
import br.com.sysmap.crux.core.rebind.screen.widget.declarative.TagAttributeDeclaration;
import br.com.sysmap.crux.core.rebind.screen.widget.declarative.TagAttributesDeclaration;
import br.com.sysmap.crux.core.rebind.screen.widget.declarative.TagChild;
import br.com.sysmap.crux.core.rebind.screen.widget.declarative.TagChildAttributes;
import br.com.sysmap.crux.core.rebind.screen.widget.declarative.TagChildren;
import br.com.sysmap.crux.widgets.client.scrollbanner.ScrollBanner;

/**
 * Factory for Scroll Banner widget
 * @author Gesse S. F. Dafe
 */
@DeclarativeFactory(id="scrollBanner", library="widgets", targetWidget=ScrollBanner.class)
@TagAttributesDeclaration({
	@TagAttributeDeclaration("messageScrollingPeriod")
})
@TagChildren({
	@TagChild(ScrollBannerFactory.MessageProcessor.class)
})
public class ScrollBannerFactory extends WidgetCreator<WidgetCreatorContext>
{
	@Override
	public String instantiateWidget(SourcePrinter out, JSONObject metaElem, String widgetId) throws CruxGeneratorException
	{
		String varName = createVariableName("widget");
		String className = getWidgetClassName();

		String period = metaElem.optString("messageScrollingPeriod");
		if(period != null && period.trim().length() > 0)
		{
			out.println("final "+className + " " + varName+" = new "+className+"("+Integer.parseInt(period)+");");
		}
		else
		{
			out.println("final "+className + " " + varName+" = new "+className+"();");
		}
		return varName;
	}

	@TagChildAttributes(tagName="message", minOccurs="0", maxOccurs="unbounded", type=String.class)
	public static class MessageProcessor extends WidgetChildProcessor<WidgetCreatorContext>
	{
		@Override
		public void processChildren(SourcePrinter out, WidgetCreatorContext context) throws CruxGeneratorException
		{
			String message = getWidgetCreator().getDeclaredMessage(ensureTextChild(context.getChildElement(), true));
			String rootWidget = context.getWidget();
			out.println(rootWidget+".addMessage("+message+");");
		}
	}
	
	@Override
    public WidgetCreatorContext instantiateContext()
    {
	    return new WidgetCreatorContext();
    }
}