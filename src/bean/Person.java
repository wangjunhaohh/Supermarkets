package bean;

public class Person {
  private int id;
  private String name;
  private String startTime;
  private String endTime;
  private String reason;
  private String Photo;
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getPhoto() {
    return Photo;
  }

  public void setPhoto(String photo) {
    Photo = photo;
  }
}
