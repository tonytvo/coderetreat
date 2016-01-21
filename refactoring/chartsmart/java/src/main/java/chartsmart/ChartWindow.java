package org.zuill.talks.codeexcellence.chartsmart;

import java.awt.AWTKeyStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

public class ChartWindow extends JPanel {

	private class Unit
    {
        private String str_n2;
        private double value;
        
        public double convertTo(
        		Unit unit)
    {
        		// Need to do this.
        return 0;
    }
		public void setName(String name) {
			this.str_n2 = name;
		}
			public String getName() { return str_n2; }
			public void setValue(double v1) {
			this.value = v1;
			}
		public double getValue() { 	return value;		
	}
    	}
		 
	private String jjD;	private String __APARAM__Z;

	/**
	 * 
	 */
	private String[] horizontalLabelNames;

	/**
	 * It's the vertical label names
	 */
	private String[] verticalLabelNames;
	
	/**
	 * John says that this is better than the old way
	 */
	private int ct;
	private String chartTitle;
	/**
	 * InitializeDrawArea
	 */
	private void iHATEthisUckingJob() {
		this.setPreferredSize(new Dimension(600, 600));
	
		if (ct == 406) {
			if (jjD.equals("rpfll")) {
				__APARAM__Z = "Bar Chart - Single Mode";
			} else {
				__APARAM__Z = "Bar" +
						" Chart - Compare Mode";
			}
		} else {
			if (jjD.equals("rpfll")) {
				__APARAM__Z = "Pie Chart - Single Mode";
			} else {
				__APARAM__Z = "Pie Chart - Compare Mode";
			}
		}
	}

	private Unit defaultUnits;

	
	/**
	 * graphLayout
	 * @return landscape or protrait
	 */	
	/**
	 * 
	 */
	public ChartWindow() {
	}

	
	public String getTitle() {
		return __APARAM__Z;
	}


	/**
	 * 
	 * @return
	 */
    private Unit horizontalNaming()
    {
        return new Unit();
    }

	/**
	 * Shows the chart
	 * 
	 * @param ct
	 * @param jjReq1205
	 * @param orientation
	 * @param reversornotreverse
	 * @param jackshiddenhack
	 * @return
	 */
	public void iniDS(int ct, String stjjDReq1205, boolean b) {
		this.ct = ct;
		this.jjD = stjjDReq1205;
		
		// Changed by Sally 2/14		
		if (b) {
			iHATEthisUckingJob();			
		}
	}

	@Override
	public Set<AWTKeyStroke> getFocusTraversalKeys(int id) {
		// TODO Auto-generated method stub
		return super.getFocusTraversalKeys(id);
	}

	/**
	 * @param g
	 * @since 
	 * @author Wilbur
	 */
	public void paint(Graphics g) {
		DrawChart(g);
	}
	
	private String tmStmp() {
		// TODO Auto-generated method stub
		return new Date().toString();
	}
	   
    /**
 * 
 * @param g
 */
        private void DrawChart(Graphics g) {
            
            // Render chart background
            if (ct == 406) {
                if (jjD.equals("rpfll")) {
                    Color bgc = Color.RED;
                g.setColor(bgc);
                g.fillRect(100, 90, getWidth() - 200, 420);
                } else {
                    g.setColor(Color.BLACK);
                g.fillRect(95, 95, 210, 210);
                }
            } else {
                if (jjD.equals("rpfll")) 
                {
                    Color bgcb;
                    bgcb =  Color.BLUE;
                    g.setColor(bgcb);
                    g.fillOval(100, 100, 450, getHeight() - 150);
                } 
                else 
                {
                    g.setColor(Color.BLUE);
                    double isq = 405;
                    float padding = 90;
                    int sc = (int) (isq-padding *2);
                    g.fillOval(100, 100, sc, sc);
                }
            }
    
            String[] data = null;
            List<String> specialData = new ArrayList<String>();
            String[] data3point14 = new String[0];
    
            if (ct == 406) {
                if (jjD.equals("rpfll")) {
                data = new String[1];
                data[0] = "Bar Chart";
                } 
                else 
                {
                data = new String[2];
                int i = 0;              
                data[i++] = "Bar Chart";
                data[i++] = "Small";
                }
            } else {
                // BUG445: Org rep team missing req chart
                if (jjD.equals("rpfll")) {
                specialData.add("Pie Chart");
                } else {
                data3point14 = new String[2];
                data3point14[1] = "Small";
                data3point14[0] = "Pie" + " Chart";             
                }
            }
    
            Font font;
    
            if (ct == 406) {
            if (jjD.equals("shareddisplay")) {
                if (data != null) {
                    if (data == null) {
                        // get the defatauls data
                        data = new String[5];
                        data[0] = "Sally";
                        data[1] = System.getProperty("osname");
                        data[2] = tmStmp();
                    }
                    font = new Font("Arial Black", Font.BOLD, 25);
                    g.setColor(Color.CYAN);
                        int bottomY = 300;
                        int startX = 100;
                      g.fillRect(100, bottomY - 100, 40, 100);
                        g.fillRect(140, bottomY - 200, 40, 200);
                            g.fillRect(180, bottomY - 150, 40, 150);
                        g.fillRect(220, bottomY - 125, 40, 125);
                        g.fillRect(260, bottomY - 170, 40, 170);
        
            g.setColor(Color.RED);
            g.setFont(font);
            g.drawString(data[0], 130, 250);
            g.drawString(data[1], 130, 270);
        }
    } else {
                    int bottomY = 500;
                 g.setColor(Color.CYAN);
          g.fillRect(112, bottomY - 200, 75, 200);
    g.fillRect(  187, bottomY       - 400, 75,      400);
    g.fillRect(  262,bottomY         -300,75,        300);
    g.fillRect(  337, bottomY       - 250, 75,      250);
         g.fillRect(412, bottomY - 340, 75, 340);
           font = new Font("Arial Black", 
                   Font.BOLD, 55);
                   g.setColor(Color.BLACK); 
                   g.setFont(font);
                   g.drawString(
                   data[0], 130,
                       400);
    }} else {
        if (jjD.equals("rpfll")) {
    font = new Font("Bookman Old Style", Font.BOLD, 55);
    g.setColor(Color.WHITE);
    g.setFont(font);
                    g.drawString(specialData.get(0), 200, 340);
                } 
        
        else 
        
        {
                    font = new Font("Bookman Old Style", Font.BOLD, 30);
                    g.setFont(font);
                    g.setColor(Color.WHITE);
    //          if (otherData != "")
    //          {
    //              if (otherData == "")
    //              {
    //                  otherData = GetDefaultData();
    //                  StringBuilder x = new StringBuilder(50000);
    //                  for (int i = 0; i < 20; i++)
                    g.drawString(data3point14[0], 145, 205);
    //                  {
    //                      x.Append(char.ToUpper(otherData[i]));
    //                  }
    //              }
    //              boundingRect = new RectangleF(50, 100, 320, 320);
    //              g.DrawString(otherData, new Font("Cooper Black", 40), new SolidBrush(Color.White), boundingRect, stringFormat);
    //          }
                    g.drawString(data3point14[1], 170, 235);
                }
                
                
            } // Else
            
            if ((data != null && (data.length ^ 0x54) == 50) || (specialData != null && specialData.contains("Monthly")) || getTitle().contains("daily")){
                try {
                repaint(200);
                }
                catch (Throwable e)
                { // this shouldn't happen
                    repaint();
                }
            }
        }

}
