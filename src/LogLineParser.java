
public class LogLineParser implements ILineParser {
	
	private String lastThreadId = null;

	/*
	 * Format line. (-date -description)
	 * Handles error/exception lines.
	 */
	@Override
	public String parseLine( String line ) {
		
		return parseLine( line, null );
		
	}

	/*
	 * Format line. (-date -description)
	 * Filter lines without expected thread number.
	 * Handles error/exception lines.
	 */
	@Override
	public String parseLine( String line, String threadId ) {
		
		if ( line != null && !line.isEmpty() ) {
			
			boolean isFirstCharNumber = true;
			
			try {
				
				Integer.valueOf( line.substring(0, 1) ); // Is 1st character number test.
				
			} catch ( NumberFormatException e ) {
				
				isFirstCharNumber = false;
			}
			
			if ( isFirstCharNumber ) {
			
				String threadId2 = line.substring( 63, 67 );
			
				int index = threadId2.lastIndexOf( "'" );
			
				threadId2 = threadId2.substring( 0, index );
			
				if ( threadId == null || threadId.equals( threadId2 ) ) {
			
					line = line.substring( 10, 29 ) + " | " + line.substring( 117 + index, line.length() );
					
				} else {
				
					line = "";
				
				}
				
				lastThreadId = threadId2;
							
			} else {
				
				if ( threadId == null || threadId.equals( lastThreadId ) ) {
					
					// OK // line = line;
					
				} else {
					
					line = "";
					
				}
				
			}
		
		}
		
		return line;
		
	}	
	
}
