package at.syrenio.helden.rechner;

import helden.plugin.werteplugin.PluginTalent;
import helden.plugin.werteplugin2.PluginHeld2;
import helden.plugin.werteplugin3.PluginHeldenWerteWerkzeug3;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class HeldenCalc {

	private JPanel panel;
	private JTextArea textArea;
	private PluginHeldenWerteWerkzeug3 temp;
	private JTable table;
	private JDialog dialog;
	private RechnerTableModel model;

	public HeldenCalc(JFrame frame) {
		this.panel = new JPanel();
		JDialog edit = new JDialog(frame, "Kalender", true);
		edit.getContentPane().add(panel);
		edit.setSize(300, 200);
		edit.setVisible(true);
	}

	private void createLayout(JFrame frame) {

		panel = new JPanel();
		dialog = new JDialog(frame, "Rechner", false);
		dialog.getContentPane().add(panel);
		dialog.setSize(600, 500);

		textArea = new JTextArea(5, 20);
		// JScrollPane scrollPane = new JScrollPane(textArea);
		// edit.add(scrollPane);

		model = new RechnerTableModel();
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);

		Action recalc = new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JTable t = (JTable) e.getSource();
				int modelRow = Integer.valueOf(e.getActionCommand());
				RechnerTableModel dm = (RechnerTableModel) t.getModel();
				RechnerRowData row = dm.getRow(modelRow);
				int rechenWert = calcTalent(row.Talent);
				dm.setValueAt(rechenWert, modelRow, 4);
				dm.fireChange(modelRow, 4);
				table.revalidate();
				table.repaint();

				System.out.println("recalc wert " + rechenWert);
			}
		};
		ButtonColumn btn = new ButtonColumn(table, recalc, 3);

		dialog.getContentPane().add(scrollPane);
	}

	public HeldenCalc(JFrame frame, PluginHeld2[] helden, PluginHeldenWerteWerkzeug3 phww) {
		this.temp = phww;
		this.temp.setAktivenHeld(temp.getSelectesHeld());

		createLayout(frame);

		StringBuilder sb = new StringBuilder();
		List<RechnerRowData> data = new ArrayList<RechnerRowData>();
		for (int i = 0; i < phww.getTalenteAlsString().length; i++) {
			RechnerRowData row = new RechnerRowData();

			String str = phww.getTalenteAlsString()[i];
			PluginTalent talent = phww.getTalent(str);
			List<String> probe = new ArrayList<String>(Arrays.asList(talent.getProbe()));

			int talentWert = temp.getTalentwert(talent);
			int rechenWert = calcTalent(talent);

			row.TalentName = str;
			row.Probe = probeAsText(probe);
			row.Wert = talentWert;
			row.Action = "würfeln";
			row.Endwert = rechenWert;
			row.Talent = talent;
			data.add(row);

			sb.append(str + "[");
			sb.append(probeAsText(probe));
			sb.append("]:");
			sb.append(talentWert);
			sb.append("\t--->\t");
			sb.append(rechenWert);
			sb.append("\n");

		}

		java.util.Collections.sort(data, new Comparator<RechnerRowData>() {

			@Override
			public int compare(RechnerRowData a, RechnerRowData b) {
				return a.TalentName.compareTo(b.TalentName);
			}
		});

		model.setData(data);
		table.repaint();

		textArea.append(sb.toString());
		System.out.println(sb.toString());

		dialog.setVisible(true);
	}

	private String probeAsText(List<String> probe) {
		StringBuilder str = new StringBuilder();
		for (int idx = 0; idx < probe.size(); idx++) {
			str.append(probe.get(idx));
			if (idx > 0)
				str.append("/");
		}
		return str.toString();
	}

	private int calcTalent(PluginTalent talent) {

		int talentWert = temp.getTalentwert(talent);
		int rechenWert = talentWert;
		List<String> probe = new ArrayList<String>(Arrays.asList(talent.getProbe()));

		for (String string : probe) {
			int wurf = wuerfelTalent(talent);
			int eigWert = temp.getEigenschaftswert(string);
			int diff = eigWert - wurf;
			if (diff < 0) {
				// probe nicht geschafft
				rechenWert += diff;
			}
		}

		return rechenWert;
	}

	private int wuerfelTalent(PluginTalent talent) {
		int wurf = (int) (Math.random() * 20);
		return wurf;
	}

	public static void main(String[] args) {

	}
}
