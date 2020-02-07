package com.jawidmohammadi.applocker.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    foreignKeys = {
        @ForeignKey(
            entity = Application.class,
            parentColumns = "application_id",
            childColumns = "application_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Attempt {


  @ColumnInfo(name = "attempt_id")
  @PrimaryKey(autoGenerate = true)
  private long id;

  @ColumnInfo(name = "applicaton_id", index = true)
  private long applicationId;

  @ColumnInfo(name = "time_stamp", index = true)
  private Date timeStamp;

  @ColumnInfo(index = true)
  // No name needed when we wanted to use the actual method name as a column name.
  private boolean success;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(long applicationId) {
    this.applicationId = applicationId;
  }

  public Date getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(Date timeStamp) {
    this.timeStamp = timeStamp;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
