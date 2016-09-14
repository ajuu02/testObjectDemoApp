package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/*
This class does the following  
1.Kill Any Existing ADB Servers
2.Get Connected Device Info - Android
*/


public class deviceManager {
	Properties configprop;
	Runtime run = Runtime.getRuntime();
	
	
	public deviceManager() throws IOException
	{
		
		System.out.println("Test run started...");
		FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\Properties\\Config.properties");
		configprop = new Properties();
		configprop.load(fs);
	}
	

	public void launchApp() throws IOException, InterruptedException
	{
		executeCommandWithRunTime(configprop.getProperty("adbKillServerCommand"));
		System.out.println("Installing..");
		executeCommandWithRunTime(configprop.getProperty("installApp")+" "+configprop.getProperty("ApkPath_demoApp"));	
	}

	private void executeCommandWithRunTime(String Command) throws IOException, InterruptedException 
	{
		System.out.println("Command Under Execution --> "+Command);
		Process process = run.exec(Command);
		process.waitFor();
		//readCommandExecutionResponse(process.getInputStream());
		process.destroy();
		
	}
	
	public void uninstallApp() throws IOException, InterruptedException
	{
		System.out.println("UnInstalling App......");
		executeCommandWithRunTime(configprop.getProperty("uninstallApp")+" "+configprop.getProperty("appPackage_demoApp"));
		
	}
	
	
}
 
