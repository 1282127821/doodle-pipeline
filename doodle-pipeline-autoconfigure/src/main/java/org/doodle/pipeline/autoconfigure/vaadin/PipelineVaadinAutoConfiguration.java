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
package org.doodle.pipeline.autoconfigure.vaadin;

import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.sidenav.SideNavItem;
import org.doodle.boot.vaadin.EnableVaadin;
import org.doodle.boot.vaadin.views.VaadinSideNavItemSupplier;
import org.doodle.pipeline.vaadin.PipelineVaadinAgentService;
import org.doodle.pipeline.vaadin.PipelineVaadinProperties;
import org.doodle.pipeline.vaadin.PipelineVaadinTaskService;
import org.doodle.pipeline.vaadin.PipelineVaadinWorkflowService;
import org.doodle.pipeline.vaadin.views.PipelineVaadinAgentsView;
import org.doodle.pipeline.vaadin.views.PipelineVaadinTasksView;
import org.doodle.pipeline.vaadin.views.PipelineVaadinWorkflowsView;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(PipelineVaadinProperties.class)
@EnableConfigurationProperties(PipelineVaadinProperties.class)
@EnableVaadin(PipelineVaadinProperties.PREFIX_VIEWS)
public class PipelineVaadinAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public PipelineVaadinAgentService pipelineVaadinAgentService() {
    return new PipelineVaadinAgentService();
  }

  @Bean
  @ConditionalOnMissingBean
  public PipelineVaadinTaskService pipelineVaadinTaskService() {
    return new PipelineVaadinTaskService();
  }

  @Bean
  @ConditionalOnMissingBean
  public PipelineVaadinWorkflowService pipelineVaadinWorkflowService() {
    return new PipelineVaadinWorkflowService();
  }

  @Bean
  public VaadinSideNavItemSupplier pipelineSideNavView() {
    return (authenticationContext) -> {
      SideNavItem item = new SideNavItem("CI Pipeline");
      item.setPrefixComponent(VaadinIcon.AUTOMATION.create());
      item.addItem(
          new SideNavItem("Agent", PipelineVaadinAgentsView.class, VaadinIcon.TASKS.create()));
      item.addItem(
          new SideNavItem("工作流", PipelineVaadinWorkflowsView.class, VaadinIcon.TASKS.create()));
      item.addItem(new SideNavItem("任务", PipelineVaadinTasksView.class, VaadinIcon.TASKS.create()));
      return item;
    };
  }
}
