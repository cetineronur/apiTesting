package pojos;

public class BookingResponsePojo {

    /*
    {
        "bookingid": 11,
            "booking": {
                 "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 500,
                "depositpaid": true,
                "bookingdates": {
                            "checkin": "2022-03-01",
                             "checkout": "2022-03-11"
                                 }
                         }
       }
     */

    //1) private degiskenleri olustur
    private int bookingid;
    private BookingPojo bookingPojo;

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public BookingPojo getBookingPojo() {
        return bookingPojo;
    }

    public void setBookingPojo(BookingPojo bookingPojo) {
        this.bookingPojo = bookingPojo;
    }

    public BookingResponsePojo() {
    }

    public BookingResponsePojo(int bookingid, BookingPojo bookingPojo) {
        this.bookingid = bookingid;
        this.bookingPojo = bookingPojo;
    }

    @Override
    public String toString() {
        return "BookingResponsePojo{" +
                "bookingid=" + bookingid +
                ", bookingPojo=" + bookingPojo +
                '}';
    }
}
