package de.leonhardt.beanfabrics.test;

import java.util.ArrayList;
import java.util.Collection;

import org.beanfabrics.model.AbstractPM;
import org.beanfabrics.model.IntegerPM;
import org.beanfabrics.model.ListPM;
import org.beanfabrics.model.PMManager;
import org.beanfabrics.model.TextPM;
import org.beanfabrics.support.OnChange;

public class TestPM extends AbstractPM {

	private final ListPM<TextPM> list = new ListPM<TextPM>(50);
	private final IntegerPM listSize = new IntegerPM(0);
	
	public TestPM() {
		PMManager.setup(this);
	}

	public void refresh() {
		setEntries(getTextPMs());
	}

	private void setEntries(Collection<TextPM> textPMs) {
		list.clear();
		list.addAll(textPMs);
	}
	
	@OnChange(path="this.list")
	public void outputSize() {
		System.out.println("List size: " + list.size());
		listSize.setInteger(list.size());
	}

	/**
	 * Generates some dummy entries for the list.
	 * 
	 * @return
	 */
	private Collection<TextPM> getTextPMs() {
		ArrayList<TextPM> textPMs = new ArrayList<TextPM>();
		for (int i = 0; i < 50; i++) {
			textPMs.add(new TextPM("Number " + i));
		}
		return textPMs;
	}
	
}
