package ua.com.funnybus.manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import ua.com.funnybus.entity.Booking;
import ua.com.funnybus.entity.Trip;
import ua.com.funnybus.service.BookingService;
import ua.com.funnybus.service.TripService;

@Service
public class Changer {
	
	@Autowired
	private TripService tripService;
	
	@Autowired
	private BookingService bookingService;
	
		//cron="0 1 0 * * ?"
	@Scheduled(fixedDelay=50000)
	public void Booker(){
		int days=100;
		int tripId=0;
		int bookId=0;
		Date date = new Date();
		Date dateC = new Date();
		Calendar cn = Calendar.getInstance();
		cn.setTime(dateC);
		cn.add(Calendar.MONTH, -1);
		dateC = cn.getTime();
		List<Booking> bookings = bookingService.findAll();
		for (int i = 0; i < bookings.size(); i++) {
			Booking booking = bookings.get(i);
			if(dateC.after(booking.getTrip().getDate())){
				bookId=booking.getId();
				bookingService.delete(bookId);
			}
		}
		
		List<Trip> trips = tripService.findAll();
		for (int i = 0; i < trips.size(); i++) {
			Trip trip = trips.get(i);
			if(dateC.after(trip.getDate())){
				tripId=trip.getId();
				tripService.delete(tripId);
			}
		}
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String dateM=dateFormat.format(date);
		int j=0;
		try {
			List<Trip> triping = tripService.findAll();
			Date dateNew=dateFormat.parse(dateM);
			Calendar c = Calendar.getInstance();
			Calendar cNew = Calendar.getInstance();
			Date maxDate=new Date();
			for (int i = 1; i < triping.size(); i++) {
				Trip trip1 = triping.get(i);
				Trip trip2 = triping.get(i-1);
				if(trip1.getDate().after(trip2.getDate())) maxDate=trip1.getDate();
				else maxDate=trip2.getDate();
			}
			
			while(maxDate.after(dateNew)) {
				c.setTime(dateNew);
				c.add(Calendar.DAY_OF_WEEK, 1);
				dateNew=c.getTime();
				j++;
			}
			String d=dateFormat.format(maxDate);
			Date dMax=dateFormat.parse(d);
			while(days>j){
				cNew.setTime(dMax);
				cNew.add(Calendar.DAY_OF_WEEK, 1);
				dMax=cNew.getTime();
				SimpleDateFormat dateFormat1 = new SimpleDateFormat("u");
				String dayS=dateFormat1.format(dMax);
				int wd=Integer.valueOf(dayS);
				String BUS="Городенка - Івано-Франківськ - Прага";
				String MICRO="Україна - Словаччина - Чехія";
				String UA="з України";
				String CH="з Чехії";
				if(wd==2){
					Trip trip1=new Trip();
					Trip trip2=new Trip();
					trip1.setDate(dMax);
					trip2.setDate(dMax);
					trip1.setPlace(UA);
					trip2.setPlace(UA);
					trip2.setWay(BUS);
					trip1.setWay(MICRO);
					tripService.update(trip1);
					tripService.update(trip2);
				}else if(wd==4){
					Trip trip1=new Trip();
					Trip trip2=new Trip();
					trip1.setDate(dMax);
					trip2.setDate(dMax);
					trip1.setPlace(CH);
					trip2.setPlace(CH);
					trip1.setWay(BUS);
					trip2.setWay(MICRO);
					tripService.update(trip1);
					tripService.update(trip2);
				}else if(wd==5){
					Trip trip1=new Trip();
					Trip trip2=new Trip();
					trip1.setDate(dMax);
					trip2.setDate(dMax);
					trip1.setPlace(UA);
					trip2.setPlace(UA);
					trip1.setWay(BUS);
					trip2.setWay(MICRO);
					tripService.update(trip1);
					tripService.update(trip2);
				}else if(wd==7){
					Trip trip1=new Trip();
					Trip trip2=new Trip();
					trip1.setDate(dMax);
					trip2.setDate(dMax);
					trip1.setPlace(CH);
					trip2.setPlace(CH);
					trip1.setWay(BUS);
					trip2.setWay(MICRO);
					tripService.update(trip1);
					tripService.update(trip2);
				}
				j++;
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
