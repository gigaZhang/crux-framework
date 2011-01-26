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
package br.com.sysmap.crux.widgets.client.select;

import br.com.sysmap.crux.core.client.declarative.DeclarativeFactory;
import br.com.sysmap.crux.core.client.declarative.TagChild;
import br.com.sysmap.crux.core.client.declarative.TagChildren;
import br.com.sysmap.crux.core.client.screen.InterfaceConfigException;
import br.com.sysmap.crux.core.client.screen.parser.CruxMetaDataElement;
import br.com.sysmap.crux.gwt.rebind.AbstractListBoxFactory;
import br.com.sysmap.crux.gwt.rebind.ListBoxContext;

/**
 * Represents a List Box component
 * @author Thiago Bustamante
 */
@DeclarativeFactory(id="singleSelect", library="widgets")
public class SingleSelectFactory extends AbstractListBoxFactory<SingleSelect>
{
	@Override
	@TagChildren({
		@TagChild(SelectItemsProcessor.class)
	})
	public void processChildren(ListBoxContext context) throws InterfaceConfigException {}	
	
	@Override
	public SingleSelect instantiateWidget(CruxMetaDataElement element, String widgetId) throws InterfaceConfigException
	{
		return new SingleSelect();
	}
	
	public static class SelectItemsProcessor extends ItemsProcessor<SingleSelect>
	{		
	}
}