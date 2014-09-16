package at.syrenio.helden.rechner.start;

import helden.plugin.HeldenWertePlugin3;
import helden.plugin.datenxmlplugin.DatenAustauschImpl;
import helden.plugin.werteplugin2.PluginHeld2;
import helden.plugin.werteplugin3.PluginHeldenWerteWerkzeug3;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import at.syrenio.helden.rechner.HeldenCalc;

public class HeldenStart implements HeldenWertePlugin3 {

	private HeldenCalc heldenCalc;

	public HeldenStart() {
		super();
	}

	@Override
	public ImageIcon getIcon() {
		return null;
	}

	@Override
	public String getMenuName() {
		return "Rechner";
	}

	@Override
	public String getToolTipText() {
		return "Rechner";
	}

	@Override
	public String getType() {
		return DATEN3;
	}

	@Override
	public void doWork(JFrame frame) {
		// new HeldenCalc(frame);
	}

	@Override
	public void doWork(JFrame frame, PluginHeld2[] helden, PluginHeldenWerteWerkzeug3 phww) {
		this.heldenCalc = new HeldenCalc(frame, helden, phww);

	}

	@Override
	public void doWork(JFrame arg0, Integer arg1, DatenAustauschImpl arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<String> getUntermenus() {
		// TODO Auto-generated method stub
		return null;
	}

}
