package com.venkat.modelMapper;

import org.modelmapper.ModelMapper;

public class AppMain {
	ModelMapper modelMapper = new ModelMapper();
	OrderDTO orderDTO = modelMapper.map(new Order(), OrderDTO.class);
}
