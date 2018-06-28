/* Save this in a file called Main.java to compile and test it */

/* 
   Example file showing how to write a program that reads
   input from `input.txt` in the current directory
   and writes output to `output.txt` in the current directory
*/

/* Do not add a package declaration */
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

/* DO NOT CHANGE ANYTHING ABOVE THIS LINE */
/* You may add any imports here, if you wish, but only from the 
   standard library */

/* Do not add a namespace declaration */

public class Main {
	public static void main(String[] args) {
		ArrayList<APIObject> apiList = null;
		try {

			Util u = new Util();
			u.createApiObjectListOutOfFile();

			apiList = u.getApiList();

			// Sort the stream:
			Comparator<APIObject> comparator = Comparator.comparing(APIObject::getApiName);
			comparator = comparator.thenComparing(Comparator.comparing(APIObject::getApiVersion));
			Stream<APIObject> sortedStream = apiList.stream().sorted(comparator);

			Stream<APIObject> myNewStream = sortedStream.filter(s -> {
				if (u.isOnlyApiVersion(s.getApiName()))
					return true;
				else
					return false;
			});

			// Lets pick the first element in the group, as we have sorted it ,
			// lowest version will be on top
			Map<String, String> appWithLowestVersion = myNewStream.collect(Collectors.groupingBy(APIObject::getApiName,
					Collectors.collectingAndThen(Collectors.toList(), values -> values.get(0).getAppName())));

			u.printToFile(appWithLowestVersion);

		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IO error in input.txt or output.txt");
		}
	}
}
