package info.androidhive.navigationdrawer.other;

public class Event {

    String eventId;
    String eventProgramme;
    String eventTime;
    String eventVenue;

    public Event(){

    }

    public Event(String eventId, String eventProgramme, String eventTime, String eventVenue) {
        this.eventId = eventId;
        this.eventProgramme = eventProgramme;
        this.eventTime = eventTime;
        this.eventVenue = eventVenue;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventProgramme() {
        return eventProgramme;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventVenue() {
        return eventVenue;
    }

}
