import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

	public static void parseFile( String fileName ) throws IOException {
		
		parseFile( fileName, null );

	}
	
	public static void parseFile( String fileName, String threadId ) throws IOException {
		
		int index = fileName.lastIndexOf( "." );
		
		String prefix  = fileName.substring( 0, index );
		String postfix = fileName.substring( index, fileName.length() );
		
		//System.out.println( "Prefix:  \"" + prefix  + "\"" );
		//System.out.println( "Postfix: \"" + postfix + "\"" );
		
		String outputFileName = prefix + "_2" + postfix;
		
		//System.out.println( "Output file name: \"" + outputFileName + "\"" );
		
		parseFile( fileName, outputFileName, threadId );
		
	}
	
	public static void parseFile( String inputFileName, String outputFileName, String threadId ) throws IOException {
		
		String everything = "";
		
		BufferedReader br = new BufferedReader( new FileReader( inputFileName ) );
		
		ILineParser lineParser = new LogLineParser();
		
		try {
			
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		    	
		    	line = lineParser.parseLine( line, threadId );
		    	
		        if ( !line.isEmpty() ) {
		        	
		        	sb.append( line);
		        	sb.append( System.lineSeparator() );
		        
		        }
		        
		        line = br.readLine();
		        
		    }
		    
		    everything = sb.toString();
		    
		} finally {
			
		    br.close();
		    
		}	
		
		try {
            
            byte[] buffer = everything.getBytes();

            FileOutputStream outputStream = new FileOutputStream( outputFileName );

            outputStream.write(buffer);

            outputStream.close();       

            System.out.println( "Wrote " + buffer.length + " bytes." );
            
        } catch(IOException ex) {
        	
            System.err.println( "Error writing file '" + outputFileName + "'!" );

        }
		
	}
	
}
