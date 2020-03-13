package com.jawidmohammadi.applocker.model.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
// inorder to sort packages it has to be comparable
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

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public String getHashedPassword() {
    return hashedPassword;
  }

  public void setHashedPassword(String hashedPassword) {
    this.hashedPassword = hashedPassword;
  }

  public long getId() {
    return id;
  }

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
