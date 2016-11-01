import java.io.*;

public class HostfileChanger {
	private static final String HOSTFILE = "C:/windows/system32/drivers/etc/hosts";
	public static final String REMOTE_KEYWORD = "# remote";

	public void change(String address) throws IOException {
		String hostInfo = replaceHostFile(address);
		writeHostFile(hostInfo);
	}

	private void writeHostFile(String hostInfo) throws IOException {
		FileWriter writer = null;

		try {
			writer = new FileWriter(HOSTFILE);
			writer.write(hostInfo);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private String replaceHostFile(String address) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(HOSTFILE));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = reader.readLine();
			if (line == null) {
				break;
			}

			if (isReplaceLine(line)) {
				line = line.replaceAll("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}", address);
			}
			sb.append(line).append("\r\n");
		}
		
		return sb.toString();
	}

	private boolean isReplaceLine(String line) {
		if (line == null || line.trim().startsWith("#")) {
			 return false;
		}

		return line.indexOf(REMOTE_KEYWORD) > 0;
	}

	public static void main(String[] args) throws IOException {
		HostfileChanger main = new HostfileChanger();
		main.change("123.123.123.123");
	}
}
