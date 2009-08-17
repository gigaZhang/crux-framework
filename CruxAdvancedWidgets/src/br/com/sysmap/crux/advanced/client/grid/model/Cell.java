package br.com.sysmap.crux.advanced.client.grid.model;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class Cell extends Composite {

	private SimplePanel basePanel;
	
	protected Cell()
	{
		basePanel = new SimplePanel();
		initWidget(basePanel);
		basePanel.getElement().getStyle().setProperty("whiteSpace", "nowrap");
		basePanel.getElement().getStyle().setProperty("overflow", "hidden");
	}
	
	protected Cell(Widget widget)
	{
		this();
		this.basePanel.add(widget);		
	}
	
	protected void setCellWidget(Widget widget)
	{
		basePanel.add(widget);
	}
	
	protected Widget getCellWidget()
	{
		return basePanel.getWidget();
	}
}