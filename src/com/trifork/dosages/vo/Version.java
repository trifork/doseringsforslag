package com.trifork.dosages.vo;

import java.util.Date;

public class Version {

	public Long dumpVersionPID;
	public long releaseNumber;
	public Date releaseDate;
	public Date lmsDate;
	public Date daDate;
	
	public Version(Long dumpVersionPID, long releaseNumber, Date releaseDate, Date lmsDate, Date daDate) {
		this.dumpVersionPID = dumpVersionPID;
		this.releaseNumber = releaseNumber;
		this.releaseDate = releaseDate;
		this.lmsDate = lmsDate;
		this.daDate = daDate;
	}

	public Long getDumpVersionPID() {
		return dumpVersionPID;
	}

	public long getReleaseNumber() {
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
