package com.qa.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporterNG {
    static ExtentReports extent;

    public static ExtentReports getReportObject() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm.ss'Z'").format(new Date());
        String path = System.getProperty("user.dir") + "/reports/Reqres_API_" + timestamp + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("API Automation result");
        reporter.config().setDocumentTitle("Reqres API");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA", System.getProperty("user.name"));
        return extent;
    }
}
