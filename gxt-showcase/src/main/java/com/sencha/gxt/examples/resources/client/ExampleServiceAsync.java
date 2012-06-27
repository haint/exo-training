/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.examples.resources.client.model.BaseDto;
import com.sencha.gxt.examples.resources.client.model.FolderDto;
import com.sencha.gxt.examples.resources.client.model.Photo;
import com.sencha.gxt.examples.resources.client.model.Post;
import com.sencha.gxt.examples.resources.client.model.Stock;

public interface ExampleServiceAsync {

  void getPosts(PagingLoadConfig config, AsyncCallback<PagingLoadResult<Post>> callback);

  void getMusicRootFolder(AsyncCallback<FolderDto> callback);
  
  void getMusicFolderChildren(FolderDto folder, AsyncCallback<List<BaseDto>> callback);
  
  void getPhotos(AsyncCallback<List<Photo>> callback);
  
  void getStocks(FilterPagingLoadConfig config, AsyncCallback<PagingLoadResult<Stock>> callback);
}
