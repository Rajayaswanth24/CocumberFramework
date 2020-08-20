package LibraryFiles;

import java.io.IOException;

public class ExtentConfigFileReader extends ReUsableLibrary{
	

public String getReportConfigPath() throws IOException{
 String reportConfigPath = getElementFromPropFile("Extentconfigfilepath");
 if(reportConfigPath!= null) return reportConfigPath;
 else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath"); 
}

}
