package com.petstore.pet.utilities;

import org.slf4j.Logger;

public class LoggerUtil {

	public static void entry(Logger logger) {
		logger.debug("ENTRY");
		
	}

	public static void exit(Logger logger) {
		logger.debug("EXIT");
	}

}
