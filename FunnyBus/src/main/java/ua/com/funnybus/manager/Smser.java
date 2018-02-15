package ua.com.funnybus.manager;

import ua.com.funnybus.entity.Booking;
import ua.com.funnybus.util.Smsc;

public class Smser {
	
	private final Booking booking;
	
	private String adminPhone="+380662736758";
	private int translit=0;
	private String time="";
	private String id="";
	private int format = 0; 
	private String sender="";
	private String query = "";

	public Smser(Booking booking) {
		this.booking = booking;
	}

	Smsc sms = new Smsc();
	
	public void adminSender(){
		String b=sms.get_balance();
		double bm = Double.valueOf(b);
		String firstName=booking.getUser().getFirstName();
		String secondName=booking.getUser().getSecondName();
		String phone=booking.getUser().getPhone();
		String trip=booking.getTrip().getFull();
		String message = "Замовлення: "+firstName+" "+secondName+" "+phone+" "+trip+".";
		if(bm<=50){
			message= message+" У вас на рахунку менше "+b+" грн";
		}
		sms.send_sms(adminPhone, message, translit, time, id, format, sender, query);
	}
	
	public void userSender(){
		String firstName = booking.getUser().getFirstName();
		String secondName = booking.getUser().getSecondName();
		String phone = booking.getUser().getPhone();
		String way = booking.getTrip().getWay();
		String date = booking.getTrip().getData();
		String message = "Доброго дня, "+firstName+" "+secondName+", Ви забронювали поїздку по напрямку "+way+": "+date;
		sms.send_sms(phone, message, translit, time, id, format, sender, query);
	}
}
