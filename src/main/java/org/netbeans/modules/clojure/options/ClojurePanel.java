/*
 * Copyright 2018 delivery.
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
package org.netbeans.modules.clojure.options;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import org.openide.util.Exceptions;

final class ClojurePanel extends javax.swing.JPanel {

    private final ClojureOptionsPanelController controller;

    ClojurePanel(ClojureOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        // TODO listen to changes in form fields and call controller.changed()
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLeiningenBatchFile = new javax.swing.JLabel();
        tfPathToLeiningenBatchFile = new javax.swing.JTextField();
        btnBrowseLeiningenBatchFile = new javax.swing.JButton();
        lblVersionLeiningen = new javax.swing.JLabel();
        lblLeiningenJarFile = new javax.swing.JLabel();
        tfPathToLeiningenJarFile = new javax.swing.JTextField();
        btnBrowseLeiningenJarFile = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(lblLeiningenBatchFile, org.openide.util.NbBundle.getMessage(ClojurePanel.class, "ClojurePanel.lblLeiningenBatchFile.text")); // NOI18N

        tfPathToLeiningenBatchFile.setText(org.openide.util.NbBundle.getMessage(ClojurePanel.class, "ClojurePanel.tfPathToLeiningenBatchFile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowseLeiningenBatchFile, org.openide.util.NbBundle.getMessage(ClojurePanel.class, "ClojurePanel.btnBrowseLeiningenBatchFile.text")); // NOI18N
        btnBrowseLeiningenBatchFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBrowseLeiningenBatchFileMouseClicked(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lblVersionLeiningen, org.openide.util.NbBundle.getMessage(ClojurePanel.class, "ClojurePanel.lblVersionLeiningen.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblLeiningenJarFile, org.openide.util.NbBundle.getMessage(ClojurePanel.class, "ClojurePanel.lblLeiningenJarFile.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnBrowseLeiningenJarFile, org.openide.util.NbBundle.getMessage(ClojurePanel.class, "ClojurePanel.btnBrowseLeiningenJarFile.text")); // NOI18N
        btnBrowseLeiningenJarFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBrowseLeiningenJarFileMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLeiningenBatchFile)
                            .addComponent(lblLeiningenJarFile))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfPathToLeiningenJarFile, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                            .addComponent(tfPathToLeiningenBatchFile, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBrowseLeiningenBatchFile)
                            .addComponent(btnBrowseLeiningenJarFile)))
                    .addComponent(lblVersionLeiningen))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLeiningenBatchFile)
                    .addComponent(tfPathToLeiningenBatchFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBrowseLeiningenBatchFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPathToLeiningenJarFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLeiningenJarFile)
                    .addComponent(btnBrowseLeiningenJarFile))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblVersionLeiningen)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseLeiningenBatchFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseLeiningenBatchFileMouseClicked
        // TODO add your handling code here:

        //java.nio.file.Paths.get(System.getProperty("user.home"), ".lein", "self-installs");
        String pathToLeiningen = java.nio.file.Paths.get(System.getProperty("user.home"), "Documents", "NetbeansTools", "lein.bat").toString();

        Runtime rt = Runtime.getRuntime();
        String[] command = {pathToLeiningen, "--version"};
        Process proc = null;
        try {
            proc = rt.exec(command);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        //BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

        try {
            String version = null;
            while ((version = stdInput.readLine()) != null) {
                lblVersionLeiningen.setText("Version " + version.substring(0, 15));
            }
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_btnBrowseLeiningenBatchFileMouseClicked

    private void btnBrowseLeiningenJarFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBrowseLeiningenJarFileMouseClicked
        // TODO add your handling code here:
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            if ("LEIN_JAR".equals(envName)) {
                tfPathToLeiningenBatchFile.setText(env.get(envName));
            }
        }

        if (tfPathToLeiningenBatchFile.getText().length() > 0) {
            return;
        }

        String pathToJar = java.nio.file.Paths.get(System.getProperty("user.home"), ".lein", "self-installs").toString();
        File file = new File(pathToJar);
        if (file.exists() && file.isDirectory()) {
            tfPathToLeiningenBatchFile.setText(pathToJar);
        }
    }//GEN-LAST:event_btnBrowseLeiningenJarFileMouseClicked

    void load() {
        // TODO read settings and initialize GUI
        // Example:        
        // someCheckBox.setSelected(Preferences.userNodeForPackage(ClojurePanel.class).getBoolean("someFlag", false));
        // or for org.openide.util with API spec. version >= 7.4:
        // someCheckBox.setSelected(NbPreferences.forModule(ClojurePanel.class).getBoolean("someFlag", false));
        // or:
        // someTextField.setText(SomeSystemOption.getDefault().getSomeStringProperty());
    }

    void store() {
        // TODO store modified settings
        // Example:
        // Preferences.userNodeForPackage(ClojurePanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or for org.openide.util with API spec. version >= 7.4:
        // NbPreferences.forModule(ClojurePanel.class).putBoolean("someFlag", someCheckBox.isSelected());
        // or:
        // SomeSystemOption.getDefault().setSomeStringProperty(someTextField.getText());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowseLeiningenBatchFile;
    private javax.swing.JButton btnBrowseLeiningenJarFile;
    private javax.swing.JLabel lblLeiningenBatchFile;
    private javax.swing.JLabel lblLeiningenJarFile;
    private javax.swing.JLabel lblVersionLeiningen;
    private javax.swing.JTextField tfPathToLeiningenBatchFile;
    private javax.swing.JTextField tfPathToLeiningenJarFile;
    // End of variables declaration//GEN-END:variables
}
