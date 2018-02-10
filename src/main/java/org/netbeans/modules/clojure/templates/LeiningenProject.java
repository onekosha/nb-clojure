/*
 * Copyright 2018 Sandy Corn.
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
package org.netbeans.modules.clojure.templates;

import java.beans.PropertyChangeListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.project.Project;
import org.netbeans.api.project.ProjectInformation;
import org.netbeans.spi.project.ProjectState;
import org.openide.filesystems.FileObject;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 *
 * @author Sandy Corn
 */
public class LeiningenProject implements Project {

    private final FileObject projectDir;
    private final ProjectState state;
    private Lookup lkp;
    final String leiningenDirectory;
    final String fullNameLeiningenFile;

    LeiningenProject(FileObject dir, ProjectState state) {
        this.projectDir = dir;
        this.state = state;
        this.fullNameLeiningenFile = getFullNameLeiningenFile();
        this.leiningenDirectory = getLeiningenDirectory();
    }

    @Override
    public FileObject getProjectDirectory() {
        return projectDir;
    }

    @Override
    public Lookup getLookup() {
        if (lkp == null) {
            lkp = Lookups.fixed(new Object[]{
                new LeiningenProjectInformation(),
                new LeiningenProjectLogicalView(this),
                new LeiningenActionProvider()}
            );
        }
        return lkp;
    }

    private String getFullNameLeiningenFile() {
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            os = "Windows";
        }

        switch (os) {
            case "Windows":
                return "lein.bat";
            case "Linux":
                return "lein";
            case "MacOs":
                return "lein";
            default:
                return "lein";
        }
    }

    private String getLeiningenDirectory() {
        String os = System.getProperty("os.name");

        if (os.contains("Windows")) {
            os = "Windows";
        }

        switch (os) {
            case "Windows":
                return java.nio.file.Paths.get(System.getProperty("user.home"), "AppData", "Roaming", "Netbeans", "8.2", "modules", "clojure").toString();
            case "Linux":
                return "";
            case "MacOs":
                return "";
            default:
                return "";
        }
    }

    private final class LeiningenProjectInformation implements ProjectInformation {

        @StaticResource()
        public static final String CUSTOMER_ICON = "org/netbeans/modules/clojure/templates/leiningen-projects.png";

        @Override
        public Icon getIcon() {
            return new ImageIcon(ImageUtilities.loadImage(CUSTOMER_ICON));
        }

        @Override
        public String getName() {
            return getProjectDirectory().getName();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener pcl) {
            //do nothing, won't change
        }

        @Override
        public Project getProject() {
            return LeiningenProject.this;
        }
    }
}
