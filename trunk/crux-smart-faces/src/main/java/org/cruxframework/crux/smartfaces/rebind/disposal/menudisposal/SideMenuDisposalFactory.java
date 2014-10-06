/*
 * Copyright 2014 cruxframework.org.
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
package org.cruxframework.crux.smartfaces.rebind.disposal.menudisposal;

import org.cruxframework.crux.core.rebind.AbstractProxyCreator.SourcePrinter;
import org.cruxframework.crux.core.rebind.CruxGeneratorException;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.DeclarativeFactory;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagAttributeDeclaration;
import org.cruxframework.crux.core.rebind.screen.widget.declarative.TagAttributesDeclaration;
import org.cruxframework.crux.smartfaces.client.disposal.menudisposal.SideMenuDisposal;
import org.cruxframework.crux.smartfaces.client.disposal.menudisposal.SideMenuDisposal.MenuPosition;
import org.cruxframework.crux.smartfaces.client.menu.Type.LargeType;
import org.cruxframework.crux.smartfaces.rebind.Constants;


@DeclarativeFactory(library=Constants.LIBRARY_NAME,id="sideMenuDisposal",targetWidget=SideMenuDisposal.class,description="A component to define the page's layout. It contains a header, a interactive menu, a content panel and a footer.")
@TagAttributesDeclaration({
	@TagAttributeDeclaration(value="menuPositioning",type=MenuPosition.class,defaultValue="LEFT"),
})
public class SideMenuDisposalFactory extends AbstractMenuDisposalFactory 
{
	public void instantiateWidget(SourcePrinter out, DisposalLayoutContext context) throws CruxGeneratorException {
		super.instantiateWidget(out, context);
		
		String menuPositioning = context.readChildProperty("menuPositioning");
    	if(menuPositioning.isEmpty())
    	{
    		menuPositioning = MenuPosition.LEFT.toString();
    	}
    	
    	out.println(context.getWidget()+".setMenuPositioning("+MenuPosition.class.getCanonicalName()+"."+menuPositioning+");");
	}

	@Override
	String getDefaultMenuType()
	{
		return LargeType.VERTICAL_DROPDOWN.name();
	}
}
