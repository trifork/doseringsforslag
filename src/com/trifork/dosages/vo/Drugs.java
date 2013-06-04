package com.trifork.dosages.vo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

import com.trifork.dosages.converter.properties.ConverterProperties;

public class Drugs {
	
	private TreeMap<Long, Drug> drugs = new TreeMap<Long, Drug>();
	
	public void add(Drug drug) {
		drugs.put(drug.getDrugId(), drug);
	}

	public Drug getByDrugId(Long drugId) {
		return drugs.get(drugId);
	}
	
	public Collection<Drug> getAll() {
		return drugs.values();
	}
	
	public static Drugs readDrugs(Version version, DosageUnits dosageUnits, ConverterProperties properties) {
		ArrayList<Drug> drugList = readFromFile(version, dosageUnits, properties);
		reorder(drugList);
		Drugs drugs = new Drugs();
		for(Drug d: drugList) 
			drugs.add(d);
		return drugs;
	}
	
	private static ArrayList<Drug> readFromFile(Version version, DosageUnits dosageUnits, ConverterProperties properties) {
		File file = new File(properties.getDrugs().getInputFileName());
		if(!file.exists())
			throw new RuntimeException("Cannot read \""+file.getAbsolutePath()+"\": No file");
		ArrayList<Long> hasRead = new ArrayList<Long>();
		ArrayList<Drug> drugList = new ArrayList<Drug>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), properties.getUnits().getEncoding()));
			while(reader.ready()) {
				String s = reader.readLine();
				if(s.trim().length()>0) {
					if(s.startsWith("drugid")) // TODO: Parameterize this
						continue;
					String[] ss = s.split(properties.getDrugs().getSeparatorRegEx());
					if(ss.length!=properties.getDrugs().getColumnCount()) 
						throw new RuntimeException("Cannot read \""+file.getAbsolutePath()+"\": Cannot parse line \""+s+"\"");
					if(ss[properties.getDrugs().getColumnForDrugId()].trim().length()==0 ||  ss[properties.getDrugs().getColumnForUnitSingular()].trim().length()==0 || ss[properties.getDrugs().getColumnForUnitPlural()].trim().length()==0)
						continue;
					Long drugId = Long.parseLong(ss[properties.getDrugs().getColumnForDrugId()]);
					if(hasRead.contains(ss[properties.getDrugs().getColumnForDrugId()])) 
						throw new RuntimeException("Cannot read \""+file.getAbsolutePath()+"\": Duplicate drugId "+ss[properties.getDrugs().getColumnForDrugId()]+" in line \""+s+"\"");
					DosageUnit dosageUnit = dosageUnits.getDosageUnit(ss[properties.getDrugs().getColumnForUnitSingular()], ss[properties.getDrugs().getColumnForUnitPlural()]);
					drugList.add(
						new Drug(
								null, 
								version.getReleaseNumber(), 
								drugId,
								dosageUnit.getCode(), 
								null, 
								"-"));
					hasRead.add(drugId);
				}
			}
			return drugList;
		} 
		catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} 
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				reader.close();
			}
			catch(Exception e) {
				// Ignore
			}
		}		
	}
	
	private static void reorder(ArrayList<Drug> drugList) {
		Collections.sort(drugList, new Comparator<Drug>() {
			@Override
			public int compare(Drug d1, Drug d2) {
				return d1.getDrugId().compareTo(d2.getDrugId());
			}			
		});
	}

	
}
