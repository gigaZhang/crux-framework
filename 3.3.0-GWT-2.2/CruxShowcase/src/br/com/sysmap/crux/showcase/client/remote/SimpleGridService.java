package br.com.sysmap.crux.showcase.client.remote;

import java.util.ArrayList;

import br.com.sysmap.crux.showcase.client.dto.Contact;

import com.google.gwt.user.client.rpc.RemoteService;

public interface SimpleGridService extends RemoteService {
	
	public ArrayList<Contact> getContactList();
}