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
package br.com.sysmap.crux.gwt.client;

import java.util.List;

import br.com.sysmap.crux.core.client.declarative.DeclarativeFactory;
import br.com.sysmap.crux.core.client.declarative.TagAttributeDeclaration;
import br.com.sysmap.crux.core.client.declarative.TagAttributesDeclaration;
import br.com.sysmap.crux.core.client.declarative.TagChild;
import br.com.sysmap.crux.core.client.declarative.TagChildAttributes;
import br.com.sysmap.crux.core.client.declarative.TagChildren;
import br.com.sysmap.crux.core.client.screen.InterfaceConfigException;
import br.com.sysmap.crux.core.client.screen.children.WidgetChildProcessor;
import br.com.sysmap.crux.core.client.screen.children.WidgetChildProcessorContext;
import br.com.sysmap.crux.core.client.screen.children.WidgetChildProcessor.AnyWidget;
import br.com.sysmap.crux.core.client.screen.parser.CruxMetaDataElement;
import br.com.sysmap.crux.core.client.utils.StringUtils;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Thiago da Rosa de Bustamante
 *
 */
@DeclarativeFactory(id="layoutPanel", library="gwt")
public class LayoutPanelFactory extends AbstractLayoutPanelFactory<LayoutPanel>
{
	@Override
	public LayoutPanel instantiateWidget(CruxMetaDataElement element, String widgetId)
	{
		return new LayoutPanel();
	}

	@Override
	@TagChildren({
		@TagChild(LayoutPanelProcessor.class)
	})		
	public void processChildren(WidgetFactoryContext context) throws InterfaceConfigException {}
	
	@TagChildAttributes(minOccurs="0", maxOccurs="unbounded", tagName="layer")
	public static class LayoutPanelProcessor extends WidgetChildProcessor<LayoutPanel> 
	{
		@Override
		@TagAttributesDeclaration({
			@TagAttributeDeclaration(value="left", type=Double.class),
			@TagAttributeDeclaration(value="right", type=Double.class),
			@TagAttributeDeclaration(value="top", type=Double.class),
			@TagAttributeDeclaration(value="bottom", type=Double.class),
			@TagAttributeDeclaration(value="width", type=Double.class),
			@TagAttributeDeclaration(value="height", type=Double.class),
			@TagAttributeDeclaration(value="animationStartLeft", type=Double.class),
			@TagAttributeDeclaration(value="animationStartRight", type=Double.class),
			@TagAttributeDeclaration(value="animationStartTop", type=Double.class),
			@TagAttributeDeclaration(value="animationStartBottom", type=Double.class),
			@TagAttributeDeclaration(value="animationStartWidth", type=Double.class),
			@TagAttributeDeclaration(value="animationStartHeight", type=Double.class),
			@TagAttributeDeclaration(value="horizontalPosition", type=Alignment.class),
			@TagAttributeDeclaration(value="verticalPosition", type=Alignment.class),
			@TagAttributeDeclaration(value="leftUnit", type=Unit.class, defaultValue="PX"),
			@TagAttributeDeclaration(value="rightUnit", type=Unit.class, defaultValue="PX"),
			@TagAttributeDeclaration(value="topUnit", type=Unit.class, defaultValue="PX"),
			@TagAttributeDeclaration(value="bottomUnit", type=Unit.class, defaultValue="PX"),
			@TagAttributeDeclaration(value="widthUnit", type=Unit.class, defaultValue="PX"),
			@TagAttributeDeclaration(value="heightUnit", type=Unit.class, defaultValue="PX")
		})
		@TagChildren({
			@TagChild(LayoutPanelWidgetProcessor.class)
		})		
		public void processChildren(WidgetChildProcessorContext context) throws InterfaceConfigException 
		{
			context.setAttribute("left", context.readChildProperty("left"));
			context.setAttribute("right", context.readChildProperty("right"));
			context.setAttribute("top", context.readChildProperty("top"));
			context.setAttribute("bottom", context.readChildProperty("bottom"));
			context.setAttribute("width", context.readChildProperty("width"));
			context.setAttribute("height", context.readChildProperty("height"));
			context.setAttribute("animationStartLeft", context.readChildProperty("animationStartLeft"));
			context.setAttribute("animationStartRight", context.readChildProperty("animationStartRight"));
			context.setAttribute("animationStartTop", context.readChildProperty("animationStartTop"));
			context.setAttribute("animationStartBottom", context.readChildProperty("animationStartBottom"));
			context.setAttribute("animationStartWidth", context.readChildProperty("animationStartWidth"));
			context.setAttribute("animationStartHeight", context.readChildProperty("animationStartHeight"));
			context.setAttribute("horizontalPosition", context.readChildProperty("horizontalPosition"));
			context.setAttribute("verticalPosition", context.readChildProperty("verticalPosition"));
			context.setAttribute("leftUnit", context.readChildProperty("leftUnit"));
			context.setAttribute("rightUnit", context.readChildProperty("rightUnit"));
			context.setAttribute("topUnit", context.readChildProperty("topUnit"));
			context.setAttribute("bottomUnit", context.readChildProperty("bottomUnit"));
			context.setAttribute("widthUnit", context.readChildProperty("widthUnit"));
			context.setAttribute("heightUnit", context.readChildProperty("heightUnit"));
		}
	}
	
