/*
 * Copyright (c) 2022-present Doodle. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.doodle.pipeline.vaadin.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.RolesAllowed;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.doodle.boot.vaadin.views.VaadinMainLayout;
import org.doodle.pipeline.vaadin.PipelineVaadinAgentService;

@RolesAllowed("PIPELINE_ADMIN")
@Route(value = "pipeline-agents", layout = VaadinMainLayout.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PipelineVaadinAgentsView extends VerticalLayout {
  final PipelineVaadinAgentService agentService;

  public PipelineVaadinAgentsView(PipelineVaadinAgentService agentService) {
    this.agentService = agentService;
  }
}
