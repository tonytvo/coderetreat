package chartsmart;

import org.approvaltests.Approvals;
import org.approvaltests.reporters.ImageReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@UseReporter(ImageReporter.class)
public class ChartSmartTest {

    @Test
	public void testMainWindow() throws Exception {
		ChartSmart chartSmart = new ChartSmart();
		Approvals.verify(chartSmart);
	}

	@Test
	public void testBarChartWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(406, "rpfll", true);
		Approvals.verify(chartSmart);
		assertEquals("Bar Chart - Single Mode", chartSmart.getTitle());
	}

	@Test
	public void testPieChartWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(323, "rpfll", true);
		Approvals.verify(chartSmart);
		assertEquals("Pie Chart - Single Mode", chartSmart.getTitle());
	}

	@Test
	public void testBarChartSmallWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(406, "shareddisplay", true);
		Approvals.verify(chartSmart);
		assertEquals("Bar Chart - Compare Mode", chartSmart.getTitle());
	}

	@Test
	public void testPieChartSmallWindow() throws Exception {
		ChartWindow chartSmart = new ChartWindow();
		chartSmart.iniDS(323, "shareddisplay", true);
		Approvals.verify(chartSmart);
		assertEquals("Pie Chart - Compare Mode", chartSmart.getTitle());
	}
}
