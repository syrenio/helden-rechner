package at.syrenio.helden.rechner.gui;

import helden.plugin.werteplugin.PluginTalent;

public class TalentDTO {
	public String TalentName;
	public String TalentArt;
	public String Probe;
	public int Wert;
	public String Action;
	public int Endwert;
	public PluginTalent Talent;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(TalentName);
		sb.append(" " + TalentArt + " ");
		sb.append("[");
		sb.append(Probe);
		sb.append("]:");
		sb.append(Wert);
		sb.append("\t--->\t");
		sb.append(Endwert);
		sb.append("\n");

		return sb.toString();
	}
}
