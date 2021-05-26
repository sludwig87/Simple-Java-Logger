/**
 * 
 */
package simplejavalogger;

/**
 * @author Malice
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;    

/**
 * @author Malice
 *
 */


public class SimpleLogger 
{

	/// Private members
	private static String logFile = "demo_log.txt";
	private PrintWriter writer;  
	private DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	public enum LogLevel
	{
		TRACE, //Lowest Level
		DEBUG,
		INFO,
		ERROR
	}
	
	static private LogLevel logLevel = LogLevel.INFO;
	
	/// ==== Singleton Pattern ====
	private static SimpleLogger _logger = null;
	public static synchronized SimpleLogger getInstance()
	{
	    if(_logger == null)
	    {
	        _logger = new SimpleLogger();
	    }
	    
	    return _logger;
	}
	
	
	
	public static synchronized SimpleLogger getInstance(String newFileName)
	{
	    if(_logger == null)
	    {
	    	if(newFileName.length() > 0)
	    	{
	    		logFile = newFileName;
	    	}
	        _logger = new SimpleLogger();
	    }
	    else if(logFile != newFileName)
	    {
	    	System.out.printf("Error In SimpleLogger. Log File Already Specified Elsewhere. Cannot Set log file to %s", newFileName);
	    }
	    
	    return _logger;
	}
	
	public static synchronized SimpleLogger getInstance(String newFileName, LogLevel newLogLevel)
	{
	    if(_logger == null)
	    {
	    	if(newFileName.length() > 0)
	    	{
	    		logFile = newFileName;
	    	}
	    	
	    	logLevel = newLogLevel; 
	    	
	    	
	        _logger = new SimpleLogger();
	    }
	    else if(logFile != newFileName)
	    {
	    	System.out.printf("Error In SimpleLogger. Log File Already Specified Elsewhere. Cannot Set log file to %s", newFileName);
	    }
	    
	    return _logger;
	}
	
	///==========================
	
	
	
    
    ///     Private Methods
	/// Basic Constructor
    private SimpleLogger() 
    {
        try 
        {
            FileWriter fw = new FileWriter(logFile);
            writer = new PrintWriter(fw, true);
        } catch (IOException e) 
        {	
        }
        
	} 
    
    
    /// Basic Log Function used in more specific log functions
    public void Log(String formatString, Object ...args)
    {
    	// Attempt to just pass the inputs to the printf function of
    	// the print writer class.
    	try 
    	{
    		writer.printf(formatString, args);
    	}
    	catch (Exception logError)
    	{
    		writer.printf("Failed to format input: %s", formatString);
    	}
    	
    }
    
      
    /// Public Formated Log Messages
    public void Error(String formatString, Object ...args)
    {
    	this.Log(dateTimeFormat.format(LocalDateTime.now()) + "[Error] - " + formatString, args);
    }
    
    public void Trace(String formatString, Object ...args)
    {
    	this.Log(dateTimeFormat.format(LocalDateTime.now()) + "[Trace] - " + formatString, args);
    }
    
    public void Debug(String formatString, Object ...args)
    {
    	
    	this.Log(dateTimeFormat.format(LocalDateTime.now()) + "[Debug] - " + formatString, args);
    }
    
    public void Info(String formatString, Object ...args)
    {
    	
    	this.Log(dateTimeFormat.format(LocalDateTime.now()) + "[Info] - " + formatString, args);
    }
    
    
    
    public Boolean SetLogLevel(LogLevel newLogLevel)
    {
    	try 
    	{
    		logLevel = newLogLevel;
    	}
    	catch(Exception e)
    	{
    		//TODO: Make this better
    		return false;
    	}
    	
    	return true;

    }
    
    
    public Boolean SetLogLevel(String newLogLevelString)
    {
    	if( newLogLevelString.contains("TRACE") )
    	{
    		logLevel = LogLevel.TRACE;
    	}
    	else if(newLogLevelString.contains("ERROR") )
    	{
    		logLevel = LogLevel.ERROR;
    	}
    	else if(newLogLevelString.contains("DEBUG") )
    	{
    		logLevel = LogLevel.DEBUG;
    	}
    	else if(newLogLevelString.contains("INFO") )
    	{
    		logLevel = LogLevel.INFO;
    	}
    	else
    	{
    		return false;
    	}
    	
    	return true;
    }
    
}
