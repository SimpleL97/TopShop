package ua.com.funnybus.editor;

import java.beans.PropertyEditorSupport;

import ua.com.funnybus.entity.Trip;
import ua.com.funnybus.service.TripService;

public class TripEditor extends PropertyEditorSupport  {

	private final TripService tripService;

	public TripEditor(TripService tripService) {
		this.tripService = tripService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Trip trip = tripService.findOne(Integer.valueOf(text));
		setValue(trip);
	}

}
