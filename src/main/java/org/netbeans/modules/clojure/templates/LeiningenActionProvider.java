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

import org.netbeans.spi.project.ActionProvider;
import org.openide.util.Lookup;

/**
 *
 * @author Sandy Corn
 */
public class LeiningenActionProvider implements ActionProvider {
    public static final String COMMAND_DEPS = "deps";
    public static final String COMMAND_HELP = "help";

    @Override
    public String[] getSupportedActions() {
        return new String[]{
            ActionProvider.COMMAND_RUN/*,
            COMMAND_DEPS,
            ActionProvider.COMMAND_BUILD,
            ActionProvider.COMMAND_CLEAN,
            COMMAND_HELP*/
        };
    }

    @Override
    public void invokeAction(String string, Lookup lookup) throws IllegalArgumentException {
    }

    @Override
    public boolean isActionEnabled(String string, Lookup lookup) throws IllegalArgumentException {
        return true;
    }

}
