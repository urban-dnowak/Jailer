/*
 * Copyright 2007 - 2020 Ralf Wisser.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.jailer.ui;

import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.w3c.dom.Document;

import net.sf.jailer.datamodel.DataModel;
import net.sf.jailer.datamodel.Table;
import net.sf.jailer.xml.XmlUtil;

/**
 * XML Column Mapping Dialog.
 *
 * @author Ralf Wisser
 */
public class ColumnMapperDialog extends javax.swing.JDialog {
	
	private final java.awt.Frame parent;
	private Table table;
	private DataModel dataModel;
	private boolean ok;
	private ParameterSelector parameterSelector;
	private String initialTemplate = "";
	
	/** Creates new form ColumnMapperDialog */
	public ColumnMapperDialog(java.awt.Frame parent, ParameterSelector.ParametersGetter parametersGetter) {
		super(parent, true);
		this.parent = parent;
		this.mappingField = new RSyntaxTextArea();
		mappingField.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
		mappingField.setCodeFoldingEnabled(true);

		initComponents();

		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setViewportView(mappingField);
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 20;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 0, 4);
		jPanel1.add(jScrollPane2, gridBagConstraints);
		jScrollPane2.setViewportView(mappingField);
		
		AutoCompletion.enable(tableCombobox);
		
		paramPanel.add(parameterSelector = new ParameterSelector(this, mappingField, parametersGetter));
		tableCombobox.addActionListener(new ActionListener() {
			 @Override
			public void actionPerformed(java.awt.event.ActionEvent e) {
				 try {
					 if (table != null) {
						 table = dataModel.getTableByDisplayName((String) tableCombobox.getSelectedItem());
						 mappingField.setText(XmlUtil.build(table.getXmlTemplateAsDocument(null)));
						 mappingField.discardAllEdits();
					 }
				 } catch (Exception ex) {
					UIUtil.showException(ColumnMapperDialog.this.parent, "Error", ex);
				 }
			 }
		});
		UIUtil.initPeer();
	}

	/**
	 * Edits column mapping for a given table.
	 * 
	 * @param dataModel the data model
	 * @param table the table
	 */
	public boolean edit(DataModel dataModel, Table table) {
		parameterSelector.updateParameters();
		this.table = null;
		Vector<String> tableNames = new Vector<String>();
		for (Table t: dataModel.getTables()) {
			tableNames.add(dataModel.getDisplayName(t));
		}
		Collections.sort(tableNames);
		tableCombobox.setModel(new DefaultComboBoxModel(tableNames));
		tableCombobox.setMaximumRowCount(40);
		tableCombobox.setSelectedItem(dataModel.getDisplayName(table));
		int w = 600, h = 600;
		setSize(w, h);
		setLocation(Math.max(0, parent.getX() + parent.getWidth() / 2 - w / 2),
					Math.max(0, parent.getY() + parent.getHeight() / 2 - h / 2));
		invalidate();
		try {
			mappingField.setText(XmlUtil.build(table.getXmlTemplateAsDocument(null)));
			initialTemplate = mappingField.getText();
		} catch (Exception e) {
			try {
				// try again with default template,
				// there was a bug in Jailer 3.0 which causes corruption of XML templates
				// on windows platform
				mappingField.setText(XmlUtil.build(table.getDefaultXmlTemplate(null)));
			} catch (Exception e2) {
				UIUtil.showException(parent, "Error", e);
				return false;
			}
		}
		mappingField.discardAllEdits();
		ok = false;
		this.table = table;
		this.dataModel = dataModel;
		setVisible(true);
		return ok;
	}
	
	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tableCombobox = new net.sf.jailer.ui.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        formatButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        paramPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("XML Column Mapping");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setText(" Table ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(jLabel1, gridBagConstraints);

        tableCombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Eintrag 1", "Eintrag 2", "Eintrag 3", "Eintrag 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(4, 0, 0, 0);
        jPanel1.add(tableCombobox, gridBagConstraints);

        jLabel2.setText(" Mapping  ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        formatButton.setText("Format");
        formatButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                formatButtonActionPerformed(evt);
            }
        });
        jPanel2.add(formatButton);

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        jPanel2.add(resetButton);

        jLabel3.setText("  ");
        jPanel2.add(jLabel3);

        okButton.setText("Ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        jPanel2.add(okButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel2.add(cancelButton);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 30;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        jPanel1.add(jPanel2, gridBagConstraints);

        paramPanel.setMinimumSize(new java.awt.Dimension(150, 0));
        paramPanel.setLayout(new javax.swing.BoxLayout(paramPanel, javax.swing.BoxLayout.LINE_AXIS));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 20;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(paramPanel, gridBagConstraints);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
		try {
			Document doc = table.getDefaultXmlTemplate(null);
			mappingField.setText(XmlUtil.build(doc));
			mappingField.discardAllEdits();
		} catch (Exception e) {
			UIUtil.showException(parent, "Syntax Error", e);
		}
	}//GEN-LAST:event_resetButtonActionPerformed

	private void formatButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatButtonActionPerformed
		try {
			Document doc = XmlUtil.parse(mappingField.getText());
			mappingField.setText(XmlUtil.build(doc));
			mappingField.discardAllEdits();
		} catch (Exception e) {
			UIUtil.showException(parent, "Syntax Error", e, UIUtil.EXCEPTION_CONTEXT_USER_ERROR);
		}
	}//GEN-LAST:event_formatButtonActionPerformed

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
		ok = false;
		setVisible(false);
	}//GEN-LAST:event_cancelButtonActionPerformed

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
		try {
			Document doc = XmlUtil.parse(mappingField.getText());
			mappingField.setText(XmlUtil.build(doc));
			if (!initialTemplate.equals(mappingField.getText())) {
				table.setXmlTemplate(mappingField.getText());
				ok = true;
			} else {
				ok = false;
			}
			setVisible(false);
		} catch (Exception e) {
			UIUtil.showException(parent, "Syntax Error", e, UIUtil.EXCEPTION_CONTEXT_USER_ERROR);
		}
	}//GEN-LAST:event_okButtonActionPerformed
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton formatButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel paramPanel;
    private javax.swing.JButton resetButton;
    private net.sf.jailer.ui.JComboBox tableCombobox;
    // End of variables declaration//GEN-END:variables

    private final RSyntaxTextArea mappingField;
    
	private static final long serialVersionUID = -5437578641818236294L;
}
