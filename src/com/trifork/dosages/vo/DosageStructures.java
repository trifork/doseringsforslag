package com.trifork.dosages.vo;

import java.util.ArrayList;

public class DosageStructures {
	
	private ArrayList<DosageStructure> dosageStructures = new ArrayList<DosageStructure>();
	
	public void add(DosageStructure dosageStructure) {
		if(dosageStructure==null)
			return;
		dosageStructures.add(dosageStructure);
	}

	public ArrayList<DosageStructure> getAll() {
		return dosageStructures;
	}
	
}
