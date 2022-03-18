package dtr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

public class PrintCell extends DefaultTableCellRenderer{
	
	public PrintCell() {
		setOpaque(true); 
	}
	private static final long serialVersionUID = 1L;
	
	@Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      //table.setRowHeight(23);
        TableColumnModel columnModel = table.getColumnModel();
 		
 		columnModel.getColumn(0).setPreferredWidth(0);
        c.setFont(new Font("Tahoma", Font.PLAIN, 11));
        setHorizontalAlignment(SwingConstants.CENTER);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 13));
        table.getTableHeader().setBackground(Color.LIGHT_GRAY);

    	c.setForeground(Color.BLACK);
    	
        c.setBackground(row % 2 == 0 ? UIManager.getColor("Button.background") : Color.WHITE);
        
        if(column == 0) {
        	  c.setForeground(row % 2 == 0 ? UIManager.getColor("Button.background") : Color.WHITE);
        }
        
        
        
        return c;
    }
	
	
	
	

}
