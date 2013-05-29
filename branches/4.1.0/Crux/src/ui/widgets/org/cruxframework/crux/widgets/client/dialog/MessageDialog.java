/*
 * Copyright 2013 cruxframework.org.
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
package org.cruxframework.crux.widgets.client.dialog;

import org.cruxframework.crux.core.client.Crux;
import org.cruxframework.crux.widgets.client.WidgetMessages;
import org.cruxframework.crux.widgets.client.WidgetMsgFactory;
import org.cruxframework.crux.widgets.client.event.HasOkHandlers;
import org.cruxframework.crux.widgets.client.event.OkEvent;
import org.cruxframework.crux.widgets.client.event.OkHandler;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.HasAnimation;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple message dialog box
 * @author Thiago da Rosa de Bustamante
 *
 */
public class MessageDialog  implements HasOkHandlers, HasAnimation, IsWidget
{
	private static final String DEFAULT_STYLE_NAME = "crux-MessageDialog";
	private DialogBox dialogBox;
	private DockPanel messagePanel;
	private Label messageLabel;
	private Button okButton;
	protected WidgetMessages messages = WidgetMsgFactory.getMessages();

	/**
	 * Constructor 
	 */
	public MessageDialog()
	{
		dialogBox = new DialogBox(false, true);

		messagePanel = new DockPanel();
		messageLabel = createMessageLabel();
		messagePanel.add(messageLabel, DockPanel.CENTER);

		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSpacing(10);
		okButton = createOkButton();
		horizontalPanel.add(okButton);

		messagePanel.add(horizontalPanel, DockPanel.SOUTH);
		messagePanel.setCellHorizontalAlignment(horizontalPanel, HasHorizontalAlignment.ALIGN_CENTER);

		dialogBox.add(messagePanel);
		messagePanel.getElement().getParentElement().setAttribute("align", "center");

		setStyleName(DEFAULT_STYLE_NAME);
    }

	/**
	 * Get the dialog box title
	 * @return
	 */
	public String getDialogTitle()
	{
		return dialogBox.getText();
	}

	/**
	 * Set the dialog box title
	 * @param title
	 */
	public void setDialogTitle(String title)
	{
		dialogBox.setText(title);
	}
	
	/**
	 * @see com.google.gwt.user.client.ui.HasAnimation#isAnimationEnabled()
	 */
	public boolean isAnimationEnabled()
	{
		return dialogBox.isAnimationEnabled();
	}

	/**
	 * Gets the message to be displayed to the user
	 * @return the message
	 */
	public String getMessage()
	{
		return messageLabel.getText();
	}

	/**
	 * Sets the message to be displayed to the user
	 * @param message
	 */
	public void setMessage(String message)
	{
		messageLabel.setText(message);
	}

	
	@Override
    public Widget asWidget()
    {
	    return dialogBox;
    }

	/**
	 * 
	 * @param styleName
	 */
	public void setStyleName(String styleName)
	{
		dialogBox.setStyleName(styleName);
	}
	
	/**
	 * 
	 * @param width
	 */
	public void setWidth(String width)
	{
		dialogBox.setWidth(width);
	}

	/**
	 * 
	 * @param height
	 */
	public void setHeight(String height)
	{
		dialogBox.setHeight(height);
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title)
	{
		dialogBox.setTitle(title);
	}

	/**
	 * 
	 * @return
	 */
	public String getTitle()
	{
		return dialogBox.getTitle();
	}

	/**
	 * @see com.google.gwt.user.client.ui.HasAnimation#setAnimationEnabled(boolean)
	 */
	public void setAnimationEnabled(boolean animationEnabled)
	{
		dialogBox.setAnimationEnabled(animationEnabled);
	}	

	/**
	 * Adds a handler for the OK button click event
	 */
	public HandlerRegistration addOkHandler(OkHandler handler)
	{
		return dialogBox.addHandler(handler, OkEvent.getType());
	}	

	/**
	 * Show message dilaog. The dialog is centered and the screen is blocked for edition
	 */
	public void show()
	{
		try
		{
			dialogBox.center();
			dialogBox.show();
			okButton.setFocus(true);
		}
		catch (Exception e)
		{
			Crux.getErrorHandler().handleError(e);
		}
	}

	/**
	 * Hides the message dialog
	 */
	public void hide()
	{
		dialogBox.hide();
	}
	
	
	/**
	 * Shows a message dialog
	 * @param title the text to be displayed as the caption of the message box 
	 * @param message the text to be displayed in the body of the message box
	 * @param okHandler a handler for the OK button click event
	 */
	public static MessageDialog show(String title, String message, OkHandler okHandler)
	{
		return show(title, message, okHandler, DEFAULT_STYLE_NAME, false);
	}
	
	/**
	 * Shows a message dialog
	 * @param title the text to be displayed as the caption of the message box 
	 * @param message the text to be displayed in the body of the message box
	 * @param okHandler a handler for the OK button click event
	 * @param styleName the name of the CSS class to be applied in the message box element 
	 * @param animationEnabled true to enable animations while showing or hiding the message box
	 */
	public static MessageDialog show(String title, String message, OkHandler okHandler, String styleName, boolean animationEnabled)
	{
		MessageDialog messageBox = new MessageDialog(); 
		messageBox.setTitle(title);
		messageBox.setMessage(message);
		messageBox.setStyleName(styleName);
		messageBox.setAnimationEnabled(animationEnabled);
		if (okHandler != null)
		{
			messageBox.addOkHandler(okHandler);
		}
		messageBox.show();
		return messageBox;
	}
	
	/**
	 * Creates the OK button
	 * @return
	 */
	private Button createOkButton()
	{
		Button okButton = new Button();
		
		okButton.setText(messages.okLabel());
		okButton.addStyleName("button");
		okButton.addStyleName("okButton");
		okButton.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				dialogBox.hide();
				try
				{
					OkEvent.fire(MessageDialog.this);
				}
				catch (Throwable e)
				{
					Crux.getErrorHandler().handleError(e);
				}
			}
		});
		return okButton;
	}
	
	/**
	 * Creates a label to display the message
	 * @param data
	 * @return
	 */
	private Label createMessageLabel()
	{
		Label label = new Label();
		label.setStyleName("message");
		return label;
	}

	@Override
    public void fireEvent(GwtEvent<?> event)
    {
		dialogBox.fireEvent(event);
    }
}
