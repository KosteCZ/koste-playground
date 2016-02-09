import java.io.IOException;

public class Executor {
	
	public static void main( String[] args ) throws IOException {
		
		/*
		//String fileName = "C:/Users/jkoscak/workspace/LogTool/testData/file2.txt";
		//String fileName = "C:/Users/jkoscak/workspace/LogTool/testData/CSDMX-946 LOG s6.txt";
		String fileName = "C:/Users/jkoscak/workspace/LogTool/testData/CSDMX-947 LOG s4 complete.txt";
		
		String threadId = "24"; //"13";
		
		Parser.parseFile( fileName, threadId );
		
		*/
		
		String inputFileName  = "C:/Users/jkoscak/workspace/LogTool/testData/CSDMX-947 LOG s4 complete.txt";
		String outputFileName = "C:/Users/jkoscak/workspace/LogTool/testData/CSDMX-947 LOG s4 complete - 27.txt";
		
		String threadId = "27"; //"13";
		
		Parser.parseFile( inputFileName, outputFileName, threadId );

	}

}
