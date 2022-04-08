package ch.myriam.parktime;

public class EventModel {

    public  Integer event_id ;
    public Integer event_park_id;
    public String event_desc;
    public String event_date;

    public EventModel(Integer event_id, Integer event_park_id,String event_desc,String event_date) {
        this.event_id = event_id;
        this.event_park_id = event_park_id;
        this.event_desc = event_desc;
        this.event_date = event_date;
    }
    public Integer getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Integer event_id) {
        this.event_id = event_id;
    }

    public Integer getEvent_park_id() {
        return event_park_id;
    }

    public void setEvent_park_id(Integer event_park_id) {
        this.event_park_id = event_park_id;
    }

    public String getEvent_desc() {
        return event_desc;
    }

    public void setEvent_desc(String event_desc) {
        this.event_desc = event_desc;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }


}
