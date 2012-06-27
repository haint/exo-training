/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client.model;

public class Task {
  
  private int id;
  private String project;
  private int taskId;
  private String description;
  private double estimate;
  private double rate;
  private String due;// date?
  
  public Task(int id, String project, int taskId, String desc, double estimate, double rate, String due) {
    setId(id);
    setProject(project);
    setTaskId(taskId);
    setDescription(desc);
    setEstimate(estimate);
    setRate(rate);
    setDue(due);
  }

  public String getDescription() {
    return description;
  }

  public String getDue() {
    return due;
  }

  public double getEstimate() {
    return estimate;
  }

  public int getId() {
    return id;
  }

  public String getProject() {
    return project;
  }

  public double getRate() {
    return rate;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setDue(String due) {
    this.due = due;
  }

  public void setEstimate(double estimate) {
    this.estimate = estimate;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setProject(String project) {
    this.project = project;
  }

  public void setRate(double rate) {
    this.rate = rate;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }
}
