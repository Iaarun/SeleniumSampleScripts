package testngDemo;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTestCase implements IRetryAnalyzer{
	
	private int retryCount =0;
	private int maxTryCount = 2;	

	public boolean retry(ITestResult result) {
		if(retryCount < maxTryCount) {
			System.out.println("Retrying "+result.getName());
			retryCount++;
			return true;
		}
		return false;
	}

}
