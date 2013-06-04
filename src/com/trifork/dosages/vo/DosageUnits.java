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

public class DosageUnits {

	private static final int codeOffset = 1001;
	
	private TreeMap<Integer, DosageUnit> dosageUnits = new TreeMap<Integer, DosageUnit>();
	
	public void add(DosageUnit dosageUnit) {
		dosageUnits.put(dosageUnit.getCode(), dosageUnit);
	}

	public Collection<DosageUnit> getAll() {
		return dosageUnits.values();
	}
	
	public DosageUnit getDosageUnit(String textSingular, String textPlural) {
		for(DosageUnit d: dosageUnits.values()) 
			if(d.getTextSingular().equals(textSingular) && d.getTextPlural().equals(textPlural))
				return d;
		return null;
	}

	public static DosageUnits readDosageUnits(Version version, ConverterProperties properties) {
		ArrayList<DosageUnit> dosageUnitList = readFromFile(version, properties);
		reorder(dosageUnitList);
		DosageUnits dosageUnits = new DosageUnits();
		for(DosageUnit d: dosageUnitList) 
			dosageUnits.add(d);
		return dosageUnits;
	}

	private static ArrayList<DosageUnit> readFromFile(Version version, ConverterProperties properties) {
		File file = new File(properties.getUnits().getInputFileName());
		if(!file.exists())
			throw new RuntimeException("Cannot read \""+file.getAbsolutePath()+"\": No file");
		ArrayList<String> hasRead = new ArrayList<String>();
		ArrayList<DosageUnit> dosageUnitList = new ArrayList<DosageUnit>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), properties.getUnits().getEncoding()));
			while(reader.ready()) {
				String s = reader.readLine();
				if(s.trim().length()>0) {
					if(s.startsWith("drugid")) // TODO: Parameterize this
						continue;
					String[] ss = s.split(properties.getUnits().getSeparatorRegEx());
					if(ss.length!=properties.getUnits().getColumnCount()) 
						throw new RuntimeException("Cannot read \""+file.getAbsolutePath()+"\": Cannot parse line \""+s+"\"");
					if(ss[properties.getUnits().getColumnForUnitSingular()].trim().length()==0 || ss[properties.getUnits().getColumnForUnitPlural()].trim().length()==0)
						continue;
					if(!hasRead.contains(ss[properties.getUnits().getColumnForUnitSingular()])) {
						dosageUnitList.add(new DosageUnit(null, version.getReleaseNumber(), 0, ss[properties.getUnits().getColumnForUnitSingular()], ss[properties.getUnits().getColumnForUnitPlural()]));
						hasRead.add(ss[properties.getUnits().getColumnForUnitSingular()]);
					}
				}
			}
			return dosageUnitList;
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
	
	private static void reorder(ArrayList<DosageUnit> dosageUnitList) {
		Collections.sort(dosageUnitList, new Comparator<DosageUnit>() {
			@Override
			public int compare(DosageUnit d1, DosageUnit d2) {
				return d1.getTextSingular().toLowerCase().compareTo(d2.getTextSingular().toLowerCase());
			}			
		});
		for(int i=0; i<dosageUnitList.size(); i++) 
			dosageUnitList.get(i).setCode(codeOffset+i);
	}

}