	@TagChildAttributes(type=AnyWidget.class)
	public static class LayoutPanelWidgetProcessor extends WidgetChildProcessor<LayoutPanel> 
	{
		@Override
		public void processChildren(WidgetChildProcessorContext context) throws InterfaceConfigException 
		{
			Widget childWidget = createChildWidget(context.getChildElement());
			LayoutPanel rootWidget = context.getRootWidget();
			rootWidget.add(childWidget);
			
			String left = (String) context.getAttribute("left");
			String right = (String) context.getAttribute("right");
			String top = (String) context.getAttribute("top");
			String bottom = (String) context.getAttribute("bottom");
			String width = (String) context.getAttribute("width");
			String height = (String) context.getAttribute("height");
			String horizontalPosition = (String) context.getAttribute("horizontalPosition");
			String verticalPosition = (String) context.getAttribute("verticalPosition");
			Unit leftUnit = getUnit((String) context.getAttribute("leftUnit"));
			Unit rightUnit = getUnit((String) context.getAttribute("rightUnit"));
			Unit topUnit = getUnit((String) context.getAttribute("topUnit"));
			Unit bottomUnit = getUnit((String) context.getAttribute("bottomUnit"));
			Unit widthUnit = getUnit((String) context.getAttribute("widthUnit"));
			Unit heightUnit = getUnit((String) context.getAttribute("heightUnit"));
			
			Integer animationDuration = (Integer) context.getAttribute("animationDuration");
			if (animationDuration != null)
			{
				processAnimation(context, childWidget, left, right, top, bottom, width, height, leftUnit, rightUnit, topUnit, bottomUnit, widthUnit, heightUnit);
			}
			else
			{
				setConstraints(rootWidget, childWidget, left, right, top, bottom, width, height, leftUnit, rightUnit, topUnit, bottomUnit, widthUnit, heightUnit);
			}
			
			if (!StringUtils.isEmpty(horizontalPosition))
			{
				rootWidget.setWidgetHorizontalPosition(childWidget, Alignment.valueOf(horizontalPosition));
			}
			if (!StringUtils.isEmpty(verticalPosition))
			{
				rootWidget.setWidgetVerticalPosition(childWidget, Alignment.valueOf(verticalPosition));
			}
		}

		@SuppressWarnings("unchecked")
		private void processAnimation(final WidgetChildProcessorContext context, final Widget childWidget, 
				final String left, final String right, final String top, final String bottom, final String width, final String height,
				final Unit leftUnit, final Unit rightUnit, final Unit topUnit, final Unit bottomUnit, final Unit widthUnit,
				final Unit heightUnit)
		{
			String animationStartLeft = (String) context.getAttribute("animationStartLeft");
			String animationStartRight = (String) context.getAttribute("animationStartRight");
			String animationStartTop = (String) context.getAttribute("animationStartTop");
			String animationStartBottom = (String) context.getAttribute("animationStartBottom");
			String animationStartWidth = (String) context.getAttribute("animationStartWidth");
			String animationStartHeight = (String) context.getAttribute("animationStartHeight");
			if (!StringUtils.isEmpty(animationStartLeft) || !StringUtils.isEmpty(animationStartRight) || !StringUtils.isEmpty(animationStartTop)
					|| !StringUtils.isEmpty(animationStartBottom) || !StringUtils.isEmpty(animationStartWidth) || !StringUtils.isEmpty(animationStartHeight))
			{
				final LayoutPanel rootWidget = context.getRootWidget();
				setConstraints(rootWidget, childWidget, animationStartLeft, animationStartRight, animationStartTop, 
						animationStartBottom, animationStartWidth, animationStartHeight, 
						leftUnit, rightUnit, topUnit, bottomUnit, widthUnit, heightUnit);
				List<Command> animationConstraints = (List<Command>) context.getAttribute("animationCommands");
				animationConstraints.add(new Command(){
					public void execute()
					{
						setConstraints(rootWidget, childWidget, left, right, top, bottom, width, height, 
								leftUnit, rightUnit, topUnit, bottomUnit, widthUnit, heightUnit);
					}
				});
			
			}
		}

		private void setConstraints(LayoutPanel rootWidget, Widget childWidget, String left, String right, String top, String bottom, String width, String height,
				Unit leftUnit, Unit rightUnit, Unit topUnit, Unit bottomUnit, Unit widthUnit, Unit heightUnit)
		{
			if (!StringUtils.isEmpty(left) && !StringUtils.isEmpty(right))
			{
				rootWidget.setWidgetLeftRight(childWidget, Double.parseDouble(left), leftUnit, Double.parseDouble(right), rightUnit);
			}
			else if (!StringUtils.isEmpty(left) && !StringUtils.isEmpty(width))
			{
				rootWidget.setWidgetLeftWidth(childWidget, Double.parseDouble(left), leftUnit, Double.parseDouble(width), widthUnit);
			}
			else if (!StringUtils.isEmpty(right) && !StringUtils.isEmpty(width))
			{
				rootWidget.setWidgetRightWidth(childWidget, Double.parseDouble(right), rightUnit, Double.parseDouble(width), widthUnit);
			}
			
			if (!StringUtils.isEmpty(top) && !StringUtils.isEmpty(bottom))
			{
				rootWidget.setWidgetTopBottom(childWidget, Double.parseDouble(top), topUnit, Double.parseDouble(bottom), bottomUnit);
			}
			else if (!StringUtils.isEmpty(top) && !StringUtils.isEmpty(height))
			{
				rootWidget.setWidgetTopHeight(childWidget, Double.parseDouble(top), topUnit, Double.parseDouble(height), heightUnit);
			}
			else if (!StringUtils.isEmpty(bottom) && !StringUtils.isEmpty(height))
			{
				rootWidget.setWidgetBottomHeight(childWidget, Double.parseDouble(bottom), bottomUnit, Double.parseDouble(height), heightUnit);
			}
		}
	}
}
