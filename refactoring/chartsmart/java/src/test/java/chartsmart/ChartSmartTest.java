package org.zuill.talks.codeexcellence.chartsmart.tests;

import junit.framework.TestCase;

import org.approvaltests.Approvals;
import org.approvaltests.UseReporter;
import org.approvaltests.reporters.ImageDiffReporter;
import org.zuill.talks.codeexcellence.chartsmart.ChartSmart;
import org.zuill.talks.codeexcellence.chartsmart.ChartWindow;

@UseReporter(ImageDiffReporter.class)
public class ChartSmartTest extends TestCase {

	public void testMainWindow() throws Exception {
		ChartSmart chartSmart = new ChartSmart();
		Approvals.approve(chartSmart);		
	}
	
	public void testBarChartWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(406, "rpfll", true);
		Approvals.approve(chartSmart);	
		assertEquals("Bar Chart - Single Mode", chartSmart.getTitle());
	}
	
	public void testPieChartWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(323, "rpfll", true);
		Approvals.approve(chartSmart);		
		assertEquals("Pie Chart - Single Mode", chartSmart.getTitle());
	}
	public void testBarChartSmallWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(406, "shareddisplay", true);
		Approvals.approve(chartSmart);		
		assertEquals("Bar Chart - Compare Mode", chartSmart.getTitle());
	}
	
	public void testPieChartSmallWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(323, "shareddisplay", true);
		Approvals.approve(chartSmart);		
		assertEquals("Pie Chart - Compare Mode", chartSmart.getTitle());
	}
}
