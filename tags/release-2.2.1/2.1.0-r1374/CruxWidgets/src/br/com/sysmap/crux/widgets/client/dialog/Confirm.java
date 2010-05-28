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
package br.com.sysmap.crux.widgets.client.dialog;

import br.com.sysmap.crux.widgets.client.event.dialog.CancelEvent;
import br.com.sysmap.crux.widgets.client.event.dialog.CancelHandler;
import br.com.sysmap.crux.widgets.client.event.dialog.HasCancelHandlers;
import br.com.sysmap.crux.widgets.client.event.dialog.HasOkHandlers;
import br.com.sysmap.crux.widgets.client.event.dialog.OkEvent;
import br.com.sysmap.crux.widgets.client.event.dialog.OkHandler;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.Widget;


/**
 * @author Thiago da Rosa de Bustamante <code>tr_bustamante@yahoo.com.br</code>
 */
public class Confirm extends Widget implements HasCancelHandlers, HasOkHandlers, HasAnimation
{
	public static final String DEFAULT_STYLE_NAME = "crux-Confirm" ;
	private static CruxInternalConfirmController confirmController = null;
	private String title;
	private String message;
	private String okButtonText;
	private String cancelButtonText;
	private String styleName;
	private boolean animationEnabled;
	protected static Confirm confirm;
	
	/**
	 * 
	 */
	public Confirm()
	{
		setElement(DOM.createSpan());
	}

	/**
	 * 
	 */
	public HandlerRegistration addCancelHandler(CancelHandler handler)
	{
		return addHandler(handler, CancelEvent.getType());
	}

	/**
	 * 
	 */
	public HandlerRegistration addOkHandler(OkHandler handler)
	{
		return addHandler(handler, OkEvent.getType());
	}	
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public String getStyleName()
	{
		return styleName;
	}

	public void setStyleName(String styleName)
	{
		this.styleName = styleName;
	}
	
	public boolean isAnimationEnabled()
	{
		return animationEnabled;
	}

	public void setAnimationEnabled(boolean animationEnabled)
	{
		this.animationEnabled = animationEnabled;
	}	

	/**
	 * 
	 */
	public void show()
	{
		if (confirmController == null)
		{
			confirmController = new CruxInternalConfirmController(); 
		}
		confirm = this;
		confirmController.showConfirm(new ConfirmData(title, message, okButtonText, cancelButtonText, styleName!=null?styleName:DEFAULT_STYLE_NAME, animationEnabled));
	}
	
	/**
	 * 
	 * @param title
	 * @param message
	 * @param okCall
	 * @param cancelCall
	 */
	public static void show(String title, String message, OkHandler okHandler, CancelHandler cancelHandler)
	{
		show(title, message, okHandler, cancelHandler, DEFAULT_STYLE_NAME, false);
	}
	
	/**
	 * @param title
	 * @param message
	 * @param okButtonText
	 * @param cancelButtonText
	 * @param okHandler
	 * @param cancelHandler
	 */
	public static void show(String title, String message, String okButtonText, String cancelButtonText, OkHandler okHandler, CancelHandler cancelHandler)
	{
		show(title, message, okButtonText, cancelButtonText, okHandler, cancelHandler, null, false);
	}
	
	/**
	 * @param title
	 * @param message
	 * @param okHandler
	 * @param cancelHandler
	 * @param styleName
	 * @param animationEnabled
	 */
	public static void show(String title, String message, OkHandler okHandler, CancelHandler cancelHandler, String styleName, boolean animationEnabled)
	{
		show(title, message, null, null, okHandler, cancelHandler, styleName, animationEnabled);
	}
	
	/**
	 * 
	 * @param title
	 * @param message
	 * @param okCall
	 * @param cancelCall
	 * @param styleName
	 */
	public static void show(String title, String message, String okButtonText, String cancelButtonText, OkHandler okHandler, CancelHandler cancelHandler, String styleName, boolean animationEnabled)
	{
		Confirm confirm = new Confirm(); 
		confirm.setTitle(title);
		confirm.setMessage(message);
		confirm.setOkButtonText(okButtonText);
		confirm.setCancelButtonText(cancelButtonText);
		confirm.setStyleName(styleName);
		confirm.setAnimationEnabled(animationEnabled);
		if (okHandler != null)
		{
			confirm.addOkHandler(okHandler);
		}
		if (cancelHandler != null)
		{
			confirm.addCancelHandler(cancelHandler);
		}
		confirm.show();
	}

	/**
	 * @return the okButtonText
	 */
	public String getOkButtonText()
	{
		return okButtonText;
	}

	/**
	 * @param okButtonText the okButtonText to set
	 */
	public void setOkButtonText(String okButtonText)
	{
		this.okButtonText = okButtonText;
	}

	/**
	 * @return the cancelButtonText
	 */
	public String getCancelButtonText()
	{
		return cancelButtonText;
	}

	/**
	 * @param cancelButtonText the cancelButtonText to set
	 */
	public void setCancelButtonText(String cancelButtonText)
	{
		this.cancelButtonText = cancelButtonText;
	}
}
