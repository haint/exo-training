/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.explorer.rebind.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.dev.util.Name;
import com.sencha.gxt.explorer.client.model.Example;
import com.sencha.gxt.explorer.rebind.model.SourceModel.FileType;

public class ExampleDetailModel implements Comparable<ExampleDetailModel> {
  // private final TreeLogger logger;
  private final GeneratorContext context;

  private final JClassType exampleType;
  private final Example.Detail detail;

  public ExampleDetailModel(TreeLogger l, GeneratorContext ctx, JClassType type, Example.Detail annotation) {
    // logger = l;
    context = ctx;
    exampleType = type;
    detail = annotation;
  }

  public JClassType getExampleType() {
    return exampleType;
  }

  public String getName() {
    return detail.name();
  }

  public List<SourceModel> getJavaSources() {
    List<SourceModel> srcs = new ArrayList<SourceModel>();

    srcs.add(new SourceModel(getExampleType()));
    srcs.addAll(getSourceModels(context.getTypeOracle(), detail.classes()));
    return srcs;
  }

  public List<SourceModel> getAllSources() {
    List<SourceModel> paths = new ArrayList<SourceModel>(getJavaSources());
    for (FileType type : FileType.values()) {
      if (type != FileType.JAVA && type != FileType.FOLDER) {
        paths.addAll(getOtherSources(type));
      }
    }
    return paths;
  }

  public List<SourceModel> getOtherSources(FileType type) {
    List<SourceModel> paths = new ArrayList<SourceModel>();
    //make the paths absolute to the classpath, not to the example type
    for (String initialPath : detail.files()) {
      final String path;
      if (!context.getResourcesOracle().getPathNames().contains(initialPath)) {
        path = getExampleType().getPackage().getName().replace('.', '/') + '/' + initialPath;
      } else {
        path = initialPath;
      }
      SourceModel src = new SourceModel(path);
      if (src.getType() == type) {
        paths.add(src);
      }
    }
    return paths;
  }

  /**
   * Collects the types in an array, and turns them into JClassTypes
   * 
   * @param oracle
   * @param classes
   * @return
   */
  private Collection<SourceModel> getSourceModels(TypeOracle oracle, Class<?>[] classes) {
    Set<SourceModel> types = new HashSet<SourceModel>(classes.length);
    for (Class<?> klass : classes) {
      JClassType classType = oracle.findType(Name.getSourceNameForClass(klass));
      types.add(new SourceModel(classType));
    }

    return types;
  }

  @Override
  public int compareTo(ExampleDetailModel other) {
    return this.getName().compareTo(other.getName());
  }

  public JClassType getClientBundleType() {
    return context.getTypeOracle().findType(Name.getSourceNameForClass(detail.iconClientBundle()));
  }

  public boolean usesFitLayout() {
    return detail.fit();
  }

  public String getIconMethodName() {
    return detail.icon();
  }
}
