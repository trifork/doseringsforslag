package com.trifork.dosages.vo;

import java.util.ArrayList;

public class DrugsDosageStructures {

	private ArrayList<DrugsDosageStructure> drugsDosageStructures = new ArrayList<DrugsDosageStructure>();
	
	public void add(DrugsDosageStructure drugsDosageStructure) {
		drugsDosageStructures.add(drugsDosageStructure);
	}

	public ArrayList<DrugsDosageStructure> getAll() {
		return drugsDosageStructures;
	}
	
}
