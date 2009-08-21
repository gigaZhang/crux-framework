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
package br.com.sysmap.crux.advanced.client.event.row;

import br.com.sysmap.crux.advanced.client.grid.model.Row;

import com.google.gwt.event.shared.GwtEvent;


/**
 * TODO - Gess� - Comment this
 * @author Gess� S. F. Daf� - <code>gessedafe@gmail.com</code>
 */
public class RowRenderEvent extends GwtEvent<RowRenderHandler>{

	private static Type<RowRenderHandler> TYPE = new Type<RowRenderHandler>();
	private HasRowRenderHandlers source;
	private Row row;

	/**
	 * 
	 */
	public RowRenderEvent(HasRowRenderHandlers source, Row row)
	{
		this.source = source;
		this.row = row;
	}

	/**
	 * @return the source
	 */
	public HasRowRenderHandlers getSource()
	{
		return source;
	}
	
	/**
	 * @return
	 */
	public static Type<RowRenderHandler> getType()
	{
		return TYPE;
	}

	@Override
	protected void dispatch(RowRenderHandler handler)
	{
		handler.onRenderRow(this);
	}

	@Override
	public Type<RowRenderHandler> getAssociatedType()
	{
		return TYPE;
	}
	
	public static RowRenderEvent fire(HasRowRenderHandlers source, Row row)
	{
		RowRenderEvent event = new RowRenderEvent(source, row);
		source.fireEvent(event);
		return event;
	}

	/**
	 * @return the row
	 */
	public Row getRow()
	{
		return row;
	}
}