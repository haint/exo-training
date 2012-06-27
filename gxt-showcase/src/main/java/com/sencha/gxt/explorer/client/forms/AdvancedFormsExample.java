/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.client.forms;

import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanelHelper;
import com.sencha.gxt.widget.core.client.form.HtmlEditor;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;

@Detail(name = "Advanced Forms", icon = "advancedforms", category = "Forms")
public class AdvancedFormsExample implements IsWidget, EntryPoint {

  private static final int COLUMN_FORM_WIDTH = 680;
  private VerticalPanel vp;

  public Widget asWidget() {
    if (vp == null) {
      vp = new VerticalPanel();
      vp.setSpacing(10);
      createColumnForm();
      createTabForm();
    }
    return vp;
  }

  public void onModuleLoad() {
    RootPanel.get().add(asWidget());
  }

  private void createColumnForm() {
    FramedPanel panel = new FramedPanel();
    panel.setHeadingText("Form Example");
    panel.setWidth(COLUMN_FORM_WIDTH);

    HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
    panel.setWidget(con);

    int cw = (COLUMN_FORM_WIDTH / 2) - 12;

    TextField firstName = new TextField();
    firstName.setAllowBlank(false);
    firstName.setWidth(cw);
    con.add(new FieldLabel(firstName, "First Name"), new HtmlData(".fn"));

    TextField lastName = new TextField();
    lastName.setAllowBlank(false);
    lastName.setWidth(cw);
    con.add(new FieldLabel(lastName, "Last Name"), new HtmlData(".ln"));

    TextField company = new TextField();
    company.setWidth(cw);
    con.add(new FieldLabel(company, "Company"), new HtmlData(".company"));

    TextField email = new TextField();
    email.setWidth(cw);
    con.add(new FieldLabel(email, "Email"), new HtmlData(".email"));

    DateField birthday = new DateField();
    birthday.setWidth(cw);
    con.add(new FieldLabel(birthday, "Birthday"), new HtmlData(".birthday"));

    Radio radio1 = new Radio();
    radio1.setBoxLabel("Yes");

    Radio radio2 = new Radio();
    radio2.setBoxLabel("No");

    HorizontalPanel hp = new HorizontalPanel();
    hp.add(radio1);
    hp.add(radio2);

    con.add(new FieldLabel(hp, "GXT User"), new HtmlData(".user"));

    ToggleGroup group = new ToggleGroup();
    group.add(radio1);
    group.add(radio2);

    HtmlEditor a = new HtmlEditor();
    a.setWidth(COLUMN_FORM_WIDTH - 25);
    con.add(new FieldLabel(a, "Comment"), new HtmlData(".editor"));

    panel.addButton(new TextButton("Cancel"));
    panel.addButton(new TextButton("Submit"));

    // need to call after everything is constructed
    List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
    for (FieldLabel lbl : labels) {
      lbl.setLabelAlign(LabelAlign.TOP);
    }

    vp.add(panel);
  }

  private void createTabForm() {
    FormPanel panel = new FormPanel();
    panel.setWidth(300);

    TabPanel tabs = new TabPanel();
    panel.setWidget(tabs);

    VerticalLayoutContainer p = new VerticalLayoutContainer();
    p.setLayoutData(new MarginData(8));

    tabs.add(p, "Person Details");

    TextField firstName = new TextField();
    firstName.setAllowBlank(false);
    firstName.setValue("Darrell");
    p.add(new FieldLabel(firstName, "First Name"), new VerticalLayoutData(1, -1));

    TextField lastName = new TextField();
    lastName.setAllowBlank(false);
    lastName.setValue("Meyer");
    p.add(new FieldLabel(lastName, "Last Name"), new VerticalLayoutData(1, -1));

    TextField email = new TextField();
    email.setAllowBlank(false);
    p.add(new FieldLabel(email, "Email"), new VerticalLayoutData(1, -1));

    p = new VerticalLayoutContainer();
    p.setLayoutData(new MarginData(8));

    tabs.add(p, "Phone Numbers");

    TextField home = new TextField();
    home.setValue("888-888-888");
    p.add(new FieldLabel(home, "Home"), new VerticalLayoutData(1, -1));

    TextField business = new TextField();
    business.setValue("888-888-888");
    p.add(new FieldLabel(business, "Business"), new VerticalLayoutData(1, -1));

    vp.add(panel);
  }

  private native String getTableMarkup() /*-{
    return [ '<table width=100% cellpadding=0 cellspacing=0>',
        '<tr><td class=fn width=50%></td><td class=ln width=50%></td></tr>',
        '<tr><td class=company></td><td class=email></td></tr>',
        '<tr><td class=birthday></td><td class=user></td></tr>',
        '<tr><td class=editor colspan=2></td></tr>', '</table>'

    ].join("");
  }-*/;

}
