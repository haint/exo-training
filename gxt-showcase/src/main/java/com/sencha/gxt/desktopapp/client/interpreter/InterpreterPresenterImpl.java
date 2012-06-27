/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.desktopapp.client.interpreter;

import com.sencha.gxt.desktopapp.client.DesktopBus;
import com.sencha.gxt.desktopapp.client.FileBasedMiniAppPresenterImpl;
import com.sencha.gxt.desktopapp.client.persistence.FileModel;
import com.sencha.gxt.desktopapp.client.persistence.FileSystem;
import com.sencha.gxt.desktopapp.client.utility.PromptImpl;

/**
 * Presents the interpreter application to the user.
 * <p/>
 * <b>CAUTION:</b> Be sure to read the following security notes carefully before
 * using this class or adapting it for other purposes:
 * <ul>
 * <li>This method enables arbitrary code execution by the browser. This
 * arbitrary code has access to the browser's HTML5 Local Storage and server
 * pages for the domain specified by the origin of the page.</li>
 * <li>In general, the risk of enabling arbitrary code execution is equivalent
 * to enabling the download and execution of an arbitrary program, i.e. there is
 * a potential that the program contains malicious code.</li>
 * <li>This risk is mitigated in the sample program because the content of the
 * program is visible to the user and the user is prompted to accept the risk
 * prior to running the program.</li>
 * <li>This prompt is analogous to the prompt that browsers and operating
 * systems display prior to allowing arbitrary code execution from the Internet.
 * </li>
 * </ul>
 */
public class InterpreterPresenterImpl extends FileBasedMiniAppPresenterImpl implements InterpreterPresenter {

  private static boolean isAwareOfRisks;

  public InterpreterPresenterImpl(DesktopBus desktopBus, FileSystem fileSystem, FileModel fileModel) {
    super(desktopBus, fileSystem, fileModel);
  }

  @Override
  public void bind() {
    super.bind();
    startAutoSaveTimer();
  }

  /**
   * Runs a JavaScript snippet. The JavaScript snippet may use <code>$wnd</code>
   * to refer to the <code>window</code> for the page and <code>$doc</code> to
   * refer to the <code>document</code> for the page. As an added convenience,
   * the JavaScript snippet may use <code>$wnd.p(string-value)</code> to print
   * strings to the output panel.
   * <p/>
   * <b>CAUTION:</b> Be sure to read the security notes at
   * {@link InterpreterPresenterImpl} before using this function.
   * <p/>
   * Implementation Notes:
   * <ul>
   * <li>Variable <code>javaThis</code> contains the value of <code>this</code>
   * at the time the function is invoked from Java. This is likely to be
   * different from the value of <code>this</code> when function <code>p</code>
   * is invoked later from the JavaScript snippet. For more information, see
   * <code>
   * https://developers.google.com/web-toolkit/doc/latest/FAQ_Client</code>.</li>
   * <li>Variables <code>$wnd</code> and <code>$doc</code> are JavaScript global
   * variables maintained by GWT to reference the window and document for the
   * page. These are likely to be different from the JavaScript objects
   * <code>window</code> and <code>document</code> which are associated with the
   * frame that is executing the JavaScript code. For more information, see
   * <code>https://developers.google.com/web-toolkit/doc/latest/DevGuideCodingBasicsJSNI</code>
   * .</li>
   * </ul>
   * 
   * @param snippet JavaScript program snippet to run
   */
  public native void eval(String snippet) /*-{
		var javaThis = this;
		$wnd.p = function(s) {
			return javaThis.@com.sencha.gxt.desktopapp.client.interpreter.InterpreterPresenterImpl::print(Ljava/lang/String;)(s);
		}
		eval(snippet);
  }-*/;

  public void print(String value) {
    getInterpreterView().print(value);
  }

  @Override
  public void run() {
    final String program = getInterpreterView().getValue();
    if (isAwareOfRisks) {
      eval(program);
    } else {
      PromptImpl.get().confirm(
          "Security Alert",
          "You are about to run a program using the JavaScript eval function, which enables arbitrary code execution.<br/><br/>Please verify that the program is defined as intended and does not contain any malicious code.<br/><br/>If you have any doubts, click No.",
          new Runnable() {
            @Override
            public void run() {
              isAwareOfRisks = true;
              eval(program);
            }
          });
    }
  }

  @Override
  public void unbind() {
    stopAutoSaveTimer();
    super.unbind();
  }

  @Override
  protected InterpreterViewImpl createFileBasedMiniAppView() {
    return new InterpreterViewImpl(this);
  }

  @Override
  protected String getTitle() {
    return "JavaScript - " + super.getTitle();
  }

  @Override
  protected void setDisplayedContent(String value) {
    if (value == null || value.isEmpty()) {
      value = "// Press the Run button to try this sample program\nfor (i = 1; i <= 5; i++) {\n  $wnd.p(\"Iteration \"+i);\n}";
    }
    super.setDisplayedContent(value);
  }

  private InterpreterView getInterpreterView() {
    return (InterpreterView) getFileBasedMiniAppView();
  }

}
