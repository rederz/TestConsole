package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	public static Properties prop;

	private static void loadProperties() {
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			InputStream stream = loader.getResourceAsStream("config.properties");

			prop = new Properties();
			prop.load(stream);
		} catch (IOException e) {
			prop = null;
		}
	}

	public static String getProp(String propiedad) {
		String value = "";

		if (prop == null) {
			loadProperties();
		}

		if (prop != null && prop.containsKey(propiedad)) {
			value = prop.getProperty(propiedad);
		}

		return value;
	}
}
