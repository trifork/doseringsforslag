package com.trifork.dosages.converter.properties;

import java.util.Date;

public class VersionProperties {
	
	private String outputFileName;
	private int releaseNumber;
	private Date releaseDate;
	private Date lmsDate;
	private Date daDate;
	
	public String getOutputFileName() {
		return outputFileName;
	}

	public int getReleaseNumber() {
		return releaseNumber;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public Date getLmsDate() {
		return lmsDate;
	}
	
	public Date getDaDate() {
		return daDate;
	}
	
}
