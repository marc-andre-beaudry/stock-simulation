package com.maillets.stocksimulation.controller;

import com.maillets.stocksimulation.controller.exception.BadRequestException;

public final class ControllerUtils {

	public static int parseToId(String id) {
		int parsedInt = 0;
		try {
			parsedInt = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			throw new BadRequestException();
		}
		return parsedInt;
	}
}
