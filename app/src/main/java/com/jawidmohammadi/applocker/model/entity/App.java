package com.jawidmohammadi.applocker.model.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
// inorder to sort packages it has to be comparable

/**
 * This is our Entity Class that Room Creates a SQLite database for this class fields
 * each Entity represent a table in SQLite database.
 * @author Jawid Mohammadi
 */
@Entity(
    indices = {
        @Index(value = "package", unique = true)
    }
)
public class App implements Comparable<App> {

  @ColumnInfo(name = "application_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "package", collate = ColumnInfo.NOCASE)
  private String pkg;

  @ColumnInfo(name = "restriction_method", collate = ColumnInfo.NOCASE)
  private String restrictionMethod;

  @ColumnInfo(name = "hashed_password", index = true)
  private String hashedPassword;

  private String salt;

  @Ignore
  private String label;

  /**
   * we use getters and setters for each private method that can not be accessed out side of this class so we can get access to them
   * by using getter and setters.
   * This get method used to get or retrieve salt, from database table.
   */
  public String getSalt() {
    return salt;
  }

  /**
   *this set method use to update salt in the database table.
   */
  public void setSalt(String salt) {
    this.salt = salt;
  }

  /**
   *this get method used to get hashed password from the database.
   */
  public String getHashedPassword() {
    return hashedPassword;
  }

  /**
   * this set method is used to update hashed password in the database.
   */
  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  /**
   * this get method use to get id from the database.
   */
  public long getId() {
    return id;
  }

  /**
   * this method use to update id in the database.
   */
  public void setId(long id) {
    this.id = id;
  }


  public String getPkg() {
    return pkg;
  }

  public void setPkg(String pkg) {
    this.pkg = pkg;
  }

  public String getRestrictionMethod() {
    return restrictionMethod;
  }

  public void setRestrictionMethod(String restrictionMethod) {
    this.restrictionMethod = restrictionMethod;
  }

  public boolean isLocked(){
    return id != 0;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public int compareTo(App other) {
    return label.compareToIgnoreCase(other.label);
  }

  @Override
  public int hashCode() {
    return pkg.hashCode();
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    return pkg.equals(obj);
  }
}
