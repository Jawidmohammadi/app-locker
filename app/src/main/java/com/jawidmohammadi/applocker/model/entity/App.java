package com.jawidmohammadi.applocker.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    indices = {
        @Index(value = "package", unique = true)

    }
)
public class App {

  @ColumnInfo(name = "application_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "package", collate = ColumnInfo.NOCASE)
  private String pkg;

  @ColumnInfo(name = "restriction_method", collate = ColumnInfo.NOCASE)
  private String restrictionMethod;

  @ColumnInfo(name = "hashed_password", index = true)
  private String hashedPassword;

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
}
