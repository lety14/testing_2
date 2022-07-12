package com.Base;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import com.extentReports.ExtentFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

/**
 * BaseTest Class (model PageObjects)
 * @author Leticia
 * @version 1.0
 */
public class BaseTest {
    protected static ExtentSparkReporter spark;
    protected static ExtentReports extent;
    protected ExtentTest test;
    private static boolean started = false;

    @BeforeAll
    public static void setUpBaseTest()
    {
        if (!started)
        {
            spark = new ExtentSparkReporter("target/Spark.html");
            extent = ExtentFactory.getInstance();
            extent.attachReporter(spark);
            started = true;
        }
    }

    @AfterAll
    public static void tearDownBaseTest()
    {
        extent.flush();
    }
}