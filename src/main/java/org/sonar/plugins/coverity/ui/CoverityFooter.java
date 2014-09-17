/*
 * Coverity Sonar Plugin
 * Copyright (c) 2014 Coverity, Inc
 * support@coverity.com
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */

package org.sonar.plugins.coverity.ui;

import com.coverity.ws.v6.ProjectDataObj;
import org.sonar.api.config.Settings;
import org.sonar.api.web.Footer;
import org.sonar.plugins.coverity.util.CoverityUtil;

public final class CoverityFooter implements Footer {
    Settings settings;
    // This object is set up by CoveritySensor and then used to create a footer sending the user to his cim instance.
    // If this object is null, the footer will redirect the user to www.coverity.com.
    public static ProjectDataObj covProjectObjFooter;

    public CoverityFooter(Settings settings) {
        this.settings = settings;
    }

    public String getHtml() {

        String url = null;
        String text = null;

        String serverUrl = CoverityUtil.createURL(settings);
        if(covProjectObjFooter != null){
            url = serverUrl + "reports.htm#p" + covProjectObjFooter.getProjectKey();
            text = "Coverity Connect";
        } else {
            url = "http://www.coverity.com";
            serverUrl = url + "/";
            text = "Coverity";
        }

        return String.format(
                "<div style=\"text-align:center\">" +
                "<a href=\"%s\"><img src=\"" + serverUrl + "favicon.ico\" />%s</a>" +
                "</div>",
                url, text);
    }


}
