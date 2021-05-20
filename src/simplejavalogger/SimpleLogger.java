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
import java.time.*;

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
	
	
	/// Private members
	private final String logFile = "demo_log.txt";
    private PrintWriter writer;  
    
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
    
    
    
    public void Error()
    {
    	
    }
    
    
    public void Trace()
    {
    	
    }
    
    
    public void Debug()
    {
    	
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
