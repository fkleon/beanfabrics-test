package de.leonhardt.beanfabrics.test;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.beanfabrics.IModelProvider;
import org.beanfabrics.Link;
import org.beanfabrics.ModelProvider;
import org.beanfabrics.ModelSubscriber;
import org.beanfabrics.Path;
import org.beanfabrics.View;
import org.beanfabrics.model.TextPM;
import org.beanfabrics.swing.BnLabel;
import org.beanfabrics.swing.list.BnList;
import org.beanfabrics.swing.list.cellrenderer.TextPMListCellRenderer;

public class TestView extends JFrame implements View<TestPM>, ModelSubscriber {

	private static final long serialVersionUID = -6181609852414033551L;
	
	private JPanel mainPanel;
	
	public static void main(String[] args) {
		TestView mainView = new TestView();
    	TestPM pm = new TestPM();
    	mainView.setPresentationModel(pm);
    	mainView.setVisible(true);
    	
    	pm.refresh();
	}
	
	public TestView() {
		super("Test View");
		
		// configure frame itself
		setTitle("Test View");
		setBounds(100, 100, 900, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set layout
		getContentPane().setLayout(new BorderLayout());

		// add main panel
		mainPanel = getMainPanel();
		add(mainPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Returns the main panel of the application.
	 * @return
	 */
	private JPanel getMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());

		JPanel headerPanel = new JPanel();
		headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.LINE_AXIS));
		headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		BnLabel listCountLabel = new BnLabel(getLocalModelProvider(), new Path("this.listSize"));

		headerPanel.add(new JLabel("List Size: "));
		headerPanel.add(listCountLabel);
		
		mainPanel.add(headerPanel, BorderLayout.NORTH);
		
		// list
		JScrollPane scrollPaneContacts = new JScrollPane(getList());
		mainPanel.add(scrollPaneContacts, BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	/**
	 * Builds the list
	 * @return
	 */
	private JList<TextPM> getList() {
		BnList list = new BnList();
		list.setModelProvider(getLocalModelProvider());
		list.setPath(new Path("this.list"));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.setCellRenderer(new TextPMListCellRenderer());
		return list;
	}
	
	/* BOILERPLATE */
	
	private final Link link = new Link(this);
	private ModelProvider localModelProvider;
	
	/**
	 * Returns the local {@link ModelProvider} for this class.
	 * 
	 * @return the local <code>ModelProvider</code>
	 */
	protected ModelProvider getLocalModelProvider() {
		if (localModelProvider == null) {
			localModelProvider = new ModelProvider();
			localModelProvider.setPresentationModelType(TestPM.class);
		}
		return localModelProvider;
	}
	
	@Override
	public IModelProvider getModelProvider() {
		return this.link.getModelProvider();
	}

	@Override
	public void setModelProvider(IModelProvider provider) {
		this.link.setModelProvider(provider);
	}

	@Override
	public void setPath(Path path) {
		this.link.setPath(path);
	}

	@Override
	public Path getPath() {
		return this.link.getPath();
	}

	@Override
	public TestPM getPresentationModel() {
		return getLocalModelProvider().getPresentationModel();
	}

	@Override
	public void setPresentationModel(TestPM model) {
		getLocalModelProvider().setPresentationModel(model);
	}
}
