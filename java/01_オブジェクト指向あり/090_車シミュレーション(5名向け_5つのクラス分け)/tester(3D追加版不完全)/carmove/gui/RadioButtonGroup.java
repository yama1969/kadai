package carmove.gui;

import java.util.ArrayList;
import javax.swing.JRadioButton;

public class RadioButtonGroup{
	ArrayList<JRadioButton> rbList = new ArrayList<JRadioButton>();

	public int add(JRadioButton rb){
		rbList.add(rb);
		return rbList.size();
	}

	public void selectButton(int no){
		for(int cnt = 0; cnt < rbList.size(); cnt++){
			if(cnt == no){
				continue;
			}
			((JRadioButton)rbList.get(cnt)).setSelected(false);
		}
	}

	public int getSelectedNo(){
		for(int cnt = 0; cnt < rbList.size(); cnt++){
			if(((JRadioButton)rbList.get(cnt)).isSelected()){
				return cnt;
			}
		}
		return 0;
	}

	public void setAllEnabled(boolean sw){
		for(int cnt = 0; cnt < rbList.size(); cnt++){
			((JRadioButton)rbList.get(cnt)).setEnabled(sw);
		}
	}
}
