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
package net.sf.jailer.ui.databrowser;

import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import net.sf.jailer.datamodel.DataModel;
import net.sf.jailer.datamodel.Table;
import net.sf.jailer.ui.AutoCompletion;
import net.sf.jailer.ui.StringSearchPanel;

/**
 * Dialog for selecting the table for a new table browser.
 * 
 * @author Ralf Wisser
 */
@SuppressWarnings("serial")
public abstract class NewTableBrowser extends javax.swing.JDialog {

	/** Creates new form NewTableBrowser */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public NewTableBrowser(java.awt.Frame parent, DataModel datamodel, boolean offerAlternatives) {
		super(parent, true);
		initComponents();

		AutoCompletion.enable(tableComboBox);
		tableComboBox.getEditor().getEditorComponent().addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == '\n') {
					if (okButton.isEnabled()) {
						okButtonActionPerformed(null);
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
		});
		
		GridBagConstraints gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 4, 0);
        jPanel2.add(StringSearchPanel.createSearchButton(this, tableComboBox, "Open Table", new Runnable() {
			@Override
			public void run() {
				okButtonActionPerformed(null);
			}
		}), gridBagConstraints);
        
		analyzeButton.setVisible(false /* DataBrowserContext.isSupportsDataModelUpdates() */);
		
		if (offerAlternatives) {
			setTitle(DataBrowserContext.getAppName(true));
			okButton.setText(" Open Table ");
		} else {
			analyzeButton.setVisible(false);
			restoreSessionButton.setVisible(false);
		}
		
		List<String> tables = new ArrayList<String>();
		
		for (Table table: datamodel.getTables()) {
			tables.add(datamodel.getDisplayName(table));
		}
		Collections.sort(tables);
		ComboBoxModel model = new DefaultComboBoxModel(new Vector(tables));
			
		tableComboBox.setModel(model);
		
		pack();
		setSize(Math.max(400, getWidth()), getHeight());
		if (parent != null) {
			setLocation(parent.getX() + 300, parent.getY() + 100);
		} else {
			setLocation(300, 100);
		}
		
		tableComboBox.grabFocus();
		
		setVisible(true);
	}


    @SuppressWarnings({ "rawtypes", "unchecked" })
    /** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        okButton = new javax.swing.JButton();
        analyzeButton = new javax.swing.JButton();
        restoreSessionButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tableComboBox = new net.sf.jailer.ui.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New Table Browser");

        jPanel1.setLayout(new java.awt.GridBagLayout());

        okButton.setText(" Open Table ");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(okButton, gridBagConstraints);

        analyzeButton.setText(" Analyze Database ");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel1.add(analyzeButton, gridBagConstraints);

        restoreSessionButton.setText(" Restore Layout ");
        restoreSessionButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreSessionButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        jPanel1.add(restoreSessionButton, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridBagLayout());

        tableComboBox.setMaximumRowCount(24);
        tableComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 4, 0);
        jPanel2.add(tableComboBox, gridBagConstraints);

        jLabel1.setText("  Table ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 4, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
		setVisible(false);
		Object s = tableComboBox.getSelectedItem();
		if (s instanceof String) {
			openTableBrowser((String) s);
		}
	}//GEN-LAST:event_okButtonActionPerformed

	private void analyzeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzeButtonActionPerformed
		setVisible(false);
		openDatabaseAnalyzer();
	}//GEN-LAST:event_analyzeButtonActionPerformed

	private void restoreSessionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreSessionButtonActionPerformed
		setVisible(false);
		restoreSession();
	}//GEN-LAST:event_restoreSessionButtonActionPerformed

	abstract void openTableBrowser(String tableName);
	abstract void openDatabaseAnalyzer();
	abstract void restoreSession();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton analyzeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton okButton;
    private javax.swing.JButton restoreSessionButton;
    @SuppressWarnings("rawtypes")
	private net.sf.jailer.ui.JComboBox tableComboBox;
    // End of variables declaration//GEN-END:variables

}
