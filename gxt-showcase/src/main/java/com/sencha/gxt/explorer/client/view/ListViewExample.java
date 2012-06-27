/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.view;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.core.client.XTemplates.Formatter;
import com.sencha.gxt.core.client.XTemplates.FormatterFactories;
import com.sencha.gxt.core.client.XTemplates.FormatterFactory;
import com.sencha.gxt.core.client.resources.CommonStyles;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.ListStoreBinding;
import com.sencha.gxt.data.shared.loader.Loader;
import com.sencha.gxt.examples.resources.client.ExampleService;
import com.sencha.gxt.examples.resources.client.ExampleServiceAsync;
import com.sencha.gxt.examples.resources.client.model.Photo;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.ListViewCustomAppearance;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;

@Detail(name = "ListView", icon = "listview", category = "Templates & Lists", files = {
    "ListViewExample.html", "ListViewExample.css"})
public class ListViewExample implements IsWidget, EntryPoint {

  @FormatterFactories(@FormatterFactory(factory = ShortenFactory.class, name = "shorten"))
  interface Renderer extends XTemplates {
    @XTemplate(source = "ListViewExample.html")
    public SafeHtml renderItem(Photo photo, Style style);
  }

  interface Resources extends ClientBundle {
    @Source("ListViewExample.css")
    Style css();
  }

  static class Shorten implements Formatter<String> {

    private int length;

    public Shorten(int length) {
      this.length = length;
    }

    @Override
    public String format(String data) {
      return Format.ellipse(data, length);
    }
  }

  static class ShortenFactory {
    public static Shorten getFormat(int length) {
      return new Shorten(length);
    }
  }

  interface Style extends CssResource {
    String over();

    String select();

    String thumb();

    String thumbWrap();
  }

  public Widget asWidget() {
    final ExampleServiceAsync service = GWT.create(ExampleService.class);

    RpcProxy<Object, List<Photo>> proxy = new RpcProxy<Object, List<Photo>>() {
      @Override
      public void load(Object loadConfig, AsyncCallback<List<Photo>> callback) {
        service.getPhotos(callback);
      }
    };

    Loader<Object, List<Photo>> loader = new Loader<Object, List<Photo>>(proxy);

    ModelKeyProvider<Photo> kp = new ModelKeyProvider<Photo>() {
      @Override
      public String getKey(Photo item) {
        return item.getName();
      }
    };

    ListStore<Photo> store = new ListStore<Photo>(kp);
    loader.addLoadHandler(new ListStoreBinding<Object, Photo, List<Photo>>(store));
    loader.load();

    final FramedPanel panel = new FramedPanel();
    panel.setCollapsible(true);
    panel.setAnimCollapse(false);
    panel.setId("images-view2");
    panel.setHeadingText("Simple ListView (0 items selected)");
    panel.setWidth(535);
    panel.setBodyBorder(false);

    final Resources resources = GWT.create(Resources.class);
    resources.css().ensureInjected();
    final Style style = resources.css();

    final Renderer r = GWT.create(Renderer.class);

    ListViewCustomAppearance<Photo> appearance = new ListViewCustomAppearance<Photo>("." + style.thumbWrap(),
        style.over(), style.select()) {

      @Override
      public void renderEnd(SafeHtmlBuilder builder) {
        String markup = new StringBuilder("<div class=\"").append(CommonStyles.get().clear()).append("\"></div>").toString();
        builder.appendHtmlConstant(markup);
      }

      @Override
      public void renderItem(SafeHtmlBuilder builder, SafeHtml content) {
        builder.appendHtmlConstant("<div class='" + style.thumbWrap() + "' style='border: 1px solid white'>");
        builder.append(content);
        builder.appendHtmlConstant("</div>");
      }

    };

    ListView<Photo, Photo> view = new ListView<Photo, Photo>(store, new IdentityValueProvider<Photo>() {

      @Override
      public void setValue(Photo object, Photo value) {

      }
    }, appearance);
    view.setCell(new SimpleSafeHtmlCell<Photo>(new AbstractSafeHtmlRenderer<Photo>() {

      @Override
      public SafeHtml render(Photo object) {
        return r.renderItem(object, style);
      }
    }));
    view.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<Photo>() {
      @Override
      public void onSelectionChanged(SelectionChangedEvent<Photo> event) {
        panel.setHeadingText("Simple ListView (" + event.getSelection().size() + " items selected)");
      }
    });

    panel.add(view);
    panel.addStyleName("margin-10");
    return panel;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
