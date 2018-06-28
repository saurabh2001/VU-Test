import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Util {

	private ArrayList<APIObject> apiList = null;

	Map<String, Long> onlyApiVersionsMap = null;

	public Util() {
		super();
	}

	public ArrayList<APIObject> getApiList() {
		return apiList;
	}

	public void createApiObjectListOutOfFile() throws FileNotFoundException {

		Scanner in = new Scanner(new BufferedReader(new FileReader("input.txt")));

		/* Here we can read in the input file */
		/*
		 * In this example, we're reading all the lines of file `input.txt` and
		 * then ignoring them. You should modify this part of the program to
		 * read and process the input as desired
		 */
		while (in.hasNextLine()) {
			String line = in.nextLine();
			if (!line.isEmpty()) {
				if (apiList == null)
					apiList = new ArrayList<APIObject>();
				apiList.add(splitApiObject(line));
			}
		}
		identifyOnlyApiVersions();
		in.close();
	}

	private void identifyOnlyApiVersions() {

		onlyApiVersionsMap = (Map<String, Long>) apiList.stream().distinct()
				.collect(Collectors.groupingBy(e -> e.getApiName(), Collectors.counting()));

	}

	public boolean isOnlyApiVersion(String apiName) {
		if (onlyApiVersionsMap.get(apiName) > 1)
			return true;
		else
			return false;

	}

	private APIObject splitApiObject(String line) {
		APIObject apiObject = null;
		try {
			String a[] = line.split(",");

			apiObject = new APIObject(a[0], a[1], a[2]);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return apiObject;
	}

	public void printToFile(Map<String, String> appWithLowestVersion) throws IOException {
		
		PrintWriter output = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
		
		appWithLowestVersion.values().stream().distinct().forEach(v -> {
			output.println(v);
		}

		);

		output.close();
	}

}
