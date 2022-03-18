package dtr;
// NOTE ---- BEWARE OF THE METHODS SEQUENCE ---- 
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;


public class CellColour extends DefaultTableCellRenderer{
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//	dont know what dis do
	Object obj;
	
	 public CellColour() {
         setOpaque(true); 
     }
	 
//STATIC SETTING OF COLORS
	 //can also set dynamic element using if else statments
	 
	  public Component getTableCellRendererComponent(JTable table, Object value,
    		boolean selected, boolean focused, int row, int column) {
		 Component comp =  super.getTableCellRendererComponent(table, value, selected, focused, row, column);
		 setHorizontalAlignment(SwingConstants.CENTER);
		 		Color ones = Color.WHITE;
		 		
		 		
		 		TableColumnModel columnModel = table.getColumnModel();
		 		
		 		columnModel.getColumn(0).setPreferredWidth(10);
		 		columnModel.getColumn(1).setPreferredWidth(150);
		 		columnModel.getColumn(2).setPreferredWidth(50);
		 		
		 		if(column == 1) {
		 			
		 		 comp.setFont(new Font("San Serif", Font.BOLD, 12));
		 		 
		 		}
		 		
		 		
		

			comp.setForeground(ones);
			table.setRowHeight(25);
			if(column == 0) {

				comp.setForeground(Color.BLACK);
			}
			
			if(column == 2) {
				
				Object result = table.getModel().getValueAt(row, column);
				//String aver = result.toString();
				String aver = result.toString();
				//Double aver = Double.parseDouble(result.toString());
				 Color tw = null;
				if(aver.length() == 3) {
					tw = Color.RED;
					comp.setBackground(tw);
					comp.setForeground(tw);
				}
				if(aver.length() == 2) {

					tw = Color.YELLOW;
					comp.setBackground(tw);
					comp.setForeground(tw);
				}
				
			}else {

				comp.setBackground(Color.BLACK);
				
			}
			
			
			
			
			
			
			
		 		
			
		 	
		 		
		 		
			/*
			 * if(!selected) {
			 * 
			 * if(column == 0) { comp.setForeground(Color.BLUE);
			 * comp.setBackground(Color.YELLOW); }
			 * 
			 * if(column == 1) { comp.setForeground(Color.YELLOW);
			 * comp.setBackground(Color.BLUE); }
			 * 
			 * if(column == 2) { comp.setForeground(Color.BLACK);
			 * comp.setBackground(Color.RED); }
			 * 
			 * }else {
			 * 
			 * 
			 * comp.setForeground(Color.white); comp.setBackground(Color.pink);
			 * 
			 * }
			 */
				/*
				 * int control = row; control = control % 2; control = (control == 0) ? 1 : 0;
				 * 
				 * if (control == 1) { comp.setBackground(Color.green); } else {
				 * comp.setBackground(Color.cyan); }
				 */
		 
		 		
		 
		 		return comp;
    }
	  
	  
	  

}