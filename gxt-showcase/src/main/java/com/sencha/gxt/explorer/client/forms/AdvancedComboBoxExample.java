/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.forms;

import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBean.PropertyName;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.XTemplates;
import com.sencha.gxt.data.client.loader.JsoReader;
import com.sencha.gxt.data.client.loader.ScriptTagProxy;
import com.sencha.gxt.data.client.writer.UrlEncodingWriter;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent;
import com.sencha.gxt.data.shared.loader.BeforeLoadEvent.BeforeLoadHandler;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.form.ComboBox;

@Detail(name = "Advanced ComboBox", icon = "advancedcombobox", category = "Combos")
public class AdvancedComboBoxExample implements IsWidget, EntryPoint {

  interface Bundle extends ClientBundle {
    @Source("AdvancedComboBox.css")
    ExampleStyle css();
  }

  interface ExampleStyle extends CssResource {
    String searchItem();
  }

  interface ExampleTemplate extends XTemplates {
    @XTemplate("<div class='{style.searchItem}'><h3><span>{post.date:date(\"M/d/yyyy\")}<br />by {post.author}</span>{post.title}</h3>{post.excerpt}</div>")
    SafeHtml render(Forum post, ExampleStyle style);
  }

  interface TestAutoBeanFactory extends AutoBeanFactory {
    static TestAutoBeanFactory instance = GWT.create(TestAutoBeanFactory.class);

    AutoBean<ForumCollection> dataCollection();

    AutoBean<ForumListLoadResult> dataLoadResult();

    AutoBean<ForumLoadConfig> loadConfig();
  }

  public interface Forum {
    @PropertyName("topic_title")
    public String getTitle();

    @PropertyName("topic_id")
    public String getTopicId();

    public String getAuthor();
    
    @PropertyName("forumid")
    public String getForumId();

    @PropertyName("post_text")
    public String getExcerpt();
    
    @PropertyName("post_id")
    public String getPostId();

    @PropertyName("post_time")
    public Date getDate();

  }

  interface ForumCollection {
    String getTotalCount();

    List<Forum> getTopics();
  }

  interface ForumLoadConfig extends PagingLoadConfig {
    String getQuery();

    void setQuery(String query);

    @Override
    @PropertyName("start")
    public int getOffset();

    @Override
    @PropertyName("start")
    public void setOffset(int offset);
  }

  interface ForumListLoadResult extends PagingLoadResult<Forum> {
    void setData(List<Forum> data);

    @Override
    @PropertyName("start")
    public int getOffset();

    @Override
    @PropertyName("start")
    public void setOffset(int offset);
  }

  interface ForumProperties extends PropertyAccess<Forum> {
    ModelKeyProvider<Forum> topicId();

    LabelProvider<Forum> title();
  }

  private ComboBox<Forum> combo;
  private VerticalPanel vp;

  public Widget asWidget() {

    if (vp == null) {
      vp = new VerticalPanel();
      vp.setSpacing(10);

      String url = "http://www.sencha.com/forum/topics-remote.php";

      ScriptTagProxy<ForumLoadConfig> proxy = new ScriptTagProxy<ForumLoadConfig>(url);
      proxy.setWriter(new UrlEncodingWriter<ForumLoadConfig>(TestAutoBeanFactory.instance, ForumLoadConfig.class));

      JsoReader<ForumListLoadResult, ForumCollection> reader = new JsoReader<ForumListLoadResult, ForumCollection>(
          TestAutoBeanFactory.instance, ForumCollection.class) {
        @Override
        protected ForumListLoadResult createReturnData(Object loadConfig, ForumCollection records) {
          PagingLoadConfig cfg = (PagingLoadConfig) loadConfig;
          ForumListLoadResult res = TestAutoBeanFactory.instance.dataLoadResult().as();
          res.setData(records.getTopics());
          res.setOffset(cfg.getOffset());
          res.setTotalLength(Integer.parseInt(records.getTotalCount()));
          return res;
        }
      };

      PagingLoader<ForumLoadConfig, ForumListLoadResult> loader = new PagingLoader<ForumLoadConfig, ForumListLoadResult>(
          proxy, reader);
      loader.useLoadConfig(TestAutoBeanFactory.instance.loadConfig().as());
      loader.addBeforeLoadHandler(new BeforeLoadHandler<ForumLoadConfig>() {
        @Override
        public void onBeforeLoad(BeforeLoadEvent<ForumLoadConfig> event) {
          String query = combo.getText();
          if (query != null && !query.equals("")) {
            event.getLoadConfig().setQuery(query);
          }
        }
      });

      ForumProperties props = GWT.create(ForumProperties.class);

      ListStore<Forum> store = new ListStore<Forum>(props.topicId());
      loader.addLoadHandler(new LoadResultListStoreBinding<ForumLoadConfig, Forum, ForumListLoadResult>(store));

      final Bundle b = GWT.create(Bundle.class);
      b.css().ensureInjected();

      final ExampleTemplate template = GWT.create(ExampleTemplate.class);

      ListView<Forum, Forum> view = new ListView<Forum, Forum>(store, new IdentityValueProvider<Forum>());
      view.setCell(new AbstractCell<Forum>() {

        @Override
        public void render(com.google.gwt.cell.client.Cell.Context context, Forum value, SafeHtmlBuilder sb) {
          sb.append(template.render(value, b.css()));
        }
      });
      
     

      ComboBoxCell<Forum> cell = new ComboBoxCell<Forum>(store, props.title(), view);

      combo = new ComboBox<Forum>(cell);
      combo.setLoader(loader);
      combo.setWidth(580);
      combo.setHideTrigger(true);
      combo.setPageSize(10);
      combo.addBeforeSelectionHandler(new BeforeSelectionHandler<Forum>() {
        
        @Override
        public void onBeforeSelection(BeforeSelectionEvent<Forum> event) {
          event.cancel();
          Forum f = combo.getListView().getSelectionModel().getSelectedItem();
          Window.open("http://sencha.com/forum/showthread.php?t=" + f.getTopicId() + "&p=" + f.getPostId(), null, null);
        }
      });
      
      combo.getElement().getStyle().setMargin(10, Unit.PX);
      
      ContentPanel cp = new ContentPanel();
      cp.setWidth(600);
      cp.setHeadingText("Search the Sencha Forums");
      cp.add(combo);
      cp.setResize(false);

      vp.add(cp);
    }

    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

}
