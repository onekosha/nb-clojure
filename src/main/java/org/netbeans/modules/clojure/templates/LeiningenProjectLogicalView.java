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

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.netbeans.api.annotations.common.StaticResource;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import static org.netbeans.modules.clojure.templates.LeiningenActionProvider.COMMAND_DEPS;
import static org.netbeans.modules.clojure.templates.LeiningenActionProvider.COMMAND_HELP;
import org.netbeans.spi.project.ActionProvider;
import static org.netbeans.spi.project.ActionProvider.COMMAND_BUILD;
import static org.netbeans.spi.project.ActionProvider.COMMAND_CLEAN;
import static org.netbeans.spi.project.ActionProvider.COMMAND_REBUILD;
import static org.netbeans.spi.project.ActionProvider.COMMAND_RUN;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.CommonProjectActions;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.AbstractNode;
import org.openide.nodes.Children;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;
import org.openide.util.lookup.ProxyLookup;

/**
 *
 * @author Sandy Corn
 */
class LeiningenProjectLogicalView implements LogicalViewProvider {

    @StaticResource()
    public static final String LIENINGEN_ICON = "org/netbeans/modules/clojure/templates/leiningen-projects.png";

    private final LeiningenProject project;

    public LeiningenProjectLogicalView(LeiningenProject project) {
        this.project = project;
    }

    @Override
    public Node createLogicalView() {
        try {
            //Obtain the project directory's node:
            FileObject projectDirectory = project.getProjectDirectory();
            DataFolder projectFolder = DataFolder.findFolder(projectDirectory);
            Node nodeOfProjectFolder = projectFolder.getNodeDelegate();
            //Decorate the project directory's node:
            return new ProjectNode(nodeOfProjectFolder, project);
        } catch (DataObjectNotFoundException donfe) {
            Exceptions.printStackTrace(donfe);
            //Fallback-the directory couldn't be created -
            //read-only filesystem or something evil happened
            return new AbstractNode(Children.LEAF);
        }
    }

    private final class ProjectNode extends FilterNode {

        final LeiningenProject project;

        public ProjectNode(Node node, LeiningenProject project)
                throws DataObjectNotFoundException {
            super(node,
                    new FilterNode.Children(node),
                    new ProxyLookup(
                            new Lookup[]{
                                Lookups.singleton(project),
                                node.getLookup()
                            }));
            this.project = project;
        }

        @Override
        public Action[] getActions(boolean arg0) {
            return new Action[]{
                new ProjectAction(COMMAND_RUN, "Run", project),
                null,
                new ProjectAction(COMMAND_DEPS, "Deps", project),
                new ProjectAction(COMMAND_BUILD, "JAR", project),
                new ProjectAction(COMMAND_REBUILD, "Uber JAR", project),
                null,
                new ProjectAction(COMMAND_CLEAN, "Clean", project),
                null,
                CommonProjectActions.closeProjectAction(),
                null,
                new ProjectAction(COMMAND_HELP, "Help", project),
                CommonProjectActions.newFileAction(),
                CommonProjectActions.copyProjectAction(),
                CommonProjectActions.deleteProjectAction()
            };
        }

        @Override
        public Image getIcon(int type) {
            return ImageUtilities.loadImage(LIENINGEN_ICON);
        }

        @Override
        public Image getOpenedIcon(int type) {
            return getIcon(type);
        }

        @Override
        public String getDisplayName() {
            return project.getProjectDirectory().getName();
        }

    }

    private static class ProjectAction extends AbstractAction {

        private final LeiningenProject project;
        private final String command;

        public ProjectAction(String cmd, String displayName, LeiningenProject prj) {
            super(displayName);
            this.project = prj;
            this.command = cmd;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            switch (command) {
                case COMMAND_RUN:
                    processCommand("run");
                    break;
                case COMMAND_DEPS:
                    processCommand("deps");
                    break;
                case COMMAND_CLEAN:
                    processCommand("clean");
                    break;
                case COMMAND_BUILD:
                    processCommand("jar");
                    break;
                case COMMAND_REBUILD:
                    processCommand("uberjar");
                    break;
                case COMMAND_HELP:
                    processCommand("help");
                    break;
                default:
                    throw new IllegalArgumentException(String.format("Invalid command %s", command));
            }
        }

        private void processCommand(String command) {
            ExternalProcessBuilder processBuilder = new ExternalProcessBuilder(project.fullNameLeiningenFile).
                    addArgument(command).
                    prependPath(new File(project.leiningenDirectory)).
                    workingDirectory(FileUtil.toFile(project.getProjectDirectory()));
            ExecutionDescriptor descriptor = new ExecutionDescriptor().
                    frontWindow(true).
                    showProgress(true).
                    controllable(true);
            ExecutionService service = ExecutionService.newService(
                    processBuilder,
                    descriptor,
                    command);
            service.run();
        }

        @Override
        public boolean isEnabled() {
            ActionProvider prov = (ActionProvider) project.getLookup().lookup(ActionProvider.class);
            return prov.isActionEnabled(command, null);
        }

    }

    @Override
    public Node findPath(Node root, Object target) {
        //leave unimplemented for now
        return null;
    }

}
