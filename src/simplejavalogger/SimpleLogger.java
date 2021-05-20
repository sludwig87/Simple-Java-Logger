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
	
	/// Singleton Pattern
	private static SimpleLogger _logger = null;
	
	
	public static synchronized SimpleLogger getInstance()
	{
	    if(_logger == null)
	    {
	        _logger = new SimpleLogger();
	    }
	    
	    return _logger;
	}
	
	public static synchronized SimpleLogger getInstance(String nFileName)
	{
	    if(_logger == null)
	    {
	    	if(nFileName.length() > 0)
	    	{
	    		logFile = nFileName;
	    	}
	        _logger = new SimpleLogger();
	    }
	    else if(logFile != nFileName)
	    {
	    	System.out.println("Error In SimpleLogger. Log File Already Specified Elsewhere. Cannot Set");
	    }
	    
	    return _logger;
	}
	
	/// Private members
	private final static String logFile = "demo_log.txt";
	
	
    private PrintWriter writer;  
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    
    
    // Private Methods
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
    
    
    
    // Public Methods
    public void Log(String formatString, Object ...args)
    {
    	String formatedString = formatString;
    	
    	for(Object toReplace : args) 
    	{
    		try 
    		{
    			formatedString = formatedString.replaceFirst("\\{}", toReplace.toString());
    		}
    		catch(Exception e)
    		{
    			writer.println("Failed to log message....");
    		}
    		
    	}
    	
    	writer.println(formatedString);
    }
    
    
    
    /// Public Formated Log Messages
    public void Error(String formatString, Object ...args)
    {
    	this.Log(dtf.format(LocalDateTime.now()) + "[Error] - " + formatString, args);
    }
    
    public void Trace(String formatString, Object ...args)
    {
    	this.Log(dtf.format(LocalDateTime.now()) + "[Trace] - " + formatString, args);
    }
    
    public void Debug(String formatString, Object ...args)
    {
    	
    	this.Log(dtf.format(LocalDateTime.now()) + "[Debug] - " + formatString, args);
    }
    
    public void Info(String formatString, Object ...args)
    {
    	
    	this.Log(dtf.format(LocalDateTime.now()) + "[Info] - " + formatString, args);
    }
    
    
    
    public void logWithdraw (String account, double amount) 
    {
	    writer.println("WITHDRAW (" + account + "): " + amount + "$");
	}    
    
    public void logDeposit (String account, double amount) 
    {
	    writer.println("DEPOSIT (" + account + "): " + amount + "$");
	}   
    
    public void logTransfer (String fromAccount, String toAccount, double amount) 
    {
	    writer.println("TRANSFER (" + fromAccount + "->" + toAccount + "): " + amount + "$");
	}

}
