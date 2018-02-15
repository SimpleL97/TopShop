package ua.com.funnybus.entity;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.Date;

@Entity
public class Trip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String way;
	
	private Date date;
	
	private String place;
	
	@OneToMany(mappedBy="trip")
	private List<Booking> bookings;

	public Trip() {
	}

	public Trip(String way, Date date, String place) {
		this.way = way;
		this.date = date;
		this.place = place;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
 
	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}
	
	public String getFull(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dateDay = new SimpleDateFormat("u");
		String dateNew=dateFormat.format(date);
		String d=dateDay.format(date);
		int day = Integer.valueOf(d);
		return way+" "+dateNew+" "+dater(day);
	}
	
	public String getField(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dateDay = new SimpleDateFormat("u");
		String dateNew=dateFormat.format(date);
		String d=dateDay.format(date);
		int day = Integer.valueOf(d);
		return place+" "+dateNew+" "+dater(day);
	}
	
	public String getData(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		SimpleDateFormat dateDay = new SimpleDateFormat("u");
		String dateNew=dateFormat.format(date);
		String d=dateDay.format(date);
		int day = Integer.valueOf(d);
		return dater(day)+" "+dateNew;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trip other = (Trip) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	private String dater(int day){
		if(day==1)return "Понеділок";
		else if(day==2)return "Вівторок";
		else if(day==3)return "Середа";
		else if(day==4)return "Четвер";
		else if(day==5)return "П'ятниця";
		else if(day==6)return "Субота";
		else if(day==7)return "Неділя";
		else return "Невідомо";
	}
}
