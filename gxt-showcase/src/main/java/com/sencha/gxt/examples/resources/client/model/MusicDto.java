/**
 * Sencha GXT 3.0.0b - Sencha for GWT
 * Copyright(c) 2007-2012, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.sencha.gxt.examples.resources.client.model;


@SuppressWarnings("serial")
public class MusicDto extends BaseDto {

  private String genre;
  private String author;

  protected MusicDto() {

  }

  public MusicDto(Integer id, String name, String genre, String author) {
    super(id, name);
    this.genre = genre;
    this.author = author;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
