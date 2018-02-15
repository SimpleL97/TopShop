package ua.com.topshop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.topshop.entity.Memory;
import ua.com.topshop.service.MemoryService;

public class MemoryEditor extends PropertyEditorSupport{
	
	private final MemoryService memoryService;

	public MemoryEditor(MemoryService memoryService) {
		this.memoryService = memoryService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Memory memory = memoryService.findOne(Integer.valueOf(text));
		setValue(memory);
	}
	
	
}
