doseringsforslag
================

Converter mapping from text/table based dosages and dosage units to input format for SDM. 

To use add a Google GSON in lib. 

Each time a new dataset is to be made: 
- Add input file(s) in e.g. input 
- Increase releaseNumber with 1
- Set releaseDate, typically next day
- Set lmsDate. Ask the data producer which "takst"-version is used. 
- For units, drugs set inputFileName(s)

Then run com.trifork.dosages.converter.Converter

The zip file specified under zipFile is ready to be sent to SDM via FTP. 
