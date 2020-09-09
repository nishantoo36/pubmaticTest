package dataProviders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {

    private Properties properties;
    final private  String configRelative =  "Config/Common.properties";
    final private String commonConfigFilePath = System.getProperty("user.dir")+"/" + configRelative;


	public ConfigFileReader() {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(configRelative));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + commonConfigFilePath);
		}
	}

	public String getBrowserName() {
		if (System.getProperty("browser") != null) {
			return (System.getProperty("browser"));
		}else if(properties.getProperty("browser")!=null) {
			return properties.getProperty("browser");
		}
		else {
			throw new RuntimeException("browser not specified.");
		}
	}

	public String getUrl()  {
		if (System.getProperty("url") != null) {
			return (System.getProperty("url"));
		}else if(properties.getProperty("url")!=null) {
			return properties.getProperty("url");
		}
		else {
			throw new RuntimeException("url not specified.");
		}
	}

	public String getReportConfigPath() {
		String reportConfigPath = getValueFromPropertyFile(commonConfigFilePath, "reportConfigPath");
		System.out.println("Extent Report Path :" + reportConfigPath);
		if (reportConfigPath != null)
			return reportConfigPath;
		else
			throw new RuntimeException(
					"Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
	}

	public String getValueFromPropertyFile(String filePath, String key) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filePath));
			properties = new Properties();
			try {
				properties.load(reader);
				String value = properties.getProperty(key);
				reader.close();
				return value;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found at " + commonConfigFilePath);
		}
	}
}
